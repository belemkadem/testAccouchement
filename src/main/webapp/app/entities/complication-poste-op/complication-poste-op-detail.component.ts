import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IComplicationPosteOp } from 'app/shared/model/complication-poste-op.model';

@Component({
    selector: 'jhi-complication-poste-op-detail',
    templateUrl: './complication-poste-op-detail.component.html'
})
export class ComplicationPosteOpDetailComponent implements OnInit {
    complicationPosteOp: IComplicationPosteOp;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ complicationPosteOp }) => {
            this.complicationPosteOp = complicationPosteOp;
        });
    }

    previousState() {
        window.history.back();
    }
}
