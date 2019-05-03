import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAntecedentObstetrico } from 'app/shared/model/antecedent-obstetrico.model';
import { AntecedentObstetricoService } from './antecedent-obstetrico.service';

@Component({
    selector: 'jhi-antecedent-obstetrico-delete-dialog',
    templateUrl: './antecedent-obstetrico-delete-dialog.component.html'
})
export class AntecedentObstetricoDeleteDialogComponent {
    antecedentObstetrico: IAntecedentObstetrico;

    constructor(
        protected antecedentObstetricoService: AntecedentObstetricoService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.antecedentObstetricoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'antecedentObstetricoListModification',
                content: 'Deleted an antecedentObstetrico'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-antecedent-obstetrico-delete-popup',
    template: ''
})
export class AntecedentObstetricoDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ antecedentObstetrico }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AntecedentObstetricoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.antecedentObstetrico = antecedentObstetrico;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/antecedent-obstetrico', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/antecedent-obstetrico', { outlets: { popup: null } }]);
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
