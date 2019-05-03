import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AccouchementV3SharedModule } from 'app/shared';
import {
    ComplicationPerOpComponent,
    ComplicationPerOpDetailComponent,
    ComplicationPerOpUpdateComponent,
    ComplicationPerOpDeletePopupComponent,
    ComplicationPerOpDeleteDialogComponent,
    complicationPerOpRoute,
    complicationPerOpPopupRoute
} from './';

const ENTITY_STATES = [...complicationPerOpRoute, ...complicationPerOpPopupRoute];

@NgModule({
    imports: [AccouchementV3SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ComplicationPerOpComponent,
        ComplicationPerOpDetailComponent,
        ComplicationPerOpUpdateComponent,
        ComplicationPerOpDeleteDialogComponent,
        ComplicationPerOpDeletePopupComponent
    ],
    entryComponents: [
        ComplicationPerOpComponent,
        ComplicationPerOpUpdateComponent,
        ComplicationPerOpDeleteDialogComponent,
        ComplicationPerOpDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AccouchementV3ComplicationPerOpModule {}
