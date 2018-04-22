package in.eloksolutions.evas.messaging;

public class Notification {
	String body;
	String title;
	
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Notification [body=" + body + ", title=" + title + "]";
	}
	
}
