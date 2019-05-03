/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AccouchementV3TestModule } from '../../../test.module';
import { AntecedentGynecoDetailComponent } from 'app/entities/antecedent-gyneco/antecedent-gyneco-detail.component';
import { AntecedentGyneco } from 'app/shared/model/antecedent-gyneco.model';

describe('Component Tests', () => {
    describe('AntecedentGyneco Management Detail Component', () => {
        let comp: AntecedentGynecoDetailComponent;
        let fixture: ComponentFixture<AntecedentGynecoDetailComponent>;
        const route = ({ data: of({ antecedentGyneco: new AntecedentGyneco(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [AntecedentGynecoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AntecedentGynecoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AntecedentGynecoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.antecedentGyneco).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
