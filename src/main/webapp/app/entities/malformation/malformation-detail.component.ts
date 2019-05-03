import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMalformation } from 'app/shared/model/malformation.model';

@Component({
    selector: 'jhi-malformation-detail',
    templateUrl: './malformation-detail.component.html'
})
export class MalformationDetailComponent implements OnInit {
    malformation: IMalformation;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ malformation }) => {
            this.malformation = malformation;
        });
    }

    previousState() {
        window.history.back();
    }
}
