import java.util.Vector;

public class Customer extends Account {
    private int blockNumber;
    private Vector<Order>orders;

    public int getBlockNumber() {
        return blockNumber;
    }

    public Vector<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order){
        orders.add(order);
    }
    public Customer(String userName, String password, int blockNumber) {
        super(userName, password);
        this.blockNumber = blockNumber;
        this.orders = new Vector<Order>();
    }
}