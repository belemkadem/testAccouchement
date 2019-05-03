/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AccouchementV3TestModule } from '../../../test.module';
import { ComplicationNNEComponent } from 'app/entities/complication-nne/complication-nne.component';
import { ComplicationNNEService } from 'app/entities/complication-nne/complication-nne.service';
import { ComplicationNNE } from 'app/shared/model/complication-nne.model';

describe('Component Tests', () => {
    describe('ComplicationNNE Management Component', () => {
        let comp: ComplicationNNEComponent;
        let fixture: ComponentFixture<ComplicationNNEComponent>;
        let service: ComplicationNNEService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [ComplicationNNEComponent],
                providers: []
            })
                .overrideTemplate(ComplicationNNEComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ComplicationNNEComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ComplicationNNEService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ComplicationNNE(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.complicationNNES[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
