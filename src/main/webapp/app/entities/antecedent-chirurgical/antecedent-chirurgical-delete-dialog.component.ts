import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAntecedentChirurgical } from 'app/shared/model/antecedent-chirurgical.model';
import { AntecedentChirurgicalService } from './antecedent-chirurgical.service';

@Component({
    selector: 'jhi-antecedent-chirurgical-delete-dialog',
    templateUrl: './antecedent-chirurgical-delete-dialog.component.html'
})
export class AntecedentChirurgicalDeleteDialogComponent {
    antecedentChirurgical: IAntecedentChirurgical;

    constructor(
        protected antecedentChirurgicalService: AntecedentChirurgicalService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.antecedentChirurgicalService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'antecedentChirurgicalListModification',
                content: 'Deleted an antecedentChirurgical'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-antecedent-chirurgical-delete-popup',
    template: ''
})
export class AntecedentChirurgicalDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ antecedentChirurgical }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AntecedentChirurgicalDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.antecedentChirurgical = antecedentChirurgical;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/antecedent-chirurgical', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/antecedent-chirurgical', { outlets: { popup: null } }]);
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
