import { IAccouchement } from 'app/shared/model/accouchement.model';

export interface IAntecedentMedical {
    id?: number;
    designation?: string;
    accouchements?: IAccouchement[];
}

export class AntecedentMedical implements IAntecedentMedical {
    constructor(public id?: number, public designation?: string, public accouchements?: IAccouchement[]) {}
}
