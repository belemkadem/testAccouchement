import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IAntecedentObstetrico } from 'app/shared/model/antecedent-obstetrico.model';
import { AccountService } from 'app/core';
import { AntecedentObstetricoService } from './antecedent-obstetrico.service';

@Component({
    selector: 'jhi-antecedent-obstetrico',
    templateUrl: './antecedent-obstetrico.component.html'
})
export class AntecedentObstetricoComponent implements OnInit, OnDestroy {
    antecedentObstetricos: IAntecedentObstetrico[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected antecedentObstetricoService: AntecedentObstetricoService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.antecedentObstetricoService
            .query()
            .pipe(
                filter((res: HttpResponse<IAntecedentObstetrico[]>) => res.ok),
                map((res: HttpResponse<IAntecedentObstetrico[]>) => res.body)
            )
            .subscribe(
                (res: IAntecedentObstetrico[]) => {
                    this.antecedentObstetricos = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInAntecedentObstetricos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IAntecedentObstetrico) {
        return item.id;
    }

    registerChangeInAntecedentObstetricos() {
        this.eventSubscriber = this.eventManager.subscribe('antecedentObstetricoListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
