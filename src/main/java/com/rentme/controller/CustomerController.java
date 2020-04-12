package com.rentme.controller;

import com.rentme.models.Customer;
import com.rentme.models.Vehicle;
import com.rentme.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/add/customer")
    public ResponseEntity addCustomer(@Valid @RequestBody final Customer customer){
        return ResponseEntity.ok(customerService.postCustomer(customer));
    }

    @PutMapping("/update/customer/{customerId}")
    public ResponseEntity updateCustomer(@PathVariable String customerId,
                                            @RequestParam Optional<String> address,
                                            @RequestParam Optional<String> mobile,
                                            @RequestParam Optional<String> emailId){
        return ResponseEntity
                .accepted()
                .body(customerService.updateCustomerDetails(customerId, address, mobile, emailId));
    }

    @DeleteMapping("/delete/customer/{customerId}")
    public ResponseEntity deleteCustomer(@PathVariable String customerId){
        return ResponseEntity
                .accepted()
                .body(customerService.deleteCustomer(customerId));
    }

    @GetMapping("/get/customer/{customerId}")
    public ResponseEntity getCustomerInfo(@PathVariable final String customerId){
        return ResponseEntity.ok(customerService.getCustomer(customerId));
    }
}