import { IAccouchement } from 'app/shared/model/accouchement.model';

export interface IAntecedentChirurgical {
    id?: number;
    designation?: string;
    accouchements?: IAccouchement[];
}

export class AntecedentChirurgical implements IAntecedentChirurgical {
    constructor(public id?: number, public designation?: string, public accouchements?: IAccouchement[]) {}
}
