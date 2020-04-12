package com.rentme.services;

import com.rentme.models.Customer;
import com.rentme.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer postCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomerDetails(String customerId,
                                          Optional<String> address,
                                          Optional<String> mobile,
                                          Optional<String> emailId) {

        Optional<Customer> customer = customerRepository.findById(customerId);
        if(!customer.isPresent())
            return null;
        Customer currentCustomer = customer.get();
        if(address.isPresent())
            currentCustomer.setAddress(address.get());
        if(mobile.isPresent())
            currentCustomer.setMobile(mobile.get());
        if(emailId.isPresent())
            currentCustomer.setEmailId(emailId.get());
        customerRepository.save(currentCustomer);
        return currentCustomer;
    }

    public String deleteCustomer(String customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(!customer.isPresent())
            return "Invalid Customer Id";
        customerRepository.deleteById(customerId);
        return "Successfully deleted";
    }

    public Optional<Customer> getCustomer(String customerId) {
        return customerRepository.findById(customerId);
    }
}