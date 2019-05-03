import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPathologieActuelle } from 'app/shared/model/pathologie-actuelle.model';

type EntityResponseType = HttpResponse<IPathologieActuelle>;
type EntityArrayResponseType = HttpResponse<IPathologieActuelle[]>;

@Injectable({ providedIn: 'root' })
export class PathologieActuelleService {
    public resourceUrl = SERVER_API_URL + 'api/pathologie-actuelles';

    constructor(protected http: HttpClient) {}

    create(pathologieActuelle: IPathologieActuelle): Observable<EntityResponseType> {
        return this.http.post<IPathologieActuelle>(this.resourceUrl, pathologieActuelle, { observe: 'response' });
    }

    update(pathologieActuelle: IPathologieActuelle): Observable<EntityResponseType> {
        return this.http.put<IPathologieActuelle>(this.resourceUrl, pathologieActuelle, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPathologieActuelle>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPathologieActuelle[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
