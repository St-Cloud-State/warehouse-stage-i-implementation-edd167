import java.util.*;
import java.text.*;
import java.io.*;
public class UserInterface {
  private static UserInterface userInterface;
  private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private static Warehouse Warehouse;
  private static final int EXIT = 0;
  private static final int ADD_CLIENT = 1;
  private static final int ADD_PRODUCT_TO WISHLIST= 2;
  private static final int DISPLAY_ALL_CLIENTS= 3;
  private static final int DISPLAY_PRODUCTS_IN_WISHLIST = 4;
  private static final int ADD_PRODUCT_TO_CATALOG = 5;
  private static final int REMOVE_PRODUCT_FROM_CATALOG = 6;
  private static final int REMOVE_PRODUCT_FROM_WISHLIST = 7;
  private static final int PLACE_AN_ORDER = 8;
  private static final int PRINT_INVOICE = 9;
  private static final int GET_TRANSACTIONS = 10;
  private static final int SHOW_CLIENT_BY_ID = 11;
  private static final int SHOW_PRODUCTS= 12;
  private static final int SAVE = 13;
  private static final int RETRIEVE = 14;
  private static final int HELP = 15;
  private UserInterface() {
    if (yesOrNo("Look for saved data and  use it?")) {
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
        StringTokenizer tokenizer = new StringTokenizer(line,"\n\r\f");
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
    if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
      return false;
    }
    return true;
  }
  public int getNumber(String prompt) {
    do {
      try {
        String item = getToken(prompt);
        Integer num = Integer.valueOf(item);
        return num.intValue();
      } catch (NumberFormatException nfe) {
        System.out.println("Please input a number ");
      }
    } while (true);
  }
  public Calendar getDate(String prompt) {
    do {
      try {
        Calendar date = new GregorianCalendar();
        String item = getToken(prompt);
        DateFormat df = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
        date.setTime(df.parse(item));
        return date;
      } catch (Exception fe) {
        System.out.println("Please input a date as mm/dd/yy");
      }
    } while (true);
  }
  public int getCommand() {
    do {
      try {
        int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
        if (value >= EXIT && value <= HELP) {
          return value;
        }
      } catch (NumberFormatException nfe) {
        System.out.println("Enter a number");
      }
    } while (true);
  }

  public void help() {
    System.out.println("Enter a number between 0 and 12 as explained below:");
    System.out.println(EXIT + " to Exit\n");
    System.out.println(ADD_CLIENT + " to add a client");
    System.out.println(ADD_PRODUCT_TO_WISHlIST + " to  add product to wishlist");
    System.out.println(DISPLAY_ALL_CLIENTS + " to  print all clients");
    System.out.println(DISPLAY_PRODUCTS_IN_WISHLIST + " to print products in wishlist ");
    System.out.println(ADD_PRODUCT_TO_CATALOG + " Ading products to catalog ");
    System.out.println(REMOVE_PRODUCT_FROM_CATALOG + " to  remove product from catalog");
    System.out.println(REMOVE_PRODUCT_FROM_WISHLIST + " to  remove a product from wishList");
    System.out.println(PLACE_AN_ORDER + " to place an order");
    System.out.println(PRINT_INVOICE+ " to  print an invoice");
    System.out.println(GET_TRANSACTIONS + " to  print all transactions");
    System.out.println(SHOW_CLIENTS_BY_ID + " to  print members by ID");
    System.out.println(SHOW_PRODUCTS + " to  print books");
    System.out.println(SAVE + " to  save data");
    System.out.println(RETRIEVE + " to  retrieve");
    System.out.println(HELP + " for help");
  }

  public void addClient() {
    String name = getToken("Enter client name");
    String address = getToken("Enter address");
    String phone = getToken("Enter phone");
    Client result;
    result = warehouse.addClient(name, address, phone);
    if (result == null) {
      System.out.println("Could not add client");
    }
    System.out.println(result);
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

  public void showClients() {
      Iterator allClients = warehouse.getAllClients();
      while (allClients.hasNext()){
	  Client client = (Client)(allClients.next());
          System.out.println(client.toString());
      }
  }

  public void getTransactions() {
      System.out.println("Dummy Action");   
  }
  private void save() {
    if (warehouse.save()) {
      System.out.println(" The warehouse has been successfully saved in the file WarehouseData \n" );
    } else {
      System.out.println(" There has been an error in saving \n" );
    }
  }
  private void retrieve() {
    try {
      Warehouse tempWarehouse = Warehouse.retrieve();
      if (tempWarehouse != null) {
        System.out.println(" The Warehouse has been successfully retrieved from the file WarehouseData \n" );
        warehouse = tempWarehouse;
      } else {
        System.out.println("File doesnt exist; creating new warehouse" );
        warehouse = Warehouse.instance();
      }
    } catch(Exception cnfe) {
      cnfe.printStackTrace();
    }
  }
  public void process() {
    int command;
    help();
    while ((command = getCommand()) != EXIT) {
      switch (command) {
        case ADD_CLIENT:        addClient();
                                break;
        case ADD_PRODUCT_TO_WISHlIST:addProducts();
                                break;
        case DISPLAY_ALL_CLIENTS:showClients();
                                break;
        case DISPLAY_PRODUCTS_IN_WISHLIST:displayWishlist();
                                break;
        case ADD_PRODUCT_TO_CATALOG:addProductsToCatalog();
                                break;
        case REMOVE_PRODUCT_FROM_CATALOG:removeProductFromCatalog();
                                break;
        case REMOVE_PRODUCT_FROM_WISHLIST:placeOrder();
                                break;
        case PLACE_AN_ORDER:placeOrder();
                                break;
        case PRINT_INVOICE:generateInvoice();
                                break;
        case GET_TRANSACTIONS_BY_CLIENT_ID:printTransactionSummary();
                                break;
        case SHOW_CLIENT_BY_ID:	showClientsByID();
                                break;   
        case RETRIEVE:          retrieve();
                                break;                                             
        case SAVE:              save();
                                break;	
        case HELP:              help();
                                break;
      }
    }
  }
  public static void main(String[] s) {
    UserInterface.instance().process();
  }
}
