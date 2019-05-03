import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPathologieActuelle } from 'app/shared/model/pathologie-actuelle.model';
import { PathologieActuelleService } from './pathologie-actuelle.service';

@Component({
    selector: 'jhi-pathologie-actuelle-delete-dialog',
    templateUrl: './pathologie-actuelle-delete-dialog.component.html'
})
export class PathologieActuelleDeleteDialogComponent {
    pathologieActuelle: IPathologieActuelle;

    constructor(
        protected pathologieActuelleService: PathologieActuelleService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.pathologieActuelleService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'pathologieActuelleListModification',
                content: 'Deleted an pathologieActuelle'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-pathologie-actuelle-delete-popup',
    template: ''
})
export class PathologieActuelleDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ pathologieActuelle }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PathologieActuelleDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.pathologieActuelle = pathologieActuelle;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/pathologie-actuelle', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/pathologie-actuelle', { outlets: { popup: null } }]);
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
