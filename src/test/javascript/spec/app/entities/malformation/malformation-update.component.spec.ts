/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AccouchementV3TestModule } from '../../../test.module';
import { MalformationUpdateComponent } from 'app/entities/malformation/malformation-update.component';
import { MalformationService } from 'app/entities/malformation/malformation.service';
import { Malformation } from 'app/shared/model/malformation.model';

describe('Component Tests', () => {
    describe('Malformation Management Update Component', () => {
        let comp: MalformationUpdateComponent;
        let fixture: ComponentFixture<MalformationUpdateComponent>;
        let service: MalformationService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [MalformationUpdateComponent]
            })
                .overrideTemplate(MalformationUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MalformationUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MalformationService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Malformation(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.malformation = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Malformation();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.malformation = entity;
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
