package in.eloksolutions.evas.model;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.eloksolutions.evas.model.OrderItems.OrderItem;

public class Booking extends CommonVO{
	int id;
	String customerName;
	int customerId;
	String strOrderItems;
	String strOrderDate;
	int totalPrice;
	Date orderDate;
	java.sql.Date sqlOrderDate;
	OrderItems orderItems;
	
	public Booking(){
		orderDate=new Date();
	}
	public Booking(int id, String customerName, int customerId, String strOrderItems, int totalPrice,
			java.sql.Date sqlOrderDate) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.customerId = customerId;
		this.strOrderItems = strOrderItems;
		try {
			;
			orderItems=new OrderItems();
			orderItems.setOrderItems((List<OrderItem>)new ObjectMapper().readValue(strOrderItems, List.class));
		}catch (Exception e) {
			System.out.println(" Error while parsing order items "+e.getMessage());
			//e.printStackTrace();
		}
		
		this.totalPrice = totalPrice;
		this.sqlOrderDate = sqlOrderDate;
		if(sqlOrderDate!=null) {
			orderDate=new Date(sqlOrderDate.getTime());
			strOrderDate=new SimpleDateFormat("dd/MM/yyyy").format(orderDate);
		}
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getStrOrderItems() {
		return strOrderItems;
	}
	public void setStrOrderItems(String strOrderItems) {
		this.strOrderItems = strOrderItems;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
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
