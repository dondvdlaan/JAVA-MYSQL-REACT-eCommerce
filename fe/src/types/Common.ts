export interface Price{
    formattedWithSymbol: string;
}

export interface Total{
    formattedWithSymbol: string;
    }

export interface Item{
    itemID: number;
    itemImage: string;
    productName: string;
    lineTotal: Total;
    itemQuantity: number;
    price: Price;

    }