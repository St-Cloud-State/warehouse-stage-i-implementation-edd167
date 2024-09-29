public class WishList {

    // Class to represent an entry in the wishlist (product + quantity)
    public static class WishListItem {
        private Product product;
        private int quantity;

        public WishListItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public Product getProduct() {
            return product;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return product + ", Quantity: " + quantity;
        }
    }
private void readObject(java.io.ObjectInputStream input) {
    try {
      if (WishListItem!= null) {
        return;
      } else {
        input.defaultReadObject();
        if (wishListItem== null) {
          wishListItem = (wishListItem) input.readObject();
        } else {
          input.readObject();
        }
      }
    } catch(IOException ioe) {
      ioe.printStackTrace();
    } catch(ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
    }
  }
  public String toString() {
    return clients.toString();
  }

    // LinkedList to store the products in the wishlist
    private LinkedList<WishListItem> wishListItems;

    // Constructor
    public WishList() {
        wishListItems = new LinkedList<>();
    }

    // Method to add or update a product in the wishlist
    public void addProductToWishlist(Product product, int quantity) {
        for (WishListItem item : wishListItems) {
            if (item.getProduct().equals(product)) {
                // If product already exists, update the quantity
                item.setQuantity(item.getQuantity() + quantity);
                System.out.println("Updated quantity of product: " + product.getName() + " to " + item.getQuantity());
                return;
            }
        }
        // If product does not exist, add it as a new entry
        wishListItems.add(new WishListItem(product, quantity));
        System.out.println("Added new product to wishlist: " + product.getName() + " with quantity " + quantity);
    }

    // Method to update the quantity of a product in the wishlist
    public void updateProductQuantity(Product product, int newQuantity) {
        for (WishListItem item : wishListItems) {
            if (item.getProduct().equals(product)) {
                item.setQuantity(newQuantity);
                System.out.println("Updated product quantity for " + product.getName() + " to " + newQuantity);
                return;
            }
        }
        System.out.println("Product " + product.getName() + " is not in the wishlist.");
    }

    // Method to display all products in the wishlist with quantities
    public void displayWishlist() {
        if (wishListItems.isEmpty()) {
            System.out.println("Wishlist is empty.");
        } else {
            System.out.println("Wishlist:");
            for (WishListItem item : wishListItems) {
                System.out.println(item);
            }
        }
    }

    // Method to check if a product is already in the wishlist
    public boolean checkProductInWishlist(Product product) {
        for (WishListItem item : wishListItems) {
            if (item.getProduct().equals(product)) {
                return true;
            }
        }
        return false;
    }
  private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(clientList);
    } catch(IOException ioe) {
      ioe.printStackTrace();
    }
  }
  
}