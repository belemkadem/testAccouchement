/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AccouchementV3TestModule } from '../../../test.module';
import { AntecedentObstetricoUpdateComponent } from 'app/entities/antecedent-obstetrico/antecedent-obstetrico-update.component';
import { AntecedentObstetricoService } from 'app/entities/antecedent-obstetrico/antecedent-obstetrico.service';
import { AntecedentObstetrico } from 'app/shared/model/antecedent-obstetrico.model';

describe('Component Tests', () => {
    describe('AntecedentObstetrico Management Update Component', () => {
        let comp: AntecedentObstetricoUpdateComponent;
        let fixture: ComponentFixture<AntecedentObstetricoUpdateComponent>;
        let service: AntecedentObstetricoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [AntecedentObstetricoUpdateComponent]
            })
                .overrideTemplate(AntecedentObstetricoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AntecedentObstetricoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AntecedentObstetricoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new AntecedentObstetrico(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.antecedentObstetrico = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new AntecedentObstetrico();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.antecedentObstetrico = entity;
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
