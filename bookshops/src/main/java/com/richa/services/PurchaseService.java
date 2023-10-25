package com.richa.services;

import com.richa.entities.Purchase;
import com.richa.exception.global.customexceptions.BookCreateException;
import com.richa.exception.global.customexceptions.CustomerCreateException;
import com.richa.exception.global.customexceptions.PaymentException;
import com.richa.repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    public Purchase save(Purchase purchase)throws Exception,BookCreateException, CustomerCreateException,PaymentException{
        return purchaseRepository.save(purchase);
    }

    @Transactional
    public List<Purchase> findPurchasesByCustomerEmail(String customerEmail) throws Exception{
        return purchaseRepository.findPurchaseByCustomerEmail(customerEmail);
    }

    @Transactional
    public List<Purchase> findPurchasesByBookId(String bookId) throws Exception, BookCreateException{
        return purchaseRepository.findPurchaseByBookId(bookId);
    }

}