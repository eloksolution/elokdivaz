package com.eloksolutions.evas.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.eloksolutions.evas.model.Booking;
import com.eloksolutions.evas.model.Context;

@Repository("bookingsDAO")
public class BookingsDAOImpl implements BookingsDAO{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public List<Booking> findAll(Context ctx) {
		System.out.println("Context "+ctx);
		return jdbcTemplate.query(
                "SELECT ID,CUSTOMER_NAME,CUSTOMER_ID,ORDER_DATE,ORDER_ITEMS,TOTAL_PRICE FROM "+ctx.getSchema()+".BOOKINGS",
                (rs, rowNum) -> new Booking(rs.getInt("ID"),
                        rs.getString("CUSTOMER_NAME"), rs.getInt("CUSTOMER_ID"),
                        rs.getString("ORDER_ITEMS"),rs.getInt("TOTAL_PRICE"),
                        rs.getDate("ORDER_DATE")
                        )
        );
	}

	@Override
	public List<Booking> findByColumn(String columnName, String columnValue, Context ctx) {
		return jdbcTemplate.query(
                "SELECT ID,CUSTOMER_NAME,CUSTOMER_ID,ORDER_DATE,ORDER_ITEMS,TOTAL_PRICE FROM "+ctx.getSchema()+".BOOKINGS"
                		+ " WHERE "+columnName+" = '"+columnValue+"'",
                (rs, rowNum) -> new Booking(rs.getInt("ID"),
                        rs.getString("CUSTOMER_NAME"), rs.getInt("CUSTOMER_ID"),
                        rs.getString("ORDER_ITEMS"),rs.getInt("TOTAL_PRICE"),
                        rs.getDate("ORDER_DATE")
                        )
        );
	}

	@Override
	public List<Booking> findNext(Integer noOfRecords, Context ctx) {
		return null;
	}

	@Override
	public Booking findById(Integer id, Context ctx) {
		return jdbcTemplate.queryForObject(
                "SELECT ID,CUSTOMER_NAME,CUSTOMER_ID,ORDER_DATE,ORDER_ITEMS,TOTAL_PRICE FROM "+ctx.getSchema()+".BOOKINGS"
                		+ " WHERE id=?",
                		new Object[] { id },
                (rs, rowNum) -> new Booking(rs.getInt("ID"),
                        rs.getString("CUSTOMER_NAME"), rs.getInt("CUSTOMER_ID"),
                        rs.getString("ORDER_ITEMS"),rs.getInt("TOTAL_PRICE"),
                        rs.getDate("ORDER_DATE")
                        )
        );
	}

	@Override
	public Integer add(Booking model, Context ctx) {
		return jdbcTemplate.update(
                "INSERT INTO "+ctx.getSchema()+".BOOKINGS ( CUSTOMER_NAME, CUSTOMER_ID, ORDER_DATE, ORDER_ITEMS, TOTAL_PRICE) VALUES (?, ?, ?, ?, ?)",
                new Object[] { model.getCustomerName(),model.getCustomerId(),model.getOrderDate(),
                		model.getStrOrderItems(),model.getTotalPrice()
                }
        );
	}

	@Override
	public Integer update(Booking model, Context ctx) {
		return jdbcTemplate.update(
                "UPDATE "+ctx.getSchema()+".BOOKINGS CUSTOMER_NAME = ?, CUSTOMER_ID = ?, ORDER_TIME = ?, ORDER_ITEMS = ?, TOTAL_PRICE = ? WHERE ID = ?",
                new Object[] { model.getCustomerName(),model.getCustomerId(),model.getOrderDate(),
                		model.getStrOrderItems(),model.getTotalPrice(), model.getId()
                }
        );
	}

	@Override
	public Integer delete(Booking model, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}

}
