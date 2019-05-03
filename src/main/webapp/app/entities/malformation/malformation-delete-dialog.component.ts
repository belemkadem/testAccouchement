import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMalformation } from 'app/shared/model/malformation.model';
import { MalformationService } from './malformation.service';

@Component({
    selector: 'jhi-malformation-delete-dialog',
    templateUrl: './malformation-delete-dialog.component.html'
})
export class MalformationDeleteDialogComponent {
    malformation: IMalformation;

    constructor(
        protected malformationService: MalformationService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.malformationService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'malformationListModification',
                content: 'Deleted an malformation'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-malformation-delete-popup',
    template: ''
})
export class MalformationDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ malformation }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MalformationDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.malformation = malformation;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/malformation', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/malformation', { outlets: { popup: null } }]);
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
