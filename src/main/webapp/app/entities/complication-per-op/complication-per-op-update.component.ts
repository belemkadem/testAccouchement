import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IComplicationPerOp } from 'app/shared/model/complication-per-op.model';
import { ComplicationPerOpService } from './complication-per-op.service';
import { IAccouchement } from 'app/shared/model/accouchement.model';
import { AccouchementService } from 'app/entities/accouchement';

@Component({
    selector: 'jhi-complication-per-op-update',
    templateUrl: './complication-per-op-update.component.html'
})
export class ComplicationPerOpUpdateComponent implements OnInit {
    complicationPerOp: IComplicationPerOp;
    isSaving: boolean;

    accouchements: IAccouchement[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected complicationPerOpService: ComplicationPerOpService,
        protected accouchementService: AccouchementService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ complicationPerOp }) => {
            this.complicationPerOp = complicationPerOp;
        });
        this.accouchementService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IAccouchement[]>) => mayBeOk.ok),
                map((response: HttpResponse<IAccouchement[]>) => response.body)
            )
            .subscribe((res: IAccouchement[]) => (this.accouchements = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.complicationPerOp.id !== undefined) {
            this.subscribeToSaveResponse(this.complicationPerOpService.update(this.complicationPerOp));
        } else {
            this.subscribeToSaveResponse(this.complicationPerOpService.create(this.complicationPerOp));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IComplicationPerOp>>) {
        result.subscribe((res: HttpResponse<IComplicationPerOp>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackAccouchementById(index: number, item: IAccouchement) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}
