package com.omotyliu.Customer.repository;


import com.omotyliu.Customer.Customer;
import com.omotyliu.Customer.service.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostgreCustomerRepository implements CustomerRepository {

    @Override
    public Customer getCustomer(Long id) {
        System.out.println("return Customer");
        return null;
    }

    @Override
    public void saveCustomer(Customer order) {
        System.out.println("save Customer");
    }

    @Override
    public List<Customer> getAll(Integer limit)
    {
        System.out.println("return all customers");
        return null;
    }

    @Override
    public Customer getCustomerByINN(Long customerINN) {
        System.out.println("return Customer");
        return null;
    }
}
