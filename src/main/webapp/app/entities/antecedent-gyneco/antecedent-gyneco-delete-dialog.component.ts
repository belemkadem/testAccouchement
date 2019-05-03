import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAntecedentGyneco } from 'app/shared/model/antecedent-gyneco.model';
import { AntecedentGynecoService } from './antecedent-gyneco.service';

@Component({
    selector: 'jhi-antecedent-gyneco-delete-dialog',
    templateUrl: './antecedent-gyneco-delete-dialog.component.html'
})
export class AntecedentGynecoDeleteDialogComponent {
    antecedentGyneco: IAntecedentGyneco;

    constructor(
        protected antecedentGynecoService: AntecedentGynecoService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.antecedentGynecoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'antecedentGynecoListModification',
                content: 'Deleted an antecedentGyneco'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-antecedent-gyneco-delete-popup',
    template: ''
})
export class AntecedentGynecoDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ antecedentGyneco }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AntecedentGynecoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.antecedentGyneco = antecedentGyneco;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/antecedent-gyneco', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/antecedent-gyneco', { outlets: { popup: null } }]);
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
