import java.util.*;
import java.io.*;
public class Client implements Serializable {
  private static final long serialVersionUID = 1L;
  private String name;
  private String address;
  private String phone;
  private String id;
  private static final String CLIENT_STRING = "C";
  private static int idNum = 1;
  private List productsOnWishList = new LinkedList();
  private List productsOnWaitList = new LinkedList();
  private List transactions = new LinkedList();
  public  Client (String name, String address, String phone) {
    this.name = name;
    this.address = address;
    this.phone = phone;
    id = CLIENT_STRING + (ClientIdServer.instance()).getId();
  }

  public String getName() {
    return name;
  }
  public String getPhone() {
    return phone;
  }
  public String getAddress() {
    return address;
  }
  public String getId() {
    return id;
  }
  public void setName(String newName) {
    name = newName;
  }
  public void setAddress(String newAddress) {
    address = newAddress;
  }
  public void setPhone(String newPhone) {
    phone = newPhone;
  }
  public boolean equals(String id) {
    return this.id.equals(id);
  }
  public String toString() {
    String string = " Id: " + id + " Name: " + name + " Address: " + address + " Phone: " + phone;
    return string;
  }
}
