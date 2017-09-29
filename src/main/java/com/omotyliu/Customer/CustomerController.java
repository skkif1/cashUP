package com.omotyliu.Customer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer)
    {
        if (customer != null)
        {
            Customer newCustomer = customerService.createCustomer(customer);
            return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
        }else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer)
    {
        if (customer != null)
        {
            customerService.updateCustomer(customer);
            return new ResponseEntity<String>(HttpStatus.OK);
        }
        return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{id}",  method = RequestMethod.GET)
    public ResponseEntity<Customer> getCustomer(@PathVariable(value = "id") Long id)
    {

        Customer customer = new Customer();
        customer.setFirstName("LOll");
        customerService.getCustomer(id);
        //return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @RequestMapping(value = {"/all","/all/{limit}"}, method = RequestMethod.GET)
    public ResponseEntity<String> getAllCustomers(@PathVariable(value = "limit", required = false) Integer limit)
    {
        limit = (limit != null) ? limit : Integer.MAX_VALUE;
        customerService.getAllCustomers(limit);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
