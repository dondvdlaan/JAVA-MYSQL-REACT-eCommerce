import { Price } from "./Common";

export interface Country{
    countryID: number;
    countryName: string;
}

export interface Subdivision{
    provID: number;
    provName: string;
}

export interface Option{
    optionID: number;
    optionDescription: string;
    price: Price;
}

