import java.util.*;
import java.io.*;

public class Warehouse implements Serializable {
  private static final long serialVersionUID = 1L;
  private ClientList clientList;
  private WishList wishList;
  private static Warehouse warehouse;

  private Warehouse() {
    clientList = ClientList.instance();
    wishList = WishList.instance();
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
    if (clientList.insertClient(product)) {
      return client;
    }
    return null;
  }

  public boolean orderProduct(String productId, int quantity) {
    Product product = wishList.search(productId);
    if (product == null) {
      wishList.addProduct(productId, quantity);
      return false;
    }
    product.IncreaseQuantity(quantity);
    return true;
  }

  public Iterator<Client> getClients() {
    return clientList.getClients();
  }

  public Iterator<wishListItem> getWishlistItems() {
    return wishListItem.getWishListItems();
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

  public wishList getWishListInstance() {
    return wishList;
  }
}