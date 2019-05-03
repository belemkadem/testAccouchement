/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AccouchementV3TestModule } from '../../../test.module';
import { AntecedentMedicalUpdateComponent } from 'app/entities/antecedent-medical/antecedent-medical-update.component';
import { AntecedentMedicalService } from 'app/entities/antecedent-medical/antecedent-medical.service';
import { AntecedentMedical } from 'app/shared/model/antecedent-medical.model';

describe('Component Tests', () => {
    describe('AntecedentMedical Management Update Component', () => {
        let comp: AntecedentMedicalUpdateComponent;
        let fixture: ComponentFixture<AntecedentMedicalUpdateComponent>;
        let service: AntecedentMedicalService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [AntecedentMedicalUpdateComponent]
            })
                .overrideTemplate(AntecedentMedicalUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AntecedentMedicalUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AntecedentMedicalService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new AntecedentMedical(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.antecedentMedical = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new AntecedentMedical();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.antecedentMedical = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
