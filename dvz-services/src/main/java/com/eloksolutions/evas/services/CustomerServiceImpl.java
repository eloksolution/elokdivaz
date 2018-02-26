package com.eloksolutions.evas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eloksolutions.evas.dao.CustomerDAO;
import com.eloksolutions.evas.model.Customer;

@Repository("customerService")
public class CustomerServiceImpl implements CustomerService{
	@Autowired
    private CustomerDAO customerDAO;
	 public List<Customer> findAll() {
		 return customerDAO.findAll();
	 }
}
