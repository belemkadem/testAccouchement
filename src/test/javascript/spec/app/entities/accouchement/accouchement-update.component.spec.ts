/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AccouchementV3TestModule } from '../../../test.module';
import { AccouchementUpdateComponent } from 'app/entities/accouchement/accouchement-update.component';
import { AccouchementService } from 'app/entities/accouchement/accouchement.service';
import { Accouchement } from 'app/shared/model/accouchement.model';

describe('Component Tests', () => {
    describe('Accouchement Management Update Component', () => {
        let comp: AccouchementUpdateComponent;
        let fixture: ComponentFixture<AccouchementUpdateComponent>;
        let service: AccouchementService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [AccouchementUpdateComponent]
            })
                .overrideTemplate(AccouchementUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AccouchementUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AccouchementService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Accouchement(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.accouchement = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Accouchement();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.accouchement = entity;
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
