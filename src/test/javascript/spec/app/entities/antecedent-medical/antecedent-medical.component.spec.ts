/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AccouchementV3TestModule } from '../../../test.module';
import { AntecedentMedicalComponent } from 'app/entities/antecedent-medical/antecedent-medical.component';
import { AntecedentMedicalService } from 'app/entities/antecedent-medical/antecedent-medical.service';
import { AntecedentMedical } from 'app/shared/model/antecedent-medical.model';

describe('Component Tests', () => {
    describe('AntecedentMedical Management Component', () => {
        let comp: AntecedentMedicalComponent;
        let fixture: ComponentFixture<AntecedentMedicalComponent>;
        let service: AntecedentMedicalService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [AntecedentMedicalComponent],
                providers: []
            })
                .overrideTemplate(AntecedentMedicalComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AntecedentMedicalComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AntecedentMedicalService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new AntecedentMedical(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.antecedentMedicals[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
