package com.richa.controllers;

import com.richa.dtos.PurchaseRequestDTO;
import com.richa.dtos.PurchaseResponseDTO;
import com.richa.facades.PurchaseFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    PurchaseFacade purchaseFacade;

    @PostMapping
    public PurchaseResponseDTO addPurchase(@RequestBody @Valid PurchaseRequestDTO purchaseRequestDTO) {
    	PurchaseResponseDTO responsedto= purchaseFacade.performPurchase(purchaseRequestDTO);
    	System.out.println(responsedto);
    	return responsedto;
    }

    @GetMapping(params = "email")
    public List<PurchaseResponseDTO> findPurchasesByCustomerEmail(@RequestParam("email") String email){
        return purchaseFacade.findPurchasesByCustomerEmail(email);
    }

}