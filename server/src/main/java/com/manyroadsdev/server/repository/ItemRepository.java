package com.manyroadsdev.server.repository;

import com.manyroadsdev.server.model.Cart;
import com.manyroadsdev.server.model.Item;
import org.springframework.data.repository.CrudRepository;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called ItemRepository
 * CRUD refers to Create, Read, Update, Delete
 */

public interface ItemRepository extends CrudRepository<Item, Integer> {

    boolean findByProdID(int prodID);
}
