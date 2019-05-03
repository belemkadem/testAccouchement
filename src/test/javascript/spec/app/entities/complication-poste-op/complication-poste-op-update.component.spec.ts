/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AccouchementV3TestModule } from '../../../test.module';
import { ComplicationPosteOpUpdateComponent } from 'app/entities/complication-poste-op/complication-poste-op-update.component';
import { ComplicationPosteOpService } from 'app/entities/complication-poste-op/complication-poste-op.service';
import { ComplicationPosteOp } from 'app/shared/model/complication-poste-op.model';

describe('Component Tests', () => {
    describe('ComplicationPosteOp Management Update Component', () => {
        let comp: ComplicationPosteOpUpdateComponent;
        let fixture: ComponentFixture<ComplicationPosteOpUpdateComponent>;
        let service: ComplicationPosteOpService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [ComplicationPosteOpUpdateComponent]
            })
                .overrideTemplate(ComplicationPosteOpUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ComplicationPosteOpUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ComplicationPosteOpService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ComplicationPosteOp(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.complicationPosteOp = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ComplicationPosteOp();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.complicationPosteOp = entity;
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
