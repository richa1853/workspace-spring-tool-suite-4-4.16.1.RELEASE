package com.richa.controllers;

import com.richa.dtos.CustomerDTO;
import com.richa.facades.CustomerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerFacade customerFacade;

    @PostMapping
    public CustomerDTO addNewCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
    	
    	System.out.println("Add Customers");
        return customerFacade.addCustomer(customerDTO);
    }

    @GetMapping(params="email")
    public CustomerDTO findCustomerByEmail(@RequestParam("email") String email) {
    	System.out.println("Customers are");
        return customerFacade.findCustomerByEmail(email).get();
    }
    @GetMapping
    public List<CustomerDTO> getAll(){
    	System.out.println("Inside getAll");

        return customerFacade.findAll();
    }

}
