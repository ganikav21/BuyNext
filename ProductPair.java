public class ProductPair {

    String product1;
    String product2;

    public ProductPair(String product1, String product2) {

        // Keep the pair in a consistent order
        if (product1.compareTo(product2) < 0) {
            this.product1 = product1;
            this.product2 = product2;
        } else {
            this.product1 = product2;
            this.product2 = product1;
        }
    }

    public void displayPair() {
        System.out.println(product1 + " + " + product2);
    }
}