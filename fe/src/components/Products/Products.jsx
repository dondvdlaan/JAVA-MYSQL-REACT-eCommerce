import React, { useState, useEffect } from "react";
import { useParams } from 'react-router-dom';

import { Grid } from '@mui/material';

import Product from './Product/Product';
import Navbar from './../Navbar/Navbar';

import { commerce } from "../../lib/commerce";

import css from './Products.module.css'
import { useApi, apiSimple} from '../../shared/Api';

/**
 * Component Products will call up the products from back-end and show in store
 */
const Products = () => {

// *** Constants and variables ***
// Fallback value for cartID = 0
const { cartID = 0 }            = useParams();
const [products, setProducts]   = useApi('products');
const [cart, setCart]           = useApi(`cart/${cartID}`);

if(!products) return <p>Loading products...</p>
if(!cart) return <p>Loading cart...</p>

console.log("Products cartID ", cartID)
console.log("Products cart.cartID ", cart.cartID)
console.log("Products cart.totalItems ", cart.totalItems)

// *** Event handlers ***
const handleAddToCart = ( productId, quantity) => {

    console.log("now in handleAddToCart ")
    
    apiSimple("GET",`addToCart/${cart.cartID}/${productId}/${quantity}`)
    .then(res=> setCart(res.data))
    // .then(res=> console.log("res",res))
    .catch(err=> console.log(err))
};

// *** Functions **


return (
    <>
    <Navbar cartID={cart.cartID} totalItems={ cart.totalItems} />
    <br />
    <main className={css.content}>
         
        <div className={css.toolbar}  />
            <Grid container justify="center" spacing={4}>
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