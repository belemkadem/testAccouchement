import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IAntecedentObstetrico } from 'app/shared/model/antecedent-obstetrico.model';
import { AntecedentObstetricoService } from './antecedent-obstetrico.service';
import { IAccouchement } from 'app/shared/model/accouchement.model';
import { AccouchementService } from 'app/entities/accouchement';

@Component({
    selector: 'jhi-antecedent-obstetrico-update',
    templateUrl: './antecedent-obstetrico-update.component.html'
})
export class AntecedentObstetricoUpdateComponent implements OnInit {
    antecedentObstetrico: IAntecedentObstetrico;
    isSaving: boolean;

    accouchements: IAccouchement[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected antecedentObstetricoService: AntecedentObstetricoService,
        protected accouchementService: AccouchementService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ antecedentObstetrico }) => {
            this.antecedentObstetrico = antecedentObstetrico;
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
        if (this.antecedentObstetrico.id !== undefined) {
            this.subscribeToSaveResponse(this.antecedentObstetricoService.update(this.antecedentObstetrico));
        } else {
            this.subscribeToSaveResponse(this.antecedentObstetricoService.create(this.antecedentObstetrico));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IAntecedentObstetrico>>) {
        result.subscribe(
            (res: HttpResponse<IAntecedentObstetrico>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
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
