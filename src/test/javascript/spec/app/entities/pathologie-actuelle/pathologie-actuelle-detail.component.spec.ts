/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AccouchementV3TestModule } from '../../../test.module';
import { PathologieActuelleDetailComponent } from 'app/entities/pathologie-actuelle/pathologie-actuelle-detail.component';
import { PathologieActuelle } from 'app/shared/model/pathologie-actuelle.model';

describe('Component Tests', () => {
    describe('PathologieActuelle Management Detail Component', () => {
        let comp: PathologieActuelleDetailComponent;
        let fixture: ComponentFixture<PathologieActuelleDetailComponent>;
        const route = ({ data: of({ pathologieActuelle: new PathologieActuelle(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [PathologieActuelleDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PathologieActuelleDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PathologieActuelleDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.pathologieActuelle).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
