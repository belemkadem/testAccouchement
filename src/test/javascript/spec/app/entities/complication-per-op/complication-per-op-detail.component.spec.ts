/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AccouchementV3TestModule } from '../../../test.module';
import { ComplicationPerOpDetailComponent } from 'app/entities/complication-per-op/complication-per-op-detail.component';
import { ComplicationPerOp } from 'app/shared/model/complication-per-op.model';

describe('Component Tests', () => {
    describe('ComplicationPerOp Management Detail Component', () => {
        let comp: ComplicationPerOpDetailComponent;
        let fixture: ComponentFixture<ComplicationPerOpDetailComponent>;
        const route = ({ data: of({ complicationPerOp: new ComplicationPerOp(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [ComplicationPerOpDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ComplicationPerOpDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ComplicationPerOpDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.complicationPerOp).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
