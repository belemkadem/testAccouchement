import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AccouchementV3SharedModule } from 'app/shared';
import {
    AntecedentGynecoComponent,
    AntecedentGynecoDetailComponent,
    AntecedentGynecoUpdateComponent,
    AntecedentGynecoDeletePopupComponent,
    AntecedentGynecoDeleteDialogComponent,
    antecedentGynecoRoute,
    antecedentGynecoPopupRoute
} from './';

const ENTITY_STATES = [...antecedentGynecoRoute, ...antecedentGynecoPopupRoute];

@NgModule({
    imports: [AccouchementV3SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AntecedentGynecoComponent,
        AntecedentGynecoDetailComponent,
        AntecedentGynecoUpdateComponent,
        AntecedentGynecoDeleteDialogComponent,
        AntecedentGynecoDeletePopupComponent
    ],
    entryComponents: [
        AntecedentGynecoComponent,
        AntecedentGynecoUpdateComponent,
        AntecedentGynecoDeleteDialogComponent,
        AntecedentGynecoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AccouchementV3AntecedentGynecoModule {}
