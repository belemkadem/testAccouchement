/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AccouchementV3TestModule } from '../../../test.module';
import { MalformationDetailComponent } from 'app/entities/malformation/malformation-detail.component';
import { Malformation } from 'app/shared/model/malformation.model';

describe('Component Tests', () => {
    describe('Malformation Management Detail Component', () => {
        let comp: MalformationDetailComponent;
        let fixture: ComponentFixture<MalformationDetailComponent>;
        const route = ({ data: of({ malformation: new Malformation(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [MalformationDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(MalformationDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(MalformationDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.malformation).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
