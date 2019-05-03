import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IIndicationCS } from 'app/shared/model/indication-cs.model';
import { IndicationCSService } from './indication-cs.service';

@Component({
    selector: 'jhi-indication-cs-delete-dialog',
    templateUrl: './indication-cs-delete-dialog.component.html'
})
export class IndicationCSDeleteDialogComponent {
    indicationCS: IIndicationCS;

    constructor(
        protected indicationCSService: IndicationCSService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.indicationCSService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'indicationCSListModification',
                content: 'Deleted an indicationCS'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-indication-cs-delete-popup',
    template: ''
})
export class IndicationCSDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ indicationCS }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(IndicationCSDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.indicationCS = indicationCS;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/indication-cs', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/indication-cs', { outlets: { popup: null } }]);
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
