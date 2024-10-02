import java.io.Serializable;

public class WishlistItem implements Serializable {
  private static final long serialVersionUID = 1L;
  private String clientId;
  private String productId;

  public WishlistItem(String clientId, String productId) {
    this.clientId = clientId;
    this.productId = productId;
  }

  public String getClientId() {
    return clientId;
  }

  public String getProductId() {
    return productId;
  }

  public String toString() {
    return "WishlistItem[clientId=" + clientId + ", productId=" + productId + "]";
  }
}