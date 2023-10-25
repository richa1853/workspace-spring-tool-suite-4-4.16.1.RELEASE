package com.richa.repositories;

import com.richa.entities.Purchase;
import com.richa.exception.global.customexceptions.BookCreateException;
import com.richa.exception.global.customexceptions.CustomerCreateException;
import com.richa.exception.global.customexceptions.PaymentException;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Long> {

    List<Purchase> findPurchaseByBookId(String bookId)throws Exception;

    List<Purchase> findPurchaseByCustomerEmail(String customerEmail) throws Exception;

}
