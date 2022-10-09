import { Typography, Button, Card, CardActions, CardContent, CardMedia } from '@mui/material';

import css from './CartItem.module.css';


const CartItem = ({ item, onUpdateCartQty, onRemoveFromCart }) => {

return (
    <Card>
        <CardMedia
        component="img" 
        image={item.itemImage} 
        alt={item.productName} 
        className={css.media} />
         <CardContent className={css.cardContent}>
            <Typography variant="h4">{item.productName}</Typography>
            <Typography variant="h5">{item.lineTotal.formattedWithSymbols}</Typography>
        </CardContent>
        <CardActions className={css.cardActions}>
            <div className={css.buttons}>
                <Button type="button" variant="contained" size="small" onClick={() => onUpdateCartQty(item.itemID, item.itemQuantity + 1)}>+</Button>
                <Typography variant="h5">{item.itemQuantity}</Typography>
                <Button type="button" variant="contained" size="small" onClick={() => onUpdateCartQty(item.itemID, item.itemQuantity - 1)}>-</Button>
            </div>
            <Button variant="contained" type="button" color="secondary" onClick={() => onRemoveFromCart(item.itemID)}>Remove</Button>
        </CardActions>
    </Card>
)
}

export default CartItem