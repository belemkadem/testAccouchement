import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IAccouchement } from 'app/shared/model/accouchement.model';
import { AccouchementService } from './accouchement.service';
import { IAntecedentChirurgical } from 'app/shared/model/antecedent-chirurgical.model';
import { AntecedentChirurgicalService } from 'app/entities/antecedent-chirurgical';
import { IAntecedentMedical } from 'app/shared/model/antecedent-medical.model';
import { AntecedentMedicalService } from 'app/entities/antecedent-medical';
import { IAntecedentGyneco } from 'app/shared/model/antecedent-gyneco.model';
import { AntecedentGynecoService } from 'app/entities/antecedent-gyneco';
import { IAntecedentObstetrico } from 'app/shared/model/antecedent-obstetrico.model';
import { AntecedentObstetricoService } from 'app/entities/antecedent-obstetrico';
import { IIndicationCS } from 'app/shared/model/indication-cs.model';
import { IndicationCSService } from 'app/entities/indication-cs';
import { IComplicationPosteOp } from 'app/shared/model/complication-poste-op.model';
import { ComplicationPosteOpService } from 'app/entities/complication-poste-op';
import { IPathologieActuelle } from 'app/shared/model/pathologie-actuelle.model';
import { PathologieActuelleService } from 'app/entities/pathologie-actuelle';
import { IComplicationPerOp } from 'app/shared/model/complication-per-op.model';
import { ComplicationPerOpService } from 'app/entities/complication-per-op';
import { IMalformation } from 'app/shared/model/malformation.model';
import { MalformationService } from 'app/entities/malformation';
import { IComplicationNNE } from 'app/shared/model/complication-nne.model';
import { ComplicationNNEService } from 'app/entities/complication-nne';

@Component({
    selector: 'jhi-accouchement-update',
    templateUrl: './accouchement-update.component.html'
})
export class AccouchementUpdateComponent implements OnInit {
    accouchement: IAccouchement;
    isSaving: boolean;

    antecedentchirurgicals: IAntecedentChirurgical[];

    antecedentmedicals: IAntecedentMedical[];

    antecedentgynecos: IAntecedentGyneco[];

    antecedentobstetricos: IAntecedentObstetrico[];

    indicationcs: IIndicationCS[];

    complicationposteops: IComplicationPosteOp[];

    pathologieactuelles: IPathologieActuelle[];

    complicationperops: IComplicationPerOp[];

    malformations: IMalformation[];

    complicationnnes: IComplicationNNE[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected accouchementService: AccouchementService,
        protected antecedentChirurgicalService: AntecedentChirurgicalService,
        protected antecedentMedicalService: AntecedentMedicalService,
        protected antecedentGynecoService: AntecedentGynecoService,
        protected antecedentObstetricoService: AntecedentObstetricoService,
        protected indicationCSService: IndicationCSService,
        protected complicationPosteOpService: ComplicationPosteOpService,
        protected pathologieActuelleService: PathologieActuelleService,
        protected complicationPerOpService: ComplicationPerOpService,
        protected malformationService: MalformationService,
        protected complicationNNEService: ComplicationNNEService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ accouchement }) => {
            this.accouchement = accouchement;
        });
        this.antecedentChirurgicalService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IAntecedentChirurgical[]>) => mayBeOk.ok),
                map((response: HttpResponse<IAntecedentChirurgical[]>) => response.body)
            )
            .subscribe(
                (res: IAntecedentChirurgical[]) => (this.antecedentchirurgicals = res),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        this.antecedentMedicalService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IAntecedentMedical[]>) => mayBeOk.ok),
                map((response: HttpResponse<IAntecedentMedical[]>) => response.body)
            )
            .subscribe(
                (res: IAntecedentMedical[]) => (this.antecedentmedicals = res),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        this.antecedentGynecoService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IAntecedentGyneco[]>) => mayBeOk.ok),
                map((response: HttpResponse<IAntecedentGyneco[]>) => response.body)
            )
            .subscribe((res: IAntecedentGyneco[]) => (this.antecedentgynecos = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.antecedentObstetricoService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IAntecedentObstetrico[]>) => mayBeOk.ok),
                map((response: HttpResponse<IAntecedentObstetrico[]>) => response.body)
            )
            .subscribe(
                (res: IAntecedentObstetrico[]) => (this.antecedentobstetricos = res),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        this.indicationCSService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IIndicationCS[]>) => mayBeOk.ok),
                map((response: HttpResponse<IIndicationCS[]>) => response.body)
            )
            .subscribe((res: IIndicationCS[]) => (this.indicationcs = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.complicationPosteOpService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IComplicationPosteOp[]>) => mayBeOk.ok),
                map((response: HttpResponse<IComplicationPosteOp[]>) => response.body)
            )
            .subscribe(
                (res: IComplicationPosteOp[]) => (this.complicationposteops = res),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        this.pathologieActuelleService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IPathologieActuelle[]>) => mayBeOk.ok),
                map((response: HttpResponse<IPathologieActuelle[]>) => response.body)
            )
            .subscribe(
                (res: IPathologieActuelle[]) => (this.pathologieactuelles = res),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        this.complicationPerOpService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IComplicationPerOp[]>) => mayBeOk.ok),
                map((response: HttpResponse<IComplicationPerOp[]>) => response.body)
            )
            .subscribe(
                (res: IComplicationPerOp[]) => (this.complicationperops = res),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        this.malformationService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IMalformation[]>) => mayBeOk.ok),
                map((response: HttpResponse<IMalformation[]>) => response.body)
            )
            .subscribe((res: IMalformation[]) => (this.malformations = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.complicationNNEService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IComplicationNNE[]>) => mayBeOk.ok),
                map((response: HttpResponse<IComplicationNNE[]>) => response.body)
            )
            .subscribe((res: IComplicationNNE[]) => (this.complicationnnes = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.accouchement.id !== undefined) {
            this.subscribeToSaveResponse(this.accouchementService.update(this.accouchement));
        } else {
            this.subscribeToSaveResponse(this.accouchementService.create(this.accouchement));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IAccouchement>>) {
        result.subscribe((res: HttpResponse<IAccouchement>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackAntecedentChirurgicalById(index: number, item: IAntecedentChirurgical) {
        return item.id;
    }

    trackAntecedentMedicalById(index: number, item: IAntecedentMedical) {
        return item.id;
    }

    trackAntecedentGynecoById(index: number, item: IAntecedentGyneco) {
        return item.id;
    }

    trackAntecedentObstetricoById(index: number, item: IAntecedentObstetrico) {
        return item.id;
    }

    trackIndicationCSById(index: number, item: IIndicationCS) {
        return item.id;
    }

    trackComplicationPosteOpById(index: number, item: IComplicationPosteOp) {
        return item.id;
    }

    trackPathologieActuelleById(index: number, item: IPathologieActuelle) {
        return item.id;
    }

    trackComplicationPerOpById(index: number, item: IComplicationPerOp) {
        return item.id;
    }

    trackMalformationById(index: number, item: IMalformation) {
        return item.id;
    }

    trackComplicationNNEById(index: number, item: IComplicationNNE) {
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
