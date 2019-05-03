import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Accouchement } from 'app/shared/model/accouchement.model';
import { AccouchementService } from './accouchement.service';
import { AccouchementComponent } from './accouchement.component';
import { AccouchementDetailComponent } from './accouchement-detail.component';
import { AccouchementUpdateComponent } from './accouchement-update.component';
import { AccouchementDeletePopupComponent } from './accouchement-delete-dialog.component';
import { IAccouchement } from 'app/shared/model/accouchement.model';

@Injectable({ providedIn: 'root' })
export class AccouchementResolve implements Resolve<IAccouchement> {
    constructor(private service: AccouchementService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IAccouchement> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Accouchement>) => response.ok),
                map((accouchement: HttpResponse<Accouchement>) => accouchement.body)
            );
        }
        return of(new Accouchement());
    }
}

export const accouchementRoute: Routes = [
    {
        path: '',
        component: AccouchementComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Accouchements'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: AccouchementDetailComponent,
        resolve: {
            accouchement: AccouchementResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Accouchements'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: AccouchementUpdateComponent,
        resolve: {
            accouchement: AccouchementResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Accouchements'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: AccouchementUpdateComponent,
        resolve: {
            accouchement: AccouchementResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Accouchements'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const accouchementPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: AccouchementDeletePopupComponent,
        resolve: {
            accouchement: AccouchementResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Accouchements'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
