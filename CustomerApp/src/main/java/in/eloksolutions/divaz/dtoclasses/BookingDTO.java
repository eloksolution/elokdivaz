package in.eloksolutions.divaz.dtoclasses;

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

    @Override
    public String toString() {
        return "BookingDTO{" +
                "id='" + id + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerId='" + customerId + '\'' +
                ", orderItems='" + orderItems + '\'' +
                ", totalPrice='" + totalPrice + '\'' +
                ", orderDate='" + orderDate + '\'' +
                '}';
    }
}
