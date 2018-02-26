package com.eloksolutions.evas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eloksolutions.evas.model.Customer;
import com.eloksolutions.evas.services.CustomerService;

@RestController
public class CustomerController {
	@Autowired
    private CustomerService customerService;
	 @RequestMapping("/")
	    public String index() {
	        return "Greetings from Spring Boot!";
	    }
	 @RequestMapping("/getCustomers")
	  public List<Customer> findAll() {
		  List<Customer> result =customerService.findAll();
		  System.out.println("in find all result "+result);
		  return result;
	  }
}
