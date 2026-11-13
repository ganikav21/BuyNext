import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class main { 


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // CREATE PRODUCTS

        Product laptop = new Product(101, "Laptop", "Electronics", 60000);
        Product mouse = new Product(102, "Mouse", "Electronics", 800);
        Product keyboard = new Product(103, "Keyboard", "Electronics", 1500);
        Product stand = new Product(104, "Stand", "Accessories", 1000);
        Product headphones = new Product(105, "Headphones", "Electronics", 2500);
        Product monitor = new Product(106, "Monitor", "Electronics", 12000);
        Product webcam = new Product(107, "Webcam", "Electronics", 3000);
        Product laptopBag = new Product(108, "Laptop Bag", "Accessories", 1800);
        Product usbHub = new Product(109, "USB Hub", "Accessories", 900);
        Product smartphone = new Product(110, "Smartphone", "Mobiles", 25000);

        // PRODUCT CATALOGUE

        List<Product> products = new ArrayList<>();

        products.add(laptop);
        products.add(mouse);
        products.add(keyboard);
        products.add(stand);
        products.add(headphones);
        products.add(monitor);
        products.add(webcam);
        products.add(laptopBag);
        products.add(usbHub);
        products.add(smartphone);

        // CREATE CUSTOMER ORDERS

        Order order1 = new Order(1001, 1);
        order1.addProduct(laptop);
        order1.addProduct(mouse);
        order1.addProduct(keyboard);

        Order order2 = new Order(1002, 2);
        order2.addProduct(laptop);
        order2.addProduct(mouse);

        Order order3 = new Order(1003, 3);
        order3.addProduct(laptop);
        order3.addProduct(keyboard);

        Order order4 = new Order(1004, 4);
        order4.addProduct(laptop);
        order4.addProduct(stand);

        Order order5 = new Order(1005, 5);
        order5.addProduct(laptop);
        order5.addProduct(headphones);
        order5.addProduct(monitor);

        Order order6 = new Order(1006, 6);
        order6.addProduct(laptop);
        order6.addProduct(headphones);
        order6.addProduct(laptopBag);

        Order order7 = new Order(1007, 7);
        order7.addProduct(laptop);
        order7.addProduct(mouse);
        order7.addProduct(usbHub);

        Order order8 = new Order(1008, 8);
        order8.addProduct(smartphone);
        order8.addProduct(headphones);
        order8.addProduct(webcam);

        Order order9 = new Order(1009, 9);
        order9.addProduct(laptop);
        order9.addProduct(monitor);
        order9.addProduct(webcam);

        // STORE ORDERS

        List<Order> orders = new ArrayList<>();

        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orders.add(order4);
        orders.add(order5);
        orders.add(order6);
        orders.add(order7);
        orders.add(order8);
        orders.add(order9);

        // CALCULATE PRODUCT PAIRS AND SALES

        Map<String, Integer> pairCounts =
                CoPurchaseCounter.countPairs(orders);

        Map<String, Integer> sales =
                CoPurchaseCounter.countProductSales(orders);

        // INTERACTIVE MENU

        int choice = 0;

        do {

            System.out.println("\n==================================");
            System.out.println("       WELCOME TO BUYNEXT");
            System.out.println("  PRODUCT RECOMMENDATION SYSTEM");
            System.out.println("==================================");

            System.out.println("1. Product-based Recommendations");
            System.out.println("2. Customer-specific Recommendations");
            System.out.println("3. Top 3 Bestselling Products");
            System.out.println("4. New Customer Recommendations");
            System.out.println("5. Top 5 Co-Purchased Product Pairs");
            System.out.println("6. Exit");

            System.out.print("\nEnter your choice: ");

            try {

                choice = Integer.parseInt(scanner.nextLine().trim());

            } catch (NumberFormatException e) {

                System.out.println("Invalid input. Enter a number from 1 to 6.");
                continue;
            }

            switch (choice) {

                // OPTION 1: PRODUCT-BASED RECOMMENDATIONS

                case 1:

                    System.out.println("\nAvailable products:");

                    for (Product product : products) {
                        System.out.println("- " + product.name);
                    }

                    System.out.print("\nEnter product name: ");

                    String selectedProduct = scanner.nextLine().trim();

                    boolean productExists = false;

                    for (Product product : products) {

                        if (product.name.equalsIgnoreCase(selectedProduct)) {

                            selectedProduct = product.name;
                            productExists = true;
                            break;
                        }
                    }

                    if (!productExists) {

                        System.out.println("Product not found.");
                        break;
                    }

                    List<String> productRecommendations =
                            RecommendationService.recommendProducts(
                                    selectedProduct, pairCounts, 3);

                    System.out.println("\nRecommended products:");

                    if (productRecommendations.isEmpty()) {

                        System.out.println("No recommendations available.");

                    } else {

                        for (String item : productRecommendations) {
                            System.out.println("- " + item);
                        }
                    }

                    break;


                // OPTION 2: CUSTOMER-SPECIFIC RECOMMENDATIONS

                case 2:

                    System.out.print("\nEnter customer ID: ");

                    try {

                        int customerId =
                                Integer.parseInt(scanner.nextLine().trim());

                        boolean customerExists = false;

                        for (Order order : orders) {

                            if (order.customerId == customerId) {

                                customerExists = true;
                                break;
                            }
                        }

                        if (!customerExists) {

                            System.out.println("Customer not found.");
                            break;
                        }

                        List<String> customerRecommendations =
                                RecommendationService.recommendForCustomer(
                                        customerId, orders, pairCounts, 3);

                        System.out.println("\nPersonalized recommendations:");

                        if (customerRecommendations.isEmpty()) {

                            System.out.println("No recommendations available.");

                        } else {

                            for (String item : customerRecommendations) {
                                System.out.println("- " + item);
                            }
                        }

                    } catch (NumberFormatException e) {

                        System.out.println("Invalid customer ID.");
                    }

                    break;


                // OPTION 3: BESTSELLER RECOMMENDATIONS

                case 3:

                    List<String> bestsellers =
                            RecommendationService.getBestsellers(sales, 3);

                    System.out.println("\n===== TOP 3 BESTSELLERS =====");

                    for (String item : bestsellers) {
                        System.out.println("- " + item);
                    }

                    break;


                // OPTION 4: NEW CUSTOMER RECOMMENDATIONS

                case 4:

                    System.out.print("\nEnter new customer ID: ");

                    try {

                        int newCustomerId =
                                Integer.parseInt(scanner.nextLine().trim());

                        List<String> newCustomerRecommendations =
                                RecommendationService.recommendForNewCustomer(
                                        newCustomerId, orders, sales, 3);

                        System.out.println("\nRecommendations for customer "
                                + newCustomerId + ":");

                        if (newCustomerRecommendations.isEmpty()) {

                            System.out.println("No recommendations available.");

                        } else {

                            for (String item : newCustomerRecommendations) {
                                System.out.println("- " + item);
                            }
                        }

                    } catch (NumberFormatException e) {

                        System.out.println("Invalid customer ID.");
                    }

                    break;


                // OPTION 5: CO-PURCHASE ANALYTICS

                case 5:

                    List<Map.Entry<String, Integer>> topPairs =
                            RecommendationService.getTopProductPairs(
                                    pairCounts, 5);

                    System.out.println(
                            "\n===== TOP 5 CO-PURCHASED PRODUCT PAIRS =====");

                    if (topPairs.isEmpty()) {

                        System.out.println("No product pair data available.");

                    } else {

                        for (Map.Entry<String, Integer> pair : topPairs) {

                            System.out.println(
                                    pair.getKey()
                                    + " | Purchased together: "
                                    + pair.getValue()
                                    + " times"
                            );
                        }
                    }

                    break;


                // OPTION 6: EXIT

                case 6:

                    System.out.println("\nThank you for using BuyNext!");
                    System.out.println("Goodbye!");

                    break;


                // INVALID MENU CHOICE

                default:

                    System.out.println(
                            "Invalid choice. Please select 1 to 6.");
            }

        } while (choice != 6);

        scanner.close();
    }
}
