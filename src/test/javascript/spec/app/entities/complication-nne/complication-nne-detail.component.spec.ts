/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AccouchementV3TestModule } from '../../../test.module';
import { ComplicationNNEDetailComponent } from 'app/entities/complication-nne/complication-nne-detail.component';
import { ComplicationNNE } from 'app/shared/model/complication-nne.model';

describe('Component Tests', () => {
    describe('ComplicationNNE Management Detail Component', () => {
        let comp: ComplicationNNEDetailComponent;
        let fixture: ComponentFixture<ComplicationNNEDetailComponent>;
        const route = ({ data: of({ complicationNNE: new ComplicationNNE(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [ComplicationNNEDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ComplicationNNEDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ComplicationNNEDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.complicationNNE).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
