/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AccouchementV3TestModule } from '../../../test.module';
import { PathologieActuelleDeleteDialogComponent } from 'app/entities/pathologie-actuelle/pathologie-actuelle-delete-dialog.component';
import { PathologieActuelleService } from 'app/entities/pathologie-actuelle/pathologie-actuelle.service';

describe('Component Tests', () => {
    describe('PathologieActuelle Management Delete Component', () => {
        let comp: PathologieActuelleDeleteDialogComponent;
        let fixture: ComponentFixture<PathologieActuelleDeleteDialogComponent>;
        let service: PathologieActuelleService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [PathologieActuelleDeleteDialogComponent]
            })
                .overrideTemplate(PathologieActuelleDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PathologieActuelleDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PathologieActuelleService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
