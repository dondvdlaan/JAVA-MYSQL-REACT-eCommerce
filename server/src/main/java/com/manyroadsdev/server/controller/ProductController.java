package com.manyroadsdev.server.controller;

import com.manyroadsdev.server.model.Product;
import com.manyroadsdev.server.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
    public class ProductController {

    // *** Constants ***

    // *** Declaration and initialisation of attributes ***
    @Autowired
    private ProductRepository productRepository;

    // *** Routing ***
    /**
     *  All products are called
     * @return  [Iterable] Collection of Objects : all Products
     */
        @GetMapping("/products")
        public @ResponseBody Iterable getAllProducts(){

            System.out.println("Route allProducts");

            return productRepository.findAll();
        }

    /**
     * Add 1 product
     * @param [Product] newProduct : Product to be added
     */
        @PostMapping("/addProduct")
        public void addProduct(@RequestBody Product newProduct) {

            System.out.println("Route addProduct");

            productRepository.save(newProduct);
        }
    }
