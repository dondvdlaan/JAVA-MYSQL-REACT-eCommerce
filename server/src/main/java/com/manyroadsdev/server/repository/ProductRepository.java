package com.manyroadsdev.server.repository;

import com.manyroadsdev.server.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;


// This will be AUTO IMPLEMENTED by Spring into a Bean called ProductRepository
// CRUD refers Create, Read, Update, Delete

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
