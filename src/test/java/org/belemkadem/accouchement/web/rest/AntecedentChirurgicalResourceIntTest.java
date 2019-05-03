package org.belemkadem.accouchement.web.rest;

import org.belemkadem.accouchement.AccouchementV3App;

import org.belemkadem.accouchement.domain.AntecedentChirurgical;
import org.belemkadem.accouchement.repository.AntecedentChirurgicalRepository;
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
 * Test class for the AntecedentChirurgicalResource REST controller.
 *
 * @see AntecedentChirurgicalResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccouchementV3App.class)
public class AntecedentChirurgicalResourceIntTest {

    private static final String DEFAULT_DESIGNATION = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION = "BBBBBBBBBB";

    @Autowired
    private AntecedentChirurgicalRepository antecedentChirurgicalRepository;

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

    private MockMvc restAntecedentChirurgicalMockMvc;

    private AntecedentChirurgical antecedentChirurgical;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AntecedentChirurgicalResource antecedentChirurgicalResource = new AntecedentChirurgicalResource(antecedentChirurgicalRepository);
        this.restAntecedentChirurgicalMockMvc = MockMvcBuilders.standaloneSetup(antecedentChirurgicalResource)
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
    public static AntecedentChirurgical createEntity(EntityManager em) {
        AntecedentChirurgical antecedentChirurgical = new AntecedentChirurgical()
            .designation(DEFAULT_DESIGNATION);
        return antecedentChirurgical;
    }

    @Before
    public void initTest() {
        antecedentChirurgical = createEntity(em);
    }

    @Test
    @Transactional
    public void createAntecedentChirurgical() throws Exception {
        int databaseSizeBeforeCreate = antecedentChirurgicalRepository.findAll().size();

        // Create the AntecedentChirurgical
        restAntecedentChirurgicalMockMvc.perform(post("/api/antecedent-chirurgicals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(antecedentChirurgical)))
            .andExpect(status().isCreated());

        // Validate the AntecedentChirurgical in the database
        List<AntecedentChirurgical> antecedentChirurgicalList = antecedentChirurgicalRepository.findAll();
        assertThat(antecedentChirurgicalList).hasSize(databaseSizeBeforeCreate + 1);
        AntecedentChirurgical testAntecedentChirurgical = antecedentChirurgicalList.get(antecedentChirurgicalList.size() - 1);
        assertThat(testAntecedentChirurgical.getDesignation()).isEqualTo(DEFAULT_DESIGNATION);
    }

    @Test
    @Transactional
    public void createAntecedentChirurgicalWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = antecedentChirurgicalRepository.findAll().size();

        // Create the AntecedentChirurgical with an existing ID
        antecedentChirurgical.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAntecedentChirurgicalMockMvc.perform(post("/api/antecedent-chirurgicals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(antecedentChirurgical)))
            .andExpect(status().isBadRequest());

        // Validate the AntecedentChirurgical in the database
        List<AntecedentChirurgical> antecedentChirurgicalList = antecedentChirurgicalRepository.findAll();
        assertThat(antecedentChirurgicalList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAntecedentChirurgicals() throws Exception {
        // Initialize the database
        antecedentChirurgicalRepository.saveAndFlush(antecedentChirurgical);

        // Get all the antecedentChirurgicalList
        restAntecedentChirurgicalMockMvc.perform(get("/api/antecedent-chirurgicals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(antecedentChirurgical.getId().intValue())))
            .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION.toString())));
    }
    
    @Test
    @Transactional
    public void getAntecedentChirurgical() throws Exception {
        // Initialize the database
        antecedentChirurgicalRepository.saveAndFlush(antecedentChirurgical);

        // Get the antecedentChirurgical
        restAntecedentChirurgicalMockMvc.perform(get("/api/antecedent-chirurgicals/{id}", antecedentChirurgical.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(antecedentChirurgical.getId().intValue()))
            .andExpect(jsonPath("$.designation").value(DEFAULT_DESIGNATION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAntecedentChirurgical() throws Exception {
        // Get the antecedentChirurgical
        restAntecedentChirurgicalMockMvc.perform(get("/api/antecedent-chirurgicals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAntecedentChirurgical() throws Exception {
        // Initialize the database
        antecedentChirurgicalRepository.saveAndFlush(antecedentChirurgical);

        int databaseSizeBeforeUpdate = antecedentChirurgicalRepository.findAll().size();

        // Update the antecedentChirurgical
        AntecedentChirurgical updatedAntecedentChirurgical = antecedentChirurgicalRepository.findById(antecedentChirurgical.getId()).get();
        // Disconnect from session so that the updates on updatedAntecedentChirurgical are not directly saved in db
        em.detach(updatedAntecedentChirurgical);
        updatedAntecedentChirurgical
            .designation(UPDATED_DESIGNATION);

        restAntecedentChirurgicalMockMvc.perform(put("/api/antecedent-chirurgicals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAntecedentChirurgical)))
            .andExpect(status().isOk());

        // Validate the AntecedentChirurgical in the database
        List<AntecedentChirurgical> antecedentChirurgicalList = antecedentChirurgicalRepository.findAll();
        assertThat(antecedentChirurgicalList).hasSize(databaseSizeBeforeUpdate);
        AntecedentChirurgical testAntecedentChirurgical = antecedentChirurgicalList.get(antecedentChirurgicalList.size() - 1);
        assertThat(testAntecedentChirurgical.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
    }

    @Test
    @Transactional
    public void updateNonExistingAntecedentChirurgical() throws Exception {
        int databaseSizeBeforeUpdate = antecedentChirurgicalRepository.findAll().size();

        // Create the AntecedentChirurgical

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAntecedentChirurgicalMockMvc.perform(put("/api/antecedent-chirurgicals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(antecedentChirurgical)))
            .andExpect(status().isBadRequest());

        // Validate the AntecedentChirurgical in the database
        List<AntecedentChirurgical> antecedentChirurgicalList = antecedentChirurgicalRepository.findAll();
        assertThat(antecedentChirurgicalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAntecedentChirurgical() throws Exception {
        // Initialize the database
        antecedentChirurgicalRepository.saveAndFlush(antecedentChirurgical);

        int databaseSizeBeforeDelete = antecedentChirurgicalRepository.findAll().size();

        // Delete the antecedentChirurgical
        restAntecedentChirurgicalMockMvc.perform(delete("/api/antecedent-chirurgicals/{id}", antecedentChirurgical.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AntecedentChirurgical> antecedentChirurgicalList = antecedentChirurgicalRepository.findAll();
        assertThat(antecedentChirurgicalList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AntecedentChirurgical.class);
        AntecedentChirurgical antecedentChirurgical1 = new AntecedentChirurgical();
        antecedentChirurgical1.setId(1L);
        AntecedentChirurgical antecedentChirurgical2 = new AntecedentChirurgical();
        antecedentChirurgical2.setId(antecedentChirurgical1.getId());
        assertThat(antecedentChirurgical1).isEqualTo(antecedentChirurgical2);
        antecedentChirurgical2.setId(2L);
        assertThat(antecedentChirurgical1).isNotEqualTo(antecedentChirurgical2);
        antecedentChirurgical1.setId(null);
        assertThat(antecedentChirurgical1).isNotEqualTo(antecedentChirurgical2);
    }
}
