package org.belemkadem.accouchement.web.rest;

import org.belemkadem.accouchement.AccouchementV3App;

import org.belemkadem.accouchement.domain.Accouchement;
import org.belemkadem.accouchement.repository.AccouchementRepository;
import org.belemkadem.accouchement.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


import static org.belemkadem.accouchement.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.belemkadem.accouchement.domain.enumeration.Provenance;
import org.belemkadem.accouchement.domain.enumeration.TypeDerniereCS;
import org.belemkadem.accouchement.domain.enumeration.OuiNon;
import org.belemkadem.accouchement.domain.enumeration.PositifNegatif;
import org.belemkadem.accouchement.domain.enumeration.GroupeSanguin;
import org.belemkadem.accouchement.domain.enumeration.EtatPDE;
import org.belemkadem.accouchement.domain.enumeration.LiquideAmneotique;
import org.belemkadem.accouchement.domain.enumeration.EtatBassin;
import org.belemkadem.accouchement.domain.enumeration.TypeCesarienne;
import org.belemkadem.accouchement.domain.enumeration.CesarienneEtTravail;
import org.belemkadem.accouchement.domain.enumeration.ModeAnesthesie;
import org.belemkadem.accouchement.domain.enumeration.TypeHysterotomie;
import org.belemkadem.accouchement.domain.enumeration.OuiNon;
import org.belemkadem.accouchement.domain.enumeration.OuiNon;
import org.belemkadem.accouchement.domain.enumeration.Sexe;
import org.belemkadem.accouchement.domain.enumeration.OuiNon;
import org.belemkadem.accouchement.domain.enumeration.OuiNon;
/**
 * Test class for the AccouchementResource REST controller.
 *
 * @see AccouchementResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccouchementV3App.class)
public class AccouchementResourceIntTest {

    private static final String DEFAULT_NUMERO_ENTREE = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_ENTREE = "BBBBBBBBBB";

    private static final Integer DEFAULT_AGE = 1;
    private static final Integer UPDATED_AGE = 2;

    private static final Provenance DEFAULT_PROVENANCE = Provenance.RURAL;
    private static final Provenance UPDATED_PROVENANCE = Provenance.URABAIN;

    private static final String DEFAULT_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE = "BBBBBBBBBB";

    private static final Integer DEFAULT_PARITE = 1;
    private static final Integer UPDATED_PARITE = 2;

    private static final Integer DEFAULT_GESTITE = 1;
    private static final Integer UPDATED_GESTITE = 2;

    private static final Integer DEFAULT_ENFANT_VIVANT = 1;
    private static final Integer UPDATED_ENFANT_VIVANT = 2;

    private static final Integer DEFAULT_MORT_NE = 1;
    private static final Integer UPDATED_MORT_NE = 2;

    private static final Integer DEFAULT_FAUSSE_COUCHE = 1;
    private static final Integer UPDATED_FAUSSE_COUCHE = 2;

    private static final Integer DEFAULT_AVORTEMENT = 1;
    private static final Integer UPDATED_AVORTEMENT = 2;

    private static final Integer DEFAULT_M_FIU = 1;
    private static final Integer UPDATED_M_FIU = 2;

    private static final Integer DEFAULT_NOMBRE_CESARIENNE = 1;
    private static final Integer UPDATED_NOMBRE_CESARIENNE = 2;

    private static final Integer DEFAULT_DATE_DERNIERE_CS = 1;
    private static final Integer UPDATED_DATE_DERNIERE_CS = 2;

    private static final TypeDerniereCS DEFAULT_TYPE_DERNIERE_CS = TypeDerniereCS.TRANSVERSE;
    private static final TypeDerniereCS UPDATED_TYPE_DERNIERE_CS = TypeDerniereCS.CORPOREAL;

    private static final String DEFAULT_INDICATION_DERNIERE_CS = "AAAAAAAAAA";
    private static final String UPDATED_INDICATION_DERNIERE_CS = "BBBBBBBBBB";

    private static final OuiNon DEFAULT_GROSSESSE_SUIVIS = OuiNon.OUI;
    private static final OuiNon UPDATED_GROSSESSE_SUIVIS = OuiNon.NON;

    private static final Double DEFAULT_AGE_GESTATIONNEL = 1D;
    private static final Double UPDATED_AGE_GESTATIONNEL = 2D;

    private static final Double DEFAULT_H_B = 1D;
    private static final Double UPDATED_H_B = 2D;

    private static final PositifNegatif DEFAULT_SIPHILIS = PositifNegatif.POSITIF;
    private static final PositifNegatif UPDATED_SIPHILIS = PositifNegatif.NEGATIF;

    private static final Double DEFAULT_G_AJ = 1D;
    private static final Double UPDATED_G_AJ = 2D;

    private static final GroupeSanguin DEFAULT_GROUPAGE = GroupeSanguin.OP;
    private static final GroupeSanguin UPDATED_GROUPAGE = GroupeSanguin.OM;

    private static final Double DEFAULT_T_A = 1D;
    private static final Double UPDATED_T_A = 2D;

    private static final Integer DEFAULT_B_CF = 1;
    private static final Integer UPDATED_B_CF = 2;

    private static final Integer DEFAULT_H_U = 1;
    private static final Integer UPDATED_H_U = 2;

    private static final Integer DEFAULT_ETAT_DE_COL = 1;
    private static final Integer UPDATED_ETAT_DE_COL = 2;

    private static final EtatPDE DEFAULT_ETAT_PDE = EtatPDE.INTACTE;
    private static final EtatPDE UPDATED_ETAT_PDE = EtatPDE.ROMPUE;

    private static final LiquideAmneotique DEFAULT_LIQUIDE_AMNEOTIQUE = LiquideAmneotique.CLAIR;
    private static final LiquideAmneotique UPDATED_LIQUIDE_AMNEOTIQUE = LiquideAmneotique.TEINTE;

    private static final Integer DEFAULT_NUMERO_DE_FOETUS = 1;
    private static final Integer UPDATED_NUMERO_DE_FOETUS = 2;

    private static final EtatBassin DEFAULT_ETAT_DE_BASSIN = EtatBassin.RETRECI;
    private static final EtatBassin UPDATED_ETAT_DE_BASSIN = EtatBassin.PATHOLOGIQUE;

    private static final TypeCesarienne DEFAULT_TYPE_CESARIENNE = TypeCesarienne.PROGRAMME;
    private static final TypeCesarienne UPDATED_TYPE_CESARIENNE = TypeCesarienne.URGENCE;

    private static final CesarienneEtTravail DEFAULT_CESARIENNE_ET_TRAVAIL = CesarienneEtTravail.AU_COURS;
    private static final CesarienneEtTravail UPDATED_CESARIENNE_ET_TRAVAIL = CesarienneEtTravail.EN_DEHORS;

    private static final ModeAnesthesie DEFAULT_MODE_ANESTHESIE = ModeAnesthesie.AG;
    private static final ModeAnesthesie UPDATED_MODE_ANESTHESIE = ModeAnesthesie.PERIDURALE;

    private static final TypeHysterotomie DEFAULT_TYPE_HYSTEROTOMIE = TypeHysterotomie.TRANSVERSE;
    private static final TypeHysterotomie UPDATED_TYPE_HYSTEROTOMIE = TypeHysterotomie.CORPOREAL;

    private static final String DEFAULT_INCIDENT_PER_OPERATOIRE = "AAAAAAAAAA";
    private static final String UPDATED_INCIDENT_PER_OPERATOIRE = "BBBBBBBBBB";

    private static final String DEFAULT_GESTE_ASSOCIE = "AAAAAAAAAA";
    private static final String UPDATED_GESTE_ASSOCIE = "BBBBBBBBBB";

    private static final String DEFAULT_SUITE_POSTE_OPERATOIRE = "AAAAAAAAAA";
    private static final String UPDATED_SUITE_POSTE_OPERATOIRE = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_DELIVRANCE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_DELIVRANCE = "BBBBBBBBBB";

    private static final OuiNon DEFAULT_REVISION_UTERIENE = OuiNon.OUI;
    private static final OuiNon UPDATED_REVISION_UTERIENE = OuiNon.NON;

    private static final Integer DEFAULT_DUREE_HOSPIT = 1;
    private static final Integer UPDATED_DUREE_HOSPIT = 2;

    private static final OuiNon DEFAULT_MORTALITE = OuiNon.OUI;
    private static final OuiNon UPDATED_MORTALITE = OuiNon.NON;

    private static final Integer DEFAULT_APGAR_1_MINUTE = 1;
    private static final Integer UPDATED_APGAR_1_MINUTE = 2;

    private static final Integer DEFAULT_APGAR_5_MINUTE = 1;
    private static final Integer UPDATED_APGAR_5_MINUTE = 2;

    private static final Integer DEFAULT_POIDS = 1;
    private static final Integer UPDATED_POIDS = 2;

    private static final Sexe DEFAULT_SEXE = Sexe.F;
    private static final Sexe UPDATED_SEXE = Sexe.M;

    private static final String DEFAULT_ASPECT = "AAAAAAAAAA";
    private static final String UPDATED_ASPECT = "BBBBBBBBBB";

    private static final OuiNon DEFAULT_VITAMINE_K = OuiNon.OUI;
    private static final OuiNon UPDATED_VITAMINE_K = OuiNon.NON;

    private static final OuiNon DEFAULT_H_B_1 = OuiNon.OUI;
    private static final OuiNon UPDATED_H_B_1 = OuiNon.NON;

    @Autowired
    private AccouchementRepository accouchementRepository;

    @Mock
    private AccouchementRepository accouchementRepositoryMock;

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

    private MockMvc restAccouchementMockMvc;

    private Accouchement accouchement;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AccouchementResource accouchementResource = new AccouchementResource(accouchementRepository);
        this.restAccouchementMockMvc = MockMvcBuilders.standaloneSetup(accouchementResource)
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
    public static Accouchement createEntity(EntityManager em) {
        Accouchement accouchement = new Accouchement()
            .numeroEntree(DEFAULT_NUMERO_ENTREE)
            .age(DEFAULT_AGE)
            .provenance(DEFAULT_PROVENANCE)
            .adresse(DEFAULT_ADRESSE)
            .parite(DEFAULT_PARITE)
            .gestite(DEFAULT_GESTITE)
            .enfantVivant(DEFAULT_ENFANT_VIVANT)
            .mortNe(DEFAULT_MORT_NE)
            .fausseCouche(DEFAULT_FAUSSE_COUCHE)
            .avortement(DEFAULT_AVORTEMENT)
            .mFIU(DEFAULT_M_FIU)
            .nombreCesarienne(DEFAULT_NOMBRE_CESARIENNE)
            .dateDerniereCS(DEFAULT_DATE_DERNIERE_CS)
            .typeDerniereCS(DEFAULT_TYPE_DERNIERE_CS)
            .indicationDerniereCS(DEFAULT_INDICATION_DERNIERE_CS)
            .grossesseSuivis(DEFAULT_GROSSESSE_SUIVIS)
            .ageGestationnel(DEFAULT_AGE_GESTATIONNEL)
            .hB(DEFAULT_H_B)
            .siphilis(DEFAULT_SIPHILIS)
            .gAJ(DEFAULT_G_AJ)
            .groupage(DEFAULT_GROUPAGE)
            .tA(DEFAULT_T_A)
            .bCF(DEFAULT_B_CF)
            .hU(DEFAULT_H_U)
            .etatDeCol(DEFAULT_ETAT_DE_COL)
            .etatPDE(DEFAULT_ETAT_PDE)
            .liquideAmneotique(DEFAULT_LIQUIDE_AMNEOTIQUE)
            .numeroDeFoetus(DEFAULT_NUMERO_DE_FOETUS)
            .etatDeBassin(DEFAULT_ETAT_DE_BASSIN)
            .typeCesarienne(DEFAULT_TYPE_CESARIENNE)
            .cesarienneEtTravail(DEFAULT_CESARIENNE_ET_TRAVAIL)
            .modeAnesthesie(DEFAULT_MODE_ANESTHESIE)
            .typeHysterotomie(DEFAULT_TYPE_HYSTEROTOMIE)
            .incidentPerOperatoire(DEFAULT_INCIDENT_PER_OPERATOIRE)
            .gesteAssocie(DEFAULT_GESTE_ASSOCIE)
            .suitePosteOperatoire(DEFAULT_SUITE_POSTE_OPERATOIRE)
            .typeDelivrance(DEFAULT_TYPE_DELIVRANCE)
            .revisionUteriene(DEFAULT_REVISION_UTERIENE)
            .dureeHospit(DEFAULT_DUREE_HOSPIT)
            .mortalite(DEFAULT_MORTALITE)
            .apgar1Minute(DEFAULT_APGAR_1_MINUTE)
            .apgar5Minute(DEFAULT_APGAR_5_MINUTE)
            .poids(DEFAULT_POIDS)
            .sexe(DEFAULT_SEXE)
            .aspect(DEFAULT_ASPECT)
            .vitamineK(DEFAULT_VITAMINE_K)
            .hB1(DEFAULT_H_B_1);
        return accouchement;
    }

    @Before
    public void initTest() {
        accouchement = createEntity(em);
    }

    @Test
    @Transactional
    public void createAccouchement() throws Exception {
        int databaseSizeBeforeCreate = accouchementRepository.findAll().size();

        // Create the Accouchement
        restAccouchementMockMvc.perform(post("/api/accouchements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accouchement)))
            .andExpect(status().isCreated());

        // Validate the Accouchement in the database
        List<Accouchement> accouchementList = accouchementRepository.findAll();
        assertThat(accouchementList).hasSize(databaseSizeBeforeCreate + 1);
        Accouchement testAccouchement = accouchementList.get(accouchementList.size() - 1);
        assertThat(testAccouchement.getNumeroEntree()).isEqualTo(DEFAULT_NUMERO_ENTREE);
        assertThat(testAccouchement.getAge()).isEqualTo(DEFAULT_AGE);
        assertThat(testAccouchement.getProvenance()).isEqualTo(DEFAULT_PROVENANCE);
        assertThat(testAccouchement.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testAccouchement.getParite()).isEqualTo(DEFAULT_PARITE);
        assertThat(testAccouchement.getGestite()).isEqualTo(DEFAULT_GESTITE);
        assertThat(testAccouchement.getEnfantVivant()).isEqualTo(DEFAULT_ENFANT_VIVANT);
        assertThat(testAccouchement.getMortNe()).isEqualTo(DEFAULT_MORT_NE);
        assertThat(testAccouchement.getFausseCouche()).isEqualTo(DEFAULT_FAUSSE_COUCHE);
        assertThat(testAccouchement.getAvortement()).isEqualTo(DEFAULT_AVORTEMENT);
        assertThat(testAccouchement.getmFIU()).isEqualTo(DEFAULT_M_FIU);
        assertThat(testAccouchement.getNombreCesarienne()).isEqualTo(DEFAULT_NOMBRE_CESARIENNE);
        assertThat(testAccouchement.getDateDerniereCS()).isEqualTo(DEFAULT_DATE_DERNIERE_CS);
        assertThat(testAccouchement.getTypeDerniereCS()).isEqualTo(DEFAULT_TYPE_DERNIERE_CS);
        assertThat(testAccouchement.getIndicationDerniereCS()).isEqualTo(DEFAULT_INDICATION_DERNIERE_CS);
        assertThat(testAccouchement.getGrossesseSuivis()).isEqualTo(DEFAULT_GROSSESSE_SUIVIS);
        assertThat(testAccouchement.getAgeGestationnel()).isEqualTo(DEFAULT_AGE_GESTATIONNEL);
        assertThat(testAccouchement.gethB()).isEqualTo(DEFAULT_H_B);
        assertThat(testAccouchement.getSiphilis()).isEqualTo(DEFAULT_SIPHILIS);
        assertThat(testAccouchement.getgAJ()).isEqualTo(DEFAULT_G_AJ);
        assertThat(testAccouchement.getGroupage()).isEqualTo(DEFAULT_GROUPAGE);
        assertThat(testAccouchement.gettA()).isEqualTo(DEFAULT_T_A);
        assertThat(testAccouchement.getbCF()).isEqualTo(DEFAULT_B_CF);
        assertThat(testAccouchement.gethU()).isEqualTo(DEFAULT_H_U);
        assertThat(testAccouchement.getEtatDeCol()).isEqualTo(DEFAULT_ETAT_DE_COL);
        assertThat(testAccouchement.getEtatPDE()).isEqualTo(DEFAULT_ETAT_PDE);
        assertThat(testAccouchement.getLiquideAmneotique()).isEqualTo(DEFAULT_LIQUIDE_AMNEOTIQUE);
        assertThat(testAccouchement.getNumeroDeFoetus()).isEqualTo(DEFAULT_NUMERO_DE_FOETUS);
        assertThat(testAccouchement.getEtatDeBassin()).isEqualTo(DEFAULT_ETAT_DE_BASSIN);
        assertThat(testAccouchement.getTypeCesarienne()).isEqualTo(DEFAULT_TYPE_CESARIENNE);
        assertThat(testAccouchement.getCesarienneEtTravail()).isEqualTo(DEFAULT_CESARIENNE_ET_TRAVAIL);
        assertThat(testAccouchement.getModeAnesthesie()).isEqualTo(DEFAULT_MODE_ANESTHESIE);
        assertThat(testAccouchement.getTypeHysterotomie()).isEqualTo(DEFAULT_TYPE_HYSTEROTOMIE);
        assertThat(testAccouchement.getIncidentPerOperatoire()).isEqualTo(DEFAULT_INCIDENT_PER_OPERATOIRE);
        assertThat(testAccouchement.getGesteAssocie()).isEqualTo(DEFAULT_GESTE_ASSOCIE);
        assertThat(testAccouchement.getSuitePosteOperatoire()).isEqualTo(DEFAULT_SUITE_POSTE_OPERATOIRE);
        assertThat(testAccouchement.getTypeDelivrance()).isEqualTo(DEFAULT_TYPE_DELIVRANCE);
        assertThat(testAccouchement.getRevisionUteriene()).isEqualTo(DEFAULT_REVISION_UTERIENE);
        assertThat(testAccouchement.getDureeHospit()).isEqualTo(DEFAULT_DUREE_HOSPIT);
        assertThat(testAccouchement.getMortalite()).isEqualTo(DEFAULT_MORTALITE);
        assertThat(testAccouchement.getApgar1Minute()).isEqualTo(DEFAULT_APGAR_1_MINUTE);
        assertThat(testAccouchement.getApgar5Minute()).isEqualTo(DEFAULT_APGAR_5_MINUTE);
        assertThat(testAccouchement.getPoids()).isEqualTo(DEFAULT_POIDS);
        assertThat(testAccouchement.getSexe()).isEqualTo(DEFAULT_SEXE);
        assertThat(testAccouchement.getAspect()).isEqualTo(DEFAULT_ASPECT);
        assertThat(testAccouchement.getVitamineK()).isEqualTo(DEFAULT_VITAMINE_K);
        assertThat(testAccouchement.gethB1()).isEqualTo(DEFAULT_H_B_1);
    }

    @Test
    @Transactional
    public void createAccouchementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = accouchementRepository.findAll().size();

        // Create the Accouchement with an existing ID
        accouchement.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAccouchementMockMvc.perform(post("/api/accouchements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accouchement)))
            .andExpect(status().isBadRequest());

        // Validate the Accouchement in the database
        List<Accouchement> accouchementList = accouchementRepository.findAll();
        assertThat(accouchementList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNumeroEntreeIsRequired() throws Exception {
        int databaseSizeBeforeTest = accouchementRepository.findAll().size();
        // set the field null
        accouchement.setNumeroEntree(null);

        // Create the Accouchement, which fails.

        restAccouchementMockMvc.perform(post("/api/accouchements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accouchement)))
            .andExpect(status().isBadRequest());

        List<Accouchement> accouchementList = accouchementRepository.findAll();
        assertThat(accouchementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAgeIsRequired() throws Exception {
        int databaseSizeBeforeTest = accouchementRepository.findAll().size();
        // set the field null
        accouchement.setAge(null);

        // Create the Accouchement, which fails.

        restAccouchementMockMvc.perform(post("/api/accouchements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accouchement)))
            .andExpect(status().isBadRequest());

        List<Accouchement> accouchementList = accouchementRepository.findAll();
        assertThat(accouchementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAccouchements() throws Exception {
        // Initialize the database
        accouchementRepository.saveAndFlush(accouchement);

        // Get all the accouchementList
        restAccouchementMockMvc.perform(get("/api/accouchements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(accouchement.getId().intValue())))
            .andExpect(jsonPath("$.[*].numeroEntree").value(hasItem(DEFAULT_NUMERO_ENTREE.toString())))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE)))
            .andExpect(jsonPath("$.[*].provenance").value(hasItem(DEFAULT_PROVENANCE.toString())))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE.toString())))
            .andExpect(jsonPath("$.[*].parite").value(hasItem(DEFAULT_PARITE)))
            .andExpect(jsonPath("$.[*].gestite").value(hasItem(DEFAULT_GESTITE)))
            .andExpect(jsonPath("$.[*].enfantVivant").value(hasItem(DEFAULT_ENFANT_VIVANT)))
            .andExpect(jsonPath("$.[*].mortNe").value(hasItem(DEFAULT_MORT_NE)))
            .andExpect(jsonPath("$.[*].fausseCouche").value(hasItem(DEFAULT_FAUSSE_COUCHE)))
            .andExpect(jsonPath("$.[*].avortement").value(hasItem(DEFAULT_AVORTEMENT)))
            .andExpect(jsonPath("$.[*].mFIU").value(hasItem(DEFAULT_M_FIU)))
            .andExpect(jsonPath("$.[*].nombreCesarienne").value(hasItem(DEFAULT_NOMBRE_CESARIENNE)))
            .andExpect(jsonPath("$.[*].dateDerniereCS").value(hasItem(DEFAULT_DATE_DERNIERE_CS)))
            .andExpect(jsonPath("$.[*].typeDerniereCS").value(hasItem(DEFAULT_TYPE_DERNIERE_CS.toString())))
            .andExpect(jsonPath("$.[*].indicationDerniereCS").value(hasItem(DEFAULT_INDICATION_DERNIERE_CS.toString())))
            .andExpect(jsonPath("$.[*].grossesseSuivis").value(hasItem(DEFAULT_GROSSESSE_SUIVIS.toString())))
            .andExpect(jsonPath("$.[*].ageGestationnel").value(hasItem(DEFAULT_AGE_GESTATIONNEL.doubleValue())))
            .andExpect(jsonPath("$.[*].hB").value(hasItem(DEFAULT_H_B.doubleValue())))
            .andExpect(jsonPath("$.[*].siphilis").value(hasItem(DEFAULT_SIPHILIS.toString())))
            .andExpect(jsonPath("$.[*].gAJ").value(hasItem(DEFAULT_G_AJ.doubleValue())))
            .andExpect(jsonPath("$.[*].groupage").value(hasItem(DEFAULT_GROUPAGE.toString())))
            .andExpect(jsonPath("$.[*].tA").value(hasItem(DEFAULT_T_A.doubleValue())))
            .andExpect(jsonPath("$.[*].bCF").value(hasItem(DEFAULT_B_CF)))
            .andExpect(jsonPath("$.[*].hU").value(hasItem(DEFAULT_H_U)))
            .andExpect(jsonPath("$.[*].etatDeCol").value(hasItem(DEFAULT_ETAT_DE_COL)))
            .andExpect(jsonPath("$.[*].etatPDE").value(hasItem(DEFAULT_ETAT_PDE.toString())))
            .andExpect(jsonPath("$.[*].liquideAmneotique").value(hasItem(DEFAULT_LIQUIDE_AMNEOTIQUE.toString())))
            .andExpect(jsonPath("$.[*].numeroDeFoetus").value(hasItem(DEFAULT_NUMERO_DE_FOETUS)))
            .andExpect(jsonPath("$.[*].etatDeBassin").value(hasItem(DEFAULT_ETAT_DE_BASSIN.toString())))
            .andExpect(jsonPath("$.[*].typeCesarienne").value(hasItem(DEFAULT_TYPE_CESARIENNE.toString())))
            .andExpect(jsonPath("$.[*].cesarienneEtTravail").value(hasItem(DEFAULT_CESARIENNE_ET_TRAVAIL.toString())))
            .andExpect(jsonPath("$.[*].modeAnesthesie").value(hasItem(DEFAULT_MODE_ANESTHESIE.toString())))
            .andExpect(jsonPath("$.[*].typeHysterotomie").value(hasItem(DEFAULT_TYPE_HYSTEROTOMIE.toString())))
            .andExpect(jsonPath("$.[*].incidentPerOperatoire").value(hasItem(DEFAULT_INCIDENT_PER_OPERATOIRE.toString())))
            .andExpect(jsonPath("$.[*].gesteAssocie").value(hasItem(DEFAULT_GESTE_ASSOCIE.toString())))
            .andExpect(jsonPath("$.[*].suitePosteOperatoire").value(hasItem(DEFAULT_SUITE_POSTE_OPERATOIRE.toString())))
            .andExpect(jsonPath("$.[*].typeDelivrance").value(hasItem(DEFAULT_TYPE_DELIVRANCE.toString())))
            .andExpect(jsonPath("$.[*].revisionUteriene").value(hasItem(DEFAULT_REVISION_UTERIENE.toString())))
            .andExpect(jsonPath("$.[*].dureeHospit").value(hasItem(DEFAULT_DUREE_HOSPIT)))
            .andExpect(jsonPath("$.[*].mortalite").value(hasItem(DEFAULT_MORTALITE.toString())))
            .andExpect(jsonPath("$.[*].apgar1Minute").value(hasItem(DEFAULT_APGAR_1_MINUTE)))
            .andExpect(jsonPath("$.[*].apgar5Minute").value(hasItem(DEFAULT_APGAR_5_MINUTE)))
            .andExpect(jsonPath("$.[*].poids").value(hasItem(DEFAULT_POIDS)))
            .andExpect(jsonPath("$.[*].sexe").value(hasItem(DEFAULT_SEXE.toString())))
            .andExpect(jsonPath("$.[*].aspect").value(hasItem(DEFAULT_ASPECT.toString())))
            .andExpect(jsonPath("$.[*].vitamineK").value(hasItem(DEFAULT_VITAMINE_K.toString())))
            .andExpect(jsonPath("$.[*].hB1").value(hasItem(DEFAULT_H_B_1.toString())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllAccouchementsWithEagerRelationshipsIsEnabled() throws Exception {
        AccouchementResource accouchementResource = new AccouchementResource(accouchementRepositoryMock);
        when(accouchementRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restAccouchementMockMvc = MockMvcBuilders.standaloneSetup(accouchementResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restAccouchementMockMvc.perform(get("/api/accouchements?eagerload=true"))
        .andExpect(status().isOk());

        verify(accouchementRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllAccouchementsWithEagerRelationshipsIsNotEnabled() throws Exception {
        AccouchementResource accouchementResource = new AccouchementResource(accouchementRepositoryMock);
            when(accouchementRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restAccouchementMockMvc = MockMvcBuilders.standaloneSetup(accouchementResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restAccouchementMockMvc.perform(get("/api/accouchements?eagerload=true"))
        .andExpect(status().isOk());

            verify(accouchementRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getAccouchement() throws Exception {
        // Initialize the database
        accouchementRepository.saveAndFlush(accouchement);

        // Get the accouchement
        restAccouchementMockMvc.perform(get("/api/accouchements/{id}", accouchement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(accouchement.getId().intValue()))
            .andExpect(jsonPath("$.numeroEntree").value(DEFAULT_NUMERO_ENTREE.toString()))
            .andExpect(jsonPath("$.age").value(DEFAULT_AGE))
            .andExpect(jsonPath("$.provenance").value(DEFAULT_PROVENANCE.toString()))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE.toString()))
            .andExpect(jsonPath("$.parite").value(DEFAULT_PARITE))
            .andExpect(jsonPath("$.gestite").value(DEFAULT_GESTITE))
            .andExpect(jsonPath("$.enfantVivant").value(DEFAULT_ENFANT_VIVANT))
            .andExpect(jsonPath("$.mortNe").value(DEFAULT_MORT_NE))
            .andExpect(jsonPath("$.fausseCouche").value(DEFAULT_FAUSSE_COUCHE))
            .andExpect(jsonPath("$.avortement").value(DEFAULT_AVORTEMENT))
            .andExpect(jsonPath("$.mFIU").value(DEFAULT_M_FIU))
            .andExpect(jsonPath("$.nombreCesarienne").value(DEFAULT_NOMBRE_CESARIENNE))
            .andExpect(jsonPath("$.dateDerniereCS").value(DEFAULT_DATE_DERNIERE_CS))
            .andExpect(jsonPath("$.typeDerniereCS").value(DEFAULT_TYPE_DERNIERE_CS.toString()))
            .andExpect(jsonPath("$.indicationDerniereCS").value(DEFAULT_INDICATION_DERNIERE_CS.toString()))
            .andExpect(jsonPath("$.grossesseSuivis").value(DEFAULT_GROSSESSE_SUIVIS.toString()))
            .andExpect(jsonPath("$.ageGestationnel").value(DEFAULT_AGE_GESTATIONNEL.doubleValue()))
            .andExpect(jsonPath("$.hB").value(DEFAULT_H_B.doubleValue()))
            .andExpect(jsonPath("$.siphilis").value(DEFAULT_SIPHILIS.toString()))
            .andExpect(jsonPath("$.gAJ").value(DEFAULT_G_AJ.doubleValue()))
            .andExpect(jsonPath("$.groupage").value(DEFAULT_GROUPAGE.toString()))
            .andExpect(jsonPath("$.tA").value(DEFAULT_T_A.doubleValue()))
            .andExpect(jsonPath("$.bCF").value(DEFAULT_B_CF))
            .andExpect(jsonPath("$.hU").value(DEFAULT_H_U))
            .andExpect(jsonPath("$.etatDeCol").value(DEFAULT_ETAT_DE_COL))
            .andExpect(jsonPath("$.etatPDE").value(DEFAULT_ETAT_PDE.toString()))
            .andExpect(jsonPath("$.liquideAmneotique").value(DEFAULT_LIQUIDE_AMNEOTIQUE.toString()))
            .andExpect(jsonPath("$.numeroDeFoetus").value(DEFAULT_NUMERO_DE_FOETUS))
            .andExpect(jsonPath("$.etatDeBassin").value(DEFAULT_ETAT_DE_BASSIN.toString()))
            .andExpect(jsonPath("$.typeCesarienne").value(DEFAULT_TYPE_CESARIENNE.toString()))
            .andExpect(jsonPath("$.cesarienneEtTravail").value(DEFAULT_CESARIENNE_ET_TRAVAIL.toString()))
            .andExpect(jsonPath("$.modeAnesthesie").value(DEFAULT_MODE_ANESTHESIE.toString()))
            .andExpect(jsonPath("$.typeHysterotomie").value(DEFAULT_TYPE_HYSTEROTOMIE.toString()))
            .andExpect(jsonPath("$.incidentPerOperatoire").value(DEFAULT_INCIDENT_PER_OPERATOIRE.toString()))
            .andExpect(jsonPath("$.gesteAssocie").value(DEFAULT_GESTE_ASSOCIE.toString()))
            .andExpect(jsonPath("$.suitePosteOperatoire").value(DEFAULT_SUITE_POSTE_OPERATOIRE.toString()))
            .andExpect(jsonPath("$.typeDelivrance").value(DEFAULT_TYPE_DELIVRANCE.toString()))
            .andExpect(jsonPath("$.revisionUteriene").value(DEFAULT_REVISION_UTERIENE.toString()))
            .andExpect(jsonPath("$.dureeHospit").value(DEFAULT_DUREE_HOSPIT))
            .andExpect(jsonPath("$.mortalite").value(DEFAULT_MORTALITE.toString()))
            .andExpect(jsonPath("$.apgar1Minute").value(DEFAULT_APGAR_1_MINUTE))
            .andExpect(jsonPath("$.apgar5Minute").value(DEFAULT_APGAR_5_MINUTE))
            .andExpect(jsonPath("$.poids").value(DEFAULT_POIDS))
            .andExpect(jsonPath("$.sexe").value(DEFAULT_SEXE.toString()))
            .andExpect(jsonPath("$.aspect").value(DEFAULT_ASPECT.toString()))
            .andExpect(jsonPath("$.vitamineK").value(DEFAULT_VITAMINE_K.toString()))
            .andExpect(jsonPath("$.hB1").value(DEFAULT_H_B_1.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAccouchement() throws Exception {
        // Get the accouchement
        restAccouchementMockMvc.perform(get("/api/accouchements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAccouchement() throws Exception {
        // Initialize the database
        accouchementRepository.saveAndFlush(accouchement);

        int databaseSizeBeforeUpdate = accouchementRepository.findAll().size();

        // Update the accouchement
        Accouchement updatedAccouchement = accouchementRepository.findById(accouchement.getId()).get();
        // Disconnect from session so that the updates on updatedAccouchement are not directly saved in db
        em.detach(updatedAccouchement);
        updatedAccouchement
            .numeroEntree(UPDATED_NUMERO_ENTREE)
            .age(UPDATED_AGE)
            .provenance(UPDATED_PROVENANCE)
            .adresse(UPDATED_ADRESSE)
            .parite(UPDATED_PARITE)
            .gestite(UPDATED_GESTITE)
            .enfantVivant(UPDATED_ENFANT_VIVANT)
            .mortNe(UPDATED_MORT_NE)
            .fausseCouche(UPDATED_FAUSSE_COUCHE)
            .avortement(UPDATED_AVORTEMENT)
            .mFIU(UPDATED_M_FIU)
            .nombreCesarienne(UPDATED_NOMBRE_CESARIENNE)
            .dateDerniereCS(UPDATED_DATE_DERNIERE_CS)
            .typeDerniereCS(UPDATED_TYPE_DERNIERE_CS)
            .indicationDerniereCS(UPDATED_INDICATION_DERNIERE_CS)
            .grossesseSuivis(UPDATED_GROSSESSE_SUIVIS)
            .ageGestationnel(UPDATED_AGE_GESTATIONNEL)
            .hB(UPDATED_H_B)
            .siphilis(UPDATED_SIPHILIS)
            .gAJ(UPDATED_G_AJ)
            .groupage(UPDATED_GROUPAGE)
            .tA(UPDATED_T_A)
            .bCF(UPDATED_B_CF)
            .hU(UPDATED_H_U)
            .etatDeCol(UPDATED_ETAT_DE_COL)
            .etatPDE(UPDATED_ETAT_PDE)
            .liquideAmneotique(UPDATED_LIQUIDE_AMNEOTIQUE)
            .numeroDeFoetus(UPDATED_NUMERO_DE_FOETUS)
            .etatDeBassin(UPDATED_ETAT_DE_BASSIN)
            .typeCesarienne(UPDATED_TYPE_CESARIENNE)
            .cesarienneEtTravail(UPDATED_CESARIENNE_ET_TRAVAIL)
            .modeAnesthesie(UPDATED_MODE_ANESTHESIE)
            .typeHysterotomie(UPDATED_TYPE_HYSTEROTOMIE)
            .incidentPerOperatoire(UPDATED_INCIDENT_PER_OPERATOIRE)
            .gesteAssocie(UPDATED_GESTE_ASSOCIE)
            .suitePosteOperatoire(UPDATED_SUITE_POSTE_OPERATOIRE)
            .typeDelivrance(UPDATED_TYPE_DELIVRANCE)
            .revisionUteriene(UPDATED_REVISION_UTERIENE)
            .dureeHospit(UPDATED_DUREE_HOSPIT)
            .mortalite(UPDATED_MORTALITE)
            .apgar1Minute(UPDATED_APGAR_1_MINUTE)
            .apgar5Minute(UPDATED_APGAR_5_MINUTE)
            .poids(UPDATED_POIDS)
            .sexe(UPDATED_SEXE)
            .aspect(UPDATED_ASPECT)
            .vitamineK(UPDATED_VITAMINE_K)
            .hB1(UPDATED_H_B_1);

        restAccouchementMockMvc.perform(put("/api/accouchements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAccouchement)))
            .andExpect(status().isOk());

        // Validate the Accouchement in the database
        List<Accouchement> accouchementList = accouchementRepository.findAll();
        assertThat(accouchementList).hasSize(databaseSizeBeforeUpdate);
        Accouchement testAccouchement = accouchementList.get(accouchementList.size() - 1);
        assertThat(testAccouchement.getNumeroEntree()).isEqualTo(UPDATED_NUMERO_ENTREE);
        assertThat(testAccouchement.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testAccouchement.getProvenance()).isEqualTo(UPDATED_PROVENANCE);
        assertThat(testAccouchement.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testAccouchement.getParite()).isEqualTo(UPDATED_PARITE);
        assertThat(testAccouchement.getGestite()).isEqualTo(UPDATED_GESTITE);
        assertThat(testAccouchement.getEnfantVivant()).isEqualTo(UPDATED_ENFANT_VIVANT);
        assertThat(testAccouchement.getMortNe()).isEqualTo(UPDATED_MORT_NE);
        assertThat(testAccouchement.getFausseCouche()).isEqualTo(UPDATED_FAUSSE_COUCHE);
        assertThat(testAccouchement.getAvortement()).isEqualTo(UPDATED_AVORTEMENT);
        assertThat(testAccouchement.getmFIU()).isEqualTo(UPDATED_M_FIU);
        assertThat(testAccouchement.getNombreCesarienne()).isEqualTo(UPDATED_NOMBRE_CESARIENNE);
        assertThat(testAccouchement.getDateDerniereCS()).isEqualTo(UPDATED_DATE_DERNIERE_CS);
        assertThat(testAccouchement.getTypeDerniereCS()).isEqualTo(UPDATED_TYPE_DERNIERE_CS);
        assertThat(testAccouchement.getIndicationDerniereCS()).isEqualTo(UPDATED_INDICATION_DERNIERE_CS);
        assertThat(testAccouchement.getGrossesseSuivis()).isEqualTo(UPDATED_GROSSESSE_SUIVIS);
        assertThat(testAccouchement.getAgeGestationnel()).isEqualTo(UPDATED_AGE_GESTATIONNEL);
        assertThat(testAccouchement.gethB()).isEqualTo(UPDATED_H_B);
        assertThat(testAccouchement.getSiphilis()).isEqualTo(UPDATED_SIPHILIS);
        assertThat(testAccouchement.getgAJ()).isEqualTo(UPDATED_G_AJ);
        assertThat(testAccouchement.getGroupage()).isEqualTo(UPDATED_GROUPAGE);
        assertThat(testAccouchement.gettA()).isEqualTo(UPDATED_T_A);
        assertThat(testAccouchement.getbCF()).isEqualTo(UPDATED_B_CF);
        assertThat(testAccouchement.gethU()).isEqualTo(UPDATED_H_U);
        assertThat(testAccouchement.getEtatDeCol()).isEqualTo(UPDATED_ETAT_DE_COL);
        assertThat(testAccouchement.getEtatPDE()).isEqualTo(UPDATED_ETAT_PDE);
        assertThat(testAccouchement.getLiquideAmneotique()).isEqualTo(UPDATED_LIQUIDE_AMNEOTIQUE);
        assertThat(testAccouchement.getNumeroDeFoetus()).isEqualTo(UPDATED_NUMERO_DE_FOETUS);
        assertThat(testAccouchement.getEtatDeBassin()).isEqualTo(UPDATED_ETAT_DE_BASSIN);
        assertThat(testAccouchement.getTypeCesarienne()).isEqualTo(UPDATED_TYPE_CESARIENNE);
        assertThat(testAccouchement.getCesarienneEtTravail()).isEqualTo(UPDATED_CESARIENNE_ET_TRAVAIL);
        assertThat(testAccouchement.getModeAnesthesie()).isEqualTo(UPDATED_MODE_ANESTHESIE);
        assertThat(testAccouchement.getTypeHysterotomie()).isEqualTo(UPDATED_TYPE_HYSTEROTOMIE);
        assertThat(testAccouchement.getIncidentPerOperatoire()).isEqualTo(UPDATED_INCIDENT_PER_OPERATOIRE);
        assertThat(testAccouchement.getGesteAssocie()).isEqualTo(UPDATED_GESTE_ASSOCIE);
        assertThat(testAccouchement.getSuitePosteOperatoire()).isEqualTo(UPDATED_SUITE_POSTE_OPERATOIRE);
        assertThat(testAccouchement.getTypeDelivrance()).isEqualTo(UPDATED_TYPE_DELIVRANCE);
        assertThat(testAccouchement.getRevisionUteriene()).isEqualTo(UPDATED_REVISION_UTERIENE);
        assertThat(testAccouchement.getDureeHospit()).isEqualTo(UPDATED_DUREE_HOSPIT);
        assertThat(testAccouchement.getMortalite()).isEqualTo(UPDATED_MORTALITE);
        assertThat(testAccouchement.getApgar1Minute()).isEqualTo(UPDATED_APGAR_1_MINUTE);
        assertThat(testAccouchement.getApgar5Minute()).isEqualTo(UPDATED_APGAR_5_MINUTE);
        assertThat(testAccouchement.getPoids()).isEqualTo(UPDATED_POIDS);
        assertThat(testAccouchement.getSexe()).isEqualTo(UPDATED_SEXE);
        assertThat(testAccouchement.getAspect()).isEqualTo(UPDATED_ASPECT);
        assertThat(testAccouchement.getVitamineK()).isEqualTo(UPDATED_VITAMINE_K);
        assertThat(testAccouchement.gethB1()).isEqualTo(UPDATED_H_B_1);
    }

    @Test
    @Transactional
    public void updateNonExistingAccouchement() throws Exception {
        int databaseSizeBeforeUpdate = accouchementRepository.findAll().size();

        // Create the Accouchement

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAccouchementMockMvc.perform(put("/api/accouchements")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(accouchement)))
            .andExpect(status().isBadRequest());

        // Validate the Accouchement in the database
        List<Accouchement> accouchementList = accouchementRepository.findAll();
        assertThat(accouchementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAccouchement() throws Exception {
        // Initialize the database
        accouchementRepository.saveAndFlush(accouchement);

        int databaseSizeBeforeDelete = accouchementRepository.findAll().size();

        // Delete the accouchement
        restAccouchementMockMvc.perform(delete("/api/accouchements/{id}", accouchement.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Accouchement> accouchementList = accouchementRepository.findAll();
        assertThat(accouchementList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Accouchement.class);
        Accouchement accouchement1 = new Accouchement();
        accouchement1.setId(1L);
        Accouchement accouchement2 = new Accouchement();
        accouchement2.setId(accouchement1.getId());
        assertThat(accouchement1).isEqualTo(accouchement2);
        accouchement2.setId(2L);
        assertThat(accouchement1).isNotEqualTo(accouchement2);
        accouchement1.setId(null);
        assertThat(accouchement1).isNotEqualTo(accouchement2);
    }
}
