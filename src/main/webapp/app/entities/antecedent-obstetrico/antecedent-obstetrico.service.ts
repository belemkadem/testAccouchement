import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAntecedentObstetrico } from 'app/shared/model/antecedent-obstetrico.model';

type EntityResponseType = HttpResponse<IAntecedentObstetrico>;
type EntityArrayResponseType = HttpResponse<IAntecedentObstetrico[]>;

@Injectable({ providedIn: 'root' })
export class AntecedentObstetricoService {
    public resourceUrl = SERVER_API_URL + 'api/antecedent-obstetricos';

    constructor(protected http: HttpClient) {}

    create(antecedentObstetrico: IAntecedentObstetrico): Observable<EntityResponseType> {
        return this.http.post<IAntecedentObstetrico>(this.resourceUrl, antecedentObstetrico, { observe: 'response' });
    }

    update(antecedentObstetrico: IAntecedentObstetrico): Observable<EntityResponseType> {
        return this.http.put<IAntecedentObstetrico>(this.resourceUrl, antecedentObstetrico, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAntecedentObstetrico>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAntecedentObstetrico[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
