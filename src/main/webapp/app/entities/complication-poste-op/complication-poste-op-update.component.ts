import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IComplicationPosteOp } from 'app/shared/model/complication-poste-op.model';
import { ComplicationPosteOpService } from './complication-poste-op.service';
import { IAccouchement } from 'app/shared/model/accouchement.model';
import { AccouchementService } from 'app/entities/accouchement';

@Component({
    selector: 'jhi-complication-poste-op-update',
    templateUrl: './complication-poste-op-update.component.html'
})
export class ComplicationPosteOpUpdateComponent implements OnInit {
    complicationPosteOp: IComplicationPosteOp;
    isSaving: boolean;

    accouchements: IAccouchement[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected complicationPosteOpService: ComplicationPosteOpService,
        protected accouchementService: AccouchementService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ complicationPosteOp }) => {
            this.complicationPosteOp = complicationPosteOp;
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
        if (this.complicationPosteOp.id !== undefined) {
            this.subscribeToSaveResponse(this.complicationPosteOpService.update(this.complicationPosteOp));
        } else {
            this.subscribeToSaveResponse(this.complicationPosteOpService.create(this.complicationPosteOp));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IComplicationPosteOp>>) {
        result.subscribe((res: HttpResponse<IComplicationPosteOp>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
