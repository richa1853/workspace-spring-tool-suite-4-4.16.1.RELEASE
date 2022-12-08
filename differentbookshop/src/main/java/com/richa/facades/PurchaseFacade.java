package com.richa.facades;

import com.richa.dtos.PurchaseRequestDTO;
import com.richa.dtos.PurchaseResponseDTO;
import com.richa.entities.Book;
import com.richa.entities.Customer;
import com.richa.entities.Purchase;
import com.richa.entities.enums.PaymentMethod;
import com.richa.services.BookService;
import com.richa.services.CustomerService;
import com.richa.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.richa.constants.Currency.GBP;
import static com.richa.constants.ErrorCode.*;

@Service
public class PurchaseFacade {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private BookService bookService;// call bookService using restTemplate

    @Autowired
    private CustomerService customerService;

    public PurchaseResponseDTO performPurchase(PurchaseRequestDTO purchaseRequestDTO) {
        PurchaseResponseDTO purchaseResponseDTO = new PurchaseResponseDTO();
        Optional<Book> bookOptional = bookService.findBookByISBN(purchaseRequestDTO.getBookISBN());
        System.out.println("BookService called");
        if(!bookOptional.isPresent()) {
            purchaseResponseDTO.setErrorMessage(BOOK_NOT_FOUND.getMessage());
            purchaseResponseDTO.setError(BOOK_NOT_FOUND.getCode());
            return  purchaseResponseDTO;
        }

        Optional<Customer> customerOptional = customerService.findCustomerByEmail(purchaseRequestDTO.getCustomerEmail());
        System.out.println("Customer Service called");
        if(!customerOptional.isPresent()) {
            purchaseResponseDTO.setErrorMessage(CUSTOMER_NOT_FOUND.getMessage());
            purchaseResponseDTO.setError(CUSTOMER_NOT_FOUND.getCode());
            return  purchaseResponseDTO;
        }

        Optional<PaymentMethod> paymentMethodOptional = PaymentMethod.fromValue(purchaseRequestDTO.getPaymentMethod());
        System.out.println("Fetched Payment Method");
        if(!paymentMethodOptional.isPresent()) {
            purchaseResponseDTO.setErrorMessage(PAYMENT_METHOD_NOT_VALID.getMessage());
            purchaseResponseDTO.setError(PAYMENT_METHOD_NOT_VALID.getCode());
            return purchaseResponseDTO;
        }

        Customer customer = customerOptional.get();
        Book book = bookOptional.get();
        PaymentMethod paymentMethod = paymentMethodOptional.get();
        Purchase purchase = new Purchase();
//   
                   purchase.setCustomer(customer);
                    purchase.setPrice(book.getPrice());
                    purchase.setBook(book);
                    purchase.setPaymentMethod(paymentMethod);
                    purchase.setCurrency(GBP.name());
//                .build();
        purchase=purchaseService.save(purchase);
        System.out.println("purchase done ");
        System.out.println(purchase);
        purchaseResponseDTO = PurchaseResponseDTO.fromPurchase(purchase);
        System.out.println("purchase response done");
        System.out.println(purchaseResponseDTO);
        return purchaseResponseDTO;
    }

    public List<PurchaseResponseDTO> findPurchasesByCustomerEmail(String email){
        return purchaseService.findPurchasesByCustomerEmail(email).stream().map(PurchaseResponseDTO::fromPurchase).collect(Collectors.toList());
    }
}
