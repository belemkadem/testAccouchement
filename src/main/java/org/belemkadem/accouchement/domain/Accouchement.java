package org.belemkadem.accouchement.domain;



import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

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

import org.belemkadem.accouchement.domain.enumeration.Sexe;

/**
 * A Accouchement.
 */
@Entity
@Table(name = "accouchement")
public class Accouchement implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "numero_entree", nullable = false, unique = true)
    private String numeroEntree;

    @NotNull
    @Column(name = "age", nullable = false)
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(name = "provenance")
    private Provenance provenance;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "parite")
    private Integer parite;

    @Column(name = "gestite")
    private Integer gestite;

    @Column(name = "enfant_vivant")
    private Integer enfantVivant;

    @Column(name = "mort_ne")
    private Integer mortNe;

    @Column(name = "fausse_couche")
    private Integer fausseCouche;

    @Column(name = "avortement")
    private Integer avortement;

    @Column(name = "m_fiu")
    private Integer mFIU;

    @Column(name = "nombre_cesarienne")
    private Integer nombreCesarienne;

    @Column(name = "date_derniere_cs")
    private Integer dateDerniereCS;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_derniere_cs")
    private TypeDerniereCS typeDerniereCS;

    @Column(name = "indication_derniere_cs")
    private String indicationDerniereCS;

    @Enumerated(EnumType.STRING)
    @Column(name = "grossesse_suivis")
    private OuiNon grossesseSuivis;

    @Column(name = "age_gestationnel")
    private Double ageGestationnel;

    @Column(name = "h_b")
    private Double hB;

    @Enumerated(EnumType.STRING)
    @Column(name = "siphilis")
    private PositifNegatif siphilis;

    @Column(name = "g_aj")
    private Double gAJ;

    @Enumerated(EnumType.STRING)
    @Column(name = "groupage")
    private GroupeSanguin groupage;

    @Column(name = "t_a")
    private Double tA;

    @Column(name = "b_cf")
    private Integer bCF;

    @Column(name = "h_u")
    private Integer hU;

    @Column(name = "etat_de_col")
    private Integer etatDeCol;

    @Enumerated(EnumType.STRING)
    @Column(name = "etat_pde")
    private EtatPDE etatPDE;

    @Enumerated(EnumType.STRING)
    @Column(name = "liquide_amneotique")
    private LiquideAmneotique liquideAmneotique;

    @Column(name = "numero_de_foetus")
    private Integer numeroDeFoetus;

    @Enumerated(EnumType.STRING)
    @Column(name = "etat_de_bassin")
    private EtatBassin etatDeBassin;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_cesarienne")
    private TypeCesarienne typeCesarienne;

    @Enumerated(EnumType.STRING)
    @Column(name = "cesarienne_et_travail")
    private CesarienneEtTravail cesarienneEtTravail;

    @Enumerated(EnumType.STRING)
    @Column(name = "mode_anesthesie")
    private ModeAnesthesie modeAnesthesie;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_hysterotomie")
    private TypeHysterotomie typeHysterotomie;

    @Column(name = "incident_per_operatoire")
    private String incidentPerOperatoire;

    @Column(name = "geste_associe")
    private String gesteAssocie;

    @Column(name = "suite_poste_operatoire")
    private String suitePosteOperatoire;

    @Column(name = "type_delivrance")
    private String typeDelivrance;

    @Enumerated(EnumType.STRING)
    @Column(name = "revision_uteriene")
    private OuiNon revisionUteriene;

    @Column(name = "duree_hospit")
    private Integer dureeHospit;

    @Enumerated(EnumType.STRING)
    @Column(name = "mortalite")
    private OuiNon mortalite;

    @Column(name = "apgar_1_minute")
    private Integer apgar1Minute;

    @Column(name = "apgar_5_minute")
    private Integer apgar5Minute;

    @Column(name = "poids")
    private Integer poids;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexe")
    private Sexe sexe;

    @Column(name = "aspect")
    private String aspect;

    @Enumerated(EnumType.STRING)
    @Column(name = "vitamine_k")
    private OuiNon vitamineK;

    @Enumerated(EnumType.STRING)
    @Column(name = "h_b_1")
    private OuiNon hB1;

    @ManyToMany
    @JoinTable(name = "accouchement_antecedent_chirurgical",
               joinColumns = @JoinColumn(name = "accouchement_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "antecedent_chirurgical_id", referencedColumnName = "id"))
    private Set<AntecedentChirurgical> antecedentChirurgicals = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "accouchement_antecedent_medical",
               joinColumns = @JoinColumn(name = "accouchement_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "antecedent_medical_id", referencedColumnName = "id"))
    private Set<AntecedentMedical> antecedentMedicals = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "accouchement_antecedent_gyneco",
               joinColumns = @JoinColumn(name = "accouchement_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "antecedent_gyneco_id", referencedColumnName = "id"))
    private Set<AntecedentGyneco> antecedentGynecos = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "accouchement_antecedent_obstetrico",
               joinColumns = @JoinColumn(name = "accouchement_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "antecedent_obstetrico_id", referencedColumnName = "id"))
    private Set<AntecedentObstetrico> antecedentObstetricos = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "accouchement_indication_decs",
               joinColumns = @JoinColumn(name = "accouchement_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "indication_decs_id", referencedColumnName = "id"))
    private Set<IndicationCS> indicationDeCS = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "accouchement_complication_poste_op",
               joinColumns = @JoinColumn(name = "accouchement_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "complication_poste_op_id", referencedColumnName = "id"))
    private Set<ComplicationPosteOp> complicationPosteOps = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "accouchement_pathologie_actuelle",
               joinColumns = @JoinColumn(name = "accouchement_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "pathologie_actuelle_id", referencedColumnName = "id"))
    private Set<PathologieActuelle> pathologieActuelles = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "accouchement_complication_per_op",
               joinColumns = @JoinColumn(name = "accouchement_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "complication_per_op_id", referencedColumnName = "id"))
    private Set<ComplicationPerOp> complicationPerOps = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "accouchement_malformation",
               joinColumns = @JoinColumn(name = "accouchement_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "malformation_id", referencedColumnName = "id"))
    private Set<Malformation> malformations = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "accouchement_complicationnne",
               joinColumns = @JoinColumn(name = "accouchement_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "complicationnne_id", referencedColumnName = "id"))
    private Set<ComplicationNNE> complicationNNES = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroEntree() {
        return numeroEntree;
    }

    public Accouchement numeroEntree(String numeroEntree) {
        this.numeroEntree = numeroEntree;
        return this;
    }

    public void setNumeroEntree(String numeroEntree) {
        this.numeroEntree = numeroEntree;
    }

    public Integer getAge() {
        return age;
    }

    public Accouchement age(Integer age) {
        this.age = age;
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Provenance getProvenance() {
        return provenance;
    }

    public Accouchement provenance(Provenance provenance) {
        this.provenance = provenance;
        return this;
    }

    public void setProvenance(Provenance provenance) {
        this.provenance = provenance;
    }

    public String getAdresse() {
        return adresse;
    }

    public Accouchement adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Integer getParite() {
        return parite;
    }

    public Accouchement parite(Integer parite) {
        this.parite = parite;
        return this;
    }

    public void setParite(Integer parite) {
        this.parite = parite;
    }

    public Integer getGestite() {
        return gestite;
    }

    public Accouchement gestite(Integer gestite) {
        this.gestite = gestite;
        return this;
    }

    public void setGestite(Integer gestite) {
        this.gestite = gestite;
    }

    public Integer getEnfantVivant() {
        return enfantVivant;
    }

    public Accouchement enfantVivant(Integer enfantVivant) {
        this.enfantVivant = enfantVivant;
        return this;
    }

    public void setEnfantVivant(Integer enfantVivant) {
        this.enfantVivant = enfantVivant;
    }

    public Integer getMortNe() {
        return mortNe;
    }

    public Accouchement mortNe(Integer mortNe) {
        this.mortNe = mortNe;
        return this;
    }

    public void setMortNe(Integer mortNe) {
        this.mortNe = mortNe;
    }

    public Integer getFausseCouche() {
        return fausseCouche;
    }

    public Accouchement fausseCouche(Integer fausseCouche) {
        this.fausseCouche = fausseCouche;
        return this;
    }

    public void setFausseCouche(Integer fausseCouche) {
        this.fausseCouche = fausseCouche;
    }

    public Integer getAvortement() {
        return avortement;
    }

    public Accouchement avortement(Integer avortement) {
        this.avortement = avortement;
        return this;
    }

    public void setAvortement(Integer avortement) {
        this.avortement = avortement;
    }

    public Integer getmFIU() {
        return mFIU;
    }

    public Accouchement mFIU(Integer mFIU) {
        this.mFIU = mFIU;
        return this;
    }

    public void setmFIU(Integer mFIU) {
        this.mFIU = mFIU;
    }

    public Integer getNombreCesarienne() {
        return nombreCesarienne;
    }

    public Accouchement nombreCesarienne(Integer nombreCesarienne) {
        this.nombreCesarienne = nombreCesarienne;
        return this;
    }

    public void setNombreCesarienne(Integer nombreCesarienne) {
        this.nombreCesarienne = nombreCesarienne;
    }

    public Integer getDateDerniereCS() {
        return dateDerniereCS;
    }

    public Accouchement dateDerniereCS(Integer dateDerniereCS) {
        this.dateDerniereCS = dateDerniereCS;
        return this;
    }

    public void setDateDerniereCS(Integer dateDerniereCS) {
        this.dateDerniereCS = dateDerniereCS;
    }

    public TypeDerniereCS getTypeDerniereCS() {
        return typeDerniereCS;
    }

    public Accouchement typeDerniereCS(TypeDerniereCS typeDerniereCS) {
        this.typeDerniereCS = typeDerniereCS;
        return this;
    }

    public void setTypeDerniereCS(TypeDerniereCS typeDerniereCS) {
        this.typeDerniereCS = typeDerniereCS;
    }

    public String getIndicationDerniereCS() {
        return indicationDerniereCS;
    }

    public Accouchement indicationDerniereCS(String indicationDerniereCS) {
        this.indicationDerniereCS = indicationDerniereCS;
        return this;
    }

    public void setIndicationDerniereCS(String indicationDerniereCS) {
        this.indicationDerniereCS = indicationDerniereCS;
    }

    public OuiNon getGrossesseSuivis() {
        return grossesseSuivis;
    }

    public Accouchement grossesseSuivis(OuiNon grossesseSuivis) {
        this.grossesseSuivis = grossesseSuivis;
        return this;
    }

    public void setGrossesseSuivis(OuiNon grossesseSuivis) {
        this.grossesseSuivis = grossesseSuivis;
    }

    public Double getAgeGestationnel() {
        return ageGestationnel;
    }

    public Accouchement ageGestationnel(Double ageGestationnel) {
        this.ageGestationnel = ageGestationnel;
        return this;
    }

    public void setAgeGestationnel(Double ageGestationnel) {
        this.ageGestationnel = ageGestationnel;
    }

    public Double gethB() {
        return hB;
    }

    public Accouchement hB(Double hB) {
        this.hB = hB;
        return this;
    }

    public void sethB(Double hB) {
        this.hB = hB;
    }

    public PositifNegatif getSiphilis() {
        return siphilis;
    }

    public Accouchement siphilis(PositifNegatif siphilis) {
        this.siphilis = siphilis;
        return this;
    }

    public void setSiphilis(PositifNegatif siphilis) {
        this.siphilis = siphilis;
    }

    public Double getgAJ() {
        return gAJ;
    }

    public Accouchement gAJ(Double gAJ) {
        this.gAJ = gAJ;
        return this;
    }

    public void setgAJ(Double gAJ) {
        this.gAJ = gAJ;
    }

    public GroupeSanguin getGroupage() {
        return groupage;
    }

    public Accouchement groupage(GroupeSanguin groupage) {
        this.groupage = groupage;
        return this;
    }

    public void setGroupage(GroupeSanguin groupage) {
        this.groupage = groupage;
    }

    public Double gettA() {
        return tA;
    }

    public Accouchement tA(Double tA) {
        this.tA = tA;
        return this;
    }

    public void settA(Double tA) {
        this.tA = tA;
    }

    public Integer getbCF() {
        return bCF;
    }

    public Accouchement bCF(Integer bCF) {
        this.bCF = bCF;
        return this;
    }

    public void setbCF(Integer bCF) {
        this.bCF = bCF;
    }

    public Integer gethU() {
        return hU;
    }

    public Accouchement hU(Integer hU) {
        this.hU = hU;
        return this;
    }

    public void sethU(Integer hU) {
        this.hU = hU;
    }

    public Integer getEtatDeCol() {
        return etatDeCol;
    }

    public Accouchement etatDeCol(Integer etatDeCol) {
        this.etatDeCol = etatDeCol;
        return this;
    }

    public void setEtatDeCol(Integer etatDeCol) {
        this.etatDeCol = etatDeCol;
    }

    public EtatPDE getEtatPDE() {
        return etatPDE;
    }

    public Accouchement etatPDE(EtatPDE etatPDE) {
        this.etatPDE = etatPDE;
        return this;
    }

    public void setEtatPDE(EtatPDE etatPDE) {
        this.etatPDE = etatPDE;
    }

    public LiquideAmneotique getLiquideAmneotique() {
        return liquideAmneotique;
    }

    public Accouchement liquideAmneotique(LiquideAmneotique liquideAmneotique) {
        this.liquideAmneotique = liquideAmneotique;
        return this;
    }

    public void setLiquideAmneotique(LiquideAmneotique liquideAmneotique) {
        this.liquideAmneotique = liquideAmneotique;
    }

    public Integer getNumeroDeFoetus() {
        return numeroDeFoetus;
    }

    public Accouchement numeroDeFoetus(Integer numeroDeFoetus) {
        this.numeroDeFoetus = numeroDeFoetus;
        return this;
    }

    public void setNumeroDeFoetus(Integer numeroDeFoetus) {
        this.numeroDeFoetus = numeroDeFoetus;
    }

    public EtatBassin getEtatDeBassin() {
        return etatDeBassin;
    }

    public Accouchement etatDeBassin(EtatBassin etatDeBassin) {
        this.etatDeBassin = etatDeBassin;
        return this;
    }

    public void setEtatDeBassin(EtatBassin etatDeBassin) {
        this.etatDeBassin = etatDeBassin;
    }

    public TypeCesarienne getTypeCesarienne() {
        return typeCesarienne;
    }

    public Accouchement typeCesarienne(TypeCesarienne typeCesarienne) {
        this.typeCesarienne = typeCesarienne;
        return this;
    }

    public void setTypeCesarienne(TypeCesarienne typeCesarienne) {
        this.typeCesarienne = typeCesarienne;
    }

    public CesarienneEtTravail getCesarienneEtTravail() {
        return cesarienneEtTravail;
    }

    public Accouchement cesarienneEtTravail(CesarienneEtTravail cesarienneEtTravail) {
        this.cesarienneEtTravail = cesarienneEtTravail;
        return this;
    }

    public void setCesarienneEtTravail(CesarienneEtTravail cesarienneEtTravail) {
        this.cesarienneEtTravail = cesarienneEtTravail;
    }

    public ModeAnesthesie getModeAnesthesie() {
        return modeAnesthesie;
    }

    public Accouchement modeAnesthesie(ModeAnesthesie modeAnesthesie) {
        this.modeAnesthesie = modeAnesthesie;
        return this;
    }

    public void setModeAnesthesie(ModeAnesthesie modeAnesthesie) {
        this.modeAnesthesie = modeAnesthesie;
    }

    public TypeHysterotomie getTypeHysterotomie() {
        return typeHysterotomie;
    }

    public Accouchement typeHysterotomie(TypeHysterotomie typeHysterotomie) {
        this.typeHysterotomie = typeHysterotomie;
        return this;
    }

    public void setTypeHysterotomie(TypeHysterotomie typeHysterotomie) {
        this.typeHysterotomie = typeHysterotomie;
    }

    public String getIncidentPerOperatoire() {
        return incidentPerOperatoire;
    }

    public Accouchement incidentPerOperatoire(String incidentPerOperatoire) {
        this.incidentPerOperatoire = incidentPerOperatoire;
        return this;
    }

    public void setIncidentPerOperatoire(String incidentPerOperatoire) {
        this.incidentPerOperatoire = incidentPerOperatoire;
    }

    public String getGesteAssocie() {
        return gesteAssocie;
    }

    public Accouchement gesteAssocie(String gesteAssocie) {
        this.gesteAssocie = gesteAssocie;
        return this;
    }

    public void setGesteAssocie(String gesteAssocie) {
        this.gesteAssocie = gesteAssocie;
    }

    public String getSuitePosteOperatoire() {
        return suitePosteOperatoire;
    }

    public Accouchement suitePosteOperatoire(String suitePosteOperatoire) {
        this.suitePosteOperatoire = suitePosteOperatoire;
        return this;
    }

    public void setSuitePosteOperatoire(String suitePosteOperatoire) {
        this.suitePosteOperatoire = suitePosteOperatoire;
    }

    public String getTypeDelivrance() {
        return typeDelivrance;
    }

    public Accouchement typeDelivrance(String typeDelivrance) {
        this.typeDelivrance = typeDelivrance;
        return this;
    }

    public void setTypeDelivrance(String typeDelivrance) {
        this.typeDelivrance = typeDelivrance;
    }

    public OuiNon getRevisionUteriene() {
        return revisionUteriene;
    }

    public Accouchement revisionUteriene(OuiNon revisionUteriene) {
        this.revisionUteriene = revisionUteriene;
        return this;
    }

    public void setRevisionUteriene(OuiNon revisionUteriene) {
        this.revisionUteriene = revisionUteriene;
    }

    public Integer getDureeHospit() {
        return dureeHospit;
    }

    public Accouchement dureeHospit(Integer dureeHospit) {
        this.dureeHospit = dureeHospit;
        return this;
    }

    public void setDureeHospit(Integer dureeHospit) {
        this.dureeHospit = dureeHospit;
    }

    public OuiNon getMortalite() {
        return mortalite;
    }

    public Accouchement mortalite(OuiNon mortalite) {
        this.mortalite = mortalite;
        return this;
    }

    public void setMortalite(OuiNon mortalite) {
        this.mortalite = mortalite;
    }

    public Integer getApgar1Minute() {
        return apgar1Minute;
    }

    public Accouchement apgar1Minute(Integer apgar1Minute) {
        this.apgar1Minute = apgar1Minute;
        return this;
    }

    public void setApgar1Minute(Integer apgar1Minute) {
        this.apgar1Minute = apgar1Minute;
    }

    public Integer getApgar5Minute() {
        return apgar5Minute;
    }

    public Accouchement apgar5Minute(Integer apgar5Minute) {
        this.apgar5Minute = apgar5Minute;
        return this;
    }

    public void setApgar5Minute(Integer apgar5Minute) {
        this.apgar5Minute = apgar5Minute;
    }

    public Integer getPoids() {
        return poids;
    }

    public Accouchement poids(Integer poids) {
        this.poids = poids;
        return this;
    }

    public void setPoids(Integer poids) {
        this.poids = poids;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public Accouchement sexe(Sexe sexe) {
        this.sexe = sexe;
        return this;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public String getAspect() {
        return aspect;
    }

    public Accouchement aspect(String aspect) {
        this.aspect = aspect;
        return this;
    }

    public void setAspect(String aspect) {
        this.aspect = aspect;
    }

    public OuiNon getVitamineK() {
        return vitamineK;
    }

    public Accouchement vitamineK(OuiNon vitamineK) {
        this.vitamineK = vitamineK;
        return this;
    }

    public void setVitamineK(OuiNon vitamineK) {
        this.vitamineK = vitamineK;
    }

    public OuiNon gethB1() {
        return hB1;
    }

    public Accouchement hB1(OuiNon hB1) {
        this.hB1 = hB1;
        return this;
    }

    public void sethB1(OuiNon hB1) {
        this.hB1 = hB1;
    }

    public Set<AntecedentChirurgical> getAntecedentChirurgicals() {
        return antecedentChirurgicals;
    }

    public Accouchement antecedentChirurgicals(Set<AntecedentChirurgical> antecedentChirurgicals) {
        this.antecedentChirurgicals = antecedentChirurgicals;
        return this;
    }

    public Accouchement addAntecedentChirurgical(AntecedentChirurgical antecedentChirurgical) {
        this.antecedentChirurgicals.add(antecedentChirurgical);
        antecedentChirurgical.getAccouchements().add(this);
        return this;
    }

    public Accouchement removeAntecedentChirurgical(AntecedentChirurgical antecedentChirurgical) {
        this.antecedentChirurgicals.remove(antecedentChirurgical);
        antecedentChirurgical.getAccouchements().remove(this);
        return this;
    }

    public void setAntecedentChirurgicals(Set<AntecedentChirurgical> antecedentChirurgicals) {
        this.antecedentChirurgicals = antecedentChirurgicals;
    }

    public Set<AntecedentMedical> getAntecedentMedicals() {
        return antecedentMedicals;
    }

    public Accouchement antecedentMedicals(Set<AntecedentMedical> antecedentMedicals) {
        this.antecedentMedicals = antecedentMedicals;
        return this;
    }

    public Accouchement addAntecedentMedical(AntecedentMedical antecedentMedical) {
        this.antecedentMedicals.add(antecedentMedical);
        antecedentMedical.getAccouchements().add(this);
        return this;
    }

    public Accouchement removeAntecedentMedical(AntecedentMedical antecedentMedical) {
        this.antecedentMedicals.remove(antecedentMedical);
        antecedentMedical.getAccouchements().remove(this);
        return this;
    }

    public void setAntecedentMedicals(Set<AntecedentMedical> antecedentMedicals) {
        this.antecedentMedicals = antecedentMedicals;
    }

    public Set<AntecedentGyneco> getAntecedentGynecos() {
        return antecedentGynecos;
    }

    public Accouchement antecedentGynecos(Set<AntecedentGyneco> antecedentGynecos) {
        this.antecedentGynecos = antecedentGynecos;
        return this;
    }

    public Accouchement addAntecedentGyneco(AntecedentGyneco antecedentGyneco) {
        this.antecedentGynecos.add(antecedentGyneco);
        antecedentGyneco.getAccouchements().add(this);
        return this;
    }

    public Accouchement removeAntecedentGyneco(AntecedentGyneco antecedentGyneco) {
        this.antecedentGynecos.remove(antecedentGyneco);
        antecedentGyneco.getAccouchements().remove(this);
        return this;
    }

    public void setAntecedentGynecos(Set<AntecedentGyneco> antecedentGynecos) {
        this.antecedentGynecos = antecedentGynecos;
    }

    public Set<AntecedentObstetrico> getAntecedentObstetricos() {
        return antecedentObstetricos;
    }

    public Accouchement antecedentObstetricos(Set<AntecedentObstetrico> antecedentObstetricos) {
        this.antecedentObstetricos = antecedentObstetricos;
        return this;
    }

    public Accouchement addAntecedentObstetrico(AntecedentObstetrico antecedentObstetrico) {
        this.antecedentObstetricos.add(antecedentObstetrico);
        antecedentObstetrico.getAccouchements().add(this);
        return this;
    }

    public Accouchement removeAntecedentObstetrico(AntecedentObstetrico antecedentObstetrico) {
        this.antecedentObstetricos.remove(antecedentObstetrico);
        antecedentObstetrico.getAccouchements().remove(this);
        return this;
    }

    public void setAntecedentObstetricos(Set<AntecedentObstetrico> antecedentObstetricos) {
        this.antecedentObstetricos = antecedentObstetricos;
    }

    public Set<IndicationCS> getIndicationDeCS() {
        return indicationDeCS;
    }

    public Accouchement indicationDeCS(Set<IndicationCS> indicationCS) {
        this.indicationDeCS = indicationCS;
        return this;
    }

    public Accouchement addIndicationDeCS(IndicationCS indicationCS) {
        this.indicationDeCS.add(indicationCS);
        indicationCS.getAccouchements().add(this);
        return this;
    }

    public Accouchement removeIndicationDeCS(IndicationCS indicationCS) {
        this.indicationDeCS.remove(indicationCS);
        indicationCS.getAccouchements().remove(this);
        return this;
    }

    public void setIndicationDeCS(Set<IndicationCS> indicationCS) {
        this.indicationDeCS = indicationCS;
    }

    public Set<ComplicationPosteOp> getComplicationPosteOps() {
        return complicationPosteOps;
    }

    public Accouchement complicationPosteOps(Set<ComplicationPosteOp> complicationPosteOps) {
        this.complicationPosteOps = complicationPosteOps;
        return this;
    }

    public Accouchement addComplicationPosteOp(ComplicationPosteOp complicationPosteOp) {
        this.complicationPosteOps.add(complicationPosteOp);
        complicationPosteOp.getAccouchements().add(this);
        return this;
    }

    public Accouchement removeComplicationPosteOp(ComplicationPosteOp complicationPosteOp) {
        this.complicationPosteOps.remove(complicationPosteOp);
        complicationPosteOp.getAccouchements().remove(this);
        return this;
    }

    public void setComplicationPosteOps(Set<ComplicationPosteOp> complicationPosteOps) {
        this.complicationPosteOps = complicationPosteOps;
    }

    public Set<PathologieActuelle> getPathologieActuelles() {
        return pathologieActuelles;
    }

    public Accouchement pathologieActuelles(Set<PathologieActuelle> pathologieActuelles) {
        this.pathologieActuelles = pathologieActuelles;
        return this;
    }

    public Accouchement addPathologieActuelle(PathologieActuelle pathologieActuelle) {
        this.pathologieActuelles.add(pathologieActuelle);
        pathologieActuelle.getAccouchements().add(this);
        return this;
    }

    public Accouchement removePathologieActuelle(PathologieActuelle pathologieActuelle) {
        this.pathologieActuelles.remove(pathologieActuelle);
        pathologieActuelle.getAccouchements().remove(this);
        return this;
    }

    public void setPathologieActuelles(Set<PathologieActuelle> pathologieActuelles) {
        this.pathologieActuelles = pathologieActuelles;
    }

    public Set<ComplicationPerOp> getComplicationPerOps() {
        return complicationPerOps;
    }

    public Accouchement complicationPerOps(Set<ComplicationPerOp> complicationPerOps) {
        this.complicationPerOps = complicationPerOps;
        return this;
    }

    public Accouchement addComplicationPerOp(ComplicationPerOp complicationPerOp) {
        this.complicationPerOps.add(complicationPerOp);
        complicationPerOp.getAccouchements().add(this);
        return this;
    }

    public Accouchement removeComplicationPerOp(ComplicationPerOp complicationPerOp) {
        this.complicationPerOps.remove(complicationPerOp);
        complicationPerOp.getAccouchements().remove(this);
        return this;
    }

    public void setComplicationPerOps(Set<ComplicationPerOp> complicationPerOps) {
        this.complicationPerOps = complicationPerOps;
    }

    public Set<Malformation> getMalformations() {
        return malformations;
    }

    public Accouchement malformations(Set<Malformation> malformations) {
        this.malformations = malformations;
        return this;
    }

    public Accouchement addMalformation(Malformation malformation) {
        this.malformations.add(malformation);
        malformation.getAccouchements().add(this);
        return this;
    }

    public Accouchement removeMalformation(Malformation malformation) {
        this.malformations.remove(malformation);
        malformation.getAccouchements().remove(this);
        return this;
    }

    public void setMalformations(Set<Malformation> malformations) {
        this.malformations = malformations;
    }

    public Set<ComplicationNNE> getComplicationNNES() {
        return complicationNNES;
    }

    public Accouchement complicationNNES(Set<ComplicationNNE> complicationNNES) {
        this.complicationNNES = complicationNNES;
        return this;
    }

    public Accouchement addComplicationNNE(ComplicationNNE complicationNNE) {
        this.complicationNNES.add(complicationNNE);
        complicationNNE.getAccouchements().add(this);
        return this;
    }

    public Accouchement removeComplicationNNE(ComplicationNNE complicationNNE) {
        this.complicationNNES.remove(complicationNNE);
        complicationNNE.getAccouchements().remove(this);
        return this;
    }

    public void setComplicationNNES(Set<ComplicationNNE> complicationNNES) {
        this.complicationNNES = complicationNNES;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Accouchement accouchement = (Accouchement) o;
        if (accouchement.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), accouchement.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Accouchement{" +
            "id=" + getId() +
            ", numeroEntree='" + getNumeroEntree() + "'" +
            ", age=" + getAge() +
            ", provenance='" + getProvenance() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", parite=" + getParite() +
            ", gestite=" + getGestite() +
            ", enfantVivant=" + getEnfantVivant() +
            ", mortNe=" + getMortNe() +
            ", fausseCouche=" + getFausseCouche() +
            ", avortement=" + getAvortement() +
            ", mFIU=" + getmFIU() +
            ", nombreCesarienne=" + getNombreCesarienne() +
            ", dateDerniereCS=" + getDateDerniereCS() +
            ", typeDerniereCS='" + getTypeDerniereCS() + "'" +
            ", indicationDerniereCS='" + getIndicationDerniereCS() + "'" +
            ", grossesseSuivis='" + getGrossesseSuivis() + "'" +
            ", ageGestationnel=" + getAgeGestationnel() +
            ", hB=" + gethB() +
            ", siphilis='" + getSiphilis() + "'" +
            ", gAJ=" + getgAJ() +
            ", groupage='" + getGroupage() + "'" +
            ", tA=" + gettA() +
            ", bCF=" + getbCF() +
            ", hU=" + gethU() +
            ", etatDeCol=" + getEtatDeCol() +
            ", etatPDE='" + getEtatPDE() + "'" +
            ", liquideAmneotique='" + getLiquideAmneotique() + "'" +
            ", numeroDeFoetus=" + getNumeroDeFoetus() +
            ", etatDeBassin='" + getEtatDeBassin() + "'" +
            ", typeCesarienne='" + getTypeCesarienne() + "'" +
            ", cesarienneEtTravail='" + getCesarienneEtTravail() + "'" +
            ", modeAnesthesie='" + getModeAnesthesie() + "'" +
            ", typeHysterotomie='" + getTypeHysterotomie() + "'" +
            ", incidentPerOperatoire='" + getIncidentPerOperatoire() + "'" +
            ", gesteAssocie='" + getGesteAssocie() + "'" +
            ", suitePosteOperatoire='" + getSuitePosteOperatoire() + "'" +
            ", typeDelivrance='" + getTypeDelivrance() + "'" +
            ", revisionUteriene='" + getRevisionUteriene() + "'" +
            ", dureeHospit=" + getDureeHospit() +
            ", mortalite='" + getMortalite() + "'" +
            ", apgar1Minute=" + getApgar1Minute() +
            ", apgar5Minute=" + getApgar5Minute() +
            ", poids=" + getPoids() +
            ", sexe='" + getSexe() + "'" +
            ", aspect='" + getAspect() + "'" +
            ", vitamineK='" + getVitamineK() + "'" +
            ", hB1='" + gethB1() + "'" +
            "}";
    }
}
