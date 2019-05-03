import { IAccouchement } from 'app/shared/model/accouchement.model';

export interface IComplicationPerOp {
    id?: number;
    designation?: string;
    accouchements?: IAccouchement[];
}

export class ComplicationPerOp implements IComplicationPerOp {
    constructor(public id?: number, public designation?: string, public accouchements?: IAccouchement[]) {}
}
