package com.omotyliu.Order.repository;

import com.omotyliu.Customer.Customer;
import com.omotyliu.Customer.Gender;
import com.omotyliu.Order.Order;
import com.omotyliu.Order.OrderStatus;
import com.omotyliu.Order.service.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;

@Repository
public class MySqlOrderRepository implements OrderRepository {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;


    @Autowired
    public MySqlOrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate).usingGeneratedKeyColumns("id").withTableName("orders");
    }

    @Override
    public Order getOrder(Long id) {
        String sql = "SELECT * FROM orders WHERE id = ?";
        Order order = new Order();
        jdbcTemplate.query(sql, (ResultSet rs) -> fillOrderObject(order, rs), id);
        return order;
    }

    @Override
    public Long saveOrder(Order order) {

        HashMap<String, Object> param = new HashMap<>();
        param.put("creation_date", order.getOrderDate());
        param.put("order_status", order.getOrderStatus());
        param.put("summ", order.getOrderSum());
        param.put("currency", order.getCurrency().getSymbol());
        param.put("customer_id", order.getCustomerId());

        Long orderId  = (Long) simpleJdbcInsert.executeAndReturnKey(param);
        return orderId;
    }

    @Override
    public List<Order> getCustomerOrders(Long clientId) {

        String sql = "SELECT * FROM orders WHERE customer_id = ?";
        List<Order> customerOrders = new ArrayList<>();
        jdbcTemplate.query(sql, (ResultSet rs) ->
        {
            Order order = new Order();
            fillOrderObject(order, rs);
            customerOrders.add(order);
        }, clientId);
        return customerOrders;
    }

    @Override
    public List<Order> getAll(Integer limit) {

        String sql = "SELECT * FROM orders LIMIT  ?";
        List<Order> orders = new ArrayList<>();

        jdbcTemplate.query(sql, (ResultSet rs) ->
        {
            Order order = new Order();
            fillOrderObject(order, rs);
            orders.add(order);
        }, limit);
        return orders;
    }

    private void fillOrderObject(Order order, ResultSet rs) throws SQLException {
        order.setId(rs.getLong("id"));
        order.setOrderDate(rs.getTimestamp("creation_date").toLocalDateTime().toLocalDate());
        order.setOrderStatus(OrderStatus.valueOf(rs.getString("order_status")));
        order.setOrderSum(rs.getBigDecimal("summ"));
        order.setCurrency(Currency.getInstance(rs.getString("currency").toUpperCase()));
        order.setCustomerId(rs.getLong("customer_id"));
    }

}
