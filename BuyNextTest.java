import java.util.*;

public class BuyNextTest {

    public static void main(String[] args) {

        Product laptop = new Product(101, "Laptop", "Electronics", 60000);
        Product mouse = new Product(102, "Mouse", "Electronics", 800);
        Product keyboard = new Product(103, "Keyboard", "Electronics", 1500);

        List<Order> orders = new ArrayList<>();

        Order order1 = new Order(1001, 1);
        order1.addProduct(laptop);
        order1.addProduct(mouse);

        Order order2 = new Order(1002, 2);
        order2.addProduct(laptop);
        order2.addProduct(keyboard);

        Order order3 = new Order(1003, 3);
        order3.addProduct(laptop);
        order3.addProduct(mouse);

        orders.add(order1);
        orders.add(order2);
        orders.add(order3);

        Map<String, Integer> pairCounts =
                CoPurchaseCounter.countPairs(orders);

        Map<String, Integer> sales =
                CoPurchaseCounter.countProductSales(orders);

        // TEST 1: Co-purchase frequency

        check(
                pairCounts.get("Laptop + Mouse") == 2,
                "Co-purchase counting"
        );

        // TEST 2: Product sales

        check(
                sales.get("Laptop") == 3,
                "Product sales counting"
        );

        // TEST 3: Product recommendations

        List<String> recommendations =
                RecommendationService.recommendProducts(
                        "Laptop", pairCounts, 2
                );

        check(
                recommendations.equals(
                        Arrays.asList("Mouse", "Keyboard")
                ),
                "Product recommendations"
        );

        // TEST 4: Invalid recommendation limit

        check(
                RecommendationService.getBestsellers(sales, 0).isEmpty(),
                "Invalid recommendation limit"
        );

        // TEST 5: Bestseller ranking

        List<String> bestsellers =
                RecommendationService.getBestsellers(sales, 3);

        check(
                bestsellers.equals(
                        Arrays.asList("Laptop", "Mouse", "Keyboard")
                ),
                "Bestseller ranking"
        );

        // TEST 6: Customer-specific recommendations

List<String> customerRecommendations =
        RecommendationService.recommendForCustomer(
                1, orders, pairCounts, 3
        );

check(
        customerRecommendations.equals(
                Arrays.asList("Keyboard")
        ),
        "Customer-specific recommendations"
);


// TEST 7: New customer recommendations

List<String> newCustomerRecommendations =
        RecommendationService.recommendForNewCustomer(
                99, orders, sales, 3
        );

check(
        newCustomerRecommendations.equals(
                Arrays.asList("Laptop", "Mouse", "Keyboard")
        ),
        "Cold-start recommendations"
);

// TEST 8: Top co-purchased pairs

List<Map.Entry<String, Integer>> topPairs =
        RecommendationService.getTopProductPairs(pairCounts, 2);

check(
        topPairs.size() == 2
                && topPairs.get(0).getKey().equals("Laptop + Mouse")
                && topPairs.get(0).getValue() == 2,
        "Top co-purchased pairs"
);
// TEST 9: Duplicate product protection

Order duplicateOrder = new Order(1004, 4);

duplicateOrder.addProduct(laptop);
duplicateOrder.addProduct(laptop);
duplicateOrder.addProduct(mouse);

List<Order> duplicateOrders = new ArrayList<>();
duplicateOrders.add(duplicateOrder);

Map<String, Integer> duplicatePairCounts =
        CoPurchaseCounter.countPairs(duplicateOrders);

Map<String, Integer> duplicateSales =
        CoPurchaseCounter.countProductSales(duplicateOrders);

check(
        duplicatePairCounts.get("Laptop + Mouse") == 1,
        "Duplicate pair protection"
);

check(
        duplicateSales.get("Laptop") == 1,
        "Duplicate sales protection"
);
System.out.println("\nAll tests passed successfully!");
    }

    public static void check(boolean condition, String testName) {

        if (!condition) {
            throw new AssertionError("FAILED: " + testName);
        }

        System.out.println("PASSED: " + testName);
    }
}