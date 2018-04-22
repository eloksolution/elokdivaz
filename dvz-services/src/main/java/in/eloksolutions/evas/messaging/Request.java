package in.eloksolutions.evas.messaging;

public class Request {
	Message message;

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Request [message=" + message + "]";
	}
	
}
