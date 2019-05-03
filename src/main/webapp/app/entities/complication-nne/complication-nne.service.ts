import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IComplicationNNE } from 'app/shared/model/complication-nne.model';

type EntityResponseType = HttpResponse<IComplicationNNE>;
type EntityArrayResponseType = HttpResponse<IComplicationNNE[]>;

@Injectable({ providedIn: 'root' })
export class ComplicationNNEService {
    public resourceUrl = SERVER_API_URL + 'api/complication-nnes';

    constructor(protected http: HttpClient) {}

    create(complicationNNE: IComplicationNNE): Observable<EntityResponseType> {
        return this.http.post<IComplicationNNE>(this.resourceUrl, complicationNNE, { observe: 'response' });
    }

    update(complicationNNE: IComplicationNNE): Observable<EntityResponseType> {
        return this.http.put<IComplicationNNE>(this.resourceUrl, complicationNNE, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IComplicationNNE>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IComplicationNNE[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
