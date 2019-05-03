import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AccouchementV3SharedModule } from 'app/shared';
import {
    AccouchementComponent,
    AccouchementDetailComponent,
    AccouchementUpdateComponent,
    AccouchementDeletePopupComponent,
    AccouchementDeleteDialogComponent,
    accouchementRoute,
    accouchementPopupRoute
} from './';

const ENTITY_STATES = [...accouchementRoute, ...accouchementPopupRoute];

@NgModule({
    imports: [AccouchementV3SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AccouchementComponent,
        AccouchementDetailComponent,
        AccouchementUpdateComponent,
        AccouchementDeleteDialogComponent,
        AccouchementDeletePopupComponent
    ],
    entryComponents: [
        AccouchementComponent,
        AccouchementUpdateComponent,
        AccouchementDeleteDialogComponent,
        AccouchementDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AccouchementV3AccouchementModule {}
