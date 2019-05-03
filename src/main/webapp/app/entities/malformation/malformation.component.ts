import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IMalformation } from 'app/shared/model/malformation.model';
import { AccountService } from 'app/core';
import { MalformationService } from './malformation.service';

@Component({
    selector: 'jhi-malformation',
    templateUrl: './malformation.component.html'
})
export class MalformationComponent implements OnInit, OnDestroy {
    malformations: IMalformation[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected malformationService: MalformationService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.malformationService
            .query()
            .pipe(
                filter((res: HttpResponse<IMalformation[]>) => res.ok),
                map((res: HttpResponse<IMalformation[]>) => res.body)
            )
            .subscribe(
                (res: IMalformation[]) => {
                    this.malformations = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInMalformations();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IMalformation) {
        return item.id;
    }

    registerChangeInMalformations() {
        this.eventSubscriber = this.eventManager.subscribe('malformationListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
