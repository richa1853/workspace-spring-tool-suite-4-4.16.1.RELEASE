package com.richa.repositories;

import com.richa.entities.Customer;
import com.richa.exception.global.customexceptions.CustomerCreateException;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

	List<Customer> findAll();
    Optional<Customer> findCustomerByEmail(String author)throws Exception;

}