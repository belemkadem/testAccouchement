import { IAccouchement } from 'app/shared/model/accouchement.model';

export interface IIndicationCS {
    id?: number;
    designation?: string;
    accouchements?: IAccouchement[];
}

export class IndicationCS implements IIndicationCS {
    constructor(public id?: number, public designation?: string, public accouchements?: IAccouchement[]) {}
}
