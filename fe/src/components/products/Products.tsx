import { useParams } from 'react-router-dom';

import { Grid } from '@mui/material';

import {ProductInterface} from '../../types/ProductInterface'
import Navbar from './../navbar/Navbar';

import css from './Products.module.css'
import { useApi, apiSimple} from '../../shared/Api';
import { CartInterface } from '../../types/CartInterface';
import Product from './product/Product';

/**
 * Component Products will call up the products from back-end and show them in store
 */
const Products = () => {

// *** Constants and variables ***
// Fallback value for cartID = 0
const { cartID = 0 }            = useParams();
const [products, setProducts]   = useApi<ProductInterface []>('products');
const [cart, setCart]           = useApi<CartInterface>(`cart/${cartID}`);

if(!products) return <p>Loading products...</p>
if(!cart) return <p>Loading cart...</p>

// *** Event handlers ***
const handleAddToCart = ( productId: number, quantity: number) => {

    apiSimple("GET",`addToCart/${cart.cartID}/${productId}/${quantity}`)
    .then(res=> setCart(res.data))
    // .then(res=> console.log("res",res))
    .catch(err=> console.log(err))
};

return (
    <>
    <Navbar cartID={cart.cartID} totalItems={ cart.totalItems} />
    <br />
    <main className={css.content}>
         
        <div className={css.toolbar}  />
            <Grid container spacing={4} >
            {/* <Grid container justify="center" spacing={4}> */}
                {products.map((product) => (
                <Grid item key={product.prodID} xs={12} sm={6} md={4} lg={3}>
                    <Product product={product} onAddToCart={handleAddToCart} />
                </Grid>
                ))}
            </Grid>
    </main>
    </>
);

}

export default Products;