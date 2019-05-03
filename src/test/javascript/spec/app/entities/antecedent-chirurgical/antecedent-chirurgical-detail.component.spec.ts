/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AccouchementV3TestModule } from '../../../test.module';
import { AntecedentChirurgicalDetailComponent } from 'app/entities/antecedent-chirurgical/antecedent-chirurgical-detail.component';
import { AntecedentChirurgical } from 'app/shared/model/antecedent-chirurgical.model';

describe('Component Tests', () => {
    describe('AntecedentChirurgical Management Detail Component', () => {
        let comp: AntecedentChirurgicalDetailComponent;
        let fixture: ComponentFixture<AntecedentChirurgicalDetailComponent>;
        const route = ({ data: of({ antecedentChirurgical: new AntecedentChirurgical(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [AntecedentChirurgicalDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AntecedentChirurgicalDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AntecedentChirurgicalDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.antecedentChirurgical).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
