import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMalformation } from 'app/shared/model/malformation.model';

type EntityResponseType = HttpResponse<IMalformation>;
type EntityArrayResponseType = HttpResponse<IMalformation[]>;

@Injectable({ providedIn: 'root' })
export class MalformationService {
    public resourceUrl = SERVER_API_URL + 'api/malformations';

    constructor(protected http: HttpClient) {}

    create(malformation: IMalformation): Observable<EntityResponseType> {
        return this.http.post<IMalformation>(this.resourceUrl, malformation, { observe: 'response' });
    }

    update(malformation: IMalformation): Observable<EntityResponseType> {
        return this.http.put<IMalformation>(this.resourceUrl, malformation, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IMalformation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IMalformation[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
