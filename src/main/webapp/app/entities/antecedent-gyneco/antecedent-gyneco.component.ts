import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IAntecedentGyneco } from 'app/shared/model/antecedent-gyneco.model';
import { AccountService } from 'app/core';
import { AntecedentGynecoService } from './antecedent-gyneco.service';

@Component({
    selector: 'jhi-antecedent-gyneco',
    templateUrl: './antecedent-gyneco.component.html'
})
export class AntecedentGynecoComponent implements OnInit, OnDestroy {
    antecedentGynecos: IAntecedentGyneco[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected antecedentGynecoService: AntecedentGynecoService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.antecedentGynecoService
            .query()
            .pipe(
                filter((res: HttpResponse<IAntecedentGyneco[]>) => res.ok),
                map((res: HttpResponse<IAntecedentGyneco[]>) => res.body)
            )
            .subscribe(
                (res: IAntecedentGyneco[]) => {
                    this.antecedentGynecos = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInAntecedentGynecos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IAntecedentGyneco) {
        return item.id;
    }

    registerChangeInAntecedentGynecos() {
        this.eventSubscriber = this.eventManager.subscribe('antecedentGynecoListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
