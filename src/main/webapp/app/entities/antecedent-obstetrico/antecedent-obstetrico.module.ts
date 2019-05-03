import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AccouchementV3SharedModule } from 'app/shared';
import {
    AntecedentObstetricoComponent,
    AntecedentObstetricoDetailComponent,
    AntecedentObstetricoUpdateComponent,
    AntecedentObstetricoDeletePopupComponent,
    AntecedentObstetricoDeleteDialogComponent,
    antecedentObstetricoRoute,
    antecedentObstetricoPopupRoute
} from './';

const ENTITY_STATES = [...antecedentObstetricoRoute, ...antecedentObstetricoPopupRoute];

@NgModule({
    imports: [AccouchementV3SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AntecedentObstetricoComponent,
        AntecedentObstetricoDetailComponent,
        AntecedentObstetricoUpdateComponent,
        AntecedentObstetricoDeleteDialogComponent,
        AntecedentObstetricoDeletePopupComponent
    ],
    entryComponents: [
        AntecedentObstetricoComponent,
        AntecedentObstetricoUpdateComponent,
        AntecedentObstetricoDeleteDialogComponent,
        AntecedentObstetricoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AccouchementV3AntecedentObstetricoModule {}
