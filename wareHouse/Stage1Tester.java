import java.util.*;
import java.text.*;
import java.io.*;

public class Stage1Tester {
  public static String getToken(String prompt) {
    do {
      try {
        System.out.println(prompt);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
        if (tokenizer.hasMoreTokens()) {
          return tokenizer.nextToken();
        }
      } catch (IOException ioe) {
        System.exit(0);
      }
    } while (true);
  }

  private static boolean yesOrNo(String prompt) {
    String more = getToken(prompt + " (Y|y)[es] or anything else for no");
    return more.charAt(0) == 'y' || more.charAt(0) == 'Y';
  }

  public static void main(String[] s) {
    Warehouse warehouse = Warehouse.instance();
    // Initialize the warehouse with some products
    warehouse.addProduct("Product1", "p1", 10);
    warehouse.addProduct("Product2", "p2", 5);
    warehouse.addProduct("Product3", "p3", 20);

    // Test adding clients
    System.out.println("Adding clients...");
    Client c1 = new Client("edwin", "1st Ave N", "657647");
    Client c2 = new Client("maikara", "3rd St SE", "67488948");

    warehouse.addClient(c1.getName(), c1.getAddress(), c1.getPhone());
    warehouse.addClient(c2.getName(), c2.getAddress(), c2.getPhone());

    // Display all clients
    System.out.println("Client list after adding clients:");
    Iterator<Client> clients = warehouse.getAllClients();
    while (clients.hasNext()) {
      System.out.println(clients.next());
    }

    // Test adding a new client via user input
    if (yesOrNo("Would you like to add a new client?")) {
      String name = getToken("Enter client name");
      String address = getToken("Enter client address");
      String phone = getToken("Enter client phone");
      warehouse.addClient(name, address, phone);

      // Display all clients again
      System.out.println("Client list after adding a new client:");
      clients = warehouse.getAllClients();
      while (clients.hasNext()) {
        System.out.println(clients.next());
      }
    }

    // Display current products
    System.out.println("Current Products:");
    Iterator<Product> products = warehouse.getProducts();
    while (products.hasNext()) {
      System.out.println(products.next());
    }


    // Test ordering products OR adding it to the wishList if product doesn't exist
    while (yesOrNo("Would you like to order a product?")) {
      String clientId = getToken("Enter client id");
      String productId = getToken("Enter product id");
      int quantity = Integer.parseInt(getToken("Enter quantity"));
      if (warehouse.orderProduct(clientId, productId, quantity)) {
        System.out.println("Product ordered successfully");
      } else {
        System.out.println("Product out of stock, added to wishlist");
      }
    }

    // Test adding a product to a client's wishlist
    System.out.println("Testing wishlist functionality:");
    if (yesOrNo("Would you like to add a product to a client's wishlist?")) {
      String clientId = getToken("Enter client id");
      String productId = getToken("Enter product id");
      warehouse.addItemToWishlist(clientId, productId);
      System.out.println("Item added to wishlist");

      // Display wishlist items
      System.out.println("Wishlist items:");
      Iterator<WishlistItem> wishlistItems = warehouse.getWishlistItems();
      while (wishlistItems.hasNext()) {
        System.out.println(wishlistItems.next());
      }
    }

    // Save the warehouse data
    if (Warehouse.save()) {
      System.out.println("Warehouse data saved successfully");
    } else {
      System.out.println("Error saving warehouse data");
    }
  }
}