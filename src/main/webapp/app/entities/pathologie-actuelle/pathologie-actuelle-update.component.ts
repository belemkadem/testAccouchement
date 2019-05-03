import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IPathologieActuelle } from 'app/shared/model/pathologie-actuelle.model';
import { PathologieActuelleService } from './pathologie-actuelle.service';
import { IAccouchement } from 'app/shared/model/accouchement.model';
import { AccouchementService } from 'app/entities/accouchement';

@Component({
    selector: 'jhi-pathologie-actuelle-update',
    templateUrl: './pathologie-actuelle-update.component.html'
})
export class PathologieActuelleUpdateComponent implements OnInit {
    pathologieActuelle: IPathologieActuelle;
    isSaving: boolean;

    accouchements: IAccouchement[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected pathologieActuelleService: PathologieActuelleService,
        protected accouchementService: AccouchementService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ pathologieActuelle }) => {
            this.pathologieActuelle = pathologieActuelle;
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
        if (this.pathologieActuelle.id !== undefined) {
            this.subscribeToSaveResponse(this.pathologieActuelleService.update(this.pathologieActuelle));
        } else {
            this.subscribeToSaveResponse(this.pathologieActuelleService.create(this.pathologieActuelle));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPathologieActuelle>>) {
        result.subscribe((res: HttpResponse<IPathologieActuelle>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
