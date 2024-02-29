import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String rowId;
    private String customerId;
    private String customerName;
    private String segment;
    private String country;
    private String city;
    private String state;
    private String postalCode;
    private String region;
    private List<Order> orders;

    public Customer(String rowId, String customerId, String customerName, String segment, String country, String city, String state, String postalCode, String region) {
        this.rowId = rowId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.segment = segment;
        this.country = country;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.region = region;
        this.orders = new ArrayList<>();//each customer has a list of orders.
    }

    public String getRowId() {
        return rowId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getSegment() {
        return segment;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getRegion() {
        return region;
    }
    // Getter for orders. to be used in the Order class. purpose is to add orders to a customer.
    //and to get the orders of the customer.
    public List<Order> getOrders() {
        return orders;
    }
    // //add one order to the list of orders.so that each customer has a list of orders.
    public void addOrder(Order order) {
        orders.add(order);
    }
}
