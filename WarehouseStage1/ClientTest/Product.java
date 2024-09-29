import java.util.*;
import java.io.*;
public class Product implements Serializable {
public  Product (String id, String productName, double price) {
    this.id =id;
    this.productName = productName;
    this.price = price;
   
   public String getproductName(){
    return productName;
   }

   public double getprice(){
    return price;
   }
  public String getId() {
    return id;
  }
  public void setProductName(String ProductName) {
    productNmame = newProductName;
  }
  public void setprice(String newprice) {
    price = newprice;
  }
   public String toString() {
    String string = "product name " + productName + "product price" + price + " productID " + id ;
    return string;
  
  }
}