import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AccouchementV3SharedModule } from 'app/shared';
import {
    PathologieActuelleComponent,
    PathologieActuelleDetailComponent,
    PathologieActuelleUpdateComponent,
    PathologieActuelleDeletePopupComponent,
    PathologieActuelleDeleteDialogComponent,
    pathologieActuelleRoute,
    pathologieActuellePopupRoute
} from './';

const ENTITY_STATES = [...pathologieActuelleRoute, ...pathologieActuellePopupRoute];

@NgModule({
    imports: [AccouchementV3SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PathologieActuelleComponent,
        PathologieActuelleDetailComponent,
        PathologieActuelleUpdateComponent,
        PathologieActuelleDeleteDialogComponent,
        PathologieActuelleDeletePopupComponent
    ],
    entryComponents: [
        PathologieActuelleComponent,
        PathologieActuelleUpdateComponent,
        PathologieActuelleDeleteDialogComponent,
        PathologieActuelleDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AccouchementV3PathologieActuelleModule {}
