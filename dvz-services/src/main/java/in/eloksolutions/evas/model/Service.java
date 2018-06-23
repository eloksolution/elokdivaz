package in.eloksolutions.evas.model;

import java.sql.Date;

public class Service {
	int id;
	String name;
	String description;
	int price;
	int discount;
	String imagePath;
	String imageIcon;
	Date createDate;
	Date updateDate;
	int rating;
	int noOfRating;
	
	public Service(){
		
	}
			
	public Service(int id, String name, String description, String imagePath, int price, 
			int discount, String imageIcon,
			Date createDate, Date updateDate,int rating,int noOfRating) {
		this.id=id;
		this.name=name;
		this.description=description;
		this.imagePath=imagePath;
		this.price=price;
		this.discount=discount;
		this.imageIcon=imageIcon;
		this.imageIcon=imageIcon;
		this.createDate=createDate;
		this.updateDate=updateDate;
		this.rating=rating;
		this.noOfRating=noOfRating;
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
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getImageIcon() {
		return imageIcon;
	}
	public void setImageIcon(String imageIcon) {
		this.imageIcon = imageIcon;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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

	public void setPrice(int price) {
		this.price = price;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getNoOfRating() {
		return noOfRating;
	}

	public void setNoOfRating(int noOfRating) {
		this.noOfRating = noOfRating;
	}

	@Override
	public String toString() {
		return "Service [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", discount=" + discount + ", imagePath=" + imagePath + ", imageIcon=" + imageIcon + ", createDate="
				+ createDate + ", updateDate=" + updateDate + "]";
	}
	
	
}
