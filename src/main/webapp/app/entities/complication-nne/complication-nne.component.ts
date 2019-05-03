import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IComplicationNNE } from 'app/shared/model/complication-nne.model';
import { AccountService } from 'app/core';
import { ComplicationNNEService } from './complication-nne.service';

@Component({
    selector: 'jhi-complication-nne',
    templateUrl: './complication-nne.component.html'
})
export class ComplicationNNEComponent implements OnInit, OnDestroy {
    complicationNNES: IComplicationNNE[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected complicationNNEService: ComplicationNNEService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.complicationNNEService
            .query()
            .pipe(
                filter((res: HttpResponse<IComplicationNNE[]>) => res.ok),
                map((res: HttpResponse<IComplicationNNE[]>) => res.body)
            )
            .subscribe(
                (res: IComplicationNNE[]) => {
                    this.complicationNNES = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInComplicationNNES();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IComplicationNNE) {
        return item.id;
    }

    registerChangeInComplicationNNES() {
        this.eventSubscriber = this.eventManager.subscribe('complicationNNEListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
