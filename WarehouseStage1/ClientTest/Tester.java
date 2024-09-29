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
	
     Client c1 = new Client("edwin", "1st ave NE", "673837");
     Client c2 = new Client("Ian", "2nd Ave SE", "26538303");
     Client c3 =new Client ("maikara","3rd st N","53983890");
	 if (yesOrNo("Would you like to load the ClientList")) {
		try {
			FileInputStream file = new FileInputStream("ClientListData");
			ObjectInputStream input = new ObjectInputStream(file);
			input.readObject(); 
		} catch(IOException ioe) {
			ioe.printStackTrace();
			 
		} catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			 
		}   
	 }
     ClientList clientList = ClientList.instance();
	 clientList.insertClient(c1);
     clientList.insertClient(c2);
     clientList.insertClient(c2);
      Product product1 = new Product("P101", "Laptop", 1500.00);
      Product product2 = new Product("P102", "Smartphone", 800.00);
      Product product3 = new Product("P103", "Headphones", 200.00);
       
     System.out.println(c1.displayWishlist() +"should be null");
     c1.addProductToWishlist(product1,8);
     System.out.println(product1.getName() + " should be c1 name");
     System.out.println(product1.getAddress() + " shoud be address of c1");
     System.out.println(product1.getPhone() + " should be phone number of c1"); 
     System.out.println(c2.displayWishlist()+"shoud be null"); 
     c2.addProductToWishlist(product3,5);
     System.out.println(product3.getName() + " should be c2 name");
     System.out.println(product3.getPhone() + " should be c2 phone");
     c2.addProductToWishlist(product2,6);
     System.out.println(c2.displayWishlist()+"should be product2 and product1");
     System.out.println(product2.getPhone() + " should be phone number of c2");
     Iterator clients = clientList.getAllClients();
     System.out.println("List of all clients");
     while (clients.hasNext()){
       System.out.println(clients.next());
     }
	 if (yesOrNo("Would you like to save the catalog")) {
		 try {
			FileOutputStream file = new FileOutputStream("CatData");
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(catalog);
		} catch(IOException ioe) {
			ioe.printStackTrace();
        }
	}
  }
}
