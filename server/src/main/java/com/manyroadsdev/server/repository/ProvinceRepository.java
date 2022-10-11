package com.manyroadsdev.server.repository;

import com.manyroadsdev.server.model.Country;
import com.manyroadsdev.server.model.Province;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called ProvinceRepository
// CRUD refers Create, Read, Update, Delete

public interface ProvinceRepository extends CrudRepository<Province, Integer> {

}
