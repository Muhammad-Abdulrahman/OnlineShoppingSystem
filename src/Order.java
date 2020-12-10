import java.util.Vector;

public class Order {
    private static int counter = 0;
    private int id;
    private Vector<Product> products;
    private int storeHouseId;

    public int getId() {
        return id;
    }

    public Vector<Product> getProducts() {
        return products;
    }

    public int getStoreHouseId() {
        return storeHouseId;
    }

    public Order(Vector<Product> products, int storeHouseId) {
        counter++;
        this.id = counter;
        this.products = products;
        this.storeHouseId = storeHouseId;
    }
}
