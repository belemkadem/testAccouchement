import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAccouchement } from 'app/shared/model/accouchement.model';
import { AccouchementService } from './accouchement.service';

@Component({
    selector: 'jhi-accouchement-delete-dialog',
    templateUrl: './accouchement-delete-dialog.component.html'
})
export class AccouchementDeleteDialogComponent {
    accouchement: IAccouchement;

    constructor(
        protected accouchementService: AccouchementService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.accouchementService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'accouchementListModification',
                content: 'Deleted an accouchement'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-accouchement-delete-popup',
    template: ''
})
export class AccouchementDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ accouchement }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AccouchementDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.accouchement = accouchement;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/accouchement', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/accouchement', { outlets: { popup: null } }]);
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
