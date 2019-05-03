/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { AccouchementService } from 'app/entities/accouchement/accouchement.service';
import {
    IAccouchement,
    Accouchement,
    Provenance,
    TypeDerniereCS,
    OuiNon,
    PositifNegatif,
    GroupeSanguin,
    EtatPDE,
    LiquideAmneotique,
    EtatBassin,
    TypeCesarienne,
    CesarienneEtTravail,
    ModeAnesthesie,
    TypeHysterotomie,
    Sexe
} from 'app/shared/model/accouchement.model';

describe('Service Tests', () => {
    describe('Accouchement Service', () => {
        let injector: TestBed;
        let service: AccouchementService;
        let httpMock: HttpTestingController;
        let elemDefault: IAccouchement;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(AccouchementService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new Accouchement(
                0,
                'AAAAAAA',
                0,
                Provenance.RURAL,
                'AAAAAAA',
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                TypeDerniereCS.TRANSVERSE,
                'AAAAAAA',
                OuiNon.OUI,
                0,
                0,
                PositifNegatif.POSITIF,
                0,
                GroupeSanguin.OP,
                0,
                0,
                0,
                0,
                EtatPDE.INTACTE,
                LiquideAmneotique.CLAIR,
                0,
                EtatBassin.RETRECI,
                TypeCesarienne.PROGRAMME,
                CesarienneEtTravail.AU_COURS,
                ModeAnesthesie.AG,
                TypeHysterotomie.TRANSVERSE,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                OuiNon.OUI,
                0,
                OuiNon.OUI,
                0,
                0,
                0,
                Sexe.F,
                'AAAAAAA',
                OuiNon.OUI,
                OuiNon.OUI
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign({}, elemDefault);
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a Accouchement', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new Accouchement(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Accouchement', async () => {
                const returnedFromService = Object.assign(
                    {
                        numeroEntree: 'BBBBBB',
                        age: 1,
                        provenance: 'BBBBBB',
                        adresse: 'BBBBBB',
                        parite: 1,
                        gestite: 1,
                        enfantVivant: 1,
                        mortNe: 1,
                        fausseCouche: 1,
                        avortement: 1,
                        mFIU: 1,
                        nombreCesarienne: 1,
                        dateDerniereCS: 1,
                        typeDerniereCS: 'BBBBBB',
                        indicationDerniereCS: 'BBBBBB',
                        grossesseSuivis: 'BBBBBB',
                        ageGestationnel: 1,
                        hB: 1,
                        siphilis: 'BBBBBB',
                        gAJ: 1,
                        groupage: 'BBBBBB',
                        tA: 1,
                        bCF: 1,
                        hU: 1,
                        etatDeCol: 1,
                        etatPDE: 'BBBBBB',
                        liquideAmneotique: 'BBBBBB',
                        numeroDeFoetus: 1,
                        etatDeBassin: 'BBBBBB',
                        typeCesarienne: 'BBBBBB',
                        cesarienneEtTravail: 'BBBBBB',
                        modeAnesthesie: 'BBBBBB',
                        typeHysterotomie: 'BBBBBB',
                        incidentPerOperatoire: 'BBBBBB',
                        gesteAssocie: 'BBBBBB',
                        suitePosteOperatoire: 'BBBBBB',
                        typeDelivrance: 'BBBBBB',
                        revisionUteriene: 'BBBBBB',
                        dureeHospit: 1,
                        mortalite: 'BBBBBB',
                        apgar1Minute: 1,
                        apgar5Minute: 1,
                        poids: 1,
                        sexe: 'BBBBBB',
                        aspect: 'BBBBBB',
                        vitamineK: 'BBBBBB',
                        hB1: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign({}, returnedFromService);
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of Accouchement', async () => {
                const returnedFromService = Object.assign(
                    {
                        numeroEntree: 'BBBBBB',
                        age: 1,
                        provenance: 'BBBBBB',
                        adresse: 'BBBBBB',
                        parite: 1,
                        gestite: 1,
                        enfantVivant: 1,
                        mortNe: 1,
                        fausseCouche: 1,
                        avortement: 1,
                        mFIU: 1,
                        nombreCesarienne: 1,
                        dateDerniereCS: 1,
                        typeDerniereCS: 'BBBBBB',
                        indicationDerniereCS: 'BBBBBB',
                        grossesseSuivis: 'BBBBBB',
                        ageGestationnel: 1,
                        hB: 1,
                        siphilis: 'BBBBBB',
                        gAJ: 1,
                        groupage: 'BBBBBB',
                        tA: 1,
                        bCF: 1,
                        hU: 1,
                        etatDeCol: 1,
                        etatPDE: 'BBBBBB',
                        liquideAmneotique: 'BBBBBB',
                        numeroDeFoetus: 1,
                        etatDeBassin: 'BBBBBB',
                        typeCesarienne: 'BBBBBB',
                        cesarienneEtTravail: 'BBBBBB',
                        modeAnesthesie: 'BBBBBB',
                        typeHysterotomie: 'BBBBBB',
                        incidentPerOperatoire: 'BBBBBB',
                        gesteAssocie: 'BBBBBB',
                        suitePosteOperatoire: 'BBBBBB',
                        typeDelivrance: 'BBBBBB',
                        revisionUteriene: 'BBBBBB',
                        dureeHospit: 1,
                        mortalite: 'BBBBBB',
                        apgar1Minute: 1,
                        apgar5Minute: 1,
                        poids: 1,
                        sexe: 'BBBBBB',
                        aspect: 'BBBBBB',
                        vitamineK: 'BBBBBB',
                        hB1: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a Accouchement', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
