/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { AccouchementV3TestModule } from '../../../test.module';
import { PathologieActuelleUpdateComponent } from 'app/entities/pathologie-actuelle/pathologie-actuelle-update.component';
import { PathologieActuelleService } from 'app/entities/pathologie-actuelle/pathologie-actuelle.service';
import { PathologieActuelle } from 'app/shared/model/pathologie-actuelle.model';

describe('Component Tests', () => {
    describe('PathologieActuelle Management Update Component', () => {
        let comp: PathologieActuelleUpdateComponent;
        let fixture: ComponentFixture<PathologieActuelleUpdateComponent>;
        let service: PathologieActuelleService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [PathologieActuelleUpdateComponent]
            })
                .overrideTemplate(PathologieActuelleUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PathologieActuelleUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PathologieActuelleService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new PathologieActuelle(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.pathologieActuelle = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new PathologieActuelle();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.pathologieActuelle = entity;
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
