import { Container, Typography, Button, Grid } from '@mui/material';
import { Link, useParams } from 'react-router-dom';

import CartItem from './CartItem/CartItem';
import Navbar from '../Navbar/Navbar';

import css from './Cart.module.css'
import { useApi, apiSimple } from '../../shared/Api';

/**
 * Cart Component which displays items in cart and where you can change the
 * quantities or remove the items altogether. 
 */
const Cart = () => {

// *** Constants and variables ***
const {cartID}          = useParams();
const [cart, setCart]   = useApi(`cart/${cartID}`);

if(!cart) return <p>Loading cart...</p>

// *** Event handlers ***
const handleUpdateCartQty = (itemID, quantity) => {
    
    apiSimple("PUT",`cartQuantities/${cart.cartID}/${itemID}`,{quantity:quantity})
    .then(resCart=>setCart(resCart.data))
    // .then(resCart=>console.log(resCart.data))
};

const handleRemoveFromCart = (itemID) => {
    apiSimple("PUT",`removeItem/${cart.cartID}/${itemID}`)
    .then(resCart=>setCart(resCart.data))
    // .then(resCart=>console.log(resCart.data))
  };

const onEmptyCart = () => {
    apiSimple("DELETE",`emptyCart/${cart.cartID}`)
    .then(resCart => setCart(resCart.data))
  };

// *** Components ***
const EmptyCart = () => (
    <Typography  variant="body1">You have no items in your shopping cart,  
        <Link to="/" className={css.link}>
             start adding some
        </Link>!
    </Typography>
);

const FilledCart = () => (
    <>
        <Grid container spacing={3}>
            {cart.cartLineItems.map((item) => (
                <Grid item xs={12} sm={4} key={item.itemID}>
                    <CartItem item={item} onUpdateCartQty={handleUpdateCartQty} onRemoveFromCart={handleRemoveFromCart}/>
                </Grid>
            ))}
        </Grid>
        <div className={css.cardDetails}>
            <Typography variant="h4">Subtotal: {cart.cartSubTotal.formattedWithSymbol}</Typography>
            <div>
                <Button className={css.emptyButton} size="medium" type="button" variant="contained" color="secondary" onClick={onEmptyCart}>Empty Cart</Button>
                <Button component={Link} to={`/checkout/${cart.cartID}`} className={css.checkoutButton} size="medium" type="button" variant="contained" color="primary" >Checkout</Button>
            </div>
        </div>
    </>
);

return (
    <>
        <Navbar cartID={cart.cartID} totalItems={ cart.totalItems}  />
        <Container>
            <div className={css.toolbar} />
            <Typography className={css.title} variant="h4" >
                Your Shopping Cart
            </Typography>
            { !cart.cartLineItems.length ? <EmptyCart /> : <FilledCart />}
        </Container>
    </>
)
}

export default Cart