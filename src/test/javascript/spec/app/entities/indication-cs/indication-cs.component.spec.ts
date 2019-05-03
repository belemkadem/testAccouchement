/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AccouchementV3TestModule } from '../../../test.module';
import { IndicationCSComponent } from 'app/entities/indication-cs/indication-cs.component';
import { IndicationCSService } from 'app/entities/indication-cs/indication-cs.service';
import { IndicationCS } from 'app/shared/model/indication-cs.model';

describe('Component Tests', () => {
    describe('IndicationCS Management Component', () => {
        let comp: IndicationCSComponent;
        let fixture: ComponentFixture<IndicationCSComponent>;
        let service: IndicationCSService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [IndicationCSComponent],
                providers: []
            })
                .overrideTemplate(IndicationCSComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(IndicationCSComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(IndicationCSService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new IndicationCS(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.indicationCS[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
