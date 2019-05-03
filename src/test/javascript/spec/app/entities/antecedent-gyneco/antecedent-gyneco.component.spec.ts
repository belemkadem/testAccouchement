/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AccouchementV3TestModule } from '../../../test.module';
import { AntecedentGynecoComponent } from 'app/entities/antecedent-gyneco/antecedent-gyneco.component';
import { AntecedentGynecoService } from 'app/entities/antecedent-gyneco/antecedent-gyneco.service';
import { AntecedentGyneco } from 'app/shared/model/antecedent-gyneco.model';

describe('Component Tests', () => {
    describe('AntecedentGyneco Management Component', () => {
        let comp: AntecedentGynecoComponent;
        let fixture: ComponentFixture<AntecedentGynecoComponent>;
        let service: AntecedentGynecoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [AntecedentGynecoComponent],
                providers: []
            })
                .overrideTemplate(AntecedentGynecoComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AntecedentGynecoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AntecedentGynecoService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new AntecedentGyneco(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.antecedentGynecos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
