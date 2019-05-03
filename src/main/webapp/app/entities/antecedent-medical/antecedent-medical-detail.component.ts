import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAntecedentMedical } from 'app/shared/model/antecedent-medical.model';

@Component({
    selector: 'jhi-antecedent-medical-detail',
    templateUrl: './antecedent-medical-detail.component.html'
})
export class AntecedentMedicalDetailComponent implements OnInit {
    antecedentMedical: IAntecedentMedical;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ antecedentMedical }) => {
            this.antecedentMedical = antecedentMedical;
        });
    }

    previousState() {
        window.history.back();
    }
}
