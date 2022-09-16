import java.io.Serializable;
import java.util.*;

public class Order implements Serializable {

    private String orderID;
    private String userId;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String memberStatus;
    private String orderStatus;
    private ArrayList<Products> cart;

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return memberStatus;
    }

    public void setStatus(String memberStatus) {
        this.memberStatus = memberStatus;
    }

    public ArrayList<Products> getCart() {
        return cart;
    }

    public void setCart(ArrayList<Products> cart) {
        this.cart = cart;
    }

    public Order(String orderID, String userId, String firstName, String lastName, String address, String phone, String memberStatus, String orderStatus, ArrayList<Products> cart) {
        this.orderID = orderID;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.memberStatus = memberStatus;
        this.orderStatus = orderStatus;
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", memberStatus='" + memberStatus + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", cart=" + cart +
                '}';
    }
}
