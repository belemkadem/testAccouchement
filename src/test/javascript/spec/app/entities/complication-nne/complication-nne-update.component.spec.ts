/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AccouchementV3TestModule } from '../../../test.module';
import { ComplicationNNEUpdateComponent } from 'app/entities/complication-nne/complication-nne-update.component';
import { ComplicationNNEService } from 'app/entities/complication-nne/complication-nne.service';
import { ComplicationNNE } from 'app/shared/model/complication-nne.model';

describe('Component Tests', () => {
    describe('ComplicationNNE Management Update Component', () => {
        let comp: ComplicationNNEUpdateComponent;
        let fixture: ComponentFixture<ComplicationNNEUpdateComponent>;
        let service: ComplicationNNEService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [ComplicationNNEUpdateComponent]
            })
                .overrideTemplate(ComplicationNNEUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ComplicationNNEUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ComplicationNNEService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ComplicationNNE(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.complicationNNE = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ComplicationNNE();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.complicationNNE = entity;
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
