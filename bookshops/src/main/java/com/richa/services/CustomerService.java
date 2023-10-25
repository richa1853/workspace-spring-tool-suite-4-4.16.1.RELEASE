package com.richa.services;

import com.richa.entities.Customer;
import com.richa.exception.global.customexceptions.CustomerCreateException;
import com.richa.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer save(Customer customer)throws CustomerCreateException,Exception{
        return customerRepository.save(customer);
    }

    public Optional<Customer> findCustomerByEmail(String email) throws Exception{
        return customerRepository.findCustomerByEmail(email);
    }
    public List<Customer> findAll(){
    	return customerRepository.findAll();
    }

}