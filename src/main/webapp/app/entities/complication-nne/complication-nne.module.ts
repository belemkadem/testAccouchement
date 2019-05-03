import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AccouchementV3SharedModule } from 'app/shared';
import {
    ComplicationNNEComponent,
    ComplicationNNEDetailComponent,
    ComplicationNNEUpdateComponent,
    ComplicationNNEDeletePopupComponent,
    ComplicationNNEDeleteDialogComponent,
    complicationNNERoute,
    complicationNNEPopupRoute
} from './';

const ENTITY_STATES = [...complicationNNERoute, ...complicationNNEPopupRoute];

@NgModule({
    imports: [AccouchementV3SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ComplicationNNEComponent,
        ComplicationNNEDetailComponent,
        ComplicationNNEUpdateComponent,
        ComplicationNNEDeleteDialogComponent,
        ComplicationNNEDeletePopupComponent
    ],
    entryComponents: [
        ComplicationNNEComponent,
        ComplicationNNEUpdateComponent,
        ComplicationNNEDeleteDialogComponent,
        ComplicationNNEDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AccouchementV3ComplicationNNEModule {}
