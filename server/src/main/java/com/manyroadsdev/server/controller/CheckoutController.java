package com.manyroadsdev.server.controller;

import com.manyroadsdev.server.model.*;
import com.manyroadsdev.server.repository.*;
import com.manyroadsdev.server.services.CartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class CheckoutController {

    // *** Constants ***

    // *** Declaration and initialisation of attributes ***
    @Autowired
    private CheckoutRepository checkoutRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private CartServices cartServices;

    // *** Routing ***
    /**
     * Initialise fresh CheckoutToken between cart and new order. Most of the data
     * from current cart will be copied in CheckoutToken
     *
     * @param cartID        [int]            : ID of current Cart
     * @return returnToken  [CheckoutToken]  : new/current CheckoutToken
     */
    @GetMapping("/generateToken/{cartID}")
    public CheckOutToken generateToken(@PathVariable int cartID) {

        System.out.println("Route generateToken");

        // *** Declaration and initialisation of attributes ***
        CheckOutToken token = new CheckOutToken();
        Live live = new Live();

        // Find current Cart
        Cart currentCart = cartServices.getCartByID(cartID);

        // Configure token
        token.setLineItems(new ArrayList<>(currentCart.getCartLineItems()));

        live.setCurrency(currentCart.getCurrency());
        live.setLineItems(new ArrayList<>(currentCart.getCartLineItems()));
        live.setMerchantID(11);
        live.setSubTotal(currentCart.getCartSubTotal());
        token.setLive(live);

        token.getMerchant().setBusinessName("Many Roads Developers");

        currentCart.getCartLineItems().forEach(item -> {

            // Copy over all products from cart to token
            Product p = productRepository.findById(item.getProdID()).get();
            token.getProducts().add(p);
        });

    return checkoutRepository.save(token);
    }
    /**
     * Shipping countries are called for
     *
     * @return array  [Iterable]  : array of countries
     */
    @GetMapping("/countries")
    public @ResponseBody Iterable getCountries() {

        System.out.println("Route allCountries");

    return countryRepository.findAll();
    }

    /**
     * Find all provinces belonging to country by CountryID.
     *
     * @param   countryID [int]               : country ID
     * @return  provinces [List<Provinces>]   : provinces per country
     */
    @GetMapping("/provinces/{countryID}")
    public List<Province> getProvinces(@PathVariable int countryID) {

        System.out.println("Route allProvinces");

        Country country = countryRepository.findById(countryID).get();
    return country.getProvinces();
    }
    /**
     * Find all options per province by ProvinceID.
     *
     * @param   provID      [int]               : province ID
     * @return  provinces   [List<Provinces> ]  : provinces per country
     */
    @GetMapping("/options/{provID}")
    public List<ShippingOption> getOptions(@PathVariable int provID) {

        System.out.println("Route allOptions");

        Province province = provinceRepository.findById(provID).get();
        return province.getOptions();
    }
}
