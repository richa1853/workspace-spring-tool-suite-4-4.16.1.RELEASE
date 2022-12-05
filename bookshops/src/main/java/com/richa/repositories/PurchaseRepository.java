package com.richa.repositories;

import com.richa.entities.Purchase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Long> {

    List<Purchase> findPurchaseByBookId(String bookId);

    List<Purchase> findPurchaseByCustomerEmail(String customerEmail);

}
