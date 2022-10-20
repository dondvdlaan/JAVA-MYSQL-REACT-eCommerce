package com.manyroadsdev.server.repository;

import com.manyroadsdev.server.model.Cart;
import com.manyroadsdev.server.model.CheckOutToken;
import org.springframework.data.repository.CrudRepository;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called CheckOutTokenRepository
 * CRUD refers to Create, Read, Update, Delete
 */
public interface CheckoutRepository extends CrudRepository<CheckOutToken, Integer> {

}
