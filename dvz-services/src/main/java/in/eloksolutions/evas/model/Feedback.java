package in.eloksolutions.evas.model;

import java.sql.Timestamp;
import java.util.Date;

public class Feedback {

	int id;
	int serviceId;
	String description;
	String type;
	int userId;
	Date createDate;
	Timestamp createTimestamp;
	int rating;
	
	
	
	public Feedback() {
		super();
	}

	public Feedback(int id, int serviceId, String description, String type, int userId, Timestamp createTimestamp) {
		super();
		this.id = id;
		this.serviceId = serviceId;
		this.description = description;
		this.type = type;
		this.userId = userId;
		this.createTimestamp=createTimestamp;
		if(createTimestamp!=null){
			this.createDate = new Date(createTimestamp.getTime());
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	
	
}
