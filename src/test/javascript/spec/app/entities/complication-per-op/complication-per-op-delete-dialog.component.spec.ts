/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AccouchementV3TestModule } from '../../../test.module';
import { ComplicationPerOpDeleteDialogComponent } from 'app/entities/complication-per-op/complication-per-op-delete-dialog.component';
import { ComplicationPerOpService } from 'app/entities/complication-per-op/complication-per-op.service';

describe('Component Tests', () => {
    describe('ComplicationPerOp Management Delete Component', () => {
        let comp: ComplicationPerOpDeleteDialogComponent;
        let fixture: ComponentFixture<ComplicationPerOpDeleteDialogComponent>;
        let service: ComplicationPerOpService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [ComplicationPerOpDeleteDialogComponent]
            })
                .overrideTemplate(ComplicationPerOpDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ComplicationPerOpDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ComplicationPerOpService);
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
