package com.eloksolutions.evas.model;

import java.util.Date;

public class Gallary {
	Integer id;
	String imagePath;
	String description;
	String category;
	Date createDate;
	public Gallary(){
		
	}
	public Gallary(int id, String imagePath, String description, String category, int createDate) {
		super();
		this.id = id;
		this.imagePath = imagePath;
		this.description = description;
		this.category = category;
		//this.createDate = createDate;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "Gallary [id=" + id + ", imagePath=" + imagePath + ", description=" + description + ", category="
				+ category + ", createDate=" + createDate + "]";
	}
	
	
}
