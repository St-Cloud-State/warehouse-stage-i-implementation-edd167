import java.util.*;
import java.io.*;

public class UserInterface {
  private static UserInterface userInterface;
  private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private static Warehouse warehouse;
  private static final int EXIT = 0;
  private static final int ADD_PRODUCT = 1;
  private static final int ORDER_PRODUCT = 2;
  private static final int SHOW_PRODUCTS = 3;
  private static final int SHOW_WAITLIST = 4;
  private static final int REMOVE_WAITLIST_ITEM = 5;
  private static final int SAVE = 6;
  private static final int RETRIEVE = 7;
  private static final int HELP = 8;

  private UserInterface() {
    if (yesOrNo("Look for saved data and use it?")) {
      retrieve();
    } else {
      warehouse = Warehouse.instance();
    }
  }

  public static UserInterface instance() {
    if (userInterface == null) {
      return userInterface = new UserInterface();
    } else {
      return userInterface;
    }
  }

  public String getToken(String prompt) {
    do {
      try {
        System.out.println(prompt);
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

  private boolean yesOrNo(String prompt) {
    String more = getToken(prompt + " (Y|y)[es] or anything else for no");
    return more.charAt(0) == 'y' || more.charAt(0) == 'Y';
  }

  public int getNumber(String prompt) {
    do {
      try {
        String item = getToken(prompt);
        Integer num = Integer.valueOf(item);
        return num;
      } catch (NumberFormatException nfe) {
        System.out.println("Please input a number ");
      }
    } while (true);
  }

  public void help() {
    System.out.println("Enter a number between 0 and 8 as explained below:");
    System.out.println("0 to Exit\n");
    System.out.println("1 to add a product");
    System.out.println("2 to order a product");
    System.out.println("3 to show all products");
    System.out.println("4 to show waitlist");
    System.out.println("5 to remove an item from the waitlist");
    System.out.println("6 to save data");
    System.out.println("7 to retrieve data");
    System.out.println("8 for help");
  }

  public void addProduct() {
    String name = getToken("Enter product name");
    String id = getToken("Enter product id");
    int quantity = getNumber("Enter quantity");
    Product product = warehouse.addProduct(name, id, quantity);
    if (product == null) {
      System.out.println("Could not add product");
    } else {
      System.out.println(product);
    }
  }

  public void orderProduct() {
    String id = getToken("Enter product id");
    int quantity = getNumber("Enter quantity");
    if (warehouse.orderProduct(id, quantity)) {
      System.out.println("Product ordered successfully");
    } else {
      System.out.println("Product out of stock, added to waitlist");
    }
  }

  public void showProducts() {
    Iterator<Product> products = warehouse.getProducts();
    while (products.hasNext()) {
      System.out.println(products.next());
    }
  }

  public void showWaitlist() {
    Iterator<WaitlistItem> items = warehouse.getWaitlist();
    while (items.hasNext()) {
      System.out.println(items.next());
    }
  }

  public void removeWaitlistItem() {
    String id = getToken("Enter product id to remove from waitlist");
    if (warehouse.getWaitlistInstance().removeProduct(id)) {
      System.out.println("Product removed from waitlist");
    } else {
      System.out.println("Product not found in waitlist");
    }
  }

  private void save() {
    if (warehouse.save()) {
      System.out.println("The warehouse has been successfully saved in the file WarehouseData");
    } else {
      System.out.println("There has been an error in saving");
    }
  }

  private void retrieve() {
    try {
      Warehouse tempWarehouse = Warehouse.retrieve();
      if (tempWarehouse != null) {
        System.out.println("The warehouse has been successfully retrieved from the file WarehouseData");
        warehouse = tempWarehouse;
      } else {
        System.out.println("File doesn't exist; creating new warehouse");
        warehouse = Warehouse.instance();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void process() {
    int command;
    help();
    while ((command = getNumber("Enter command: 8 for help")) != EXIT) {
      switch (command) {
        case ADD_PRODUCT:
          addProduct();
          break;
        case ORDER_PRODUCT:
          orderProduct();
          break;
        case SHOW_PRODUCTS:
          showProducts();
          break;
        case SHOW_WAITLIST:
          showWaitlist();
          break;
        case REMOVE_WAITLIST_ITEM:
          removeWaitlistItem();
          break;
        case SAVE:
          save();
          break;
        case RETRIEVE:
          retrieve();
          break;
        case HELP:
          help();
          break;
      }
    }
  }

  public static void main(String[] args) {
    UserInterface.instance().process();
  }
}