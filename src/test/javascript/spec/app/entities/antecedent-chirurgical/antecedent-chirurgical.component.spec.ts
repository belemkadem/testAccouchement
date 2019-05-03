/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AccouchementV3TestModule } from '../../../test.module';
import { AntecedentChirurgicalComponent } from 'app/entities/antecedent-chirurgical/antecedent-chirurgical.component';
import { AntecedentChirurgicalService } from 'app/entities/antecedent-chirurgical/antecedent-chirurgical.service';
import { AntecedentChirurgical } from 'app/shared/model/antecedent-chirurgical.model';

describe('Component Tests', () => {
    describe('AntecedentChirurgical Management Component', () => {
        let comp: AntecedentChirurgicalComponent;
        let fixture: ComponentFixture<AntecedentChirurgicalComponent>;
        let service: AntecedentChirurgicalService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [AntecedentChirurgicalComponent],
                providers: []
            })
                .overrideTemplate(AntecedentChirurgicalComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AntecedentChirurgicalComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AntecedentChirurgicalService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new AntecedentChirurgical(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.antecedentChirurgicals[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
