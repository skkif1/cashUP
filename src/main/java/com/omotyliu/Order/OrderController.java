package com.omotyliu.Order;


import com.omotyliu.exceptions.OrderNotFoundException;
import com.omotyliu.validators.OrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {


    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @InitBinder
    private void InitBinder(WebDataBinder webDataBinder)
    {
        webDataBinder.addValidators(new OrderValidator());
    }

    @RequestMapping(value = {"/all", "/all/{limit}"}, method = RequestMethod.GET)
    public ResponseEntity<List<Order>> getAllOrders(@PathVariable(value = "limit", required = false) Integer limit) {
        limit = (limit != null) ? limit: Integer.MAX_VALUE;
        List<Order> orders = orderService.getAllOrders(limit);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }


    @RequestMapping("/{id}")
    public ResponseEntity<List<Order>> getCustomerOrders(@PathVariable("id") Long customerId)
    {
        List<Order> customerOrders = orderService.getClientsOrders(customerId);
        return new ResponseEntity<>(customerOrders, HttpStatus.OK);
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createOrder(@Valid @RequestBody Order order, Errors errors)
    {
        List<String> messages = orderService.getErrorList(errors);
        if (messages != null)
            return  new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
        order = orderService.createOrder(order);
        return  new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @RequestMapping("/submit/{orderId}")
    public ResponseEntity<?> submitOrder(@PathVariable("orderId") Long id)
    {
        orderService.confirmOrder(id);
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }
}
