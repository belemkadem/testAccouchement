import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAntecedentGyneco } from 'app/shared/model/antecedent-gyneco.model';

type EntityResponseType = HttpResponse<IAntecedentGyneco>;
type EntityArrayResponseType = HttpResponse<IAntecedentGyneco[]>;

@Injectable({ providedIn: 'root' })
export class AntecedentGynecoService {
    public resourceUrl = SERVER_API_URL + 'api/antecedent-gynecos';

    constructor(protected http: HttpClient) {}

    create(antecedentGyneco: IAntecedentGyneco): Observable<EntityResponseType> {
        return this.http.post<IAntecedentGyneco>(this.resourceUrl, antecedentGyneco, { observe: 'response' });
    }

    update(antecedentGyneco: IAntecedentGyneco): Observable<EntityResponseType> {
        return this.http.put<IAntecedentGyneco>(this.resourceUrl, antecedentGyneco, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAntecedentGyneco>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAntecedentGyneco[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
