import { IAccouchement } from 'app/shared/model/accouchement.model';

export interface IComplicationNNE {
    id?: number;
    designation?: string;
    accouchements?: IAccouchement[];
}

export class ComplicationNNE implements IComplicationNNE {
    constructor(public id?: number, public designation?: string, public accouchements?: IAccouchement[]) {}
}
