import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PathologieActuelle } from 'app/shared/model/pathologie-actuelle.model';
import { PathologieActuelleService } from './pathologie-actuelle.service';
import { PathologieActuelleComponent } from './pathologie-actuelle.component';
import { PathologieActuelleDetailComponent } from './pathologie-actuelle-detail.component';
import { PathologieActuelleUpdateComponent } from './pathologie-actuelle-update.component';
import { PathologieActuelleDeletePopupComponent } from './pathologie-actuelle-delete-dialog.component';
import { IPathologieActuelle } from 'app/shared/model/pathologie-actuelle.model';

@Injectable({ providedIn: 'root' })
export class PathologieActuelleResolve implements Resolve<IPathologieActuelle> {
    constructor(private service: PathologieActuelleService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPathologieActuelle> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PathologieActuelle>) => response.ok),
                map((pathologieActuelle: HttpResponse<PathologieActuelle>) => pathologieActuelle.body)
            );
        }
        return of(new PathologieActuelle());
    }
}

export const pathologieActuelleRoute: Routes = [
    {
        path: '',
        component: PathologieActuelleComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'PathologieActuelles'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: PathologieActuelleDetailComponent,
        resolve: {
            pathologieActuelle: PathologieActuelleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'PathologieActuelles'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: PathologieActuelleUpdateComponent,
        resolve: {
            pathologieActuelle: PathologieActuelleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'PathologieActuelles'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: PathologieActuelleUpdateComponent,
        resolve: {
            pathologieActuelle: PathologieActuelleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'PathologieActuelles'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const pathologieActuellePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: PathologieActuelleDeletePopupComponent,
        resolve: {
            pathologieActuelle: PathologieActuelleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'PathologieActuelles'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
