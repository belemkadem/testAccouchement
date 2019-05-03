/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AccouchementV3TestModule } from '../../../test.module';
import { AntecedentObstetricoComponent } from 'app/entities/antecedent-obstetrico/antecedent-obstetrico.component';
import { AntecedentObstetricoService } from 'app/entities/antecedent-obstetrico/antecedent-obstetrico.service';
import { AntecedentObstetrico } from 'app/shared/model/antecedent-obstetrico.model';

describe('Component Tests', () => {
    describe('AntecedentObstetrico Management Component', () => {
        let comp: AntecedentObstetricoComponent;
        let fixture: ComponentFixture<AntecedentObstetricoComponent>;
        let service: AntecedentObstetricoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [AccouchementV3TestModule],
                declarations: [AntecedentObstetricoComponent],
                providers: []
            })
                .overrideTemplate(AntecedentObstetricoComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AntecedentObstetricoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AntecedentObstetricoService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new AntecedentObstetrico(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.antecedentObstetricos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
