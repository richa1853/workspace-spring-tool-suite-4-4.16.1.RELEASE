package com.richa.facades;


//import com.richa.dtos.BookDTO;
import com.richa.dtos.CustomerDTO;
import com.richa.entities.Customer;
import com.richa.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.richa.constants.ErrorCode.CUSTOMER_ALREADY_EXISTS;
import static com.richa.constants.ErrorCode.CUSTOMER_NOT_FOUND;

@Service
public class CustomerFacade {

    @Autowired
    private CustomerService customerService;

    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
    	Optional<CustomerDTO> custOptional=findCustomerByEmail(customerDTO.getEmail());
    	CustomerDTO cusDTO=null;
        if(custOptional.isPresent()) {
        	cusDTO=custOptional.get();
        	if(cusDTO.getId()==null && cusDTO.getError()!=null && cusDTO.getError().length()>0) {
        		Customer customer=new Customer();           	
            	customer.setName(customerDTO.getName());
            	customer.setSurname(customerDTO.getSurname());
            	customer.setAddress(customerDTO.getAddress());
            	customer.setEmail(customerDTO.getEmail());
                customerService.save(customer);

               cusDTO= findCustomerByEmail(customerDTO.getEmail()).get();
        	}else {
        		cusDTO=new CustomerDTO();    		
        		cusDTO.setErrorMessage(CUSTOMER_ALREADY_EXISTS.getMessage());
                cusDTO.setError(CUSTOMER_ALREADY_EXISTS.getCode());
        	}
        	
        }  
        return cusDTO;
    }
    
    
    public Optional<CustomerDTO> findCustomerByEmail(String email){
        Optional<Customer> customerOptional = customerService.findCustomerByEmail(email);
        CustomerDTO customerDTO = new CustomerDTO();
        if(!customerOptional.isPresent()) {
            customerDTO.setErrorMessage(CUSTOMER_NOT_FOUND.getMessage());
            customerDTO.setError(CUSTOMER_NOT_FOUND.getCode());
            return Optional.of(customerDTO);
        } 
        else {
            Customer customer = customerOptional.get();
            System.out.println(customer.getPurchases());
            customerDTO = CustomerDTO.fromCustomer(customer);
            return Optional.of(customerDTO);
        }
    }
    public List<CustomerDTO> findAll(){
    	return customerService.findAll()//returns entity obj list of customers and converting entity into dto
                .stream()
                .map(CustomerDTO::fromCustomer)
                .collect(Collectors.toList()); 
    }
    
}