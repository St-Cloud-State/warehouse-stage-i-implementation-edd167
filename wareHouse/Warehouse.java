import java.util.*;
import java.io.*;
import java.util.Iterator;
import java.util.LinkedList; 
import java.util.List; 


public class Warehouse implements Serializable {
  private static final long serialVersionUID = 1L;
  private ClientList clientList;
  private Wishlist wishlist;
  private Catalog catalog;
  private Waitlist waitlist;
  private static Warehouse warehouse;

  private Warehouse() {
    clientList = ClientList.instance();
    wishlist = Wishlist.instance();
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

  public Client addClient(String name, String address, String phone) {
    Client client = new Client(name, address, phone);
    if (clientList.insertClient(client)) {
      return client;
    }
    return null;
  }

  public Iterator<Client> getAllClients() {
    return clientList.getAllClients();
  }

  public Iterator<WishlistItem> getWishlistItems() {
    return wishlist.getItems();
  }

  public Product addProduct(String name, String id, int quantity) {
    Product product = new Product(name, id, quantity);
    if (catalog.insertProduct(product)) {
      return product;
    }
    return null;
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

  public void addItemToWishlist(String clientId, String productId) {
    wishlist.addItem(clientId, productId);
  }

  public boolean removeItemFromWishlist(String clientId, String productId) {
    return wishlist.removeItem(clientId, productId);
  }
}