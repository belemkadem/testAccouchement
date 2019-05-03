/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AccouchementV3TestModule } from '../../../test.module';
import { AntecedentGynecoUpdateComponent } from 'app/entities/antecedent-gyneco/antecedent-gyneco-update.component';
import { AntecedentGynecoService } from 'app/entities/antecedent-gyneco/antecedent-gyneco.service';
import { AntecedentGyneco } from 'app/shared/model/antecedent-gyneco.model';

describe('Component Tests', () => {
    describe('AntecedentGyneco Management Update Component', () => {
        let comp: AntecedentGynecoUpdateComponent;
        let fixture: ComponentFixture<AntecedentGynecoUpdateComponent>;
        let service: AntecedentGynecoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [AntecedentGynecoUpdateComponent]
            })
                .overrideTemplate(AntecedentGynecoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AntecedentGynecoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AntecedentGynecoService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new AntecedentGyneco(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.antecedentGyneco = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new AntecedentGyneco();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.antecedentGyneco = entity;
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
