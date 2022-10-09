package com.manyroadsdev.server.services;

import com.manyroadsdev.server.model.Cart;
import com.manyroadsdev.server.model.Total;
import com.manyroadsdev.server.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.stereotype.Service;

@Service
public class CartServicesImpl implements CartServices{

    // *** Declaration and initialisation of attributes ***
    @Autowired
    private CartRepository cartRepository;

    /**
     * Helper method to retrieve cart by cartID and in case of error, message is
     * issued.
     * @param cartID    int     : ID of cart
     * @return cart     Cart    : requested cart is returned
     */
    public Cart getCartByID(int cartID){
        Cart cart = cartRepository.findById(cartID)
                .orElseThrow(() -> new InvalidConfigurationPropertyValueException("Route cartQuantities", cartID, "No Cart with this cartID " ));
    return cart;
    }

    /**
     * Method to store changes to total cart value
     * @param changeValue   double  : Changed value
     * @param currentCart   Cart    : current Cart
     * @return currentCart  Cart    : updated currentCart is returned
     */
    public Cart adjustCartSubTotal(double changeValue, Cart currentCart){

        // adjust cartSubTotal
        double cartSubTotalRaw = currentCart.getCartSubTotal().getRaw();
        cartSubTotalRaw += changeValue;
        currentCart.setCartSubTotal(new Total("€",cartSubTotalRaw,"Euro"));

    return currentCart;
    }
}
