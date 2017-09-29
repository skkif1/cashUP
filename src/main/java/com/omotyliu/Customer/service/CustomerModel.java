package com.omotyliu.Customer.service;

import com.omotyliu.Customer.Customer;
import com.omotyliu.Customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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
    public Boolean updateCustomer(Customer customer)
    {
        Customer current = customerRepository.getCustomer(customer.getId());
        if (current == null)
            return null;
        customerRepository.saveCustomer(customer);
        return null;
    }

    @Override
    public List<Customer> getAllCustomers(Integer limit) {
        List<Customer> customers = customerRepository.getAll(limit);
        return customers;
    }

    @Override
    public Customer getCustomer(Long customerId)
    {
        customerRepository.getCustomer(customerId);
        return null;
    }
}
