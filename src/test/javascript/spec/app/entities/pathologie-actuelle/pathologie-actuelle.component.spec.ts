/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AccouchementV3TestModule } from '../../../test.module';
import { PathologieActuelleComponent } from 'app/entities/pathologie-actuelle/pathologie-actuelle.component';
import { PathologieActuelleService } from 'app/entities/pathologie-actuelle/pathologie-actuelle.service';
import { PathologieActuelle } from 'app/shared/model/pathologie-actuelle.model';

describe('Component Tests', () => {
    describe('PathologieActuelle Management Component', () => {
        let comp: PathologieActuelleComponent;
        let fixture: ComponentFixture<PathologieActuelleComponent>;
        let service: PathologieActuelleService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [PathologieActuelleComponent],
                providers: []
            })
                .overrideTemplate(PathologieActuelleComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PathologieActuelleComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PathologieActuelleService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new PathologieActuelle(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.pathologieActuelles[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
