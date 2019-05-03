/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AccouchementV3TestModule } from '../../../test.module';
import { ComplicationPerOpUpdateComponent } from 'app/entities/complication-per-op/complication-per-op-update.component';
import { ComplicationPerOpService } from 'app/entities/complication-per-op/complication-per-op.service';
import { ComplicationPerOp } from 'app/shared/model/complication-per-op.model';

describe('Component Tests', () => {
    describe('ComplicationPerOp Management Update Component', () => {
        let comp: ComplicationPerOpUpdateComponent;
        let fixture: ComponentFixture<ComplicationPerOpUpdateComponent>;
        let service: ComplicationPerOpService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [ComplicationPerOpUpdateComponent]
            })
                .overrideTemplate(ComplicationPerOpUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ComplicationPerOpUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ComplicationPerOpService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ComplicationPerOp(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.complicationPerOp = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ComplicationPerOp();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.complicationPerOp = entity;
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
