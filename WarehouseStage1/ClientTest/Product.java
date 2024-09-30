import java.util.*;
import java.io.*;

public class Product implements Serializable {
    private String id;
    private String productName;
    private double price;

    // Constructor
    public Product(String id, String productName, double price) {
        this.id = id;
        this.productName = productName;
        this.price = price;
    }

    // Getter for productName
    public String getProductName() {
        return productName;
    }

    // Getter for price
    public double getPrice() {
        return price;
    }

    // Getter for id
    public String getId() {
        return id;
    }

    // Setter for productName
    public void setProductName(String productName) {
        this.productName = productName;
    }

    // Setter for price (parsing string to double)
    public void setPrice(String newprice) {
        this.price = Double.parseDouble(newprice);
    }

    // toString method to represent the product as a string
    public String toString() {
        return "Product Name: " + productName + " | Product Price: " + price + " | Product ID: " + id;
    }
}
