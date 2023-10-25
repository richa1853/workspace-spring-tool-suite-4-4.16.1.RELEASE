package com.richa.facades;

//import com.richa.AppConfig;
//import com.richa.dtos.BookDTO;
import com.richa.dtos.CustomerDTO;
//import com.richa.entities.Book;
import com.richa.entities.Customer;
import com.richa.exception.global.customexceptions.CustomerCreateException;
import com.richa.services.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//import static com.richa.constants.ErrorCode.CUSTOMER_ALREADY_EXISTS;
//import static com.richa.constants.ErrorCode.CUSTOMER_NOT_FOUND;

@Service
public class CustomerFacade {

    @Autowired
    private CustomerService customerService;

    public CustomerDTO addCustomer(CustomerDTO customerDTO)throws CustomerCreateException,Exception {
    	Optional<Customer> custOptional=customerService.findCustomerByEmail(customerDTO.getEmail());
    	//retrn customer with id null
    	//2nd test- id val present
    	//3rd test- return empty obj
    	CustomerDTO cusDTO=null;
        if(custOptional.isPresent()) {
        	cusDTO=CustomerDTO.fromCustomer(custOptional.get());
//        	if(cusDTO.getId()==null && cusDTO.getError()!=null && cusDTO.getError().length()>0) {
//        		Customer customer=new Customer();           	
//            	customer.setName(customerDTO.getName());
//            	customer.setSurname(customerDTO.getSurname());
//            	customer.setAddress(customerDTO.getAddress());
//            	customer.setEmail(customerDTO.getEmail());
//                customerService.save(customer);
//                System.out.println("Customer Saved");
//
//               cusDTO= findCustomerByEmail(customerDTO.getEmail()).get();
               
        //	}else {
        		String errorMsg ="Customer already exists";
				throw new CustomerCreateException(errorMsg,HttpStatus.BAD_REQUEST.value());
        	//}
        	
        } else {
        Customer customer=new Customer();           	
    	customer.setName(customerDTO.getName());
    	customer.setSurname(customerDTO.getSurname());
    	customer.setAddress(customerDTO.getAddress());
    	customer.setEmail(customerDTO.getEmail());
        customerService.save(customer);
        System.out.println("Customer Saved");

       cusDTO= findCustomerByEmail(customerDTO.getEmail()).get();
        	
        	
        }
        return cusDTO;
    }
    
    
    public Optional<CustomerDTO> findCustomerByEmail(String email) throws Exception{
        Optional<Customer> customerOptional = customerService.findCustomerByEmail(email);
        CustomerDTO customerDTO = new CustomerDTO();
        if(customerOptional.isPresent()) {
//            customerDTO.setErrorMessage(CUSTOMER_NOT_FOUND.getMessage());
//            customerDTO.setError(CUSTOMER_NOT_FOUND.getCode());
//            return Optional.of(customerDTO);
        	Customer customer = customerOptional.get();
//            System.out.println(customer.getPurchases());
            customerDTO = CustomerDTO.fromCustomer(customer);
            return Optional.of(customerDTO);
        	
    	}
        else {
            String errorMsg ="Customer is not there";
			throw new Exception(errorMsg);
        }
    }
    public List<CustomerDTO> findAll(){
    	return customerService.findAll()//returns entity obj list of customers and converting entity into dto
                .stream()
                .map(CustomerDTO::fromCustomer)
                .collect(Collectors.toList()); 
    }
    
}