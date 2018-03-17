package com.eloksolutions.evas.model;

public class Message {

	int id;
	String subject;
	String description;
	String img_path;
	String create_date;
	String type;
	
	public Message(){
		
	}
	
	public Message(int id, String subject, String description, String img_path, String create_date, String type) {
		super();
		this.id = id;
		this.subject = subject;
		this.description = description;
		this.img_path = img_path;
		this.create_date = create_date;
		this.type = type;
	}
	
	public Message(int id, String subject, String description, String img_path, int create_date, String type) {
		super();
		this.id = id;
		this.subject = subject;
		this.description = description;
		this.img_path = img_path;
	//	this.create_date = create_date;
		this.type = type;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	@Override
	public String toString() {
		return "Message [id=" + id + ", subject=" + subject + ", description=" + description + ", img_path=" + img_path
				+ ", create_date=" + create_date + ", type=" + type + "]";
	}
	
	

}
