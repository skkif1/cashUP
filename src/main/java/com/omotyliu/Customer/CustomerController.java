package com.omotyliu.Customer;


import com.omotyliu.validators.CustomerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new CustomerValidator());
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> createCustomer(@Valid @RequestBody Customer customer, Errors errors) {

        List<String> messages = customerService.getErrorList(errors);
        if (messages != null)
            return new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);

        Customer newCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer,
                                            @PathVariable("id") Long customerId,
                                            Errors errors) {
        List<String> messages = customerService.getErrorList(errors);
        if (messages != null)
            return new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
        customer.setId(customerId);
        customerService.updateCustomer(customer);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Customer> getCustomer(@PathVariable(value = "id") Long id) {
        Customer customer = customerService.getCustomer(id);
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @RequestMapping(value = {"/all", "/all/{limit}"}, method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getAllCustomers(@PathVariable(value = "limit", required = false) Integer limit) {
        List<Customer> res = customerService.getAllCustomers(limit);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
