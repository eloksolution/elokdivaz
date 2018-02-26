package com.eloksolutions.evas.model;

import java.util.Date;
import java.util.List;

import com.eloksolutiions.evas.vo.Parlour;

public class Customer {
    int id;
    String firstName;
    String lastName;
    String email;
    String address1;
    String address2;
    String city;
    String state;
    Date date;
    List<Parlour> myParalours;
    Date createDate;
    Date updateDate;
    
	public int getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmail() {
		return email;
	}
	public String getAddress1() {
		return address1;
	}
	public String getAddress2() {
		return address2;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public Date getDate() {
		return date;
	}
	public List<Parlour> getMyParalours() {
		return myParalours;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setMyParalours(List<Parlour> myParalours) {
		this.myParalours = myParalours;
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
	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", address1=" + address1 + ", address2=" + address2 + ", city=" + city + ", state=" + state
				+ ", date=" + date + ", myParalours=" + myParalours + ", createDate=" + createDate + ", updateDate="
				+ updateDate + "]";
	}
   
}
