import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ComplicationPosteOp } from 'app/shared/model/complication-poste-op.model';
import { ComplicationPosteOpService } from './complication-poste-op.service';
import { ComplicationPosteOpComponent } from './complication-poste-op.component';
import { ComplicationPosteOpDetailComponent } from './complication-poste-op-detail.component';
import { ComplicationPosteOpUpdateComponent } from './complication-poste-op-update.component';
import { ComplicationPosteOpDeletePopupComponent } from './complication-poste-op-delete-dialog.component';
import { IComplicationPosteOp } from 'app/shared/model/complication-poste-op.model';

@Injectable({ providedIn: 'root' })
export class ComplicationPosteOpResolve implements Resolve<IComplicationPosteOp> {
    constructor(private service: ComplicationPosteOpService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IComplicationPosteOp> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ComplicationPosteOp>) => response.ok),
                map((complicationPosteOp: HttpResponse<ComplicationPosteOp>) => complicationPosteOp.body)
            );
        }
        return of(new ComplicationPosteOp());
    }
}

export const complicationPosteOpRoute: Routes = [
    {
        path: '',
        component: ComplicationPosteOpComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ComplicationPosteOps'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ComplicationPosteOpDetailComponent,
        resolve: {
            complicationPosteOp: ComplicationPosteOpResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ComplicationPosteOps'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ComplicationPosteOpUpdateComponent,
        resolve: {
            complicationPosteOp: ComplicationPosteOpResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ComplicationPosteOps'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ComplicationPosteOpUpdateComponent,
        resolve: {
            complicationPosteOp: ComplicationPosteOpResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ComplicationPosteOps'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const complicationPosteOpPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ComplicationPosteOpDeletePopupComponent,
        resolve: {
            complicationPosteOp: ComplicationPosteOpResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ComplicationPosteOps'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
