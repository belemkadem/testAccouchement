import { IAccouchement } from 'app/shared/model/accouchement.model';

export interface IComplicationPosteOp {
    id?: number;
    designation?: string;
    accouchements?: IAccouchement[];
}

export class ComplicationPosteOp implements IComplicationPosteOp {
    constructor(public id?: number, public designation?: string, public accouchements?: IAccouchement[]) {}
}
