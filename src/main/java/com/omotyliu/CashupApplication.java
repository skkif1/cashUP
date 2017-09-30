package com.omotyliu;

import com.omotyliu.Customer.repository.MySqlCustomerRepository;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CashupApplication {

	public static void main(String[] args) {
		SpringApplication.run(CashupApplication.class, args);

	}
}
