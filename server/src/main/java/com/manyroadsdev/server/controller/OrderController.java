package com.manyroadsdev.server.controller;

import com.manyroadsdev.server.logic.helper;
import com.manyroadsdev.server.model.*;
import com.manyroadsdev.server.repository.CartRepository;
import com.manyroadsdev.server.repository.ItemRepository;
import com.manyroadsdev.server.repository.ProductRepository;
import com.manyroadsdev.server.services.CartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class OrderController {

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
     */
}
