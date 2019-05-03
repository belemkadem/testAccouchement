import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IAntecedentMedical } from 'app/shared/model/antecedent-medical.model';
import { AccountService } from 'app/core';
import { AntecedentMedicalService } from './antecedent-medical.service';

@Component({
    selector: 'jhi-antecedent-medical',
    templateUrl: './antecedent-medical.component.html'
})
export class AntecedentMedicalComponent implements OnInit, OnDestroy {
    antecedentMedicals: IAntecedentMedical[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected antecedentMedicalService: AntecedentMedicalService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.antecedentMedicalService
            .query()
            .pipe(
                filter((res: HttpResponse<IAntecedentMedical[]>) => res.ok),
                map((res: HttpResponse<IAntecedentMedical[]>) => res.body)
            )
            .subscribe(
                (res: IAntecedentMedical[]) => {
                    this.antecedentMedicals = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInAntecedentMedicals();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IAntecedentMedical) {
        return item.id;
    }

    registerChangeInAntecedentMedicals() {
        this.eventSubscriber = this.eventManager.subscribe('antecedentMedicalListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
