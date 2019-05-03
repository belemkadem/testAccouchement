/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AccouchementV3TestModule } from '../../../test.module';
import { IndicationCSUpdateComponent } from 'app/entities/indication-cs/indication-cs-update.component';
import { IndicationCSService } from 'app/entities/indication-cs/indication-cs.service';
import { IndicationCS } from 'app/shared/model/indication-cs.model';

describe('Component Tests', () => {
    describe('IndicationCS Management Update Component', () => {
        let comp: IndicationCSUpdateComponent;
        let fixture: ComponentFixture<IndicationCSUpdateComponent>;
        let service: IndicationCSService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [IndicationCSUpdateComponent]
            })
                .overrideTemplate(IndicationCSUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(IndicationCSUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(IndicationCSService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new IndicationCS(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.indicationCS = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new IndicationCS();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.indicationCS = entity;
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
