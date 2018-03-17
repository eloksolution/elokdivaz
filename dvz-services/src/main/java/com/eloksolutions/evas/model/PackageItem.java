package com.eloksolutions.evas.model;

public class PackageItem {
	Integer id;
	String name;
	Integer item_id;
	Integer price;
	String category;
	String imagePath;
	
	public PackageItem(){
		
	}
	
	
	public PackageItem(int id, int item_id, String name, int price, String category, String imagePath) {
		this.id=id;
		this.item_id=item_id;
		this.name=name;
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


	public Integer getItem_id() {
		return item_id;
	}


	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
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
		return "PackageItem [id=" + id + ", name=" + name + ", item_id=" + item_id + ", price=" + price + ", category="
				+ category + ", imagePath=" + imagePath + "]";
	}
	
	
}
