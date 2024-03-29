package pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import enums.Category;

public final class Gift {
    private final String productName;
    private final Double price;
    private final Category category;
    @JsonIgnore
    private int quantity;

    public Gift(final String productName, final Double price, final Category category,
                final int q) {
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.quantity = q;
    }

    public String getProductName() {
        return productName;
    }

    public Double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }
}
