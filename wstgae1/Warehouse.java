import java.util.*;
import java.io.*;

public class Warehouse implements Serializable {
  private static final long serialVersionUID = 1L;
  private Catalog catalog;
  private Waitlist waitlist;
  private static Warehouse warehouse;

  private Warehouse() {
    catalog = Catalog.instance();
    waitlist = Waitlist.instance();
  }

  public static Warehouse instance() {
    if (warehouse == null) {
      return (warehouse = new Warehouse());
    } else {
      return warehouse;
    }
  }

  public Product addProduct(String name, String id, int quantity) {
    Product product = new Product(name, id, quantity);
    if (catalog.insertProduct(product)) {
      return product;
    }
    return null;
  }

  public boolean orderProduct(String productId, int quantity) {
    Product product = catalog.search(productId);
    if (product == null || product.getQuantity() < quantity) {
      waitlist.addProduct(productId, quantity);
      return false;
    }
    product.reduceQuantity(quantity);
    return true;
  }

  public Iterator<Product> getProducts() {
    return catalog.getProducts();
  }

  public Iterator<WaitlistItem> getWaitlist() {
    return waitlist.getItems();
  }

  public static Warehouse retrieve() {
    try {
      FileInputStream file = new FileInputStream("WarehouseData");
      ObjectInputStream input = new ObjectInputStream(file);
      input.readObject();
      return warehouse;
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static boolean save() {
    try {
      FileOutputStream file = new FileOutputStream("WarehouseData");
      ObjectOutputStream output = new ObjectOutputStream(file);
      output.writeObject(warehouse);
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  public Waitlist getWaitlistInstance() {
    return waitlist;
  }
}