package org.belemkadem.accouchement.web.rest;

import org.belemkadem.accouchement.AccouchementV3App;

import org.belemkadem.accouchement.domain.ComplicationPerOp;
import org.belemkadem.accouchement.repository.ComplicationPerOpRepository;
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
 * Test class for the ComplicationPerOpResource REST controller.
 *
 * @see ComplicationPerOpResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccouchementV3App.class)
public class ComplicationPerOpResourceIntTest {

    private static final String DEFAULT_DESIGNATION = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION = "BBBBBBBBBB";

    @Autowired
    private ComplicationPerOpRepository complicationPerOpRepository;

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

    private MockMvc restComplicationPerOpMockMvc;

    private ComplicationPerOp complicationPerOp;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ComplicationPerOpResource complicationPerOpResource = new ComplicationPerOpResource(complicationPerOpRepository);
        this.restComplicationPerOpMockMvc = MockMvcBuilders.standaloneSetup(complicationPerOpResource)
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
    public static ComplicationPerOp createEntity(EntityManager em) {
        ComplicationPerOp complicationPerOp = new ComplicationPerOp()
            .designation(DEFAULT_DESIGNATION);
        return complicationPerOp;
    }

    @Before
    public void initTest() {
        complicationPerOp = createEntity(em);
    }

    @Test
    @Transactional
    public void createComplicationPerOp() throws Exception {
        int databaseSizeBeforeCreate = complicationPerOpRepository.findAll().size();

        // Create the ComplicationPerOp
        restComplicationPerOpMockMvc.perform(post("/api/complication-per-ops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(complicationPerOp)))
            .andExpect(status().isCreated());

        // Validate the ComplicationPerOp in the database
        List<ComplicationPerOp> complicationPerOpList = complicationPerOpRepository.findAll();
        assertThat(complicationPerOpList).hasSize(databaseSizeBeforeCreate + 1);
        ComplicationPerOp testComplicationPerOp = complicationPerOpList.get(complicationPerOpList.size() - 1);
        assertThat(testComplicationPerOp.getDesignation()).isEqualTo(DEFAULT_DESIGNATION);
    }

    @Test
    @Transactional
    public void createComplicationPerOpWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = complicationPerOpRepository.findAll().size();

        // Create the ComplicationPerOp with an existing ID
        complicationPerOp.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restComplicationPerOpMockMvc.perform(post("/api/complication-per-ops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(complicationPerOp)))
            .andExpect(status().isBadRequest());

        // Validate the ComplicationPerOp in the database
        List<ComplicationPerOp> complicationPerOpList = complicationPerOpRepository.findAll();
        assertThat(complicationPerOpList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllComplicationPerOps() throws Exception {
        // Initialize the database
        complicationPerOpRepository.saveAndFlush(complicationPerOp);

        // Get all the complicationPerOpList
        restComplicationPerOpMockMvc.perform(get("/api/complication-per-ops?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(complicationPerOp.getId().intValue())))
            .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION.toString())));
    }
    
    @Test
    @Transactional
    public void getComplicationPerOp() throws Exception {
        // Initialize the database
        complicationPerOpRepository.saveAndFlush(complicationPerOp);

        // Get the complicationPerOp
        restComplicationPerOpMockMvc.perform(get("/api/complication-per-ops/{id}", complicationPerOp.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(complicationPerOp.getId().intValue()))
            .andExpect(jsonPath("$.designation").value(DEFAULT_DESIGNATION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingComplicationPerOp() throws Exception {
        // Get the complicationPerOp
        restComplicationPerOpMockMvc.perform(get("/api/complication-per-ops/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateComplicationPerOp() throws Exception {
        // Initialize the database
        complicationPerOpRepository.saveAndFlush(complicationPerOp);

        int databaseSizeBeforeUpdate = complicationPerOpRepository.findAll().size();

        // Update the complicationPerOp
        ComplicationPerOp updatedComplicationPerOp = complicationPerOpRepository.findById(complicationPerOp.getId()).get();
        // Disconnect from session so that the updates on updatedComplicationPerOp are not directly saved in db
        em.detach(updatedComplicationPerOp);
        updatedComplicationPerOp
            .designation(UPDATED_DESIGNATION);

        restComplicationPerOpMockMvc.perform(put("/api/complication-per-ops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedComplicationPerOp)))
            .andExpect(status().isOk());

        // Validate the ComplicationPerOp in the database
        List<ComplicationPerOp> complicationPerOpList = complicationPerOpRepository.findAll();
        assertThat(complicationPerOpList).hasSize(databaseSizeBeforeUpdate);
        ComplicationPerOp testComplicationPerOp = complicationPerOpList.get(complicationPerOpList.size() - 1);
        assertThat(testComplicationPerOp.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
    }

    @Test
    @Transactional
    public void updateNonExistingComplicationPerOp() throws Exception {
        int databaseSizeBeforeUpdate = complicationPerOpRepository.findAll().size();

        // Create the ComplicationPerOp

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restComplicationPerOpMockMvc.perform(put("/api/complication-per-ops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(complicationPerOp)))
            .andExpect(status().isBadRequest());

        // Validate the ComplicationPerOp in the database
        List<ComplicationPerOp> complicationPerOpList = complicationPerOpRepository.findAll();
        assertThat(complicationPerOpList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteComplicationPerOp() throws Exception {
        // Initialize the database
        complicationPerOpRepository.saveAndFlush(complicationPerOp);

        int databaseSizeBeforeDelete = complicationPerOpRepository.findAll().size();

        // Delete the complicationPerOp
        restComplicationPerOpMockMvc.perform(delete("/api/complication-per-ops/{id}", complicationPerOp.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ComplicationPerOp> complicationPerOpList = complicationPerOpRepository.findAll();
        assertThat(complicationPerOpList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ComplicationPerOp.class);
        ComplicationPerOp complicationPerOp1 = new ComplicationPerOp();
        complicationPerOp1.setId(1L);
        ComplicationPerOp complicationPerOp2 = new ComplicationPerOp();
        complicationPerOp2.setId(complicationPerOp1.getId());
        assertThat(complicationPerOp1).isEqualTo(complicationPerOp2);
        complicationPerOp2.setId(2L);
        assertThat(complicationPerOp1).isNotEqualTo(complicationPerOp2);
        complicationPerOp1.setId(null);
        assertThat(complicationPerOp1).isNotEqualTo(complicationPerOp2);
    }
}
