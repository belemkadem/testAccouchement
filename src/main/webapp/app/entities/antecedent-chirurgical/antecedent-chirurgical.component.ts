import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IAntecedentChirurgical } from 'app/shared/model/antecedent-chirurgical.model';
import { AccountService } from 'app/core';
import { AntecedentChirurgicalService } from './antecedent-chirurgical.service';

@Component({
    selector: 'jhi-antecedent-chirurgical',
    templateUrl: './antecedent-chirurgical.component.html'
})
export class AntecedentChirurgicalComponent implements OnInit, OnDestroy {
    antecedentChirurgicals: IAntecedentChirurgical[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected antecedentChirurgicalService: AntecedentChirurgicalService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.antecedentChirurgicalService
            .query()
            .pipe(
                filter((res: HttpResponse<IAntecedentChirurgical[]>) => res.ok),
                map((res: HttpResponse<IAntecedentChirurgical[]>) => res.body)
            )
            .subscribe(
                (res: IAntecedentChirurgical[]) => {
                    this.antecedentChirurgicals = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInAntecedentChirurgicals();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IAntecedentChirurgical) {
        return item.id;
    }

    registerChangeInAntecedentChirurgicals() {
        this.eventSubscriber = this.eventManager.subscribe('antecedentChirurgicalListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
