/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AccouchementV3TestModule } from '../../../test.module';
import { ComplicationNNEDeleteDialogComponent } from 'app/entities/complication-nne/complication-nne-delete-dialog.component';
import { ComplicationNNEService } from 'app/entities/complication-nne/complication-nne.service';

describe('Component Tests', () => {
    describe('ComplicationNNE Management Delete Component', () => {
        let comp: ComplicationNNEDeleteDialogComponent;
        let fixture: ComponentFixture<ComplicationNNEDeleteDialogComponent>;
        let service: ComplicationNNEService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [ComplicationNNEDeleteDialogComponent]
            })
                .overrideTemplate(ComplicationNNEDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ComplicationNNEDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ComplicationNNEService);
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
