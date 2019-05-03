import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ComplicationPerOp } from 'app/shared/model/complication-per-op.model';
import { ComplicationPerOpService } from './complication-per-op.service';
import { ComplicationPerOpComponent } from './complication-per-op.component';
import { ComplicationPerOpDetailComponent } from './complication-per-op-detail.component';
import { ComplicationPerOpUpdateComponent } from './complication-per-op-update.component';
import { ComplicationPerOpDeletePopupComponent } from './complication-per-op-delete-dialog.component';
import { IComplicationPerOp } from 'app/shared/model/complication-per-op.model';

@Injectable({ providedIn: 'root' })
export class ComplicationPerOpResolve implements Resolve<IComplicationPerOp> {
    constructor(private service: ComplicationPerOpService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IComplicationPerOp> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ComplicationPerOp>) => response.ok),
                map((complicationPerOp: HttpResponse<ComplicationPerOp>) => complicationPerOp.body)
            );
        }
        return of(new ComplicationPerOp());
    }
}

export const complicationPerOpRoute: Routes = [
    {
        path: '',
        component: ComplicationPerOpComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ComplicationPerOps'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ComplicationPerOpDetailComponent,
        resolve: {
            complicationPerOp: ComplicationPerOpResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ComplicationPerOps'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ComplicationPerOpUpdateComponent,
        resolve: {
            complicationPerOp: ComplicationPerOpResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ComplicationPerOps'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ComplicationPerOpUpdateComponent,
        resolve: {
            complicationPerOp: ComplicationPerOpResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ComplicationPerOps'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const complicationPerOpPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ComplicationPerOpDeletePopupComponent,
        resolve: {
            complicationPerOp: ComplicationPerOpResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ComplicationPerOps'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
