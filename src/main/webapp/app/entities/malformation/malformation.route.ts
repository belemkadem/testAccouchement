import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Malformation } from 'app/shared/model/malformation.model';
import { MalformationService } from './malformation.service';
import { MalformationComponent } from './malformation.component';
import { MalformationDetailComponent } from './malformation-detail.component';
import { MalformationUpdateComponent } from './malformation-update.component';
import { MalformationDeletePopupComponent } from './malformation-delete-dialog.component';
import { IMalformation } from 'app/shared/model/malformation.model';

@Injectable({ providedIn: 'root' })
export class MalformationResolve implements Resolve<IMalformation> {
    constructor(private service: MalformationService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMalformation> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Malformation>) => response.ok),
                map((malformation: HttpResponse<Malformation>) => malformation.body)
            );
        }
        return of(new Malformation());
    }
}

export const malformationRoute: Routes = [
    {
        path: '',
        component: MalformationComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Malformations'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: MalformationDetailComponent,
        resolve: {
            malformation: MalformationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Malformations'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: MalformationUpdateComponent,
        resolve: {
            malformation: MalformationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Malformations'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: MalformationUpdateComponent,
        resolve: {
            malformation: MalformationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Malformations'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const malformationPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: MalformationDeletePopupComponent,
        resolve: {
            malformation: MalformationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Malformations'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
