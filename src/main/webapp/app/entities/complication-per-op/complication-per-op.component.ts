import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IComplicationPerOp } from 'app/shared/model/complication-per-op.model';
import { AccountService } from 'app/core';
import { ComplicationPerOpService } from './complication-per-op.service';

@Component({
    selector: 'jhi-complication-per-op',
    templateUrl: './complication-per-op.component.html'
})
export class ComplicationPerOpComponent implements OnInit, OnDestroy {
    complicationPerOps: IComplicationPerOp[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected complicationPerOpService: ComplicationPerOpService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.complicationPerOpService
            .query()
            .pipe(
                filter((res: HttpResponse<IComplicationPerOp[]>) => res.ok),
                map((res: HttpResponse<IComplicationPerOp[]>) => res.body)
            )
            .subscribe(
                (res: IComplicationPerOp[]) => {
                    this.complicationPerOps = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInComplicationPerOps();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IComplicationPerOp) {
        return item.id;
    }

    registerChangeInComplicationPerOps() {
        this.eventSubscriber = this.eventManager.subscribe('complicationPerOpListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
