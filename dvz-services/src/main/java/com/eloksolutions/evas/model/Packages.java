package com.eloksolutions.evas.model;

public class Packages {
	Integer id;
	String name;
	String description;
	Integer price;
	String category;
	String imagePath;
	
	public Packages(){
		
	}
	
	public Packages(int id, String name, String description, int price, String category, String imagePath) {
		this.id=id;
		this.name=name;
		this.description=description;
		this.price=price;
		this.category=category;
		this.imagePath=imagePath;
		
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	@Override
	public String toString() {
		return "Packages [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", category=" + category + ", imagePath=" + imagePath + "]";
	}
	
	
}
