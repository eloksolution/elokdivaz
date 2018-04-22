package in.eloksolutions.evas.messaging;

public class Message {
	String token;
	Notification notification;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	@Override
	public String toString() {
		return "Message [token=" + token + ", notification=" + notification + "]";
	}
	
}
