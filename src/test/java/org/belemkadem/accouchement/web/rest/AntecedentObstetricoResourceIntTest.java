package org.belemkadem.accouchement.web.rest;

import org.belemkadem.accouchement.AccouchementV3App;

import org.belemkadem.accouchement.domain.AntecedentObstetrico;
import org.belemkadem.accouchement.repository.AntecedentObstetricoRepository;
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
 * Test class for the AntecedentObstetricoResource REST controller.
 *
 * @see AntecedentObstetricoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccouchementV3App.class)
public class AntecedentObstetricoResourceIntTest {

    private static final String DEFAULT_DESIGNATION = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION = "BBBBBBBBBB";

    @Autowired
    private AntecedentObstetricoRepository antecedentObstetricoRepository;

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

    private MockMvc restAntecedentObstetricoMockMvc;

    private AntecedentObstetrico antecedentObstetrico;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AntecedentObstetricoResource antecedentObstetricoResource = new AntecedentObstetricoResource(antecedentObstetricoRepository);
        this.restAntecedentObstetricoMockMvc = MockMvcBuilders.standaloneSetup(antecedentObstetricoResource)
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
    public static AntecedentObstetrico createEntity(EntityManager em) {
        AntecedentObstetrico antecedentObstetrico = new AntecedentObstetrico()
            .designation(DEFAULT_DESIGNATION);
        return antecedentObstetrico;
    }

    @Before
    public void initTest() {
        antecedentObstetrico = createEntity(em);
    }

    @Test
    @Transactional
    public void createAntecedentObstetrico() throws Exception {
        int databaseSizeBeforeCreate = antecedentObstetricoRepository.findAll().size();

        // Create the AntecedentObstetrico
        restAntecedentObstetricoMockMvc.perform(post("/api/antecedent-obstetricos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(antecedentObstetrico)))
            .andExpect(status().isCreated());

        // Validate the AntecedentObstetrico in the database
        List<AntecedentObstetrico> antecedentObstetricoList = antecedentObstetricoRepository.findAll();
        assertThat(antecedentObstetricoList).hasSize(databaseSizeBeforeCreate + 1);
        AntecedentObstetrico testAntecedentObstetrico = antecedentObstetricoList.get(antecedentObstetricoList.size() - 1);
        assertThat(testAntecedentObstetrico.getDesignation()).isEqualTo(DEFAULT_DESIGNATION);
    }

    @Test
    @Transactional
    public void createAntecedentObstetricoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = antecedentObstetricoRepository.findAll().size();

        // Create the AntecedentObstetrico with an existing ID
        antecedentObstetrico.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAntecedentObstetricoMockMvc.perform(post("/api/antecedent-obstetricos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(antecedentObstetrico)))
            .andExpect(status().isBadRequest());

        // Validate the AntecedentObstetrico in the database
        List<AntecedentObstetrico> antecedentObstetricoList = antecedentObstetricoRepository.findAll();
        assertThat(antecedentObstetricoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAntecedentObstetricos() throws Exception {
        // Initialize the database
        antecedentObstetricoRepository.saveAndFlush(antecedentObstetrico);

        // Get all the antecedentObstetricoList
        restAntecedentObstetricoMockMvc.perform(get("/api/antecedent-obstetricos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(antecedentObstetrico.getId().intValue())))
            .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION.toString())));
    }
    
    @Test
    @Transactional
    public void getAntecedentObstetrico() throws Exception {
        // Initialize the database
        antecedentObstetricoRepository.saveAndFlush(antecedentObstetrico);

        // Get the antecedentObstetrico
        restAntecedentObstetricoMockMvc.perform(get("/api/antecedent-obstetricos/{id}", antecedentObstetrico.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(antecedentObstetrico.getId().intValue()))
            .andExpect(jsonPath("$.designation").value(DEFAULT_DESIGNATION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAntecedentObstetrico() throws Exception {
        // Get the antecedentObstetrico
        restAntecedentObstetricoMockMvc.perform(get("/api/antecedent-obstetricos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAntecedentObstetrico() throws Exception {
        // Initialize the database
        antecedentObstetricoRepository.saveAndFlush(antecedentObstetrico);

        int databaseSizeBeforeUpdate = antecedentObstetricoRepository.findAll().size();

        // Update the antecedentObstetrico
        AntecedentObstetrico updatedAntecedentObstetrico = antecedentObstetricoRepository.findById(antecedentObstetrico.getId()).get();
        // Disconnect from session so that the updates on updatedAntecedentObstetrico are not directly saved in db
        em.detach(updatedAntecedentObstetrico);
        updatedAntecedentObstetrico
            .designation(UPDATED_DESIGNATION);

        restAntecedentObstetricoMockMvc.perform(put("/api/antecedent-obstetricos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAntecedentObstetrico)))
            .andExpect(status().isOk());

        // Validate the AntecedentObstetrico in the database
        List<AntecedentObstetrico> antecedentObstetricoList = antecedentObstetricoRepository.findAll();
        assertThat(antecedentObstetricoList).hasSize(databaseSizeBeforeUpdate);
        AntecedentObstetrico testAntecedentObstetrico = antecedentObstetricoList.get(antecedentObstetricoList.size() - 1);
        assertThat(testAntecedentObstetrico.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
    }

    @Test
    @Transactional
    public void updateNonExistingAntecedentObstetrico() throws Exception {
        int databaseSizeBeforeUpdate = antecedentObstetricoRepository.findAll().size();

        // Create the AntecedentObstetrico

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAntecedentObstetricoMockMvc.perform(put("/api/antecedent-obstetricos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(antecedentObstetrico)))
            .andExpect(status().isBadRequest());

        // Validate the AntecedentObstetrico in the database
        List<AntecedentObstetrico> antecedentObstetricoList = antecedentObstetricoRepository.findAll();
        assertThat(antecedentObstetricoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAntecedentObstetrico() throws Exception {
        // Initialize the database
        antecedentObstetricoRepository.saveAndFlush(antecedentObstetrico);

        int databaseSizeBeforeDelete = antecedentObstetricoRepository.findAll().size();

        // Delete the antecedentObstetrico
        restAntecedentObstetricoMockMvc.perform(delete("/api/antecedent-obstetricos/{id}", antecedentObstetrico.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AntecedentObstetrico> antecedentObstetricoList = antecedentObstetricoRepository.findAll();
        assertThat(antecedentObstetricoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AntecedentObstetrico.class);
        AntecedentObstetrico antecedentObstetrico1 = new AntecedentObstetrico();
        antecedentObstetrico1.setId(1L);
        AntecedentObstetrico antecedentObstetrico2 = new AntecedentObstetrico();
        antecedentObstetrico2.setId(antecedentObstetrico1.getId());
        assertThat(antecedentObstetrico1).isEqualTo(antecedentObstetrico2);
        antecedentObstetrico2.setId(2L);
        assertThat(antecedentObstetrico1).isNotEqualTo(antecedentObstetrico2);
        antecedentObstetrico1.setId(null);
        assertThat(antecedentObstetrico1).isNotEqualTo(antecedentObstetrico2);
    }
}
