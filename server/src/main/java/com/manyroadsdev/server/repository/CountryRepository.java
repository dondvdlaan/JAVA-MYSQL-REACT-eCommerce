package com.manyroadsdev.server.repository;

import com.manyroadsdev.server.model.Cart;
import com.manyroadsdev.server.model.Country;
import com.manyroadsdev.server.model.Province;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called CountryRepository
// CRUD refers Create, Read, Update, Delete

public interface CountryRepository extends CrudRepository<Country, Integer> {

}
