import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RecommendationService {

    // 1. PRODUCT-BASED RECOMMENDATIONS

    public static List<String> recommendProducts(
            String selectedProduct,
            Map<String, Integer> pairCounts,
            int limit) {

        if (limit <= 0) {
            return new ArrayList<>();
        }

        List<Map.Entry<String, Integer>> candidates =
                new ArrayList<>();

        for (Map.Entry<String, Integer> entry : pairCounts.entrySet()) {

            String pair = entry.getKey();

            if (pair.startsWith(selectedProduct + " + ")
                    || pair.endsWith(" + " + selectedProduct)) {

                candidates.add(entry);
            }
        }

        candidates.sort(
                Comparator.comparingInt(
                        (Map.Entry<String, Integer> e) -> e.getValue()
                ).reversed()
                .thenComparing(Map.Entry::getKey)
        );

        List<String> recommendations = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : candidates) {

            String[] pair = entry.getKey().split(" \\+ ", 2);

            String recommendedProduct =
                    pair[0].equals(selectedProduct)
                    ? pair[1] : pair[0];

            recommendations.add(recommendedProduct);

            if (recommendations.size() >= limit) {
                break;
            }
        }

        return recommendations;
    }


    // 2. CUSTOMER-SPECIFIC RECOMMENDATIONS

    public static List<String> recommendForCustomer(
            int customerId,
            List<Order> orders,
            Map<String, Integer> pairCounts,
            int limit) {

        if (limit <= 0) {
            return new ArrayList<>();
        }

        Set<String> purchasedProducts = new HashSet<>();

        for (Order order : orders) {

            if (order.customerId == customerId) {

                for (Product product : order.products) {
                    purchasedProducts.add(product.name);
                }
            }
        }

        Map<String, Integer> scores = new HashMap<>();

        for (Map.Entry<String, Integer> entry : pairCounts.entrySet()) {

            String[] pair = entry.getKey().split(" \\+ ", 2);

            String product1 = pair[0];
            String product2 = pair[1];

            int count = entry.getValue();

            if (purchasedProducts.contains(product1)
                    && !purchasedProducts.contains(product2)) {

                scores.merge(product2, count, Integer::sum);

            } else if (purchasedProducts.contains(product2)
                    && !purchasedProducts.contains(product1)) {

                scores.merge(product1, count, Integer::sum);
            }
        }

        List<Map.Entry<String, Integer>> candidates =
                new ArrayList<>(scores.entrySet());

        candidates.sort(
                Comparator.comparingInt(
                        (Map.Entry<String, Integer> e) -> e.getValue()
                ).reversed()
                .thenComparing(Map.Entry::getKey)
        );

        List<String> recommendations = new ArrayList<>();

        for (Map.Entry<String, Integer> candidate : candidates) {

            recommendations.add(candidate.getKey());

            if (recommendations.size() >= limit) {
                break;
            }
        }

        return recommendations;
    }


    // 3. BESTSELLER RECOMMENDATIONS

    public static List<String> getBestsellers(
            Map<String, Integer> sales,
            int limit) {

        if (limit <= 0) {
            return new ArrayList<>();
        }

        List<Map.Entry<String, Integer>> candidates =
                new ArrayList<>(sales.entrySet());

        candidates.sort(
                Comparator.comparingInt(
                        (Map.Entry<String, Integer> e) -> e.getValue()
                ).reversed()
                .thenComparing(Map.Entry::getKey)
        );

        List<String> bestsellers = new ArrayList<>();

        for (Map.Entry<String, Integer> candidate : candidates) {

            bestsellers.add(candidate.getKey());

            if (bestsellers.size() >= limit) {
                break;
            }
        }

        return bestsellers;
    }


    // 4. NEW CUSTOMER AND COLD-START RECOMMENDATIONS

    public static List<String> recommendForNewCustomer(
            int customerId,
            List<Order> orders,
            Map<String, Integer> sales,
            int limit) {

        if (limit <= 0) {
            return new ArrayList<>();
        }

        Set<String> purchasedProducts = new HashSet<>();

        // Find customer's purchase history

        for (Order order : orders) {

            if (order.customerId == customerId) {

                for (Product product : order.products) {

                    purchasedProducts.add(product.name);
                }
            }
        }

        // No purchase history: recommend bestsellers

        if (purchasedProducts.isEmpty()) {

            return getBestsellers(sales, limit);
        }

        // Existing customer: exclude previously purchased products

        List<String> bestsellers =
                getBestsellers(sales, sales.size());

        List<String> recommendations = new ArrayList<>();

        for (String product : bestsellers) {

            if (!purchasedProducts.contains(product)) {

                recommendations.add(product);
            }

            if (recommendations.size() >= limit) {
                break;
            }
        }

        return recommendations;
    }
    // 5. TOP CO-PURCHASED PRODUCT PAIRS

public static List<Map.Entry<String, Integer>> getTopProductPairs(
        Map<String, Integer> pairCounts,
        int limit) {

    if (limit <= 0) {
        return new ArrayList<>();
    }

    List<Map.Entry<String, Integer>> candidates =
            new ArrayList<>(pairCounts.entrySet());

    candidates.sort(
            Comparator.comparingInt(
                    (Map.Entry<String, Integer> e) -> e.getValue()
            ).reversed()
            .thenComparing(Map.Entry::getKey)
    );

    List<Map.Entry<String, Integer>> topPairs =
            new ArrayList<>();

    for (Map.Entry<String, Integer> candidate : candidates) {

        topPairs.add(candidate);

        if (topPairs.size() >= limit) {
            break;
        }
    }

    return topPairs;
}
}