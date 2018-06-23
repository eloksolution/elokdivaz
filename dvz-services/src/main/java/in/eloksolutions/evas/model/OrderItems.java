package in.eloksolutions.evas.model;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class OrderItems {
	List<OrderItem> orderItems;

	public OrderItems(String strOrderItems){
		this.orderItems=orderItems;
	}
	
	@Override
	public String toString() {
		return "OrderItems [orderItems=" + orderItems + "]";
	}
	
	public String toJSON() {
		StringBuilder sb=new StringBuilder();
		sb.append("\"orderItems\" :[");
		for(OrderItem o:orderItems){
			sb.append(o.toJSON()+",");
		}
		String json=sb.substring(0,sb.length()-1);
		return json+"]";
	}

public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
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
		
		public String toJSON() {
			return "{\"id\" :" + id + ", \"serviceName\" : " + serviceName + "}";
		}
		
	}
	
	public static List<OrderItem> parse(String strOrderItems) throws Exception{
		 return (List<OrderItem>)new ObjectMapper().readValue(strOrderItems, List.class);
	}
	
	public static String format(OrderItems orderItems) throws Exception{
		 return new ObjectMapper().writeValueAsString(orderItems);
	}
}
