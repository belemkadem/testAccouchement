import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IIndicationCS } from 'app/shared/model/indication-cs.model';

@Component({
    selector: 'jhi-indication-cs-detail',
    templateUrl: './indication-cs-detail.component.html'
})
export class IndicationCSDetailComponent implements OnInit {
    indicationCS: IIndicationCS;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ indicationCS }) => {
            this.indicationCS = indicationCS;
        });
    }

    previousState() {
        window.history.back();
    }
}
