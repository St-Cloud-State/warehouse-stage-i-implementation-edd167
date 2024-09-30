import java.io.*;
import java.util.*;

public class Tester {
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

  public static void main(String[] args) {
    Warehouse warehouse = Warehouse.instance();

    Product p1 = new Product("Product1", "p1", 10);
    Product p2 = new Product("Product2", "p2", 5);

    warehouse.addProduct(p1.getName(), p1.getId(), p1.getQuantity());
    warehouse.addProduct(p2.getName(), p2.getId(), p2.getQuantity());

    System.out.println("Initial Products:");
    Iterator<Product> products = warehouse.getProducts();
    while (products.hasNext()) {
      System.out.println(products.next());
    }

    if (yesOrNo("Would you like to order a product?")) {
      String productId = getToken("Enter product id");
      int quantity = Integer.parseInt(getToken("Enter quantity"));
      if (warehouse.orderProduct(productId, quantity)) {
        System.out.println("Product ordered successfully");
      } else {
        System.out.println("Product out of stock, added to waitlist");
      }
    }

    System.out.println("Current Products:");
    products = warehouse.getProducts();
    while (products.hasNext()) {
      System.out.println(products.next());
    }

    System.out.println("Waitlist:");
    Iterator<WaitlistItem> waitlist = warehouse.getWaitlist();
    while (waitlist.hasNext()) {
      System.out.println(waitlist.next());
    }

    if (yesOrNo("Would you like to save the warehouse data?")) {
      if (Warehouse.save()) {
        System.out.println("Warehouse data saved successfully");
      } else {
        System.out.println("Error saving warehouse data");
      }
    }
  }
}