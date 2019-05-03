import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AccouchementV3SharedModule } from 'app/shared';
import {
    MalformationComponent,
    MalformationDetailComponent,
    MalformationUpdateComponent,
    MalformationDeletePopupComponent,
    MalformationDeleteDialogComponent,
    malformationRoute,
    malformationPopupRoute
} from './';

const ENTITY_STATES = [...malformationRoute, ...malformationPopupRoute];

@NgModule({
    imports: [AccouchementV3SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MalformationComponent,
        MalformationDetailComponent,
        MalformationUpdateComponent,
        MalformationDeleteDialogComponent,
        MalformationDeletePopupComponent
    ],
    entryComponents: [
        MalformationComponent,
        MalformationUpdateComponent,
        MalformationDeleteDialogComponent,
        MalformationDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AccouchementV3MalformationModule {}
