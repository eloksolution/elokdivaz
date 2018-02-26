package com.eloksolutions.evas.model;

import java.util.Date;

public class Messages {
	Integer id;
	String subject;
	String description;
	String imgPath;
	String type;
	Date createDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "Messages [id=" + id + ", subject=" + subject + ", description=" + description + ", imgPath=" + imgPath
				+ ", type=" + type + ", createDate=" + createDate + "]";
	}
	
	
}
