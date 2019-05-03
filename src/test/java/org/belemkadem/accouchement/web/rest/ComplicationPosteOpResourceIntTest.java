package org.belemkadem.accouchement.web.rest;

import org.belemkadem.accouchement.AccouchementV3App;

import org.belemkadem.accouchement.domain.ComplicationPosteOp;
import org.belemkadem.accouchement.repository.ComplicationPosteOpRepository;
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
 * Test class for the ComplicationPosteOpResource REST controller.
 *
 * @see ComplicationPosteOpResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccouchementV3App.class)
public class ComplicationPosteOpResourceIntTest {

    private static final String DEFAULT_DESIGNATION = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION = "BBBBBBBBBB";

    @Autowired
    private ComplicationPosteOpRepository complicationPosteOpRepository;

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

    private MockMvc restComplicationPosteOpMockMvc;

    private ComplicationPosteOp complicationPosteOp;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ComplicationPosteOpResource complicationPosteOpResource = new ComplicationPosteOpResource(complicationPosteOpRepository);
        this.restComplicationPosteOpMockMvc = MockMvcBuilders.standaloneSetup(complicationPosteOpResource)
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
    public static ComplicationPosteOp createEntity(EntityManager em) {
        ComplicationPosteOp complicationPosteOp = new ComplicationPosteOp()
            .designation(DEFAULT_DESIGNATION);
        return complicationPosteOp;
    }

    @Before
    public void initTest() {
        complicationPosteOp = createEntity(em);
    }

    @Test
    @Transactional
    public void createComplicationPosteOp() throws Exception {
        int databaseSizeBeforeCreate = complicationPosteOpRepository.findAll().size();

        // Create the ComplicationPosteOp
        restComplicationPosteOpMockMvc.perform(post("/api/complication-poste-ops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(complicationPosteOp)))
            .andExpect(status().isCreated());

        // Validate the ComplicationPosteOp in the database
        List<ComplicationPosteOp> complicationPosteOpList = complicationPosteOpRepository.findAll();
        assertThat(complicationPosteOpList).hasSize(databaseSizeBeforeCreate + 1);
        ComplicationPosteOp testComplicationPosteOp = complicationPosteOpList.get(complicationPosteOpList.size() - 1);
        assertThat(testComplicationPosteOp.getDesignation()).isEqualTo(DEFAULT_DESIGNATION);
    }

    @Test
    @Transactional
    public void createComplicationPosteOpWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = complicationPosteOpRepository.findAll().size();

        // Create the ComplicationPosteOp with an existing ID
        complicationPosteOp.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restComplicationPosteOpMockMvc.perform(post("/api/complication-poste-ops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(complicationPosteOp)))
            .andExpect(status().isBadRequest());

        // Validate the ComplicationPosteOp in the database
        List<ComplicationPosteOp> complicationPosteOpList = complicationPosteOpRepository.findAll();
        assertThat(complicationPosteOpList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllComplicationPosteOps() throws Exception {
        // Initialize the database
        complicationPosteOpRepository.saveAndFlush(complicationPosteOp);

        // Get all the complicationPosteOpList
        restComplicationPosteOpMockMvc.perform(get("/api/complication-poste-ops?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(complicationPosteOp.getId().intValue())))
            .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION.toString())));
    }
    
    @Test
    @Transactional
    public void getComplicationPosteOp() throws Exception {
        // Initialize the database
        complicationPosteOpRepository.saveAndFlush(complicationPosteOp);

        // Get the complicationPosteOp
        restComplicationPosteOpMockMvc.perform(get("/api/complication-poste-ops/{id}", complicationPosteOp.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(complicationPosteOp.getId().intValue()))
            .andExpect(jsonPath("$.designation").value(DEFAULT_DESIGNATION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingComplicationPosteOp() throws Exception {
        // Get the complicationPosteOp
        restComplicationPosteOpMockMvc.perform(get("/api/complication-poste-ops/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateComplicationPosteOp() throws Exception {
        // Initialize the database
        complicationPosteOpRepository.saveAndFlush(complicationPosteOp);

        int databaseSizeBeforeUpdate = complicationPosteOpRepository.findAll().size();

        // Update the complicationPosteOp
        ComplicationPosteOp updatedComplicationPosteOp = complicationPosteOpRepository.findById(complicationPosteOp.getId()).get();
        // Disconnect from session so that the updates on updatedComplicationPosteOp are not directly saved in db
        em.detach(updatedComplicationPosteOp);
        updatedComplicationPosteOp
            .designation(UPDATED_DESIGNATION);

        restComplicationPosteOpMockMvc.perform(put("/api/complication-poste-ops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedComplicationPosteOp)))
            .andExpect(status().isOk());

        // Validate the ComplicationPosteOp in the database
        List<ComplicationPosteOp> complicationPosteOpList = complicationPosteOpRepository.findAll();
        assertThat(complicationPosteOpList).hasSize(databaseSizeBeforeUpdate);
        ComplicationPosteOp testComplicationPosteOp = complicationPosteOpList.get(complicationPosteOpList.size() - 1);
        assertThat(testComplicationPosteOp.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
    }

    @Test
    @Transactional
    public void updateNonExistingComplicationPosteOp() throws Exception {
        int databaseSizeBeforeUpdate = complicationPosteOpRepository.findAll().size();

        // Create the ComplicationPosteOp

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restComplicationPosteOpMockMvc.perform(put("/api/complication-poste-ops")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(complicationPosteOp)))
            .andExpect(status().isBadRequest());

        // Validate the ComplicationPosteOp in the database
        List<ComplicationPosteOp> complicationPosteOpList = complicationPosteOpRepository.findAll();
        assertThat(complicationPosteOpList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteComplicationPosteOp() throws Exception {
        // Initialize the database
        complicationPosteOpRepository.saveAndFlush(complicationPosteOp);

        int databaseSizeBeforeDelete = complicationPosteOpRepository.findAll().size();

        // Delete the complicationPosteOp
        restComplicationPosteOpMockMvc.perform(delete("/api/complication-poste-ops/{id}", complicationPosteOp.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ComplicationPosteOp> complicationPosteOpList = complicationPosteOpRepository.findAll();
        assertThat(complicationPosteOpList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ComplicationPosteOp.class);
        ComplicationPosteOp complicationPosteOp1 = new ComplicationPosteOp();
        complicationPosteOp1.setId(1L);
        ComplicationPosteOp complicationPosteOp2 = new ComplicationPosteOp();
        complicationPosteOp2.setId(complicationPosteOp1.getId());
        assertThat(complicationPosteOp1).isEqualTo(complicationPosteOp2);
        complicationPosteOp2.setId(2L);
        assertThat(complicationPosteOp1).isNotEqualTo(complicationPosteOp2);
        complicationPosteOp1.setId(null);
        assertThat(complicationPosteOp1).isNotEqualTo(complicationPosteOp2);
    }
}
