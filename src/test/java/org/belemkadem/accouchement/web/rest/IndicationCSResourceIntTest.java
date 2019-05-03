package org.belemkadem.accouchement.web.rest;

import org.belemkadem.accouchement.AccouchementV3App;

import org.belemkadem.accouchement.domain.IndicationCS;
import org.belemkadem.accouchement.repository.IndicationCSRepository;
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
 * Test class for the IndicationCSResource REST controller.
 *
 * @see IndicationCSResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccouchementV3App.class)
public class IndicationCSResourceIntTest {

    private static final String DEFAULT_DESIGNATION = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION = "BBBBBBBBBB";

    @Autowired
    private IndicationCSRepository indicationCSRepository;

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

    private MockMvc restIndicationCSMockMvc;

    private IndicationCS indicationCS;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final IndicationCSResource indicationCSResource = new IndicationCSResource(indicationCSRepository);
        this.restIndicationCSMockMvc = MockMvcBuilders.standaloneSetup(indicationCSResource)
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
    public static IndicationCS createEntity(EntityManager em) {
        IndicationCS indicationCS = new IndicationCS()
            .designation(DEFAULT_DESIGNATION);
        return indicationCS;
    }

    @Before
    public void initTest() {
        indicationCS = createEntity(em);
    }

    @Test
    @Transactional
    public void createIndicationCS() throws Exception {
        int databaseSizeBeforeCreate = indicationCSRepository.findAll().size();

        // Create the IndicationCS
        restIndicationCSMockMvc.perform(post("/api/indication-cs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicationCS)))
            .andExpect(status().isCreated());

        // Validate the IndicationCS in the database
        List<IndicationCS> indicationCSList = indicationCSRepository.findAll();
        assertThat(indicationCSList).hasSize(databaseSizeBeforeCreate + 1);
        IndicationCS testIndicationCS = indicationCSList.get(indicationCSList.size() - 1);
        assertThat(testIndicationCS.getDesignation()).isEqualTo(DEFAULT_DESIGNATION);
    }

    @Test
    @Transactional
    public void createIndicationCSWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = indicationCSRepository.findAll().size();

        // Create the IndicationCS with an existing ID
        indicationCS.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restIndicationCSMockMvc.perform(post("/api/indication-cs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicationCS)))
            .andExpect(status().isBadRequest());

        // Validate the IndicationCS in the database
        List<IndicationCS> indicationCSList = indicationCSRepository.findAll();
        assertThat(indicationCSList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllIndicationCS() throws Exception {
        // Initialize the database
        indicationCSRepository.saveAndFlush(indicationCS);

        // Get all the indicationCSList
        restIndicationCSMockMvc.perform(get("/api/indication-cs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(indicationCS.getId().intValue())))
            .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION.toString())));
    }
    
    @Test
    @Transactional
    public void getIndicationCS() throws Exception {
        // Initialize the database
        indicationCSRepository.saveAndFlush(indicationCS);

        // Get the indicationCS
        restIndicationCSMockMvc.perform(get("/api/indication-cs/{id}", indicationCS.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(indicationCS.getId().intValue()))
            .andExpect(jsonPath("$.designation").value(DEFAULT_DESIGNATION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingIndicationCS() throws Exception {
        // Get the indicationCS
        restIndicationCSMockMvc.perform(get("/api/indication-cs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateIndicationCS() throws Exception {
        // Initialize the database
        indicationCSRepository.saveAndFlush(indicationCS);

        int databaseSizeBeforeUpdate = indicationCSRepository.findAll().size();

        // Update the indicationCS
        IndicationCS updatedIndicationCS = indicationCSRepository.findById(indicationCS.getId()).get();
        // Disconnect from session so that the updates on updatedIndicationCS are not directly saved in db
        em.detach(updatedIndicationCS);
        updatedIndicationCS
            .designation(UPDATED_DESIGNATION);

        restIndicationCSMockMvc.perform(put("/api/indication-cs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedIndicationCS)))
            .andExpect(status().isOk());

        // Validate the IndicationCS in the database
        List<IndicationCS> indicationCSList = indicationCSRepository.findAll();
        assertThat(indicationCSList).hasSize(databaseSizeBeforeUpdate);
        IndicationCS testIndicationCS = indicationCSList.get(indicationCSList.size() - 1);
        assertThat(testIndicationCS.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
    }

    @Test
    @Transactional
    public void updateNonExistingIndicationCS() throws Exception {
        int databaseSizeBeforeUpdate = indicationCSRepository.findAll().size();

        // Create the IndicationCS

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIndicationCSMockMvc.perform(put("/api/indication-cs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(indicationCS)))
            .andExpect(status().isBadRequest());

        // Validate the IndicationCS in the database
        List<IndicationCS> indicationCSList = indicationCSRepository.findAll();
        assertThat(indicationCSList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteIndicationCS() throws Exception {
        // Initialize the database
        indicationCSRepository.saveAndFlush(indicationCS);

        int databaseSizeBeforeDelete = indicationCSRepository.findAll().size();

        // Delete the indicationCS
        restIndicationCSMockMvc.perform(delete("/api/indication-cs/{id}", indicationCS.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<IndicationCS> indicationCSList = indicationCSRepository.findAll();
        assertThat(indicationCSList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(IndicationCS.class);
        IndicationCS indicationCS1 = new IndicationCS();
        indicationCS1.setId(1L);
        IndicationCS indicationCS2 = new IndicationCS();
        indicationCS2.setId(indicationCS1.getId());
        assertThat(indicationCS1).isEqualTo(indicationCS2);
        indicationCS2.setId(2L);
        assertThat(indicationCS1).isNotEqualTo(indicationCS2);
        indicationCS1.setId(null);
        assertThat(indicationCS1).isNotEqualTo(indicationCS2);
    }
}
