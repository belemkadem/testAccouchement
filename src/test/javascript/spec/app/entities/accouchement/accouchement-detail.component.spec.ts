/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AccouchementV3TestModule } from '../../../test.module';
import { AccouchementDetailComponent } from 'app/entities/accouchement/accouchement-detail.component';
import { Accouchement } from 'app/shared/model/accouchement.model';

describe('Component Tests', () => {
    describe('Accouchement Management Detail Component', () => {
        let comp: AccouchementDetailComponent;
        let fixture: ComponentFixture<AccouchementDetailComponent>;
        const route = ({ data: of({ accouchement: new Accouchement(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [AccouchementDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AccouchementDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AccouchementDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.accouchement).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
