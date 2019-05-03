import { IAccouchement } from 'app/shared/model/accouchement.model';

export interface IAntecedentObstetrico {
    id?: number;
    designation?: string;
    accouchements?: IAccouchement[];
}

export class AntecedentObstetrico implements IAntecedentObstetrico {
    constructor(public id?: number, public designation?: string, public accouchements?: IAccouchement[]) {}
}
