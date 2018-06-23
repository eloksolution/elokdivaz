package in.eloksolutions.evas.messaging;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.eloksolutions.evas.http.RestServices;
import in.eloksolutions.evas.model.Customer;
import in.eloksolutions.evas.model.Message;


public class SendNotification {

	private static final String FCM_URL="https://fcm.googleapis.com/fcm/send";
	
	
	public static void sendMessageToUser(String token,String body, String title) {
		String json= buildUserJSON(token,body, title);
		System.out.println("Sending json "+json);
		try {
			RestServices.postJSON(FCM_URL, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String sendMessageToUser(Message message, Integer companyId) {
		String json=getJSONMessage(message,companyId);
		System.out.println("Sending json "+json);
		try {
			RestServices.postJSON(FCM_URL, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	private static String getJSONMessage(Message msg, Integer companyId) {
		return "{\"to\":\"/topics/company-"+companyId+"\",\"data\" : {"
				+ " \"subject\" :\""+msg.getSubject()+"\","
						+ "\"description\" : \""+msg.getDescription()+"\","
								+ "\"img_path\" : \""+msg.getImg_path()+"\"}}";
				
	}

	private static String buildUserJSON(Message message) {
		return messageToJSON(message); 
	}

	private static String messageToJSON(Message message) {
		try {
			 ObjectMapper mapper = new ObjectMapper();
			 return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(message);
       } catch (Exception e) {
           e.printStackTrace();
       }
		return "";
	}

	private static String buildUserJSON(String token, String body, String title) {
		Notification n=new Notification();
		n.setBody(body);
		n.setTitle(title);
		Message m=new Message();
		Request r=new Request();
		try {
			 ObjectMapper mapper = new ObjectMapper();
			 return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(r);
        } catch (Exception e) {
            e.printStackTrace();
        }  
		return null;
	}
	
	public static void main(String[] args) {
		Message m=new Message();
		m.setSubject("June Offer 20%");
		m.setDescription("June Offer 20% off on all the services");
		m.setImg_path("https://s3.amazonaws.com/divaz/siri/services/S__901_1525501267804");
		System.out.println("JSON String is "+sendMessageToUser(m,1));
	}

	public static void sendOfferMessageToUsers(List<Customer> customers,String message,String subject){
		Thread t=new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("Started Sending messages to "+customers.size());
				for(Customer c: customers){
					if(c.getDeviceToken()==null || c.getDeviceToken().isEmpty())continue;
					String msg=buildUserJSON(c.getDeviceToken(), message,subject);
					try {
						RestServices.postJSON(FCM_URL, msg);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				System.out.println("Completed Sending messages to "+customers.size());
				
			}
		});
		t.start();
		
	}
	
}
