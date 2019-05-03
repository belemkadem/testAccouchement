/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AccouchementV3TestModule } from '../../../test.module';
import { ComplicationPosteOpDetailComponent } from 'app/entities/complication-poste-op/complication-poste-op-detail.component';
import { ComplicationPosteOp } from 'app/shared/model/complication-poste-op.model';

describe('Component Tests', () => {
    describe('ComplicationPosteOp Management Detail Component', () => {
        let comp: ComplicationPosteOpDetailComponent;
        let fixture: ComponentFixture<ComplicationPosteOpDetailComponent>;
        const route = ({ data: of({ complicationPosteOp: new ComplicationPosteOp(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [ComplicationPosteOpDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ComplicationPosteOpDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ComplicationPosteOpDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.complicationPosteOp).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
