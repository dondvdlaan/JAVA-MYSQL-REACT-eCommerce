import { Item, Total } from "./Common";

export interface CheckoutToken{
    id: number;
    cartID: number;
    live: Live;
}

interface Live{
    lineItems: Item [];
    subTotal: Total;
}