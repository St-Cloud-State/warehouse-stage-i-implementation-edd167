import java.util.LinkedList;
import java.util.Date;

public class Transaction {
    // Inner class to represent an item in the transaction (Product + quantity)
    public static class TransactionItem {
        private Product product;
        private int quantity;

        public TransactionItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public Product getProduct() {
            return product;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getItemTotal() {
            return product.getPrice() * quantity;
        }

        @Override
        public String toString() {
            return product + ", Quantity: " + quantity + ", Total: $" + getItemTotal();
        }
    }

    // Fields to store transaction details
    private String transactionID;
    private String clientID;
    private Date date;
    private LinkedList<TransactionItem> items;
    private boolean invoiceGenerated;
    private double totalAmount;

    // Constructor
    public Transaction(String transactionID, String clientID) {
        this.transactionID = transactionID;
        this.clientID = clientID;
        this.date = new Date();
        this.items = new LinkedList<>();
        this.invoiceGenerated = false;
        this.totalAmount = 0;
    }

    // Method to add a product to the transaction
    public void addProductToTransaction(Product product, int quantity) {
        TransactionItem newItem = new TransactionItem(product, quantity);
        items.add(newItem);
        totalAmount += newItem.getItemTotal();
        System.out.println("Added " + product.getName() + " with quantity " + quantity + " to transaction.");
    }

    // Method to calculate the total amount of the transaction
    public double calculateTotal() {
        double total = 0;
        for (TransactionItem item : items) {
            total += item.getItemTotal();
        }
        return total;
    }

    // Method to generate an invoice
    public void generateInvoice() {
        if (!invoiceGenerated) {
            System.out.println("\n*** Invoice ***");
            System.out.println("Transaction ID: " + transactionID);
            System.out.println("Client ID: " + clientID);
            System.out.println("Date: " + date);
            System.out.println("Items:");
            for (TransactionItem item : items) {
                System.out.println(item);
            }
            System.out.println("Total Amount: $" + calculateTotal());
            invoiceGenerated = true;
        } else {
            System.out.println("Invoice already generated.");
        }
    }

    // Method to print the transaction summary
    public void printTransactionSummary() {
        System.out.println("\n*** Transaction Summary ***");
        System.out.println("Transaction ID: " + transactionID);
        System.out.println("Client ID: " + clientID);
        System.out.println("Date: " + date);
        System.out.println("Total Amount: $" + calculateTotal());
    }

    // Main method for testing the class
    public static void main(String[] args) {
        // Creating some sample products
        Product product1 = new Product("P101", "Laptop", 1500.00);
        Product product2 = new Product("P102", "Smartphone", 800.00);
        Product product3 = new Product("P103", "Headphones", 200.00);

        // Creating a transaction
        Transaction transaction = new Transaction("T001", "C123");

        // Adding products to the transaction
        transaction.addProductToTransaction(product1, 1);  // 1 Laptop
        transaction.addProductToTransaction(product2, 2);  // 2 Smartphones
        transaction.addProductToTransaction(product3, 3);  // 3 Headphones

        // Generating an invoice for the transaction
        transaction.generateInvoice();

        // Displaying transaction summary
        transaction.printTransactionSummary();
    }
}
