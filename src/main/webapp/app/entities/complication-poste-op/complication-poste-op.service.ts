import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IComplicationPosteOp } from 'app/shared/model/complication-poste-op.model';

type EntityResponseType = HttpResponse<IComplicationPosteOp>;
type EntityArrayResponseType = HttpResponse<IComplicationPosteOp[]>;

@Injectable({ providedIn: 'root' })
export class ComplicationPosteOpService {
    public resourceUrl = SERVER_API_URL + 'api/complication-poste-ops';

    constructor(protected http: HttpClient) {}

    create(complicationPosteOp: IComplicationPosteOp): Observable<EntityResponseType> {
        return this.http.post<IComplicationPosteOp>(this.resourceUrl, complicationPosteOp, { observe: 'response' });
    }

    update(complicationPosteOp: IComplicationPosteOp): Observable<EntityResponseType> {
        return this.http.put<IComplicationPosteOp>(this.resourceUrl, complicationPosteOp, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IComplicationPosteOp>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IComplicationPosteOp[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
