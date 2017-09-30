package com.omotyliu.Order;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

public class Order {

    private Long id;
    private LocalDate orderDate;
    private OrderStatus orderStatus;
    private BigDecimal orderSum;
    private Currency currency;
    private Long customerId;

    public Order() {
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getOrderSum() {
        return orderSum;
    }

    public void setOrderSum(BigDecimal orderSum) {
        this.orderSum = orderSum;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderDate=" + orderDate +
                ", orderStatus=" + orderStatus +
                ", orderSum=" + orderSum +
                ", currency=" + currency +
                '}';
    }
}
