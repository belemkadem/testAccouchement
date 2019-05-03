import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IIndicationCS } from 'app/shared/model/indication-cs.model';
import { AccountService } from 'app/core';
import { IndicationCSService } from './indication-cs.service';

@Component({
    selector: 'jhi-indication-cs',
    templateUrl: './indication-cs.component.html'
})
export class IndicationCSComponent implements OnInit, OnDestroy {
    indicationCS: IIndicationCS[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected indicationCSService: IndicationCSService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.indicationCSService
            .query()
            .pipe(
                filter((res: HttpResponse<IIndicationCS[]>) => res.ok),
                map((res: HttpResponse<IIndicationCS[]>) => res.body)
            )
            .subscribe(
                (res: IIndicationCS[]) => {
                    this.indicationCS = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInIndicationCS();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IIndicationCS) {
        return item.id;
    }

    registerChangeInIndicationCS() {
        this.eventSubscriber = this.eventManager.subscribe('indicationCSListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
