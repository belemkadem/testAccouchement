/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AccouchementV3TestModule } from '../../../test.module';
import { IndicationCSDetailComponent } from 'app/entities/indication-cs/indication-cs-detail.component';
import { IndicationCS } from 'app/shared/model/indication-cs.model';

describe('Component Tests', () => {
    describe('IndicationCS Management Detail Component', () => {
        let comp: IndicationCSDetailComponent;
        let fixture: ComponentFixture<IndicationCSDetailComponent>;
        const route = ({ data: of({ indicationCS: new IndicationCS(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [IndicationCSDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(IndicationCSDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(IndicationCSDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.indicationCS).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
