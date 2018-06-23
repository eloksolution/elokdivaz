package in.eloksolutions.evas.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import in.eloksolutions.evas.vo.Parlour;

public class Customer {
    int id;
    String firstName;
    String lastName;
    String email;
    String address1;
    String address2;
    String city;
    String state;
    Set<Parlour> myParalours;
    String strMyParalours;
    Date createDate;
    Date updateDate;
    String latitude;
	String longitude;
	String phone;
	String imagePath;
	String deviceToken;
	
	
	public Customer() {
		super();
	}
	public Customer(int id, String firstName, String lastName, String email, String address1, String address2,
			String city, String state, Set<Parlour> strMyParalours, Date createDate, Date updateDate,
			String latitude, String longitude, String phone, String imagePath,String deviceToken) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.myParalours = myParalours;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.latitude = latitude;
		this.longitude = longitude;
		this.phone = phone;
		this.imagePath = imagePath;
		this.deviceToken=deviceToken;
	}

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
	
	public Set<Parlour> getMyParalours() {
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
	
	public void setMyParalours(Set<Parlour> myParalours) {
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
	
	
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", address1=" + address1 + ", address2=" + address2 + ", city=" + city + ", state=" + state
				+ ", myParalours=" + myParalours + ", createDate=" + createDate + ", updateDate="
				+ updateDate + ", latitude=" + latitude + ", longitude=" + longitude + ", phone=" + phone
				+ ", imagePath=" + imagePath + "]";
	}
   
}
