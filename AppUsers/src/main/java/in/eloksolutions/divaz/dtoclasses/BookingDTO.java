package in.eloksolutions.divaz.dtoclasses;

import java.util.Date;

/**
 * Created by welcome on 2/24/2018.
 */

public class BookingDTO {
    String id;
    String customerName;
    String customerId;
    String orderItems;
    String totalPrice;
    String orderDate;
    String email;
    String customerPhone;
    Date apointMentDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(String orderItems) {
        this.orderItems = orderItems;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Date getApointMentDate() {
        return apointMentDate;
    }

    public void setApointMentDate(Date apointMentDate) {
        this.apointMentDate = apointMentDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    @Override
    public String toString() {
        return "BookingDTO{" +
                "id='" + id + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerId='" + customerId + '\'' +
                ", orderItems='" + orderItems + '\'' +
                ", totalPrice='" + totalPrice + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", email='" + email + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", apointMentDate=" + apointMentDate +
                '}';
    }
}
