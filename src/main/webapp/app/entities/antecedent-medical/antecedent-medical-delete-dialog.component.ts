import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAntecedentMedical } from 'app/shared/model/antecedent-medical.model';
import { AntecedentMedicalService } from './antecedent-medical.service';

@Component({
    selector: 'jhi-antecedent-medical-delete-dialog',
    templateUrl: './antecedent-medical-delete-dialog.component.html'
})
export class AntecedentMedicalDeleteDialogComponent {
    antecedentMedical: IAntecedentMedical;

    constructor(
        protected antecedentMedicalService: AntecedentMedicalService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.antecedentMedicalService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'antecedentMedicalListModification',
                content: 'Deleted an antecedentMedical'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-antecedent-medical-delete-popup',
    template: ''
})
export class AntecedentMedicalDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ antecedentMedical }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AntecedentMedicalDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.antecedentMedical = antecedentMedical;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/antecedent-medical', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/antecedent-medical', { outlets: { popup: null } }]);
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
