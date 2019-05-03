/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AccouchementV3TestModule } from '../../../test.module';
import { ComplicationPerOpComponent } from 'app/entities/complication-per-op/complication-per-op.component';
import { ComplicationPerOpService } from 'app/entities/complication-per-op/complication-per-op.service';
import { ComplicationPerOp } from 'app/shared/model/complication-per-op.model';

describe('Component Tests', () => {
    describe('ComplicationPerOp Management Component', () => {
        let comp: ComplicationPerOpComponent;
        let fixture: ComponentFixture<ComplicationPerOpComponent>;
        let service: ComplicationPerOpService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [ComplicationPerOpComponent],
                providers: []
            })
                .overrideTemplate(ComplicationPerOpComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ComplicationPerOpComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ComplicationPerOpService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ComplicationPerOp(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.complicationPerOps[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
