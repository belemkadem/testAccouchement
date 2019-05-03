import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [
        RouterModule.forChild([
            {
                path: 'antecedent-medical',
                loadChildren: './antecedent-medical/antecedent-medical.module#AccouchementV3AntecedentMedicalModule'
            },
            {
                path: 'antecedent-chirurgical',
                loadChildren: './antecedent-chirurgical/antecedent-chirurgical.module#AccouchementV3AntecedentChirurgicalModule'
            },
            {
                path: 'antecedent-gyneco',
                loadChildren: './antecedent-gyneco/antecedent-gyneco.module#AccouchementV3AntecedentGynecoModule'
            },
            {
                path: 'antecedent-obstetrico',
                loadChildren: './antecedent-obstetrico/antecedent-obstetrico.module#AccouchementV3AntecedentObstetricoModule'
            },
            {
                path: 'accouchement',
                loadChildren: './accouchement/accouchement.module#AccouchementV3AccouchementModule'
            },
            {
                path: 'antecedent-medical',
                loadChildren: './antecedent-medical/antecedent-medical.module#AccouchementV3AntecedentMedicalModule'
            },
            {
                path: 'antecedent-gyneco',
                loadChildren: './antecedent-gyneco/antecedent-gyneco.module#AccouchementV3AntecedentGynecoModule'
            },
            {
                path: 'indication-cs',
                loadChildren: './indication-cs/indication-cs.module#AccouchementV3IndicationCSModule'
            },
            {
                path: 'complication-poste-op',
                loadChildren: './complication-poste-op/complication-poste-op.module#AccouchementV3ComplicationPosteOpModule'
            },
            {
                path: 'accouchement',
                loadChildren: './accouchement/accouchement.module#AccouchementV3AccouchementModule'
            },
            {
                path: 'antecedent-chirurgical',
                loadChildren: './antecedent-chirurgical/antecedent-chirurgical.module#AccouchementV3AntecedentChirurgicalModule'
            },
            {
                path: 'pathologie-actuelle',
                loadChildren: './pathologie-actuelle/pathologie-actuelle.module#AccouchementV3PathologieActuelleModule'
            },
            {
                path: 'complication-nne',
                loadChildren: './complication-nne/complication-nne.module#AccouchementV3ComplicationNNEModule'
            },
            {
                path: 'malformation',
                loadChildren: './malformation/malformation.module#AccouchementV3MalformationModule'
            },
            {
                path: 'complication-per-op',
                loadChildren: './complication-per-op/complication-per-op.module#AccouchementV3ComplicationPerOpModule'
            },
            {
                path: 'accouchement',
                loadChildren: './accouchement/accouchement.module#AccouchementV3AccouchementModule'
            },
            {
                path: 'accouchement',
                loadChildren: './accouchement/accouchement.module#AccouchementV3AccouchementModule'
            }
            /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
        ])
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AccouchementV3EntityModule {}
