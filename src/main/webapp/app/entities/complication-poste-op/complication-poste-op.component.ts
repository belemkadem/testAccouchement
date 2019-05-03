import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IComplicationPosteOp } from 'app/shared/model/complication-poste-op.model';
import { AccountService } from 'app/core';
import { ComplicationPosteOpService } from './complication-poste-op.service';

@Component({
    selector: 'jhi-complication-poste-op',
    templateUrl: './complication-poste-op.component.html'
})
export class ComplicationPosteOpComponent implements OnInit, OnDestroy {
    complicationPosteOps: IComplicationPosteOp[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected complicationPosteOpService: ComplicationPosteOpService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.complicationPosteOpService
            .query()
            .pipe(
                filter((res: HttpResponse<IComplicationPosteOp[]>) => res.ok),
                map((res: HttpResponse<IComplicationPosteOp[]>) => res.body)
            )
            .subscribe(
                (res: IComplicationPosteOp[]) => {
                    this.complicationPosteOps = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInComplicationPosteOps();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IComplicationPosteOp) {
        return item.id;
    }

    registerChangeInComplicationPosteOps() {
        this.eventSubscriber = this.eventManager.subscribe('complicationPosteOpListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
