package com.omotyliu.Order;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {


    @RequestMapping(value = {"/all", "/all/{limit}"}, method = RequestMethod.GET)
    public ResponseEntity<List<Order>> getAllOrders(@PathVariable(value = "limit", required = false) Long limit) {
        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        ArrayList<Order> list = new ArrayList<>();
        list.add(order);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @RequestMapping("/{id}")
    public ResponseEntity<List<Order>> getCustomerOrders(@PathVariable("id") Long customerId)
    {
        return new ResponseEntity<List<Order>>(new ArrayList<Order>(), HttpStatus.OK);
    }


    @RequestMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody Order order)
    {
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping("/submit/{orderId}")
    public ResponseEntity<?> submitOrder(@PathVariable("orderId") Long id)
    {
        return  new ResponseEntity<>(HttpStatus.CREATED);
    }
}
