package com.eloksolutions.evas.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.eloksolutions.evas.model.Customer;
@Repository("customerDAO")
public class CustomerDAOImpl implements CustomerDAO{
	@Autowired
    private JdbcTemplate jdbcTemplate;
	 public List<Customer> findAll() {
		 System.out.println("after schema");
		/*  List<Customer> result = jdbcTemplate.query(
	                "SELECT id, name, email, created_date FROM chitsdb.customer",
	                (rs, rowNum) -> new Customer(rs.getInt("id"),
	                        rs.getString("name"), rs.getString("email"), rs.getDate("created_date"))
	        );*/
		  System.out.println("in find all result ");
		  return null;
	 }
	@Override
	public Integer add(Customer customer) {
		return null;
	}
}
