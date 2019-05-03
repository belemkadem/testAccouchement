/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AccouchementV3TestModule } from '../../../test.module';
import { AntecedentChirurgicalUpdateComponent } from 'app/entities/antecedent-chirurgical/antecedent-chirurgical-update.component';
import { AntecedentChirurgicalService } from 'app/entities/antecedent-chirurgical/antecedent-chirurgical.service';
import { AntecedentChirurgical } from 'app/shared/model/antecedent-chirurgical.model';

describe('Component Tests', () => {
    describe('AntecedentChirurgical Management Update Component', () => {
        let comp: AntecedentChirurgicalUpdateComponent;
        let fixture: ComponentFixture<AntecedentChirurgicalUpdateComponent>;
        let service: AntecedentChirurgicalService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [AntecedentChirurgicalUpdateComponent]
            })
                .overrideTemplate(AntecedentChirurgicalUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AntecedentChirurgicalUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AntecedentChirurgicalService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new AntecedentChirurgical(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.antecedentChirurgical = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new AntecedentChirurgical();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.antecedentChirurgical = entity;
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
