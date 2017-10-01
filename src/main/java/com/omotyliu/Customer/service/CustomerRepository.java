package com.omotyliu.Customer.service;

import com.omotyliu.Customer.Customer;

import java.util.List;

public interface CustomerRepository {

    public Customer getCustomer(Long id);
    public void saveCustomer(Customer customer);
    public List<Customer> getAll(Integer limit);
    public Customer getCustomerByINN(Long customerINN);
}
