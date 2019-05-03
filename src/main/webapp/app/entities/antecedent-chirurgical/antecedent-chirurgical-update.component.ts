import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IAntecedentChirurgical } from 'app/shared/model/antecedent-chirurgical.model';
import { AntecedentChirurgicalService } from './antecedent-chirurgical.service';
import { IAccouchement } from 'app/shared/model/accouchement.model';
import { AccouchementService } from 'app/entities/accouchement';

@Component({
    selector: 'jhi-antecedent-chirurgical-update',
    templateUrl: './antecedent-chirurgical-update.component.html'
})
export class AntecedentChirurgicalUpdateComponent implements OnInit {
    antecedentChirurgical: IAntecedentChirurgical;
    isSaving: boolean;

    accouchements: IAccouchement[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected antecedentChirurgicalService: AntecedentChirurgicalService,
        protected accouchementService: AccouchementService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ antecedentChirurgical }) => {
            this.antecedentChirurgical = antecedentChirurgical;
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
        if (this.antecedentChirurgical.id !== undefined) {
            this.subscribeToSaveResponse(this.antecedentChirurgicalService.update(this.antecedentChirurgical));
        } else {
            this.subscribeToSaveResponse(this.antecedentChirurgicalService.create(this.antecedentChirurgical));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IAntecedentChirurgical>>) {
        result.subscribe(
            (res: HttpResponse<IAntecedentChirurgical>) => this.onSaveSuccess(),
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
