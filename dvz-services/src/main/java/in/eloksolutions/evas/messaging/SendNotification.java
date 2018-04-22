package in.eloksolutions.evas.messaging;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.eloksolutions.evas.http.RestServices;
import in.eloksolutions.evas.model.Customer;


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


	private static String buildUserJSON(String token, String body, String title) {
		Notification n=new Notification();
		n.setBody(body);
		n.setTitle(title);
		Message m=new Message();
		m.setToken(token);
		m.setNotification(n);
		Request r=new Request();
		r.setMessage(m);
		try {
			 ObjectMapper mapper = new ObjectMapper();
			 return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(r);
        } catch (Exception e) {
            e.printStackTrace();
        }  
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println("JSON String is "+buildUserJSON("SDFDTOKEN", "oFFERS MESSAGE", "New Offer"));
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
