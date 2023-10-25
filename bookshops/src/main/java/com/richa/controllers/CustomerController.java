package com.richa.controllers;

import com.richa.dtos.CustomerDTO;
import com.richa.exception.global.customexceptions.CustomerCreateException;
import com.richa.facades.CustomerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/customers",produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class CustomerController {

	@Autowired
    CustomerFacade customerFacade;

    @PostMapping
    public ResponseEntity<CustomerDTO> addNewCustomer(@RequestBody @Valid CustomerDTO customerDTO) throws CustomerCreateException, Exception {
    	
    	System.out.println("Add Customers");
        return new ResponseEntity<CustomerDTO>(customerFacade.addCustomer(customerDTO), HttpStatus.OK);
    }

    @GetMapping(params="email")
    public ResponseEntity<CustomerDTO> findCustomerByEmail(@RequestParam("email") String email) throws Exception {
    	System.out.println("Customers are");
        return new ResponseEntity<CustomerDTO>(customerFacade.findCustomerByEmail(email).get(), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAll() throws Exception{
    	System.out.println("Inside getAll");

        return new ResponseEntity<List<CustomerDTO>>(customerFacade.findAll(),HttpStatus.OK);
    }

}