import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAntecedentChirurgical } from 'app/shared/model/antecedent-chirurgical.model';

type EntityResponseType = HttpResponse<IAntecedentChirurgical>;
type EntityArrayResponseType = HttpResponse<IAntecedentChirurgical[]>;

@Injectable({ providedIn: 'root' })
export class AntecedentChirurgicalService {
    public resourceUrl = SERVER_API_URL + 'api/antecedent-chirurgicals';

    constructor(protected http: HttpClient) {}

    create(antecedentChirurgical: IAntecedentChirurgical): Observable<EntityResponseType> {
        return this.http.post<IAntecedentChirurgical>(this.resourceUrl, antecedentChirurgical, { observe: 'response' });
    }

    update(antecedentChirurgical: IAntecedentChirurgical): Observable<EntityResponseType> {
        return this.http.put<IAntecedentChirurgical>(this.resourceUrl, antecedentChirurgical, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAntecedentChirurgical>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAntecedentChirurgical[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
