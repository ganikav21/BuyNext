import java.util.ArrayList;
import java.util.List;

public class Order {

    int orderId;
    int customerId;
    List<Product> products;

    // Constructor
    public Order(int orderId, int customerId) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.products = new ArrayList<>();
    }

    // Add product to order
    public void addProduct(Product product) {
        products.add(product);
    }

    // Display order details
    public void displayOrder() {

        System.out.println("Order ID: " + orderId);
        System.out.println("Customer ID: " + customerId);

        System.out.println("Products purchased:");

        for (Product product : products) {
            System.out.println("- " + product.name);
        }

        System.out.println("-------------------------");
    }
}