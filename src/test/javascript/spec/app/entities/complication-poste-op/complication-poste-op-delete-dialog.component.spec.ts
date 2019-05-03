/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AccouchementV3TestModule } from '../../../test.module';
import { ComplicationPosteOpDeleteDialogComponent } from 'app/entities/complication-poste-op/complication-poste-op-delete-dialog.component';
import { ComplicationPosteOpService } from 'app/entities/complication-poste-op/complication-poste-op.service';

describe('Component Tests', () => {
    describe('ComplicationPosteOp Management Delete Component', () => {
        let comp: ComplicationPosteOpDeleteDialogComponent;
        let fixture: ComponentFixture<ComplicationPosteOpDeleteDialogComponent>;
        let service: ComplicationPosteOpService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [ComplicationPosteOpDeleteDialogComponent]
            })
                .overrideTemplate(ComplicationPosteOpDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ComplicationPosteOpDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ComplicationPosteOpService);
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
