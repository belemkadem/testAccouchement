import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAntecedentChirurgical } from 'app/shared/model/antecedent-chirurgical.model';

@Component({
    selector: 'jhi-antecedent-chirurgical-detail',
    templateUrl: './antecedent-chirurgical-detail.component.html'
})
export class AntecedentChirurgicalDetailComponent implements OnInit {
    antecedentChirurgical: IAntecedentChirurgical;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ antecedentChirurgical }) => {
            this.antecedentChirurgical = antecedentChirurgical;
        });
    }

    previousState() {
        window.history.back();
    }
}
