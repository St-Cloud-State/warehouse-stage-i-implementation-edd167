import java.util.*;
import java.text.*;
import java.io.*;

public class UserInterface {
  private static UserInterface userInterface;
  private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private static Warehouse warehouse;
  private static final int EXIT = 0;
  private static final int HELP = 17;

  // Client-related commands
  private static final int ADD_CLIENT = 1;
  private static final int DISPLAY_ALL_CLIENTS = 2;
  private static final int SHOW_CLIENT_BY_ID = 3;

  // Product-related commands
  private static final int ADD_PRODUCT_TO_CATALOG = 4;
  private static final int REMOVE_PRODUCT_FROM_CATALOG = 5;
  private static final int SHOW_PRODUCTS = 6;

  // Wishlist-related commands
  private static final int ADD_PRODUCT_TO_WISHLIST = 7;
  private static final int DISPLAY_PRODUCTS_IN_WISHLIST = 8;
  private static final int REMOVE_PRODUCT_FROM_WISHLIST = 9;
  private static final int SHOW_WAITLIST = 10;
  private static final int REMOVE_WAITLIST_ITEM = 11;

  // Order-related commands
  private static final int PLACE_AN_ORDER = 12;
  private static final int PRINT_INVOICE = 13;
  private static final int GET_TRANSACTIONS = 14;

  // Data-related commands
  private static final int SAVE = 15;
  private static final int RETRIEVE = 16;

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

  public int getCommand() {
    do {
      try {
        int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
        if (value >= EXIT && value <= REMOVE_WAITLIST_ITEM) {
          return value;
        }
      } catch (NumberFormatException nfe) {
        System.out.println("Enter a number");
      }
    } while (true);
  }

  public void help() {
    System.out.println("Enter a number between 0 and " + REMOVE_WAITLIST_ITEM + " as explained below:");
    System.out.println(EXIT + " to Exit");
    System.out.println(HELP + " for help\n");

    // Client-related commands
    System.out.println(ADD_CLIENT + " to add a client");
    System.out.println(DISPLAY_ALL_CLIENTS + " to display all clients");
    System.out.println(SHOW_CLIENT_BY_ID + " to show client by ID");

    // Product-related commands
    System.out.println(ADD_PRODUCT_TO_CATALOG + " to add a product to catalog");
    System.out.println(REMOVE_PRODUCT_FROM_CATALOG + " to remove a product from catalog");
    System.out.println(SHOW_PRODUCTS + " to show products");

    // Wishlist-related commands
    System.out.println(ADD_PRODUCT_TO_WISHLIST + " to add a product to wishlist");
    System.out.println(DISPLAY_PRODUCTS_IN_WISHLIST + " to display products in wishlist");
    System.out.println(REMOVE_PRODUCT_FROM_WISHLIST + " to remove a product from wishlist");
    System.out.println(SHOW_WAITLIST + " to show waitlist");
    System.out.println(REMOVE_WAITLIST_ITEM + " to remove an item from the waitlist");

    // Order-related commands
    System.out.println(PLACE_AN_ORDER + " to place an order");
    System.out.println(PRINT_INVOICE + " to print invoice");
    System.out.println(GET_TRANSACTIONS + " to get transactions");

    // Data-related commands
    System.out.println(SAVE + " to save data");
    System.out.println(RETRIEVE + " to retrieve data");
  }

  public void addClient() {
    String name = getToken("Enter client name");
    String address = getToken("Enter client address");
    String phone = getToken("Enter client phone");
    if (warehouse.addClient(name, address, phone) != null) {
      System.out.println("Client added successfully");
    } else {
      System.out.println("Could not add client");
    }
  }


  public void showClients() {
    Iterator<Client> clients = warehouse.getAllClients();
    while (clients.hasNext()) {
      System.out.println(clients.next());
    }
  }

  public void showProductOnWishList() {
    Iterator<WishlistItem> wishListItems = warehouse.getWishlistItems();
    while (wishListItems.hasNext()) {
      WishlistItem wishItem = wishListItems.next();
      System.out.println(wishItem.toString());
    }
  }

  public void getTransactions() {
    System.out.println("Dummy Action");
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
        System.out.println("The Warehouse has been successfully retrieved from the file WarehouseData");
        warehouse = tempWarehouse;
      } else {
        System.out.println("File doesn't exist; creating new warehouse");
        warehouse = Warehouse.instance();
      }
    } catch (Exception cnfe) {
      cnfe.printStackTrace();
    }
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

  public void addItemToWishlist() {
    String clientId = getToken("Enter client id");
    String productId = getToken("Enter product id");
    warehouse.addItemToWishlist(clientId, productId);
    System.out.println("Item added to wishlist");
  }

  public void removeItemFromWishlist() {
    String clientId = getToken("Enter client id");
    String productId = getToken("Enter product id");
    if (warehouse.removeItemFromWishlist(clientId, productId)) {
      System.out.println("Item removed from wishlist");
    } else {
      System.out.println("Item not found in wishlist");
    }
  }

  public void showWishlist() {
    Iterator<WishlistItem> items = warehouse.getWishlistItems();
    while (items.hasNext()) {
      System.out.println(items.next());
    }
  }

  public void process() {
    int command;
    help();
    while ((command = getCommand()) != EXIT) {
      switch (command) {
        case ADD_CLIENT:
          addClient();
          break;
        case ADD_PRODUCT_TO_WISHLIST:
          addItemToWishlist();
          break;
        case DISPLAY_ALL_CLIENTS:
          showClients();
          break;
        case DISPLAY_PRODUCTS_IN_WISHLIST:
          showProductOnWishList();
          break;
        case ADD_PRODUCT_TO_CATALOG:
          addProduct();
          break;
        case REMOVE_PRODUCT_FROM_CATALOG:
          // Implement removeProductFromCatalog method
          break;
        case REMOVE_PRODUCT_FROM_WISHLIST:
          // Implement removeProductFromWishlist method
          break;
        case PRINT_INVOICE:
          // Implement generateInvoice method
          break;
        case GET_TRANSACTIONS:
          getTransactions();
          break;
        case SHOW_CLIENT_BY_ID:
          // Implement showClientsByID method
          break;
        case SHOW_PRODUCTS:
          showProducts();
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
        case SHOW_WAITLIST:
          showWaitlist();
          break;
        case REMOVE_WAITLIST_ITEM:
          removeWaitlistItem();
          break;
      }
    }
  }

  public static void main(String[] s) {
    UserInterface.instance().process();
  }
}