import java.util.*;
import java.io.*;
public class Product implements Serializable {

  private String id;
   
  public  Product (String  id) {
    this.id =  id;
  }

   
  public String getId() {
    return id;
  }
   
  public String toString() {
    String string =  " id " + id;
    return string;
  }
}