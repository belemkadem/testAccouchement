package org.belemkadem.accouchement.web.rest;

import org.belemkadem.accouchement.AccouchementV3App;

import org.belemkadem.accouchement.domain.Malformation;
import org.belemkadem.accouchement.repository.MalformationRepository;
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
 * Test class for the MalformationResource REST controller.
 *
 * @see MalformationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccouchementV3App.class)
public class MalformationResourceIntTest {

    private static final String DEFAULT_DESIGNATION = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION = "BBBBBBBBBB";

    @Autowired
    private MalformationRepository malformationRepository;

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

    private MockMvc restMalformationMockMvc;

    private Malformation malformation;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MalformationResource malformationResource = new MalformationResource(malformationRepository);
        this.restMalformationMockMvc = MockMvcBuilders.standaloneSetup(malformationResource)
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
    public static Malformation createEntity(EntityManager em) {
        Malformation malformation = new Malformation()
            .designation(DEFAULT_DESIGNATION);
        return malformation;
    }

    @Before
    public void initTest() {
        malformation = createEntity(em);
    }

    @Test
    @Transactional
    public void createMalformation() throws Exception {
        int databaseSizeBeforeCreate = malformationRepository.findAll().size();

        // Create the Malformation
        restMalformationMockMvc.perform(post("/api/malformations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(malformation)))
            .andExpect(status().isCreated());

        // Validate the Malformation in the database
        List<Malformation> malformationList = malformationRepository.findAll();
        assertThat(malformationList).hasSize(databaseSizeBeforeCreate + 1);
        Malformation testMalformation = malformationList.get(malformationList.size() - 1);
        assertThat(testMalformation.getDesignation()).isEqualTo(DEFAULT_DESIGNATION);
    }

    @Test
    @Transactional
    public void createMalformationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = malformationRepository.findAll().size();

        // Create the Malformation with an existing ID
        malformation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMalformationMockMvc.perform(post("/api/malformations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(malformation)))
            .andExpect(status().isBadRequest());

        // Validate the Malformation in the database
        List<Malformation> malformationList = malformationRepository.findAll();
        assertThat(malformationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMalformations() throws Exception {
        // Initialize the database
        malformationRepository.saveAndFlush(malformation);

        // Get all the malformationList
        restMalformationMockMvc.perform(get("/api/malformations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(malformation.getId().intValue())))
            .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION.toString())));
    }
    
    @Test
    @Transactional
    public void getMalformation() throws Exception {
        // Initialize the database
        malformationRepository.saveAndFlush(malformation);

        // Get the malformation
        restMalformationMockMvc.perform(get("/api/malformations/{id}", malformation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(malformation.getId().intValue()))
            .andExpect(jsonPath("$.designation").value(DEFAULT_DESIGNATION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMalformation() throws Exception {
        // Get the malformation
        restMalformationMockMvc.perform(get("/api/malformations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMalformation() throws Exception {
        // Initialize the database
        malformationRepository.saveAndFlush(malformation);

        int databaseSizeBeforeUpdate = malformationRepository.findAll().size();

        // Update the malformation
        Malformation updatedMalformation = malformationRepository.findById(malformation.getId()).get();
        // Disconnect from session so that the updates on updatedMalformation are not directly saved in db
        em.detach(updatedMalformation);
        updatedMalformation
            .designation(UPDATED_DESIGNATION);

        restMalformationMockMvc.perform(put("/api/malformations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMalformation)))
            .andExpect(status().isOk());

        // Validate the Malformation in the database
        List<Malformation> malformationList = malformationRepository.findAll();
        assertThat(malformationList).hasSize(databaseSizeBeforeUpdate);
        Malformation testMalformation = malformationList.get(malformationList.size() - 1);
        assertThat(testMalformation.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
    }

    @Test
    @Transactional
    public void updateNonExistingMalformation() throws Exception {
        int databaseSizeBeforeUpdate = malformationRepository.findAll().size();

        // Create the Malformation

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMalformationMockMvc.perform(put("/api/malformations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(malformation)))
            .andExpect(status().isBadRequest());

        // Validate the Malformation in the database
        List<Malformation> malformationList = malformationRepository.findAll();
        assertThat(malformationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMalformation() throws Exception {
        // Initialize the database
        malformationRepository.saveAndFlush(malformation);

        int databaseSizeBeforeDelete = malformationRepository.findAll().size();

        // Delete the malformation
        restMalformationMockMvc.perform(delete("/api/malformations/{id}", malformation.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Malformation> malformationList = malformationRepository.findAll();
        assertThat(malformationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Malformation.class);
        Malformation malformation1 = new Malformation();
        malformation1.setId(1L);
        Malformation malformation2 = new Malformation();
        malformation2.setId(malformation1.getId());
        assertThat(malformation1).isEqualTo(malformation2);
        malformation2.setId(2L);
        assertThat(malformation1).isNotEqualTo(malformation2);
        malformation1.setId(null);
        assertThat(malformation1).isNotEqualTo(malformation2);
    }
}
