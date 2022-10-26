import { Item } from "./Common";

export interface PO extends POWithoutCustomerRef{
    customerReference: string;
}

export interface POWithoutCustomerRef{
    customer: Customer;
    
    orderLineItems: Item [];
    shipping: Address;
    fulfillment: string;
    billing: Address;
}

interface Customer{
    custFirstName: string;
    custLastName: string;
    custEmail: string;
}

interface Address{

}