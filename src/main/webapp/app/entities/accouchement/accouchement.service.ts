import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAccouchement } from 'app/shared/model/accouchement.model';

type EntityResponseType = HttpResponse<IAccouchement>;
type EntityArrayResponseType = HttpResponse<IAccouchement[]>;

@Injectable({ providedIn: 'root' })
export class AccouchementService {
    public resourceUrl = SERVER_API_URL + 'api/accouchements';

    constructor(protected http: HttpClient) {}

    create(accouchement: IAccouchement): Observable<EntityResponseType> {
        return this.http.post<IAccouchement>(this.resourceUrl, accouchement, { observe: 'response' });
    }

    update(accouchement: IAccouchement): Observable<EntityResponseType> {
        return this.http.put<IAccouchement>(this.resourceUrl, accouchement, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAccouchement>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAccouchement[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
