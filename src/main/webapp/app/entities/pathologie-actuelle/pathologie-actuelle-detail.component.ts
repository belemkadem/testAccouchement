import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPathologieActuelle } from 'app/shared/model/pathologie-actuelle.model';

@Component({
    selector: 'jhi-pathologie-actuelle-detail',
    templateUrl: './pathologie-actuelle-detail.component.html'
})
export class PathologieActuelleDetailComponent implements OnInit {
    pathologieActuelle: IPathologieActuelle;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ pathologieActuelle }) => {
            this.pathologieActuelle = pathologieActuelle;
        });
    }

    previousState() {
        window.history.back();
    }
}
