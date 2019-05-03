import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IComplicationPerOp } from 'app/shared/model/complication-per-op.model';
import { ComplicationPerOpService } from './complication-per-op.service';

@Component({
    selector: 'jhi-complication-per-op-delete-dialog',
    templateUrl: './complication-per-op-delete-dialog.component.html'
})
export class ComplicationPerOpDeleteDialogComponent {
    complicationPerOp: IComplicationPerOp;

    constructor(
        protected complicationPerOpService: ComplicationPerOpService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.complicationPerOpService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'complicationPerOpListModification',
                content: 'Deleted an complicationPerOp'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-complication-per-op-delete-popup',
    template: ''
})
export class ComplicationPerOpDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ complicationPerOp }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ComplicationPerOpDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.complicationPerOp = complicationPerOp;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/complication-per-op', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/complication-per-op', { outlets: { popup: null } }]);
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
