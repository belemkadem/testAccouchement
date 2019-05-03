/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AccouchementV3TestModule } from '../../../test.module';
import { AntecedentMedicalDetailComponent } from 'app/entities/antecedent-medical/antecedent-medical-detail.component';
import { AntecedentMedical } from 'app/shared/model/antecedent-medical.model';

describe('Component Tests', () => {
    describe('AntecedentMedical Management Detail Component', () => {
        let comp: AntecedentMedicalDetailComponent;
        let fixture: ComponentFixture<AntecedentMedicalDetailComponent>;
        const route = ({ data: of({ antecedentMedical: new AntecedentMedical(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [AntecedentMedicalDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AntecedentMedicalDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AntecedentMedicalDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.antecedentMedical).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
