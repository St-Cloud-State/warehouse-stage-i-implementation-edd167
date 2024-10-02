import java.io.Serializable;

public class Product implements Serializable {
  private static final long serialVersionUID = 1L;
  private String name;
  private String id;
  private int quantity;

  public Product(String name, String id, int quantity) {
    this.name = name;
    this.id = id;
    this.quantity = quantity;
  }

  public String getName() {
    return name;
  }

  public String getId() {
    return id;
  }

  public int getQuantity() {
    return quantity;
  }

  public void reduceQuantity(int amount) {
    if (amount <= quantity) {
      quantity -= amount;
    }
  }

  public String toString() {
    return "Product[name=" + name + ", id=" + id + ", quantity=" + quantity + "]";
  }
}