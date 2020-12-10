public class Product {
    private static int counter = 0;
    private int id;
    private String name;
    private double price;

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Product(String name, double price) {
        counter++;
        this.id = counter;
        this.name = name;
        this.price = price;
    }
}