package com.richa.controllers;

import com.richa.dtos.PurchaseRequestDTO;
import com.richa.dtos.PurchaseResponseDTO;
import com.richa.exception.global.customexceptions.BookCreateException;
import com.richa.exception.global.customexceptions.CustomerCreateException;
import com.richa.exception.global.customexceptions.PaymentException;
import com.richa.facades.PurchaseFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/purchases",produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class PurchaseController {

    @Autowired
    PurchaseFacade purchaseFacade;

    @PostMapping
    public ResponseEntity<PurchaseResponseDTO> addPurchase(@RequestBody @Valid PurchaseRequestDTO purchaseRequestDTO)throws Exception,BookCreateException,CustomerCreateException,PaymentException {
    	PurchaseResponseDTO responsedto= purchaseFacade.performPurchase(purchaseRequestDTO);
    	System.out.println(responsedto);
    	return new ResponseEntity<PurchaseResponseDTO>(responsedto,HttpStatus.OK);
    }

    @GetMapping(params = "email")
    public ResponseEntity<List<PurchaseResponseDTO>> findPurchasesByCustomerEmail(@RequestParam("email") String email) throws Exception{
        return new ResponseEntity<List<PurchaseResponseDTO>>(purchaseFacade.findPurchasesByCustomerEmail(email),HttpStatus.OK);
    }

}