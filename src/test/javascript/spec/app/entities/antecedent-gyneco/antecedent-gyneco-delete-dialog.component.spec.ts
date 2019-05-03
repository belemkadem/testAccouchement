/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AccouchementV3TestModule } from '../../../test.module';
import { AntecedentGynecoDeleteDialogComponent } from 'app/entities/antecedent-gyneco/antecedent-gyneco-delete-dialog.component';
import { AntecedentGynecoService } from 'app/entities/antecedent-gyneco/antecedent-gyneco.service';

describe('Component Tests', () => {
    describe('AntecedentGyneco Management Delete Component', () => {
        let comp: AntecedentGynecoDeleteDialogComponent;
        let fixture: ComponentFixture<AntecedentGynecoDeleteDialogComponent>;
        let service: AntecedentGynecoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [AntecedentGynecoDeleteDialogComponent]
            })
                .overrideTemplate(AntecedentGynecoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AntecedentGynecoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AntecedentGynecoService);
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
