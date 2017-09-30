package com.omotyliu.Customer;


import org.springframework.validation.Errors;

import java.util.List;

public interface CustomerService {

    public Customer createCustomer(Customer customer);
    public void updateCustomer(Customer customer);
    public List<Customer> getAllCustomers(Integer limit);
    public Customer getCustomer(Long customerId);
    public List<String> getErrorList(Errors error);
}
