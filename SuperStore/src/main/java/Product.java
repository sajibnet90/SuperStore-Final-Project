public class Product {
    private String rowId;
    private String productId;
    private String category;
    private String subCategory;
    private String productName;
    private int quantity;


    public Product(String rowId,String productId, String category, String subCategory, String productName,int quantity) {
        this.productId = productId;
        this.category = category;
        this.subCategory = subCategory;
        this.productName = productName;
        this.quantity = quantity; 
        this.rowId = rowId;
 
    }
    public String getRowId() {
        return rowId;
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
    public int getQuantity() {
        return quantity;
    }
}
