import { IAntecedentChirurgical } from 'app/shared/model/antecedent-chirurgical.model';
import { IAntecedentMedical } from 'app/shared/model/antecedent-medical.model';
import { IAntecedentGyneco } from 'app/shared/model/antecedent-gyneco.model';
import { IAntecedentObstetrico } from 'app/shared/model/antecedent-obstetrico.model';
import { IIndicationCS } from 'app/shared/model/indication-cs.model';
import { IComplicationPosteOp } from 'app/shared/model/complication-poste-op.model';
import { IPathologieActuelle } from 'app/shared/model/pathologie-actuelle.model';
import { IComplicationPerOp } from 'app/shared/model/complication-per-op.model';
import { IMalformation } from 'app/shared/model/malformation.model';
import { IComplicationNNE } from 'app/shared/model/complication-nne.model';

export const enum Provenance {
    RURAL = 'RURAL',
    URABAIN = 'URABAIN',
    NON_DEFINI = 'NON_DEFINI'
}

export const enum TypeDerniereCS {
    TRANSVERSE = 'TRANSVERSE',
    CORPOREAL = 'CORPOREAL',
    NON_DEFINI = 'NON_DEFINI'
}

export const enum OuiNon {
    OUI = 'OUI',
    NON = 'NON',
    NON_DEFINI = 'NON_DEFINI'
}

export const enum PositifNegatif {
    POSITIF = 'POSITIF',
    NEGATIF = 'NEGATIF',
    NON_DEFINI = 'NON_DEFINI'
}

export const enum GroupeSanguin {
    OP = 'OP',
    OM = 'OM',
    AM = 'AM',
    AP = 'AP',
    BM = 'BM',
    BP = 'BP',
    ABP = 'ABP',
    ABM = 'ABM',
    NON_DEFINI = 'NON_DEFINI'
}

export const enum EtatPDE {
    INTACTE = 'INTACTE',
    ROMPUE = 'ROMPUE',
    NON_DEFINI = 'NON_DEFINI'
}

export const enum LiquideAmneotique {
    CLAIR = 'CLAIR',
    TEINTE = 'TEINTE',
    PUREE_DE_POIS = 'PUREE_DE_POIS',
    MECONIALE = 'MECONIALE',
    NON_DEFINI = 'NON_DEFINI'
}

export const enum EtatBassin {
    RETRECI = 'RETRECI',
    PATHOLOGIQUE = 'PATHOLOGIQUE',
    NORMAL = 'NORMAL',
    NON_DEFINI = 'NON_DEFINI'
}

export const enum TypeCesarienne {
    PROGRAMME = 'PROGRAMME',
    URGENCE = 'URGENCE',
    NON_DEFINI = 'NON_DEFINI'
}

export const enum CesarienneEtTravail {
    AU_COURS = 'AU_COURS',
    EN_DEHORS = 'EN_DEHORS',
    NON_DEFINI = 'NON_DEFINI'
}

export const enum ModeAnesthesie {
    AG = 'AG',
    PERIDURALE = 'PERIDURALE',
    NON_DEFINI = 'NON_DEFINI'
}

export const enum TypeHysterotomie {
    TRANSVERSE = 'TRANSVERSE',
    CORPOREAL = 'CORPOREAL',
    NON_DEFINI = 'NON_DEFINI'
}

export const enum Sexe {
    F = 'F',
    M = 'M',
    NON_DEFINI = 'NON_DEFINI'
}

export interface IAccouchement {
    id?: number;
    numeroEntree?: string;
    age?: number;
    provenance?: Provenance;
    adresse?: string;
    parite?: number;
    gestite?: number;
    enfantVivant?: number;
    mortNe?: number;
    fausseCouche?: number;
    avortement?: number;
    mFIU?: number;
    nombreCesarienne?: number;
    dateDerniereCS?: number;
    typeDerniereCS?: TypeDerniereCS;
    indicationDerniereCS?: string;
    grossesseSuivis?: OuiNon;
    ageGestationnel?: number;
    hB?: number;
    siphilis?: PositifNegatif;
    gAJ?: number;
    groupage?: GroupeSanguin;
    tA?: number;
    bCF?: number;
    hU?: number;
    etatDeCol?: number;
    etatPDE?: EtatPDE;
    liquideAmneotique?: LiquideAmneotique;
    numeroDeFoetus?: number;
    etatDeBassin?: EtatBassin;
    typeCesarienne?: TypeCesarienne;
    cesarienneEtTravail?: CesarienneEtTravail;
    modeAnesthesie?: ModeAnesthesie;
    typeHysterotomie?: TypeHysterotomie;
    incidentPerOperatoire?: string;
    gesteAssocie?: string;
    suitePosteOperatoire?: string;
    typeDelivrance?: string;
    revisionUteriene?: OuiNon;
    dureeHospit?: number;
    mortalite?: OuiNon;
    apgar1Minute?: number;
    apgar5Minute?: number;
    poids?: number;
    sexe?: Sexe;
    aspect?: string;
    vitamineK?: OuiNon;
    hB1?: OuiNon;
    antecedentChirurgicals?: IAntecedentChirurgical[];
    antecedentMedicals?: IAntecedentMedical[];
    antecedentGynecos?: IAntecedentGyneco[];
    antecedentObstetricos?: IAntecedentObstetrico[];
    indicationDeCS?: IIndicationCS[];
    complicationPosteOps?: IComplicationPosteOp[];
    pathologieActuelles?: IPathologieActuelle[];
    complicationPerOps?: IComplicationPerOp[];
    malformations?: IMalformation[];
    complicationNNES?: IComplicationNNE[];
}

export class Accouchement implements IAccouchement {
    constructor(
        public id?: number,
        public numeroEntree?: string,
        public age?: number,
        public provenance?: Provenance,
        public adresse?: string,
        public parite?: number,
        public gestite?: number,
        public enfantVivant?: number,
        public mortNe?: number,
        public fausseCouche?: number,
        public avortement?: number,
        public mFIU?: number,
        public nombreCesarienne?: number,
        public dateDerniereCS?: number,
        public typeDerniereCS?: TypeDerniereCS,
        public indicationDerniereCS?: string,
        public grossesseSuivis?: OuiNon,
        public ageGestationnel?: number,
        public hB?: number,
        public siphilis?: PositifNegatif,
        public gAJ?: number,
        public groupage?: GroupeSanguin,
        public tA?: number,
        public bCF?: number,
        public hU?: number,
        public etatDeCol?: number,
        public etatPDE?: EtatPDE,
        public liquideAmneotique?: LiquideAmneotique,
        public numeroDeFoetus?: number,
        public etatDeBassin?: EtatBassin,
        public typeCesarienne?: TypeCesarienne,
        public cesarienneEtTravail?: CesarienneEtTravail,
        public modeAnesthesie?: ModeAnesthesie,
        public typeHysterotomie?: TypeHysterotomie,
        public incidentPerOperatoire?: string,
        public gesteAssocie?: string,
        public suitePosteOperatoire?: string,
        public typeDelivrance?: string,
        public revisionUteriene?: OuiNon,
        public dureeHospit?: number,
        public mortalite?: OuiNon,
        public apgar1Minute?: number,
        public apgar5Minute?: number,
        public poids?: number,
        public sexe?: Sexe,
        public aspect?: string,
        public vitamineK?: OuiNon,
        public hB1?: OuiNon,
        public antecedentChirurgicals?: IAntecedentChirurgical[],
        public antecedentMedicals?: IAntecedentMedical[],
        public antecedentGynecos?: IAntecedentGyneco[],
        public antecedentObstetricos?: IAntecedentObstetrico[],
        public indicationDeCS?: IIndicationCS[],
        public complicationPosteOps?: IComplicationPosteOp[],
        public pathologieActuelles?: IPathologieActuelle[],
        public complicationPerOps?: IComplicationPerOp[],
        public malformations?: IMalformation[],
        public complicationNNES?: IComplicationNNE[]
    ) {}
}
