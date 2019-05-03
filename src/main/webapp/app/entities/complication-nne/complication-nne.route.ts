import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ComplicationNNE } from 'app/shared/model/complication-nne.model';
import { ComplicationNNEService } from './complication-nne.service';
import { ComplicationNNEComponent } from './complication-nne.component';
import { ComplicationNNEDetailComponent } from './complication-nne-detail.component';
import { ComplicationNNEUpdateComponent } from './complication-nne-update.component';
import { ComplicationNNEDeletePopupComponent } from './complication-nne-delete-dialog.component';
import { IComplicationNNE } from 'app/shared/model/complication-nne.model';

@Injectable({ providedIn: 'root' })
export class ComplicationNNEResolve implements Resolve<IComplicationNNE> {
    constructor(private service: ComplicationNNEService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IComplicationNNE> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ComplicationNNE>) => response.ok),
                map((complicationNNE: HttpResponse<ComplicationNNE>) => complicationNNE.body)
            );
        }
        return of(new ComplicationNNE());
    }
}

export const complicationNNERoute: Routes = [
    {
        path: '',
        component: ComplicationNNEComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ComplicationNNES'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ComplicationNNEDetailComponent,
        resolve: {
            complicationNNE: ComplicationNNEResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ComplicationNNES'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ComplicationNNEUpdateComponent,
        resolve: {
            complicationNNE: ComplicationNNEResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ComplicationNNES'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ComplicationNNEUpdateComponent,
        resolve: {
            complicationNNE: ComplicationNNEResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ComplicationNNES'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const complicationNNEPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ComplicationNNEDeletePopupComponent,
        resolve: {
            complicationNNE: ComplicationNNEResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ComplicationNNES'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
