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
	Date orderDate=new Date();
	java.sql.Timestamp sqlOrderDate;
	OrderItems orderItems;
	String email;
	String phone;
	String status;
	String statusInt;
	Date updatedDate=new Date();
	String calEventiD;
	
	public Booking(){
		orderDate=new Date();
	}
	public Booking(int id, String customerName, int customerId, String strOrderItems, int totalPrice,
			java.sql.Timestamp sqlOrderDate,String email,String phone) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.customerId = customerId;
		this.strOrderItems = strOrderItems;
		this.email=email;
		this.phone=phone;
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
			strOrderDate=new SimpleDateFormat("E, dd MMM yyyy HH:mm").format(orderDate);
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
	public java.sql.Timestamp getSqlOrderDate() {
		return sqlOrderDate;
	}
	public void setSqlOrderDate(java.sql.Timestamp sqlOrderDate) {
		this.sqlOrderDate = sqlOrderDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusInt() {
		return statusInt;
	}
	public void setStatusInt(String statusInt) {
		this.statusInt = statusInt;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getCalEventiD() {
		return calEventiD;
	}
	public void setCalEventiD(String calEventiD) {
		this.calEventiD = calEventiD;
	}
	@Override
	public String toString() {
		return "Booking [id=" + id + ", customerName=" + customerName + ", customerId=" + customerId
				+ ", strOrderItems=" + strOrderItems + ", strOrderDate=" + strOrderDate + ", totalPrice=" + totalPrice
				+ ", orderItems=" + orderItems + "]";
	}
	
	
}
