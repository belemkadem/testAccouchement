import { IAccouchement } from 'app/shared/model/accouchement.model';

export interface IPathologieActuelle {
    id?: number;
    designation?: string;
    accouchements?: IAccouchement[];
}

export class PathologieActuelle implements IPathologieActuelle {
    constructor(public id?: number, public designation?: string, public accouchements?: IAccouchement[]) {}
}
