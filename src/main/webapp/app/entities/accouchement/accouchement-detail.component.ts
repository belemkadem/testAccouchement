import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAccouchement } from 'app/shared/model/accouchement.model';

@Component({
    selector: 'jhi-accouchement-detail',
    templateUrl: './accouchement-detail.component.html'
})
export class AccouchementDetailComponent implements OnInit {
    accouchement: IAccouchement;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ accouchement }) => {
            this.accouchement = accouchement;
        });
    }

    previousState() {
        window.history.back();
    }
}
