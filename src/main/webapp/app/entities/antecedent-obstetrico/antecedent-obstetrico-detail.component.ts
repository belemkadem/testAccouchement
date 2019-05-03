import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAntecedentObstetrico } from 'app/shared/model/antecedent-obstetrico.model';

@Component({
    selector: 'jhi-antecedent-obstetrico-detail',
    templateUrl: './antecedent-obstetrico-detail.component.html'
})
export class AntecedentObstetricoDetailComponent implements OnInit {
    antecedentObstetrico: IAntecedentObstetrico;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ antecedentObstetrico }) => {
            this.antecedentObstetrico = antecedentObstetrico;
        });
    }

    previousState() {
        window.history.back();
    }
}
