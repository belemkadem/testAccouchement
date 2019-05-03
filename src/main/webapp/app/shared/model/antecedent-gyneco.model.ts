import { IAccouchement } from 'app/shared/model/accouchement.model';

export interface IAntecedentGyneco {
    id?: number;
    designation?: string;
    accouchements?: IAccouchement[];
}

export class AntecedentGyneco implements IAntecedentGyneco {
    constructor(public id?: number, public designation?: string, public accouchements?: IAccouchement[]) {}
}
