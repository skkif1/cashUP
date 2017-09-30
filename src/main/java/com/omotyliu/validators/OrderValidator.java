package com.omotyliu.validators;

import com.omotyliu.Order.Order;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class OrderValidator implements Validator{
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Order.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Order order = (Order) target;
        if (order == null)
            errors.reject("order", "invalid data");
        if (order.getOrderSum() == null)
            errors.reject("summ", "invalid summ");
    }
}
