package org.belemkadem.accouchement.web.rest;

import org.belemkadem.accouchement.AccouchementV3App;

import org.belemkadem.accouchement.domain.AntecedentMedical;
import org.belemkadem.accouchement.repository.AntecedentMedicalRepository;
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
 * Test class for the AntecedentMedicalResource REST controller.
 *
 * @see AntecedentMedicalResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccouchementV3App.class)
public class AntecedentMedicalResourceIntTest {

    private static final String DEFAULT_DESIGNATION = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION = "BBBBBBBBBB";

    @Autowired
    private AntecedentMedicalRepository antecedentMedicalRepository;

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

    private MockMvc restAntecedentMedicalMockMvc;

    private AntecedentMedical antecedentMedical;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AntecedentMedicalResource antecedentMedicalResource = new AntecedentMedicalResource(antecedentMedicalRepository);
        this.restAntecedentMedicalMockMvc = MockMvcBuilders.standaloneSetup(antecedentMedicalResource)
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
    public static AntecedentMedical createEntity(EntityManager em) {
        AntecedentMedical antecedentMedical = new AntecedentMedical()
            .designation(DEFAULT_DESIGNATION);
        return antecedentMedical;
    }

    @Before
    public void initTest() {
        antecedentMedical = createEntity(em);
    }

    @Test
    @Transactional
    public void createAntecedentMedical() throws Exception {
        int databaseSizeBeforeCreate = antecedentMedicalRepository.findAll().size();

        // Create the AntecedentMedical
        restAntecedentMedicalMockMvc.perform(post("/api/antecedent-medicals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(antecedentMedical)))
            .andExpect(status().isCreated());

        // Validate the AntecedentMedical in the database
        List<AntecedentMedical> antecedentMedicalList = antecedentMedicalRepository.findAll();
        assertThat(antecedentMedicalList).hasSize(databaseSizeBeforeCreate + 1);
        AntecedentMedical testAntecedentMedical = antecedentMedicalList.get(antecedentMedicalList.size() - 1);
        assertThat(testAntecedentMedical.getDesignation()).isEqualTo(DEFAULT_DESIGNATION);
    }

    @Test
    @Transactional
    public void createAntecedentMedicalWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = antecedentMedicalRepository.findAll().size();

        // Create the AntecedentMedical with an existing ID
        antecedentMedical.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAntecedentMedicalMockMvc.perform(post("/api/antecedent-medicals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(antecedentMedical)))
            .andExpect(status().isBadRequest());

        // Validate the AntecedentMedical in the database
        List<AntecedentMedical> antecedentMedicalList = antecedentMedicalRepository.findAll();
        assertThat(antecedentMedicalList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAntecedentMedicals() throws Exception {
        // Initialize the database
        antecedentMedicalRepository.saveAndFlush(antecedentMedical);

        // Get all the antecedentMedicalList
        restAntecedentMedicalMockMvc.perform(get("/api/antecedent-medicals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(antecedentMedical.getId().intValue())))
            .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION.toString())));
    }
    
    @Test
    @Transactional
    public void getAntecedentMedical() throws Exception {
        // Initialize the database
        antecedentMedicalRepository.saveAndFlush(antecedentMedical);

        // Get the antecedentMedical
        restAntecedentMedicalMockMvc.perform(get("/api/antecedent-medicals/{id}", antecedentMedical.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(antecedentMedical.getId().intValue()))
            .andExpect(jsonPath("$.designation").value(DEFAULT_DESIGNATION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAntecedentMedical() throws Exception {
        // Get the antecedentMedical
        restAntecedentMedicalMockMvc.perform(get("/api/antecedent-medicals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAntecedentMedical() throws Exception {
        // Initialize the database
        antecedentMedicalRepository.saveAndFlush(antecedentMedical);

        int databaseSizeBeforeUpdate = antecedentMedicalRepository.findAll().size();

        // Update the antecedentMedical
        AntecedentMedical updatedAntecedentMedical = antecedentMedicalRepository.findById(antecedentMedical.getId()).get();
        // Disconnect from session so that the updates on updatedAntecedentMedical are not directly saved in db
        em.detach(updatedAntecedentMedical);
        updatedAntecedentMedical
            .designation(UPDATED_DESIGNATION);

        restAntecedentMedicalMockMvc.perform(put("/api/antecedent-medicals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAntecedentMedical)))
            .andExpect(status().isOk());

        // Validate the AntecedentMedical in the database
        List<AntecedentMedical> antecedentMedicalList = antecedentMedicalRepository.findAll();
        assertThat(antecedentMedicalList).hasSize(databaseSizeBeforeUpdate);
        AntecedentMedical testAntecedentMedical = antecedentMedicalList.get(antecedentMedicalList.size() - 1);
        assertThat(testAntecedentMedical.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
    }

    @Test
    @Transactional
    public void updateNonExistingAntecedentMedical() throws Exception {
        int databaseSizeBeforeUpdate = antecedentMedicalRepository.findAll().size();

        // Create the AntecedentMedical

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAntecedentMedicalMockMvc.perform(put("/api/antecedent-medicals")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(antecedentMedical)))
            .andExpect(status().isBadRequest());

        // Validate the AntecedentMedical in the database
        List<AntecedentMedical> antecedentMedicalList = antecedentMedicalRepository.findAll();
        assertThat(antecedentMedicalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAntecedentMedical() throws Exception {
        // Initialize the database
        antecedentMedicalRepository.saveAndFlush(antecedentMedical);

        int databaseSizeBeforeDelete = antecedentMedicalRepository.findAll().size();

        // Delete the antecedentMedical
        restAntecedentMedicalMockMvc.perform(delete("/api/antecedent-medicals/{id}", antecedentMedical.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AntecedentMedical> antecedentMedicalList = antecedentMedicalRepository.findAll();
        assertThat(antecedentMedicalList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AntecedentMedical.class);
        AntecedentMedical antecedentMedical1 = new AntecedentMedical();
        antecedentMedical1.setId(1L);
        AntecedentMedical antecedentMedical2 = new AntecedentMedical();
        antecedentMedical2.setId(antecedentMedical1.getId());
        assertThat(antecedentMedical1).isEqualTo(antecedentMedical2);
        antecedentMedical2.setId(2L);
        assertThat(antecedentMedical1).isNotEqualTo(antecedentMedical2);
        antecedentMedical1.setId(null);
        assertThat(antecedentMedical1).isNotEqualTo(antecedentMedical2);
    }
}
