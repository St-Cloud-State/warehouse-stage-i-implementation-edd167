/*
This is a test script that checks the Client class and ClientList class along with a dummy product class. 
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
	
     Book b1 = new Book("qq", "ww", "b1");
     Book b2 = new Book("ee", "rr", "b2");
     
	 if (yesOrNo("Would you like to load the ClientList")) {
		try {
			FileInputStream file = new FileInputStream("CatData");
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
     Client c1 = new Client("c1"); 
     Client c2 = new Client("c2");
     System.out.println(c1.getName() + " should be null");
     b1.issue(c1); 
     System.out.println(c1.getPhone() + " should be m1");
     c1.(m2); // try issuing to someone else
     System.out.println(b1.getBorrower() + " still issue to m1");
     System.out.println(b1.getDueDate() + " check due date as per Business Rule"); 
     System.out.println(b1.returnBook()); 
     System.out.println(b1.getBorrower() + " should be null");
     System.out.println(b1.getDueDate() + " should be null");
     Iterator books = catalog.getBooks();
     System.out.println("List of books");
     while (books.hasNext()){
       System.out.println(books.next());
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
