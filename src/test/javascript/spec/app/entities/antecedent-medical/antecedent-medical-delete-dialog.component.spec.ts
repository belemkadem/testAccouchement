/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AccouchementV3TestModule } from '../../../test.module';
import { AntecedentMedicalDeleteDialogComponent } from 'app/entities/antecedent-medical/antecedent-medical-delete-dialog.component';
import { AntecedentMedicalService } from 'app/entities/antecedent-medical/antecedent-medical.service';

describe('Component Tests', () => {
    describe('AntecedentMedical Management Delete Component', () => {
        let comp: AntecedentMedicalDeleteDialogComponent;
        let fixture: ComponentFixture<AntecedentMedicalDeleteDialogComponent>;
        let service: AntecedentMedicalService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [AntecedentMedicalDeleteDialogComponent]
            })
                .overrideTemplate(AntecedentMedicalDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AntecedentMedicalDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AntecedentMedicalService);
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
