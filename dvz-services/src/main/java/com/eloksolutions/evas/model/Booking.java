package com.eloksolutions.evas.model;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking extends CommonVO{
	Integer id;
	String customerName;
	Integer customerId;
	String strOrderItems;
	String strOrderDate;
	Integer totalPrice;
	Date orderDate;
	java.sql.Date sqlOrderDate;
	OrderItems orderItems;
	public Booking(){
		orderDate=new Date();
	}
	public Booking(Integer id, String customerName, Integer customerId, String strOrderItems, Integer totalPrice,
			java.sql.Date sqlOrderDate) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.customerId = customerId;
		this.strOrderItems = strOrderItems;
		orderItems=new OrderItems(strOrderItems);
		this.totalPrice = totalPrice;
		this.sqlOrderDate = sqlOrderDate;
		if(sqlOrderDate!=null) {
			orderDate=new Date(sqlOrderDate.getTime());
			strOrderDate=new SimpleDateFormat("dd/MM/yyyy").format(orderDate);
		}
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getStrOrderItems() {
		return strOrderItems;
	}
	public void setStrOrderItems(String strOrderItems) {
		this.strOrderItems = strOrderItems;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public OrderItems getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(OrderItems orderItems) {
		this.orderItems = orderItems;
	}
	
	
	public String getStrOrderDate() {
		return strOrderDate;
	}
	public void setStrOrderDate(String strOrderDate) {
		this.strOrderDate = strOrderDate;
	}
	@Override
	public String toString() {
		return "Booking [id=" + id + ", customerName=" + customerName + ", customerId=" + customerId
				+ ", strOrderItems=" + strOrderItems + ", strOrderDate=" + strOrderDate + ", totalPrice=" + totalPrice
				+ ", orderItems=" + orderItems + "]";
	}
	
	
}
