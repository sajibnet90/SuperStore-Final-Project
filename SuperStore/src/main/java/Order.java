import java.util.ArrayList;
import java.util.List;

public class Order {
    private String rowId;
    private String orderId;
    private String orderDate;
    private String shipDate;
    private String shipMode;
    private String customerId;
    private String productId;
    private String category;
    private String subCategory;
    private String productName;
    private double sales;
    private double discount;
    private double profit;
    private List<Product> products;


    public Order(String rowId, String orderId, String orderDate, String shipDate, String shipMode, String customerId, String productId, String category, String subCategory, String productName, double sales, double discount, double profit) {
        this.rowId = rowId;
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.shipDate = shipDate;
        this.shipMode = shipMode;
        this.customerId = customerId;
        this.productId = productId;
        this.category = category;
        this.subCategory = subCategory;
        this.productName = productName;
        this.sales = sales;
        this.discount = discount;
        this.profit = profit;
        this.products = new ArrayList<>();//each order has a list of products.

    }
    public List<Product> getProducts() {
        return products;
    }
    public void addProduct(Product product) {
        products.add(product);
    }

    public String getRowId() {
        return rowId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getShipDate() {
        return shipDate;
    }

    public String getShipMode() {
        return shipMode;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getProductId() {
        return productId;
    }

    public String getCategory() {
        return category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public String getProductName() {
        return productName;
    }

    public double getSales() {
        return sales;
    }


    public double getDiscount() {
        return discount;
    }

    public double getProfit() {
        return profit;
    }
}