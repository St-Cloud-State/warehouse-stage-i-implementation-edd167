/*
This is a test script that checks the Client class and ClientList class,WishList along with a dummy product class. 
 */
import java.util.*;
import java.text.*;
import java.io.*;
public class Tester {
  public static String getToken(String prompt) {
		do {
		  try {
			System.out.println(prompt);
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String line = reader.readLine();
			StringTokenizer tokenizer = new StringTokenizer(line,"\n\r\f");
			if (tokenizer.hasMoreTokens()) {
				return tokenizer.nextToken();
			}
		  } 
		  catch (IOException ioe) {
			System.exit(0);
		  }
		} while (true);
  }
  private static boolean yesOrNo(String prompt) {
		String more = getToken(prompt + " (Y|y)[es] or anything else for no");
		if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
			return false;
		}
			return true;
  }
  public static void main(String[] s) {
	 Warehouse warehouse = Warehouse.instance();

    Client c1 = new Client("edwin", "1st Ave N","657647");
    Client c2 = new Client("maikara", "3rd St SE","67488948");

    warehouse.addClient(c1.getName(), c1.getPhone(), c1.getAddress());
    warehouse.addClient(c2.getName(), c2.getPhone(), c2.getAddress());

    System.out.println("client list:");
    Iterator<Client> client = warehouse.getClients();
    while (clients.hasNext()) {
      System.out.println(clients.next());
    }

    if (yesOrNo("Would you like to order a product?")) {
      String productId = getToken("Enter product id");
      int quantity = Integer.parseInt(getToken("Enter quantity"));
      if (warehouse.orderProduct(productId, quantity)) {
        System.out.println("Product ordered successfully");
      } else {
        System.out.println("Product out of stock, added to wishlist");
      }
    }

    System.out.println("Current Products:");
    products = warehouse.getProducts();
    while (products.hasNext()) {
      System.out.println(products.next());
    }

    System.out.println("WishList:");
    Iterator<WishlistItem> wishList = warehouse.getWaitlist();
    while (wishList.hasNext()) {
      System.out.println(wishList.next());
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
   
}
