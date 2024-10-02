import java.io.Serializable;

public class WaitlistItem implements Serializable {
  private static final long serialVersionUID = 1L;
  private String productId;
  private int quantity;

  public WaitlistItem(String productId, int quantity) {
    this.productId = productId;
    this.quantity = quantity;
  }

  public String getProductId() {
    return productId;
  }

  public int getQuantity() {
    return quantity;
  }

  public String toString() {
    return "WaitlistItem[productId=" + productId + ", quantity=" + quantity + "]";
  }
}