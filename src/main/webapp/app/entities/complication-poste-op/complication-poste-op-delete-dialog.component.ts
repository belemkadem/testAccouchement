import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IComplicationPosteOp } from 'app/shared/model/complication-poste-op.model';
import { ComplicationPosteOpService } from './complication-poste-op.service';

@Component({
    selector: 'jhi-complication-poste-op-delete-dialog',
    templateUrl: './complication-poste-op-delete-dialog.component.html'
})
export class ComplicationPosteOpDeleteDialogComponent {
    complicationPosteOp: IComplicationPosteOp;

    constructor(
        protected complicationPosteOpService: ComplicationPosteOpService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.complicationPosteOpService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'complicationPosteOpListModification',
                content: 'Deleted an complicationPosteOp'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-complication-poste-op-delete-popup',
    template: ''
})
export class ComplicationPosteOpDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ complicationPosteOp }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ComplicationPosteOpDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.complicationPosteOp = complicationPosteOp;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/complication-poste-op', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/complication-poste-op', { outlets: { popup: null } }]);
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
