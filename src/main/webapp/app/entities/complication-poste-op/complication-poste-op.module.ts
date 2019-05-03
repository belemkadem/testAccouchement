import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AccouchementV3SharedModule } from 'app/shared';
import {
    ComplicationPosteOpComponent,
    ComplicationPosteOpDetailComponent,
    ComplicationPosteOpUpdateComponent,
    ComplicationPosteOpDeletePopupComponent,
    ComplicationPosteOpDeleteDialogComponent,
    complicationPosteOpRoute,
    complicationPosteOpPopupRoute
} from './';

const ENTITY_STATES = [...complicationPosteOpRoute, ...complicationPosteOpPopupRoute];

@NgModule({
    imports: [AccouchementV3SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ComplicationPosteOpComponent,
        ComplicationPosteOpDetailComponent,
        ComplicationPosteOpUpdateComponent,
        ComplicationPosteOpDeleteDialogComponent,
        ComplicationPosteOpDeletePopupComponent
    ],
    entryComponents: [
        ComplicationPosteOpComponent,
        ComplicationPosteOpUpdateComponent,
        ComplicationPosteOpDeleteDialogComponent,
        ComplicationPosteOpDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AccouchementV3ComplicationPosteOpModule {}
