import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAntecedentGyneco } from 'app/shared/model/antecedent-gyneco.model';

@Component({
    selector: 'jhi-antecedent-gyneco-detail',
    templateUrl: './antecedent-gyneco-detail.component.html'
})
export class AntecedentGynecoDetailComponent implements OnInit {
    antecedentGyneco: IAntecedentGyneco;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ antecedentGyneco }) => {
            this.antecedentGyneco = antecedentGyneco;
        });
    }

    previousState() {
        window.history.back();
    }
}
