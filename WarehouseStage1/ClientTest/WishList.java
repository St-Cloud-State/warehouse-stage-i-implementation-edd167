import java.util.*;
import java.io.*;

public class WishList implements Serializable {
  private static final long serialVersionUID = 1L;
  private List<wishListItem> items = new LinkedList<>();
  private static WishList wishlist;

  private WishList() {}

  public static Wishlist instance() {
    if (wishlist == null) {
      return (wishlist = new WishList());
    } else {
      return wishlist;
    }
  }

  public void addProduct(String productId, int quantity) {
    items.add(new wishListItem(productId, quantity));
  }

  public boolean removeProduct(String productId) {
    Iterator<wishlistItem> iterator = items.iterator();
    while (iterator.hasNext()) {
      wishListItem item = iterator.next();
      if (item.getProductId().equals(productId)) {
        iterator.remove();
        return true;
      }
    }
    return false;
  }

    
  public Iterator<wishListItem> getWishListItems () {
    return items.iterator();
  }

  private void writeObject(ObjectOutputStream output) throws IOException {
    output.defaultWriteObject();
    output.writeObject(waitlist);
  }

  private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException {
    input.defaultReadObject();
    if (wis == null) {
      wishlist = (wishlist) input.readObject();
    } else {
      input.readObject();
    }
  }

  public String toString() {
    return items.toString();
  }
}