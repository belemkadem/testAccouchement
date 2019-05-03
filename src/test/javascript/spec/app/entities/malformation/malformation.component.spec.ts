/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AccouchementV3TestModule } from '../../../test.module';
import { MalformationComponent } from 'app/entities/malformation/malformation.component';
import { MalformationService } from 'app/entities/malformation/malformation.service';
import { Malformation } from 'app/shared/model/malformation.model';

describe('Component Tests', () => {
    describe('Malformation Management Component', () => {
        let comp: MalformationComponent;
        let fixture: ComponentFixture<MalformationComponent>;
        let service: MalformationService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [MalformationComponent],
                providers: []
            })
                .overrideTemplate(MalformationComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(MalformationComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MalformationService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Malformation(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.malformations[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
