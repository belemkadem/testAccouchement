import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AntecedentGyneco } from 'app/shared/model/antecedent-gyneco.model';
import { AntecedentGynecoService } from './antecedent-gyneco.service';
import { AntecedentGynecoComponent } from './antecedent-gyneco.component';
import { AntecedentGynecoDetailComponent } from './antecedent-gyneco-detail.component';
import { AntecedentGynecoUpdateComponent } from './antecedent-gyneco-update.component';
import { AntecedentGynecoDeletePopupComponent } from './antecedent-gyneco-delete-dialog.component';
import { IAntecedentGyneco } from 'app/shared/model/antecedent-gyneco.model';

@Injectable({ providedIn: 'root' })
export class AntecedentGynecoResolve implements Resolve<IAntecedentGyneco> {
    constructor(private service: AntecedentGynecoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IAntecedentGyneco> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AntecedentGyneco>) => response.ok),
                map((antecedentGyneco: HttpResponse<AntecedentGyneco>) => antecedentGyneco.body)
            );
        }
        return of(new AntecedentGyneco());
    }
}

export const antecedentGynecoRoute: Routes = [
    {
        path: '',
        component: AntecedentGynecoComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AntecedentGynecos'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: AntecedentGynecoDetailComponent,
        resolve: {
            antecedentGyneco: AntecedentGynecoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AntecedentGynecos'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: AntecedentGynecoUpdateComponent,
        resolve: {
            antecedentGyneco: AntecedentGynecoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AntecedentGynecos'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: AntecedentGynecoUpdateComponent,
        resolve: {
            antecedentGyneco: AntecedentGynecoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AntecedentGynecos'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const antecedentGynecoPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: AntecedentGynecoDeletePopupComponent,
        resolve: {
            antecedentGyneco: AntecedentGynecoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AntecedentGynecos'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
