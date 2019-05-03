/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AccouchementV3TestModule } from '../../../test.module';
import { ComplicationPosteOpComponent } from 'app/entities/complication-poste-op/complication-poste-op.component';
import { ComplicationPosteOpService } from 'app/entities/complication-poste-op/complication-poste-op.service';
import { ComplicationPosteOp } from 'app/shared/model/complication-poste-op.model';

describe('Component Tests', () => {
    describe('ComplicationPosteOp Management Component', () => {
        let comp: ComplicationPosteOpComponent;
        let fixture: ComponentFixture<ComplicationPosteOpComponent>;
        let service: ComplicationPosteOpService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [ComplicationPosteOpComponent],
                providers: []
            })
                .overrideTemplate(ComplicationPosteOpComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ComplicationPosteOpComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ComplicationPosteOpService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ComplicationPosteOp(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.complicationPosteOps[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
