package com.manyroadsdev.server.controller;

import com.manyroadsdev.server.model.*;
import com.manyroadsdev.server.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.Order;

@CrossOrigin
@RestController
public class OrderController {

    // *** Constants ***

    // *** Declaration and initialisation of attributes ***
    @Autowired
    private OrderRepository orderRepository;


    // *** Routing ***
    /**
     * Receiving new order
     */
    @PostMapping("/newOrder/{checkOutToken}")
    public PurchaseOrder getNewOrder(@PathVariable int checkOutToken, @RequestBody PurchaseOrder newOrder  ){

        System.out.println("Route getNewOrder");

        // Save newOrder to generate new ID
        PurchaseOrder currentOrder = orderRepository.save(newOrder);

        // Set customer reference as feedback to customer
        currentOrder.setCustomerReference(currentOrder.getCustomer().getCustLastName(),currentOrder.getOrderID());

        return orderRepository.save(currentOrder);
    }

}
