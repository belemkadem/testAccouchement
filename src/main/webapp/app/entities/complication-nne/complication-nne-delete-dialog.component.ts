import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IComplicationNNE } from 'app/shared/model/complication-nne.model';
import { ComplicationNNEService } from './complication-nne.service';

@Component({
    selector: 'jhi-complication-nne-delete-dialog',
    templateUrl: './complication-nne-delete-dialog.component.html'
})
export class ComplicationNNEDeleteDialogComponent {
    complicationNNE: IComplicationNNE;

    constructor(
        protected complicationNNEService: ComplicationNNEService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.complicationNNEService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'complicationNNEListModification',
                content: 'Deleted an complicationNNE'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-complication-nne-delete-popup',
    template: ''
})
export class ComplicationNNEDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ complicationNNE }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ComplicationNNEDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.complicationNNE = complicationNNE;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/complication-nne', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/complication-nne', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
