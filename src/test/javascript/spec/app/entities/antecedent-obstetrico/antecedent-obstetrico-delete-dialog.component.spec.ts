/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AccouchementV3TestModule } from '../../../test.module';
import { AntecedentObstetricoDeleteDialogComponent } from 'app/entities/antecedent-obstetrico/antecedent-obstetrico-delete-dialog.component';
import { AntecedentObstetricoService } from 'app/entities/antecedent-obstetrico/antecedent-obstetrico.service';

describe('Component Tests', () => {
    describe('AntecedentObstetrico Management Delete Component', () => {
        let comp: AntecedentObstetricoDeleteDialogComponent;
        let fixture: ComponentFixture<AntecedentObstetricoDeleteDialogComponent>;
        let service: AntecedentObstetricoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [AntecedentObstetricoDeleteDialogComponent]
            })
                .overrideTemplate(AntecedentObstetricoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AntecedentObstetricoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AntecedentObstetricoService);
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
