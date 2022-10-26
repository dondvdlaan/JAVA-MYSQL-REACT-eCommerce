import { Typography, Button, Card, CardActions, CardContent, CardMedia } from '@mui/material';
import { Item } from '../../../types/Common';


import css from './CartItem.module.css';

interface Props{
    item: Item;
    onUpdateCartQty: (a: number, b: number) => void;
    onRemoveFromCart: (a: number) => void;
}
/**
 * Component to display each item in the cart at its own card
 */
const CartItem = (props: Props) => {

// *** Constants and variables ***
const item = props.item;

return (
    <Card>
        <CardMedia
        component="img" 
        image={item.itemImage} 
        alt={item.productName} 
        className={css.media} />
         <CardContent className={css.cardContent}>
            <Typography variant="h4">{item.productName}</Typography>
            <Typography variant="h5">{item.lineTotal.formattedWithSymbol}</Typography>
        </CardContent>
        <CardActions className={css.cardActions}>
            <div className={css.buttons}>
                <Button type="button" variant="contained" size="small" onClick={() => props.onUpdateCartQty(item.itemID, item.itemQuantity + 1)}>+</Button>
                <Typography variant="h5">{item.itemQuantity}</Typography>
                <Button type="button" variant="contained" size="small" onClick={() => props.onUpdateCartQty(item.itemID, item.itemQuantity - 1)}>-</Button>
            </div>
            <Button variant="contained" type="button" color="secondary" onClick={() => props.onRemoveFromCart(item.itemID)}>Remove</Button>
        </CardActions>
    </Card>
)
}

export default CartItem