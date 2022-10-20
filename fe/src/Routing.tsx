import { Route, Routes } from "react-router-dom"
import Products from "./components/Products/Products"
import Cart from "./components/Cart/Cart"
import Checkout from "./components/CheckoutForm/Checkout/Checkout"

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