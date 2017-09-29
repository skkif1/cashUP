package com.omotyliu.Customer;


import java.util.List;

public interface CustomerService {

    public Customer createCustomer(Customer customer);
    public Boolean updateCustomer(Customer customer);
    public List<Customer> getAllCustomers(Integer limit);
    public Customer getCustomer(Long customerId);
}
