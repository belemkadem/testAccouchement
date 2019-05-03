package org.belemkadem.accouchement.web.rest;

import org.belemkadem.accouchement.AccouchementV3App;

import org.belemkadem.accouchement.domain.AntecedentGyneco;
import org.belemkadem.accouchement.repository.AntecedentGynecoRepository;
import org.belemkadem.accouchement.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;


import static org.belemkadem.accouchement.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the AntecedentGynecoResource REST controller.
 *
 * @see AntecedentGynecoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccouchementV3App.class)
public class AntecedentGynecoResourceIntTest {

    private static final String DEFAULT_DESIGNATION = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION = "BBBBBBBBBB";

    @Autowired
    private AntecedentGynecoRepository antecedentGynecoRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restAntecedentGynecoMockMvc;

    private AntecedentGyneco antecedentGyneco;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AntecedentGynecoResource antecedentGynecoResource = new AntecedentGynecoResource(antecedentGynecoRepository);
        this.restAntecedentGynecoMockMvc = MockMvcBuilders.standaloneSetup(antecedentGynecoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AntecedentGyneco createEntity(EntityManager em) {
        AntecedentGyneco antecedentGyneco = new AntecedentGyneco()
            .designation(DEFAULT_DESIGNATION);
        return antecedentGyneco;
    }

    @Before
    public void initTest() {
        antecedentGyneco = createEntity(em);
    }

    @Test
    @Transactional
    public void createAntecedentGyneco() throws Exception {
        int databaseSizeBeforeCreate = antecedentGynecoRepository.findAll().size();

        // Create the AntecedentGyneco
        restAntecedentGynecoMockMvc.perform(post("/api/antecedent-gynecos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(antecedentGyneco)))
            .andExpect(status().isCreated());

        // Validate the AntecedentGyneco in the database
        List<AntecedentGyneco> antecedentGynecoList = antecedentGynecoRepository.findAll();
        assertThat(antecedentGynecoList).hasSize(databaseSizeBeforeCreate + 1);
        AntecedentGyneco testAntecedentGyneco = antecedentGynecoList.get(antecedentGynecoList.size() - 1);
        assertThat(testAntecedentGyneco.getDesignation()).isEqualTo(DEFAULT_DESIGNATION);
    }

    @Test
    @Transactional
    public void createAntecedentGynecoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = antecedentGynecoRepository.findAll().size();

        // Create the AntecedentGyneco with an existing ID
        antecedentGyneco.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAntecedentGynecoMockMvc.perform(post("/api/antecedent-gynecos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(antecedentGyneco)))
            .andExpect(status().isBadRequest());

        // Validate the AntecedentGyneco in the database
        List<AntecedentGyneco> antecedentGynecoList = antecedentGynecoRepository.findAll();
        assertThat(antecedentGynecoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAntecedentGynecos() throws Exception {
        // Initialize the database
        antecedentGynecoRepository.saveAndFlush(antecedentGyneco);

        // Get all the antecedentGynecoList
        restAntecedentGynecoMockMvc.perform(get("/api/antecedent-gynecos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(antecedentGyneco.getId().intValue())))
            .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION.toString())));
    }
    
    @Test
    @Transactional
    public void getAntecedentGyneco() throws Exception {
        // Initialize the database
        antecedentGynecoRepository.saveAndFlush(antecedentGyneco);

        // Get the antecedentGyneco
        restAntecedentGynecoMockMvc.perform(get("/api/antecedent-gynecos/{id}", antecedentGyneco.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(antecedentGyneco.getId().intValue()))
            .andExpect(jsonPath("$.designation").value(DEFAULT_DESIGNATION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAntecedentGyneco() throws Exception {
        // Get the antecedentGyneco
        restAntecedentGynecoMockMvc.perform(get("/api/antecedent-gynecos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAntecedentGyneco() throws Exception {
        // Initialize the database
        antecedentGynecoRepository.saveAndFlush(antecedentGyneco);

        int databaseSizeBeforeUpdate = antecedentGynecoRepository.findAll().size();

        // Update the antecedentGyneco
        AntecedentGyneco updatedAntecedentGyneco = antecedentGynecoRepository.findById(antecedentGyneco.getId()).get();
        // Disconnect from session so that the updates on updatedAntecedentGyneco are not directly saved in db
        em.detach(updatedAntecedentGyneco);
        updatedAntecedentGyneco
            .designation(UPDATED_DESIGNATION);

        restAntecedentGynecoMockMvc.perform(put("/api/antecedent-gynecos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAntecedentGyneco)))
            .andExpect(status().isOk());

        // Validate the AntecedentGyneco in the database
        List<AntecedentGyneco> antecedentGynecoList = antecedentGynecoRepository.findAll();
        assertThat(antecedentGynecoList).hasSize(databaseSizeBeforeUpdate);
        AntecedentGyneco testAntecedentGyneco = antecedentGynecoList.get(antecedentGynecoList.size() - 1);
        assertThat(testAntecedentGyneco.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
    }

    @Test
    @Transactional
    public void updateNonExistingAntecedentGyneco() throws Exception {
        int databaseSizeBeforeUpdate = antecedentGynecoRepository.findAll().size();

        // Create the AntecedentGyneco

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAntecedentGynecoMockMvc.perform(put("/api/antecedent-gynecos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(antecedentGyneco)))
            .andExpect(status().isBadRequest());

        // Validate the AntecedentGyneco in the database
        List<AntecedentGyneco> antecedentGynecoList = antecedentGynecoRepository.findAll();
        assertThat(antecedentGynecoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAntecedentGyneco() throws Exception {
        // Initialize the database
        antecedentGynecoRepository.saveAndFlush(antecedentGyneco);

        int databaseSizeBeforeDelete = antecedentGynecoRepository.findAll().size();

        // Delete the antecedentGyneco
        restAntecedentGynecoMockMvc.perform(delete("/api/antecedent-gynecos/{id}", antecedentGyneco.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AntecedentGyneco> antecedentGynecoList = antecedentGynecoRepository.findAll();
        assertThat(antecedentGynecoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AntecedentGyneco.class);
        AntecedentGyneco antecedentGyneco1 = new AntecedentGyneco();
        antecedentGyneco1.setId(1L);
        AntecedentGyneco antecedentGyneco2 = new AntecedentGyneco();
        antecedentGyneco2.setId(antecedentGyneco1.getId());
        assertThat(antecedentGyneco1).isEqualTo(antecedentGyneco2);
        antecedentGyneco2.setId(2L);
        assertThat(antecedentGyneco1).isNotEqualTo(antecedentGyneco2);
        antecedentGyneco1.setId(null);
        assertThat(antecedentGyneco1).isNotEqualTo(antecedentGyneco2);
    }
}
