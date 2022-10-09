package com.manyroadsdev.server.controller;

import com.manyroadsdev.server.model.Product;
import com.manyroadsdev.server.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
    public class ProductController {

    // *** Constants ***

    // *** Declaration and initialisation of attributes ***
    @Autowired
    private ProductRepository productRepository;

    // *** Routing ***
    /**
     *  Testing of FE - BE communication
     * @return String : Just any reply
     */
    @CrossOrigin(origins = "http://localhost:3000")  // Only accessible form REACT
        @GetMapping("/greeting")
        public String greeting() {

            System.out.println("in greeting");

            return "Holo!";
        }

    /**
     *  All products are called
     * @return  Iterable Collection of Objects : all Products
     */
        @CrossOrigin
        @GetMapping("/products")
        public @ResponseBody Iterable getAllProducts(){

            System.out.println("Route allProducts");

            return productRepository.findAll();
        }

    /**
     * Add 1 product
     * @param "Product" newProduct : Product to be added
     */
        @CrossOrigin(origins = "http://localhost:3000")
        @PostMapping("/addProduct")
        public void addProduct(@RequestBody Product newProduct) {

            System.out.println("newProduct Item: " + newProduct);

            productRepository.save(newProduct);
        }

    }
