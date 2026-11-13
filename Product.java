public class Product {

    int id;
    String name;
    String category;
    double price;

    public Product(int id, String name, String category, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public void displayProduct() {
        System.out.println("Product ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Category: " + category);
        System.out.println("Price: Rs. " + price);
        System.out.println("-------------------------");
    }
}