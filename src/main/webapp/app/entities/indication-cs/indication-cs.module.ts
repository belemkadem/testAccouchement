import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AccouchementV3SharedModule } from 'app/shared';
import {
    IndicationCSComponent,
    IndicationCSDetailComponent,
    IndicationCSUpdateComponent,
    IndicationCSDeletePopupComponent,
    IndicationCSDeleteDialogComponent,
    indicationCSRoute,
    indicationCSPopupRoute
} from './';

const ENTITY_STATES = [...indicationCSRoute, ...indicationCSPopupRoute];

@NgModule({
    imports: [AccouchementV3SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        IndicationCSComponent,
        IndicationCSDetailComponent,
        IndicationCSUpdateComponent,
        IndicationCSDeleteDialogComponent,
        IndicationCSDeletePopupComponent
    ],
    entryComponents: [
        IndicationCSComponent,
        IndicationCSUpdateComponent,
        IndicationCSDeleteDialogComponent,
        IndicationCSDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AccouchementV3IndicationCSModule {}
