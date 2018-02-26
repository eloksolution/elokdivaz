package com.eloksolutions.evas.model;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class OrderItems {
	List<OrderItem> orderItems;

	public OrderItems(String strOrderItems){
		try {
			orderItems= (List<OrderItem>)new ObjectMapper().readValue(strOrderItems, List.class);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
public OrderItems(){
		
	}
	class OrderItem{
		Integer id;
		String serviceName;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getServiceName() {
			return serviceName;
		}
		public void setServiceName(String serviceName) {
			this.serviceName = serviceName;
		}
		@Override
		public String toString() {
			return "OrderItem [id=" + id + ", serviceName=" + serviceName + "]";
		}
		
	}
	
	public static List<OrderItem> parse(String strOrderItems) throws Exception{
		 return (List<OrderItem>)new ObjectMapper().readValue(strOrderItems, List.class);
	}
	
	public static String format(OrderItems orderItems) throws Exception{
		 return new ObjectMapper().writeValueAsString(orderItems);
	}
}
