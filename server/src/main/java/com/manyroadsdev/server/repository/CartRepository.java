package com.manyroadsdev.server.repository;

import com.manyroadsdev.server.model.Cart;
import org.springframework.data.repository.CrudRepository;

/** This will be AUTO IMPLEMENTED by Spring into a Bean called CartRepository
*   CRUD refers toCreate, Read, Update, Delete
*/
public interface CartRepository extends CrudRepository<Cart, Integer> {

}
