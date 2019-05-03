import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AntecedentObstetrico } from 'app/shared/model/antecedent-obstetrico.model';
import { AntecedentObstetricoService } from './antecedent-obstetrico.service';
import { AntecedentObstetricoComponent } from './antecedent-obstetrico.component';
import { AntecedentObstetricoDetailComponent } from './antecedent-obstetrico-detail.component';
import { AntecedentObstetricoUpdateComponent } from './antecedent-obstetrico-update.component';
import { AntecedentObstetricoDeletePopupComponent } from './antecedent-obstetrico-delete-dialog.component';
import { IAntecedentObstetrico } from 'app/shared/model/antecedent-obstetrico.model';

@Injectable({ providedIn: 'root' })
export class AntecedentObstetricoResolve implements Resolve<IAntecedentObstetrico> {
    constructor(private service: AntecedentObstetricoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IAntecedentObstetrico> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AntecedentObstetrico>) => response.ok),
                map((antecedentObstetrico: HttpResponse<AntecedentObstetrico>) => antecedentObstetrico.body)
            );
        }
        return of(new AntecedentObstetrico());
    }
}

export const antecedentObstetricoRoute: Routes = [
    {
        path: '',
        component: AntecedentObstetricoComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AntecedentObstetricos'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: AntecedentObstetricoDetailComponent,
        resolve: {
            antecedentObstetrico: AntecedentObstetricoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AntecedentObstetricos'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: AntecedentObstetricoUpdateComponent,
        resolve: {
            antecedentObstetrico: AntecedentObstetricoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AntecedentObstetricos'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: AntecedentObstetricoUpdateComponent,
        resolve: {
            antecedentObstetrico: AntecedentObstetricoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AntecedentObstetricos'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const antecedentObstetricoPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: AntecedentObstetricoDeletePopupComponent,
        resolve: {
            antecedentObstetrico: AntecedentObstetricoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AntecedentObstetricos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
