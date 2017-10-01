package com.omotyliu.Customer.service;

import com.omotyliu.Customer.Customer;
import com.omotyliu.Customer.CustomerService;
import com.omotyliu.exceptions.CustomerNotFoundException;
import com.omotyliu.exceptions.UserAlreadyExistException;
import org.springframework.beans.BeanUtils;
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
        Customer ifExist = null;
        try {
             ifExist = customerRepository.getCustomerByINN(customer.getInn());
        }catch (CustomerNotFoundException ex)
        {
            //NOP
        }
        if (ifExist != null)
            throw new UserAlreadyExistException();
        customerRepository.saveCustomer(customer);
        customer = customerRepository.getCustomerByINN(customer.getInn());
        return customer;
    }

    @Override
    public void updateCustomer(Customer customer)
    {
        Customer current = customerRepository.getCustomer(customer.getId());
        BeanUtils.copyProperties(customer, current, "id", "inn");
        customerRepository.saveCustomer(current);
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
