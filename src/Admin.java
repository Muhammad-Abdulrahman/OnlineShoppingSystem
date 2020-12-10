import java.util.Vector;

public class Admin extends Account {
    private Vector<StoreHouse> storeHouses;
    private Vector<Product> products;

    public Vector<StoreHouse> getStoreHouses() {
        return storeHouses;
    }

    public Vector<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product){
        this.products.add(product);
    }
    public void removeProduct(int id){
        products.removeIf(product -> (product.getId() == id));
    }

    public Admin(String userName, String password, Vector<StoreHouse> storeHouses) {
        super(userName, password);
        this.storeHouses = storeHouses;
        this.products = new Vector<Product>();

    }
}