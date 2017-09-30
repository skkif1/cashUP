package com.omotyliu.Customer.repository;


import com.omotyliu.Customer.Customer;
import com.omotyliu.Customer.Gender;
import com.omotyliu.Customer.service.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class MySqlCustomerRepository implements CustomerRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public MySqlCustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Customer getCustomer(Long id) {
        String sql = "SELECT * FROM customers WHERE id = ?";

        Customer customer = new Customer();
        jdbcTemplate.query(sql, (ResultSet rs) -> fillCustomerObject(customer, rs), id);
        return customer;
    }

    @Override
    public Customer getCustomerByINN(Long customerINN) {
        String sql = "SELECT * FROM customers WHERE id = ?";

        Customer customer = new Customer();
        jdbcTemplate.query(sql, (ResultSet rs) -> fillCustomerObject(customer, rs), customerINN);
        return null;
    }

    @Override
    public void saveCustomer(Customer customer) {
        String insert = "INSERT INTO customers (first_name, last_name, birthdate, gender, INN) VALUES  (?,?,?,?,?)";
        String update = "UPDATE  customers SET first_name = ?, last_name = ?, birthdate = ?, gender = ?, INN = ? WHERE id = ?";

        if (customer.getId() == null)
            jdbcTemplate.update(insert, customer.getFirstName(), customer.getLastName(), Date.valueOf(customer.getBirthDate()),
                    customer.getGender().toString(), customer.getINN());
        else
            jdbcTemplate.update(update, customer.getFirstName(), customer.getLastName(), Date.valueOf(customer.getBirthDate()),
                    customer.getGender().toString(), customer.getINN(), customer.getId());
    }

    @Override
    public List<Customer> getAll(Integer limit) {
        String select = "SELECT * FROM customers LIMIT ?";

        ArrayList<Customer> allCustomers = new ArrayList<>();
        jdbcTemplate.query(select, (ResultSet rs) ->
        {
            Customer customer = new Customer();
            fillCustomerObject(customer, rs);
            allCustomers.add(customer);
        }, limit);

        System.out.println(limit);
        System.out.println(allCustomers);

        return allCustomers;
    }


    private void fillCustomerObject(Customer customer, ResultSet rs) throws SQLException {
        customer.setId(rs.getLong("id"));
        customer.setFirstName(rs.getString("first_name"));
        customer.setLastName(rs.getString("last_name"));
        customer.setBirthDate(rs.getDate("birthdate").toLocalDate());
        customer.setGender(Gender.valueOf(rs.getString("gender").toUpperCase()));
        customer.setINN(rs.getLong("INN"));
    }
}
