import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AntecedentMedical } from 'app/shared/model/antecedent-medical.model';
import { AntecedentMedicalService } from './antecedent-medical.service';
import { AntecedentMedicalComponent } from './antecedent-medical.component';
import { AntecedentMedicalDetailComponent } from './antecedent-medical-detail.component';
import { AntecedentMedicalUpdateComponent } from './antecedent-medical-update.component';
import { AntecedentMedicalDeletePopupComponent } from './antecedent-medical-delete-dialog.component';
import { IAntecedentMedical } from 'app/shared/model/antecedent-medical.model';

@Injectable({ providedIn: 'root' })
export class AntecedentMedicalResolve implements Resolve<IAntecedentMedical> {
    constructor(private service: AntecedentMedicalService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IAntecedentMedical> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AntecedentMedical>) => response.ok),
                map((antecedentMedical: HttpResponse<AntecedentMedical>) => antecedentMedical.body)
            );
        }
        return of(new AntecedentMedical());
    }
}

export const antecedentMedicalRoute: Routes = [
    {
        path: '',
        component: AntecedentMedicalComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AntecedentMedicals'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: AntecedentMedicalDetailComponent,
        resolve: {
            antecedentMedical: AntecedentMedicalResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AntecedentMedicals'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: AntecedentMedicalUpdateComponent,
        resolve: {
            antecedentMedical: AntecedentMedicalResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AntecedentMedicals'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: AntecedentMedicalUpdateComponent,
        resolve: {
            antecedentMedical: AntecedentMedicalResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AntecedentMedicals'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const antecedentMedicalPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: AntecedentMedicalDeletePopupComponent,
        resolve: {
            antecedentMedical: AntecedentMedicalResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AntecedentMedicals'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
