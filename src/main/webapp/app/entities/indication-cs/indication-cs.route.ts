import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IndicationCS } from 'app/shared/model/indication-cs.model';
import { IndicationCSService } from './indication-cs.service';
import { IndicationCSComponent } from './indication-cs.component';
import { IndicationCSDetailComponent } from './indication-cs-detail.component';
import { IndicationCSUpdateComponent } from './indication-cs-update.component';
import { IndicationCSDeletePopupComponent } from './indication-cs-delete-dialog.component';
import { IIndicationCS } from 'app/shared/model/indication-cs.model';

@Injectable({ providedIn: 'root' })
export class IndicationCSResolve implements Resolve<IIndicationCS> {
    constructor(private service: IndicationCSService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IIndicationCS> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<IndicationCS>) => response.ok),
                map((indicationCS: HttpResponse<IndicationCS>) => indicationCS.body)
            );
        }
        return of(new IndicationCS());
    }
}

export const indicationCSRoute: Routes = [
    {
        path: '',
        component: IndicationCSComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'IndicationCS'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: IndicationCSDetailComponent,
        resolve: {
            indicationCS: IndicationCSResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'IndicationCS'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: IndicationCSUpdateComponent,
        resolve: {
            indicationCS: IndicationCSResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'IndicationCS'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: IndicationCSUpdateComponent,
        resolve: {
            indicationCS: IndicationCSResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'IndicationCS'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const indicationCSPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: IndicationCSDeletePopupComponent,
        resolve: {
            indicationCS: IndicationCSResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'IndicationCS'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
