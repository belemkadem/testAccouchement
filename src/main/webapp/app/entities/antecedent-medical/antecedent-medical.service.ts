import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAntecedentMedical } from 'app/shared/model/antecedent-medical.model';

type EntityResponseType = HttpResponse<IAntecedentMedical>;
type EntityArrayResponseType = HttpResponse<IAntecedentMedical[]>;

@Injectable({ providedIn: 'root' })
export class AntecedentMedicalService {
    public resourceUrl = SERVER_API_URL + 'api/antecedent-medicals';

    constructor(protected http: HttpClient) {}

    create(antecedentMedical: IAntecedentMedical): Observable<EntityResponseType> {
        return this.http.post<IAntecedentMedical>(this.resourceUrl, antecedentMedical, { observe: 'response' });
    }

    update(antecedentMedical: IAntecedentMedical): Observable<EntityResponseType> {
        return this.http.put<IAntecedentMedical>(this.resourceUrl, antecedentMedical, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAntecedentMedical>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAntecedentMedical[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
