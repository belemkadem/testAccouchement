import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AccouchementV3SharedModule } from 'app/shared';
import {
    AntecedentChirurgicalComponent,
    AntecedentChirurgicalDetailComponent,
    AntecedentChirurgicalUpdateComponent,
    AntecedentChirurgicalDeletePopupComponent,
    AntecedentChirurgicalDeleteDialogComponent,
    antecedentChirurgicalRoute,
    antecedentChirurgicalPopupRoute
} from './';

const ENTITY_STATES = [...antecedentChirurgicalRoute, ...antecedentChirurgicalPopupRoute];

@NgModule({
    imports: [AccouchementV3SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AntecedentChirurgicalComponent,
        AntecedentChirurgicalDetailComponent,
        AntecedentChirurgicalUpdateComponent,
        AntecedentChirurgicalDeleteDialogComponent,
        AntecedentChirurgicalDeletePopupComponent
    ],
    entryComponents: [
        AntecedentChirurgicalComponent,
        AntecedentChirurgicalUpdateComponent,
        AntecedentChirurgicalDeleteDialogComponent,
        AntecedentChirurgicalDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AccouchementV3AntecedentChirurgicalModule {}
