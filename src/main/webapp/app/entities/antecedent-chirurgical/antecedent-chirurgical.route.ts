import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AntecedentChirurgical } from 'app/shared/model/antecedent-chirurgical.model';
import { AntecedentChirurgicalService } from './antecedent-chirurgical.service';
import { AntecedentChirurgicalComponent } from './antecedent-chirurgical.component';
import { AntecedentChirurgicalDetailComponent } from './antecedent-chirurgical-detail.component';
import { AntecedentChirurgicalUpdateComponent } from './antecedent-chirurgical-update.component';
import { AntecedentChirurgicalDeletePopupComponent } from './antecedent-chirurgical-delete-dialog.component';
import { IAntecedentChirurgical } from 'app/shared/model/antecedent-chirurgical.model';

@Injectable({ providedIn: 'root' })
export class AntecedentChirurgicalResolve implements Resolve<IAntecedentChirurgical> {
    constructor(private service: AntecedentChirurgicalService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IAntecedentChirurgical> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AntecedentChirurgical>) => response.ok),
                map((antecedentChirurgical: HttpResponse<AntecedentChirurgical>) => antecedentChirurgical.body)
            );
        }
        return of(new AntecedentChirurgical());
    }
}

export const antecedentChirurgicalRoute: Routes = [
    {
        path: '',
        component: AntecedentChirurgicalComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AntecedentChirurgicals'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: AntecedentChirurgicalDetailComponent,
        resolve: {
            antecedentChirurgical: AntecedentChirurgicalResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AntecedentChirurgicals'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: AntecedentChirurgicalUpdateComponent,
        resolve: {
            antecedentChirurgical: AntecedentChirurgicalResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AntecedentChirurgicals'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: AntecedentChirurgicalUpdateComponent,
        resolve: {
            antecedentChirurgical: AntecedentChirurgicalResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AntecedentChirurgicals'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const antecedentChirurgicalPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: AntecedentChirurgicalDeletePopupComponent,
        resolve: {
            antecedentChirurgical: AntecedentChirurgicalResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AntecedentChirurgicals'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
