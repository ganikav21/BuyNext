import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CoPurchaseCounter {

    // Count product pairs without duplicate counting per order

    public static Map<String, Integer> countPairs(List<Order> orders) {

        Map<String, Integer> pairCounts = new HashMap<>();

        for (Order order : orders) {

            Set<String> uniqueProducts = new LinkedHashSet<>();

            for (Product product : order.products) {
                uniqueProducts.add(product.name);
            }

            List<String> products = new java.util.ArrayList<>(uniqueProducts);

            for (int i = 0; i < products.size(); i++) {

                for (int j = i + 1; j < products.size(); j++) {

                    ProductPair pair =
                            new ProductPair(products.get(i), products.get(j));

                    String pairKey =
                            pair.product1 + " + " + pair.product2;

                    pairCounts.put(
                            pairKey,
                            pairCounts.getOrDefault(pairKey, 0) + 1
                    );
                }
            }
        }

        return pairCounts;
    }


    // Count individual product sales per order

    public static Map<String, Integer> countProductSales(
            List<Order> orders) {

        Map<String, Integer> sales = new HashMap<>();

        for (Order order : orders) {

            Set<String> uniqueProducts = new HashSet<>();

            for (Product product : order.products) {
                uniqueProducts.add(product.name);
            }

            for (String productName : uniqueProducts) {

                sales.put(
                        productName,
                        sales.getOrDefault(productName, 0) + 1
                );
            }
        }

        return sales;
    }
}