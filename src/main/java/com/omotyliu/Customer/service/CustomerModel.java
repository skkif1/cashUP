package com.omotyliu.Customer.service;

import com.omotyliu.Customer.Customer;
import com.omotyliu.Customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerModel implements CustomerService{


    private CustomerRepository customerRepository;

    @Autowired
    public CustomerModel(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {

        if (customerRepository.getCustomerByINN(customer.getINN()) != null)
                return null;
        customerRepository.saveCustomer(customer);
        customer = customerRepository.getCustomerByINN(customer.getINN());
        return customer;
    }

    @Override
    public void updateCustomer(Customer customer)
    {
        Customer current = customerRepository.getCustomer(customer.getId());
        customerRepository.saveCustomer(customer);
    }

    @Override
    public List<Customer> getAllCustomers(Integer limit) {

        limit = (limit == null || limit < 0) ? Integer.MAX_VALUE : limit;
        List<Customer> customers = customerRepository.getAll(limit);
        return customers;
    }

    @Override
    public Customer getCustomer(Long customerId)
    {
        return customerRepository.getCustomer(customerId);
    }

    @Override
    public List<String> getErrorList(Errors errors) {

        if (errors.hasErrors())
        {
            return errors.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
        }
        return null;
    }
}
