package com.omotyliu.validators;

import com.omotyliu.Customer.Customer;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CustomerValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(Customer.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Customer customer = (Customer) o;

        if (customer == null)
            errors.reject("customer", "invalid data");

        String first = customer.getFirstName();
        String last = customer.getLastName();

        if (first == null || last == null || first.length() == 0 || last.length() == 0 || first.length() > 255 || first.length() > 255)
            errors.reject("name", "invalid name");

        if (customer.getBirthDate() == null)
            errors.reject("date", "invalid date of birth");

        if (customer.getGender() == null)
            errors.reject("gender", "invalid gender");

        if(customer.getInn() == null || customer.getInn().toString().length() != 10)
            errors.reject("INN", "invalid INN");
    }

}
