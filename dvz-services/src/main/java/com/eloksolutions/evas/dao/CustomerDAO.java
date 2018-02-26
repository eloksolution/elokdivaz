package com.eloksolutions.evas.dao;

import java.util.List;

import com.eloksolutions.evas.model.Customer;

public interface CustomerDAO {
	 public List<Customer> findAll() ;
	 public Integer add(Customer customer) ;
}
