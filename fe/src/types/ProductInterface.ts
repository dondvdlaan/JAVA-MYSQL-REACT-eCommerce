import { Price } from "./Common";

export interface ProductInterface{
    prodID: number;
    prodImage: string;
    prodName: string;
    price: Price;
    prodDescription: string;
}