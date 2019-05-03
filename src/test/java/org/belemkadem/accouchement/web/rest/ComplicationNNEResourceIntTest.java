package org.belemkadem.accouchement.web.rest;

import org.belemkadem.accouchement.AccouchementV3App;

import org.belemkadem.accouchement.domain.ComplicationNNE;
import org.belemkadem.accouchement.repository.ComplicationNNERepository;
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
 * Test class for the ComplicationNNEResource REST controller.
 *
 * @see ComplicationNNEResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccouchementV3App.class)
public class ComplicationNNEResourceIntTest {

    private static final String DEFAULT_DESIGNATION = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION = "BBBBBBBBBB";

    @Autowired
    private ComplicationNNERepository complicationNNERepository;

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

    private MockMvc restComplicationNNEMockMvc;

    private ComplicationNNE complicationNNE;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ComplicationNNEResource complicationNNEResource = new ComplicationNNEResource(complicationNNERepository);
        this.restComplicationNNEMockMvc = MockMvcBuilders.standaloneSetup(complicationNNEResource)
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
    public static ComplicationNNE createEntity(EntityManager em) {
        ComplicationNNE complicationNNE = new ComplicationNNE()
            .designation(DEFAULT_DESIGNATION);
        return complicationNNE;
    }

    @Before
    public void initTest() {
        complicationNNE = createEntity(em);
    }

    @Test
    @Transactional
    public void createComplicationNNE() throws Exception {
        int databaseSizeBeforeCreate = complicationNNERepository.findAll().size();

        // Create the ComplicationNNE
        restComplicationNNEMockMvc.perform(post("/api/complication-nnes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(complicationNNE)))
            .andExpect(status().isCreated());

        // Validate the ComplicationNNE in the database
        List<ComplicationNNE> complicationNNEList = complicationNNERepository.findAll();
        assertThat(complicationNNEList).hasSize(databaseSizeBeforeCreate + 1);
        ComplicationNNE testComplicationNNE = complicationNNEList.get(complicationNNEList.size() - 1);
        assertThat(testComplicationNNE.getDesignation()).isEqualTo(DEFAULT_DESIGNATION);
    }

    @Test
    @Transactional
    public void createComplicationNNEWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = complicationNNERepository.findAll().size();

        // Create the ComplicationNNE with an existing ID
        complicationNNE.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restComplicationNNEMockMvc.perform(post("/api/complication-nnes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(complicationNNE)))
            .andExpect(status().isBadRequest());

        // Validate the ComplicationNNE in the database
        List<ComplicationNNE> complicationNNEList = complicationNNERepository.findAll();
        assertThat(complicationNNEList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllComplicationNNES() throws Exception {
        // Initialize the database
        complicationNNERepository.saveAndFlush(complicationNNE);

        // Get all the complicationNNEList
        restComplicationNNEMockMvc.perform(get("/api/complication-nnes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(complicationNNE.getId().intValue())))
            .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION.toString())));
    }
    
    @Test
    @Transactional
    public void getComplicationNNE() throws Exception {
        // Initialize the database
        complicationNNERepository.saveAndFlush(complicationNNE);

        // Get the complicationNNE
        restComplicationNNEMockMvc.perform(get("/api/complication-nnes/{id}", complicationNNE.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(complicationNNE.getId().intValue()))
            .andExpect(jsonPath("$.designation").value(DEFAULT_DESIGNATION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingComplicationNNE() throws Exception {
        // Get the complicationNNE
        restComplicationNNEMockMvc.perform(get("/api/complication-nnes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateComplicationNNE() throws Exception {
        // Initialize the database
        complicationNNERepository.saveAndFlush(complicationNNE);

        int databaseSizeBeforeUpdate = complicationNNERepository.findAll().size();

        // Update the complicationNNE
        ComplicationNNE updatedComplicationNNE = complicationNNERepository.findById(complicationNNE.getId()).get();
        // Disconnect from session so that the updates on updatedComplicationNNE are not directly saved in db
        em.detach(updatedComplicationNNE);
        updatedComplicationNNE
            .designation(UPDATED_DESIGNATION);

        restComplicationNNEMockMvc.perform(put("/api/complication-nnes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedComplicationNNE)))
            .andExpect(status().isOk());

        // Validate the ComplicationNNE in the database
        List<ComplicationNNE> complicationNNEList = complicationNNERepository.findAll();
        assertThat(complicationNNEList).hasSize(databaseSizeBeforeUpdate);
        ComplicationNNE testComplicationNNE = complicationNNEList.get(complicationNNEList.size() - 1);
        assertThat(testComplicationNNE.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
    }

    @Test
    @Transactional
    public void updateNonExistingComplicationNNE() throws Exception {
        int databaseSizeBeforeUpdate = complicationNNERepository.findAll().size();

        // Create the ComplicationNNE

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restComplicationNNEMockMvc.perform(put("/api/complication-nnes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(complicationNNE)))
            .andExpect(status().isBadRequest());

        // Validate the ComplicationNNE in the database
        List<ComplicationNNE> complicationNNEList = complicationNNERepository.findAll();
        assertThat(complicationNNEList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteComplicationNNE() throws Exception {
        // Initialize the database
        complicationNNERepository.saveAndFlush(complicationNNE);

        int databaseSizeBeforeDelete = complicationNNERepository.findAll().size();

        // Delete the complicationNNE
        restComplicationNNEMockMvc.perform(delete("/api/complication-nnes/{id}", complicationNNE.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ComplicationNNE> complicationNNEList = complicationNNERepository.findAll();
        assertThat(complicationNNEList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ComplicationNNE.class);
        ComplicationNNE complicationNNE1 = new ComplicationNNE();
        complicationNNE1.setId(1L);
        ComplicationNNE complicationNNE2 = new ComplicationNNE();
        complicationNNE2.setId(complicationNNE1.getId());
        assertThat(complicationNNE1).isEqualTo(complicationNNE2);
        complicationNNE2.setId(2L);
        assertThat(complicationNNE1).isNotEqualTo(complicationNNE2);
        complicationNNE1.setId(null);
        assertThat(complicationNNE1).isNotEqualTo(complicationNNE2);
    }
}
