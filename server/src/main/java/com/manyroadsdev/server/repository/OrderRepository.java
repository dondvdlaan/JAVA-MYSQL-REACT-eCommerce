package com.manyroadsdev.server.repository;

import com.manyroadsdev.server.model.PurchaseOrder;
import org.springframework.data.repository.CrudRepository;

/**
 * This will be AUTO IMPLEMENTED by Spring into a Bean called PurchaseOrderRepository
 * CRUD refers to Create, Read, Update, Delete
 */

public interface OrderRepository extends CrudRepository<PurchaseOrder, Integer> {

}
