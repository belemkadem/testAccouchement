package org.belemkadem.accouchement.web.rest;

import org.belemkadem.accouchement.AccouchementV3App;

import org.belemkadem.accouchement.domain.PathologieActuelle;
import org.belemkadem.accouchement.repository.PathologieActuelleRepository;
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
 * Test class for the PathologieActuelleResource REST controller.
 *
 * @see PathologieActuelleResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccouchementV3App.class)
public class PathologieActuelleResourceIntTest {

    private static final String DEFAULT_DESIGNATION = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION = "BBBBBBBBBB";

    @Autowired
    private PathologieActuelleRepository pathologieActuelleRepository;

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

    private MockMvc restPathologieActuelleMockMvc;

    private PathologieActuelle pathologieActuelle;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PathologieActuelleResource pathologieActuelleResource = new PathologieActuelleResource(pathologieActuelleRepository);
        this.restPathologieActuelleMockMvc = MockMvcBuilders.standaloneSetup(pathologieActuelleResource)
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
    public static PathologieActuelle createEntity(EntityManager em) {
        PathologieActuelle pathologieActuelle = new PathologieActuelle()
            .designation(DEFAULT_DESIGNATION);
        return pathologieActuelle;
    }

    @Before
    public void initTest() {
        pathologieActuelle = createEntity(em);
    }

    @Test
    @Transactional
    public void createPathologieActuelle() throws Exception {
        int databaseSizeBeforeCreate = pathologieActuelleRepository.findAll().size();

        // Create the PathologieActuelle
        restPathologieActuelleMockMvc.perform(post("/api/pathologie-actuelles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pathologieActuelle)))
            .andExpect(status().isCreated());

        // Validate the PathologieActuelle in the database
        List<PathologieActuelle> pathologieActuelleList = pathologieActuelleRepository.findAll();
        assertThat(pathologieActuelleList).hasSize(databaseSizeBeforeCreate + 1);
        PathologieActuelle testPathologieActuelle = pathologieActuelleList.get(pathologieActuelleList.size() - 1);
        assertThat(testPathologieActuelle.getDesignation()).isEqualTo(DEFAULT_DESIGNATION);
    }

    @Test
    @Transactional
    public void createPathologieActuelleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pathologieActuelleRepository.findAll().size();

        // Create the PathologieActuelle with an existing ID
        pathologieActuelle.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPathologieActuelleMockMvc.perform(post("/api/pathologie-actuelles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pathologieActuelle)))
            .andExpect(status().isBadRequest());

        // Validate the PathologieActuelle in the database
        List<PathologieActuelle> pathologieActuelleList = pathologieActuelleRepository.findAll();
        assertThat(pathologieActuelleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPathologieActuelles() throws Exception {
        // Initialize the database
        pathologieActuelleRepository.saveAndFlush(pathologieActuelle);

        // Get all the pathologieActuelleList
        restPathologieActuelleMockMvc.perform(get("/api/pathologie-actuelles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pathologieActuelle.getId().intValue())))
            .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION.toString())));
    }
    
    @Test
    @Transactional
    public void getPathologieActuelle() throws Exception {
        // Initialize the database
        pathologieActuelleRepository.saveAndFlush(pathologieActuelle);

        // Get the pathologieActuelle
        restPathologieActuelleMockMvc.perform(get("/api/pathologie-actuelles/{id}", pathologieActuelle.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pathologieActuelle.getId().intValue()))
            .andExpect(jsonPath("$.designation").value(DEFAULT_DESIGNATION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPathologieActuelle() throws Exception {
        // Get the pathologieActuelle
        restPathologieActuelleMockMvc.perform(get("/api/pathologie-actuelles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePathologieActuelle() throws Exception {
        // Initialize the database
        pathologieActuelleRepository.saveAndFlush(pathologieActuelle);

        int databaseSizeBeforeUpdate = pathologieActuelleRepository.findAll().size();

        // Update the pathologieActuelle
        PathologieActuelle updatedPathologieActuelle = pathologieActuelleRepository.findById(pathologieActuelle.getId()).get();
        // Disconnect from session so that the updates on updatedPathologieActuelle are not directly saved in db
        em.detach(updatedPathologieActuelle);
        updatedPathologieActuelle
            .designation(UPDATED_DESIGNATION);

        restPathologieActuelleMockMvc.perform(put("/api/pathologie-actuelles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPathologieActuelle)))
            .andExpect(status().isOk());

        // Validate the PathologieActuelle in the database
        List<PathologieActuelle> pathologieActuelleList = pathologieActuelleRepository.findAll();
        assertThat(pathologieActuelleList).hasSize(databaseSizeBeforeUpdate);
        PathologieActuelle testPathologieActuelle = pathologieActuelleList.get(pathologieActuelleList.size() - 1);
        assertThat(testPathologieActuelle.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
    }

    @Test
    @Transactional
    public void updateNonExistingPathologieActuelle() throws Exception {
        int databaseSizeBeforeUpdate = pathologieActuelleRepository.findAll().size();

        // Create the PathologieActuelle

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPathologieActuelleMockMvc.perform(put("/api/pathologie-actuelles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pathologieActuelle)))
            .andExpect(status().isBadRequest());

        // Validate the PathologieActuelle in the database
        List<PathologieActuelle> pathologieActuelleList = pathologieActuelleRepository.findAll();
        assertThat(pathologieActuelleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePathologieActuelle() throws Exception {
        // Initialize the database
        pathologieActuelleRepository.saveAndFlush(pathologieActuelle);

        int databaseSizeBeforeDelete = pathologieActuelleRepository.findAll().size();

        // Delete the pathologieActuelle
        restPathologieActuelleMockMvc.perform(delete("/api/pathologie-actuelles/{id}", pathologieActuelle.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PathologieActuelle> pathologieActuelleList = pathologieActuelleRepository.findAll();
        assertThat(pathologieActuelleList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PathologieActuelle.class);
        PathologieActuelle pathologieActuelle1 = new PathologieActuelle();
        pathologieActuelle1.setId(1L);
        PathologieActuelle pathologieActuelle2 = new PathologieActuelle();
        pathologieActuelle2.setId(pathologieActuelle1.getId());
        assertThat(pathologieActuelle1).isEqualTo(pathologieActuelle2);
        pathologieActuelle2.setId(2L);
        assertThat(pathologieActuelle1).isNotEqualTo(pathologieActuelle2);
        pathologieActuelle1.setId(null);
        assertThat(pathologieActuelle1).isNotEqualTo(pathologieActuelle2);
    }
}
