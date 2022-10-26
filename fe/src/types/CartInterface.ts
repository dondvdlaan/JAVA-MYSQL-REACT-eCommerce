import { Item, Price, Total } from "./Common";

/**
 * Interface for Cart with properties and sub-properties
 */
 export interface CartInterface{
    cartID: number;
    cartLineItems: Item[];
    cartSubTotal: Total;
    totalItems: number;
    }



