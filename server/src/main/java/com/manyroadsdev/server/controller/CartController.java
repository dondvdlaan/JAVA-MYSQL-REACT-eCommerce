package com.manyroadsdev.server.controller;

import com.manyroadsdev.server.logic.helper;
import com.manyroadsdev.server.model.*;
import com.manyroadsdev.server.repository.CartRepository;
import com.manyroadsdev.server.repository.ItemRepository;
import com.manyroadsdev.server.repository.ProductRepository;
import com.manyroadsdev.server.services.CartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.io.*;

@CrossOrigin
@RestController
public class CartController {

    // *** Constants ***

    // *** Declaration and initialisation of attributes ***
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartServices cartServices;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ProductRepository productRepository;


    // *** Routing ***
    /**
     * Initialise fresh Cart at start of shopping session or
     * retrieve current Cart.
     *
     * @param cartID int            : ID of new/current Cart
     * @return returnCart  Cart     : new/current Cart
     */
    @GetMapping("/cart/{cartID}")
    public Cart getCart(@PathVariable int cartID) {

        System.out.println("Route getCart");

        // *** Declaration and initialisation of attributes ***
        Cart returnCart = new Cart();
        Currency euro = new Currency("Euro","€");

        // Save fresh Cart if cartID is 0
        if (cartID == 0){
            returnCart.setCurrency(euro);
            returnCart.setCartSubTotal(new Total (euro.getSymbol(),0,euro.getCode()));
            cartRepository.save(returnCart);
        }
        else{
            // Retrieve current Cart
            Optional<Cart> cart = cartRepository.findById(cartID);
            if (cart.isPresent()) returnCart = cart.get();
        }

    return returnCart;
    }

    /**
     * Add (new) Item to the cart
     *
     * @param cartID   int      : ID of current cart
     * @param prodID   int      : ID of Product to be added
     * @param prodQuantity int  : Product quantity
     */
    @GetMapping("/addToCart/{cartID}/{prodID}/{prodQuantity}")
    public
   ResponseEntity< Cart> addToCart(@PathVariable int cartID, @PathVariable int prodID, @PathVariable int prodQuantity) {

        System.out.println("Route addToCart");

        // *** Declaration and initialisation attributes ***
        Currency euro = new Currency("Euro","€");
        Cart returnCart = new Cart();
        boolean itemExists = false;
        double currentLineTotalRaw = 0D;
        double increaseLineTotalRaw = 0D;
        double cartSubTotalRaw = 0D;

        // Synchronized area to avoid fast clicking parallel threads going to DB vars
        synchronized(this) {
            // Find current Cart
            Cart currentCart = cartServices.getCartByID(cartID);

            // Check if Product to be added is already in cart
            for (Item item : currentCart.getCartLineItems()) {
                if (item.getProdID() == (prodID)) {

                    // Set flag to inhibit new item creation
                    itemExists = true;

                    // increase item quantity
                    int tempQuantity = item.getItemQuantity();
                    tempQuantity += prodQuantity;
                    item.setItemQuantity(tempQuantity);

                    // adjust lineTotal
                    currentLineTotalRaw = item.getLineTotal().getRaw();
                    increaseLineTotalRaw = (item.getPrice().getRaw() * prodQuantity);
                    item.setLineTotal(new Total(euro.getSymbol(), currentLineTotalRaw + increaseLineTotalRaw, euro.getCode()));
                }
            }
            // If Product is new, start adding
            if (!itemExists) {

                // Find new Product to be added
                Product productToBeAdded = productRepository.findById(prodID).get();

                // Create new Item
                Item newItem = new Item();

                newItem.setItemImage(productToBeAdded.getProdImage());
                newItem.setProductName(productToBeAdded.getProdName());
                newItem.setItemQuantity(prodQuantity);
                newItem.setPrice(productToBeAdded.getPrice());
                newItem.setProdID(prodID);

                // Set Linetotal
                increaseLineTotalRaw = productToBeAdded.getPrice().getRaw() * prodQuantity;
                newItem.setLineTotal(new Total("€", increaseLineTotalRaw, "Euro"));

                // Add new Item to cart
                currentCart.getCartLineItems().add(newItem);
            }
            // Adjust cartSubTotal
            cartSubTotalRaw = currentCart.getCartSubTotal().getRaw();
            cartSubTotalRaw += increaseLineTotalRaw;
            currentCart.setCartSubTotal(new Total(euro.getSymbol(), cartSubTotalRaw, euro.getCode()));

            // Adjust cartTotalItems
            currentCart.setTotalItems(currentCart.getTotalItems() + prodQuantity);

            // Unique items is same as length of cartLine Items array
            currentCart.setTotalUniqueItems(currentCart.getCartLineItems().size());

            // Save cart and return
            return ResponseEntity.ok(cartRepository.save(currentCart));
        }}

    /**
     * This route handles changes of the quantities of the items in the cart
     *
     * @param   cartID      int     : ID of current cart
     * @param   itemID      int     : ID of item to change
     * @param   quantity    String  : new quantity of item
     * @return  cart        Cart    : updated cart
     */
    @PutMapping("/cartQuantities/{cartID}/{itemID}")
    public ResponseEntity<Cart> changeQuantitiesCart(@PathVariable int cartID, @PathVariable int itemID, @RequestBody String quantity){

        System.out.println("Route cartQuantities");

        // *** Declaration and initialisation attributes ***
        int itemQuantity = helper.objectToInteger(quantity);
        int currentQuantity = 0;
        double changeValue = 0D;
        Item currentItem = new Item();

        // Find current Cart
        Cart currentCart = cartServices.getCartByID(cartID);

        // Find item quantity to be changed
        for (Item item : currentCart.getCartLineItems()) {
            if(item.getItemID() == itemID){

                // Store current item
                currentItem = item;

                // Store current quantity and determine change in value
                currentQuantity = item.getItemQuantity();
                item.setItemQuantity(itemQuantity);

                changeValue =(itemQuantity - currentQuantity) * item.getPrice().getRaw();

                // Adjust lineTotal
                double currentLineTotalRaw= item.getLineTotal().getRaw();
                item.setLineTotal(new Total("€",currentLineTotalRaw + changeValue,"Euro"));
            }
        }
        // Adjust cartSubTotal
        double cartSubTotalRaw = currentCart.getCartSubTotal().getRaw();
        cartSubTotalRaw += changeValue;
        currentCart.setCartSubTotal(new Total("€",cartSubTotalRaw,"Euro"));

        // Adjust total items in cart
        currentCart.setTotalItems(currentCart.getTotalItems() + itemQuantity - currentQuantity);

        // Unique items is same as length of cartLine Items array
        currentCart.setTotalUniqueItems(currentCart.getCartLineItems().size());

        // If quantity is 0, remove item from cart
        if (itemQuantity == 0) currentCart.getCartLineItems().remove(currentItem);

    return ResponseEntity.ok(cartRepository.save(currentCart));
    }
    /**
     * This route handels removal of an item from the cart
     * @param   cartID      int     : ID of current cart
     * @param   itemID      int     : ID of item to remove
     * @return  cart        Cart    : updated cart
     */
    @PutMapping("removeItem/{cartID}/{itemID}")
    public Cart removeItem(@PathVariable int cartID, @PathVariable int itemID){

        System.out.println("Route removeItem");

        // *** Declaration and initialisation attributes ***
        int currentItemQuantity = 0;
        double changeValue = 0D;
        List<Item> updatedCartLineItems = new ArrayList<>();;

        // Find current Cart
        Cart currentCart = cartServices.getCartByID(cartID);

        // Find item to be removed
        for (Item item : currentCart.getCartLineItems()) {
            if (item.getItemID() == itemID) {

                // Store change values
                currentItemQuantity = item.getItemQuantity();
                changeValue = -currentItemQuantity * item.getPrice().getRaw();
            }
        }
        // Remove item from cartLineItems
        updatedCartLineItems = currentCart.getCartLineItems()
                .stream()
                .filter(item -> item.getItemID() != itemID)
                .collect(Collectors.toList());

        // Update current cart with lineTems
        currentCart.setCartLineItems(updatedCartLineItems);

        // adjust cartSubTotal
        currentCart = cartServices.adjustCartSubTotal(changeValue,currentCart);

        // Adjust total items in cart
        currentCart.setTotalItems(currentCart.getTotalItems()  - currentItemQuantity);

        // Unique items is same as length of cartLine Items array
        currentCart.setTotalUniqueItems(currentCart.getCartLineItems().size());

    return cartRepository.save(currentCart);
    }
    /**
     * Route to empty the cart at once.
     *
     * @param   cartID      int     : Cart ID
     * @return  currentCart Cart    : return currentCart
     */
    @DeleteMapping("emptyCart/{cartID}")
    public Cart emptyCart(@PathVariable int cartID){

        // Find current Cart
        Cart currentCart = cartServices.getCartByID(cartID);

        // Reset Cart
        currentCart.setCartLineItems(new ArrayList<>());
        currentCart.setCartSubTotal(new Total("€",0,"Euro"));
        currentCart.setTotalItems(0);

        // Unique items is same as length of cartLine Items array
        currentCart.setTotalUniqueItems(currentCart.getCartLineItems().size());

    return cartRepository.save(currentCart);
    }
}
