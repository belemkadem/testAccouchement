import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IComplicationNNE } from 'app/shared/model/complication-nne.model';

@Component({
    selector: 'jhi-complication-nne-detail',
    templateUrl: './complication-nne-detail.component.html'
})
export class ComplicationNNEDetailComponent implements OnInit {
    complicationNNE: IComplicationNNE;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ complicationNNE }) => {
            this.complicationNNE = complicationNNE;
        });
    }

    previousState() {
        window.history.back();
    }
}
