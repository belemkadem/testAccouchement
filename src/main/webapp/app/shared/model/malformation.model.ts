import { IAccouchement } from 'app/shared/model/accouchement.model';

export interface IMalformation {
    id?: number;
    designation?: string;
    accouchements?: IAccouchement[];
}

export class Malformation implements IMalformation {
    constructor(public id?: number, public designation?: string, public accouchements?: IAccouchement[]) {}
}
