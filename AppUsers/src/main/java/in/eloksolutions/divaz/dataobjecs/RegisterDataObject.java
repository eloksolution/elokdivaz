package in.eloksolutions.divaz.dataobjecs;

/**
 * Created by welcome on 7/17/2017.
 */

public class RegisterDataObject {
    String userId;
    String firstName;
    String lastName;
    String mobile;
    String email;
    String area;
    String city;
    String state;
    String password;
    String imgPath;
    double longi;
    double lati;
    String toUserId;
    String toFirstName;
    String toLastName;
    String requestSent;
    String connected;

    public RegisterDataObject(String userId, String firstName, String lastName, String mobile, String email, String area, String city, String state, String password, String imgPath, double longi, double lati, String toUserId, String toFirstName, String toLastName, String requestSent, String connected) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.email = email;
        this.area = area;
        this.city = city;
        this.state = state;
        this.password = password;
        this.imgPath = imgPath;
        this.longi = longi;
        this.lati = lati;
        this.toUserId = toUserId;
        this.toFirstName = toFirstName;
        this.toLastName = toLastName;
        this.requestSent = requestSent;
        this.connected = connected;
    }

    public double getLongi() {
        return longi;
    }

    public void setLongi(double longi) {
        this.longi = longi;
    }

    public double getLati() {
        return lati;
    }

    public void setLati(double lati) {
        this.lati = lati;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;

    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getToFirstName() {
        return toFirstName;
    }

    public void setToFirstName(String toFirstName) {
        this.toFirstName = toFirstName;
    }

    public String getToLastName() {
        return toLastName;
    }

    public void setToLastName(String toLastName) {
        this.toLastName = toLastName;
    }

    public String getRequestSent() {
        return requestSent;
    }

    public void setRequestSent(String requestSent) {
        this.requestSent = requestSent;
    }

    public String getConnected() {
        return connected;
    }

    public void setConnected(String connected) {
        this.connected = connected;
    }

    @Override
    public String toString() {
        return "RegisterDTO{" +
                "userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", area='" + area + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", password='" + password + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", longi=" + longi +
                ", lati=" + lati +
                ", toUserId='" + toUserId + '\'' +
                ", toFirstName='" + toFirstName + '\'' +
                ", toLastName='" + toLastName + '\'' +
                ", requestSent='" + requestSent + '\'' +
                ", connected='" + connected + '\'' +
                '}';
    }
}
