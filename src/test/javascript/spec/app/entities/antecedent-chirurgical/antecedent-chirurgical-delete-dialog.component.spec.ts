/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AccouchementV3TestModule } from '../../../test.module';
import { AntecedentChirurgicalDeleteDialogComponent } from 'app/entities/antecedent-chirurgical/antecedent-chirurgical-delete-dialog.component';
import { AntecedentChirurgicalService } from 'app/entities/antecedent-chirurgical/antecedent-chirurgical.service';

describe('Component Tests', () => {
    describe('AntecedentChirurgical Management Delete Component', () => {
        let comp: AntecedentChirurgicalDeleteDialogComponent;
        let fixture: ComponentFixture<AntecedentChirurgicalDeleteDialogComponent>;
        let service: AntecedentChirurgicalService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [AntecedentChirurgicalDeleteDialogComponent]
            })
                .overrideTemplate(AntecedentChirurgicalDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AntecedentChirurgicalDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AntecedentChirurgicalService);
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
