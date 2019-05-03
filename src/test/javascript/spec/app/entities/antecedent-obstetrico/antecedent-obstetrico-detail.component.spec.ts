/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AccouchementV3TestModule } from '../../../test.module';
import { AntecedentObstetricoDetailComponent } from 'app/entities/antecedent-obstetrico/antecedent-obstetrico-detail.component';
import { AntecedentObstetrico } from 'app/shared/model/antecedent-obstetrico.model';

describe('Component Tests', () => {
    describe('AntecedentObstetrico Management Detail Component', () => {
        let comp: AntecedentObstetricoDetailComponent;
        let fixture: ComponentFixture<AntecedentObstetricoDetailComponent>;
        const route = ({ data: of({ antecedentObstetrico: new AntecedentObstetrico(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [AntecedentObstetricoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AntecedentObstetricoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AntecedentObstetricoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.antecedentObstetrico).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
