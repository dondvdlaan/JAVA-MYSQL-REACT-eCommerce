package com.manyroadsdev.server.repository;

import com.manyroadsdev.server.model.Price;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called PriceRepository
// CRUD refers Create, Read, Update, Delete

public interface PriceRepository extends CrudRepository<Price, Integer> {

}
