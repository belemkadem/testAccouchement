import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AccouchementV3SharedModule } from 'app/shared';
import {
    AntecedentMedicalComponent,
    AntecedentMedicalDetailComponent,
    AntecedentMedicalUpdateComponent,
    AntecedentMedicalDeletePopupComponent,
    AntecedentMedicalDeleteDialogComponent,
    antecedentMedicalRoute,
    antecedentMedicalPopupRoute
} from './';

const ENTITY_STATES = [...antecedentMedicalRoute, ...antecedentMedicalPopupRoute];

@NgModule({
    imports: [AccouchementV3SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AntecedentMedicalComponent,
        AntecedentMedicalDetailComponent,
        AntecedentMedicalUpdateComponent,
        AntecedentMedicalDeleteDialogComponent,
        AntecedentMedicalDeletePopupComponent
    ],
    entryComponents: [
        AntecedentMedicalComponent,
        AntecedentMedicalUpdateComponent,
        AntecedentMedicalDeleteDialogComponent,
        AntecedentMedicalDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AccouchementV3AntecedentMedicalModule {}
