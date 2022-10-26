import { Route, Routes } from "react-router-dom"
import Products from "./products/Products"
import Cart from "./cart/Cart"
import Checkout from "./checkoutForm/checkout/Checkout"

/**
 * Routing component for different pages of application
 */

export const Routing = ()=>{

return(
    <Routes>
        <Route path='/:cartID'          element={<Products />} />
        <Route path='/'                 element={<Products />} />
        <Route path='/cart/:cartID'     element={<Cart />} />
        <Route path='/checkout/:cartID' element={<Checkout />} />

    </Routes>
)
}