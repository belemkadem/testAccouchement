import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IComplicationPerOp } from 'app/shared/model/complication-per-op.model';

@Component({
    selector: 'jhi-complication-per-op-detail',
    templateUrl: './complication-per-op-detail.component.html'
})
export class ComplicationPerOpDetailComponent implements OnInit {
    complicationPerOp: IComplicationPerOp;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ complicationPerOp }) => {
            this.complicationPerOp = complicationPerOp;
        });
    }

    previousState() {
        window.history.back();
    }
}
