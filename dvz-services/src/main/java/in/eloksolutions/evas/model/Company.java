package in.eloksolutions.evas.model;

import java.sql.Date;


public class Company {
	Integer id;
	String name;
	String descriptioin;
	String code;
	String address;
	String officePhone;
	String ownerPhone;
	String schema;
	String status;
	Date createDate;
	Date updatedDate;
	Integer updatedBy;
	String address_1;
	String address_2;
	String city;
	String state;
	String latitude;
	String longitude;
	String email;
	String firstName;
	String lastName;
	String password;
	String linkedin;
	String facebook;
	String whatsapp;
	String para1;
	String para2;
	String para3;
	String imgPath1;
	String imgPath2;
	String imgPath3;
	String imgPath4;
	String imgPath5;
	
	public Company(){
		
	}

	

	public Company(Integer id, String name, String descriptioin, String code,
			String address, String officePhone, String ownerPhone,
			 String schema, String status,
			Date createDate, Date updatedDate, Integer updatedBy,
			String address_1, String address_2, String city, String state,
			String latitude, String longitude, String email, String firstName,
			String lastName, String password, String linkedin, String facebook,
			String whatsapp, String para1, String para2, String para3,
			String imgPath1, String imgPath2, String imgPath3, String imgPath4, String imgPath5) {
		super();
		this.id = id;
		this.name = name;
		this.descriptioin = descriptioin;
		this.code = code;
		this.address = address;
		this.officePhone = officePhone;
		this.ownerPhone = ownerPhone;
		this.schema = schema;
		this.status = status;
		this.createDate = createDate;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
		this.address_1 = address_1;
		this.address_2 = address_2;
		this.city = city;
		this.state = state;
		this.latitude = latitude;
		this.longitude = longitude;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.linkedin = linkedin;
		this.facebook = facebook;
		this.whatsapp = whatsapp;
		this.para1 = para1;
		this.para2 = para2;
		this.para3 = para3;
		this.imgPath1 = imgPath1;
		this.imgPath2 = imgPath2;
		this.imgPath3 = imgPath3;
		this.imgPath4 = imgPath4;
		this.imgPath5 = imgPath5;
	}



	public Company(int id, String name, String code, String dbschema, String description, String office_phone,
			String owner_phone, String status, String create_date, String updated_date, int updated_by, String address,
			String address_1, String address_2, String city, String state, String latitude, String longitude,
			String email, String first_name, String last_name, String password, String linkedin, String whatsapp,
			String facebook, String para1, String para_2, String para_3, String img_path1, String img_path2,
			String img_path3, String img_path4,
			String img_path5) {
		super();
		this.id = id;
		this.name = name;
		this.descriptioin = description;
		this.code = code;
		this.address = address;
		this.officePhone = office_phone;
		this.ownerPhone = owner_phone;
		this.schema = dbschema;
		this.status = status;
		//this.createDate = create_date;
		//this.updatedDate = updatedDate;
		this.updatedBy = updated_by;
		this.address_1 = address_1;
		this.address_2 = address_2;
		this.city = city;
		this.state = state;
		this.latitude = latitude;
		this.longitude = longitude;
		this.email = email;
		this.firstName = first_name;
		this.lastName = last_name;
		this.password = password;
		this.linkedin = linkedin;
		this.facebook = facebook;
		this.whatsapp = whatsapp;
		this.para1 = para1;
		this.para2 = para_2;
		this.para3 = para_3;
		this.imgPath1 = img_path1;
		this.imgPath2 = img_path2;
		this.imgPath3 = img_path3;
		this.imgPath4 = img_path4;
		this.imgPath5 = img_path5;
		
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescriptioin() {
		return descriptioin;
	}

	public String getCode() {
		return code;
	}

	public String getAddress() {
		return address;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public String getOwnerPhone() {
		return ownerPhone;
	}

	public String getSchema() {
		return schema;
	}

	public String getStatus() {
		return status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public String getAddress_1() {
		return address_1;
	}

	public String getAddress_2() {
		return address_2;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPassword() {
		return password;
	}

	public String getLinkedin() {
		return linkedin;
	}

	public String getFacebook() {
		return facebook;
	}

	public String getWhatsapp() {
		return whatsapp;
	}

	public String getPara1() {
		return para1;
	}

	public String getPara2() {
		return para2;
	}

	public String getPara3() {
		return para3;
	}

	public String getImgPath1() {
		return imgPath1;
	}

	public String getImgPath2() {
		return imgPath2;
	}

	public String getImgPath3() {
		return imgPath3;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescriptioin(String descriptioin) {
		this.descriptioin = descriptioin;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public void setAddress_1(String address_1) {
		this.address_1 = address_1;
	}

	public void setAddress_2(String address_2) {
		this.address_2 = address_2;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String first_name) {
		this.firstName = first_name;
	}

	public void setLastName(String last_name) {
		this.lastName = last_name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}

	public void setPara1(String para1) {
		this.para1 = para1;
	}

	public void setPara2(String para2) {
		this.para2 = para2;
	}

	public void setPara3(String para3) {
		this.para3 = para3;
	}

	public void setImgPath1(String imgPath1) {
		this.imgPath1 = imgPath1;
	}

	public void setImgPath2(String imgPath2) {
		this.imgPath2 = imgPath2;
	}

	public void setImgPath3(String imgPath3) {
		this.imgPath3 = imgPath3;
	}

	public String getImgPath4() {
		return imgPath4;
	}



	public void setImgPath4(String imgPath4) {
		this.imgPath4 = imgPath4;
	}



	public String getImgPath5() {
		return imgPath5;
	}



	public void setImgPath5(String imgPath5) {
		this.imgPath5 = imgPath5;
	}



	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", descriptioin="
				+ descriptioin + ", code=" + code + ", address=" + address
				+ ", officePhone=" + officePhone + ", ownerPhone=" + ownerPhone
				+ ", companyId="
				+ ", schema=" + schema + ", status=" + status
				+ ", createDate=" + createDate + ", updatedDate=" + updatedDate
				+ ", updatedBy=" + updatedBy + ", address_1=" + address_1
				+ ", address_2=" + address_2 + ", city=" + city + ", state="
				+ state + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", email=" + email + ", first_name=" + firstName
				+ ", last_name=" + lastName + ", password=" + password
				+ ", linkedin=" + linkedin + ", facebook=" + facebook
				+ ", whatsapp=" + whatsapp + ", para1=" + para1 + ", para2="
				+ para2 + ", para3=" + para3 + ", imgPath1=" + imgPath1
				+ ", imgPath2=" + imgPath2 + ", imgPath3=" + imgPath3 + "]";
	}

	
}
