import java.awt.*;
import java.util.*;
import java.util.List;


class Pair implements Comparable<Pair>{
    public int first, second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public int compareTo(Pair o) {
        if(this.first==o.first)
            return 0;
        else if(this.first>o.first)
            return 1;
        else
            return -1;
    }
}


public class Store {
    Admin admin;
    Vector<Customer>customers;
    Vector<Vector<Pair>> city;

    int isStoreHouse(int blockNumber){
        Vector<StoreHouse> storeHouses  = admin.getStoreHouses();
        for(int i = 0 ; i < storeHouses.size() ; i++){
            if(storeHouses.elementAt(i).getBlockNumber() == blockNumber){
                return storeHouses.elementAt(i).getId();
            }
        }

        return -1;
    }
    int getNearestStoreHouseId(int customerBlockNumber){

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(0, customerBlockNumber));
        int[] length = new int[city.size()];
        Arrays.fill(length, 1000000000);
        length[customerBlockNumber] = 0;
        while (pq.isEmpty() == false){
            int currentBlockNumber = pq.peek().second;
            int currentLength = pq.peek().first;
            pq.remove();
            if(currentLength != length[currentBlockNumber] )
                continue;
            int id = isStoreHouse(currentBlockNumber);
            if(id != -1){
                return id;
            }

            for(int i = 0 ; i < city.elementAt(currentBlockNumber).size() ; i++)
            {
                int child = city.elementAt(currentBlockNumber).elementAt(i).first;
                int wChild = city.elementAt(currentBlockNumber).elementAt(i).second;
                if(wChild + currentLength < length[child]){
                    pq.add(new Pair(wChild + currentLength, child));
                    length[child] = wChild + currentLength;
                }
            }
        }
        return -1;
    }

    boolean findUserName(String username){
        for(int i = 0 ; i < customers.size() ; i++)
        {
            if(customers.elementAt(i).getUserName().equals(username)){
                return true;
            }
        }
        return false;
    }

    public void showOrders(){
        for(int i = 0 ; i < admin.getProducts().size() ; i++)
        {
            System.out.print("The product which its name is: ");
            System.out.println(admin.getProducts().elementAt(i).getName());
            System.out.print("its id is: ");
            System.out.println(admin.getProducts().elementAt(i).getId());
        }
    }
    public void loginAdmin(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the username");
        String username = scan.next();
        System.out.println("Please enter the password");
        String password = scan.next();
        if(admin.getUserName().equals(username) && admin.getPassword().equals(password)){
            while (true){
                System.out.println("To add a product type 1");
                System.out.println("To delete a product type 2");
                System.out.println("To logout type anything else");
                int option = scan.nextInt();
                if(option == 1){
                    //add product
                    System.out.println("Please enter the product's name");
                    String productName = scan.next();
                    System.out.println("Please enter the product's price");
                    double price = scan.nextDouble();

                    Product newProduct = new Product(productName, price);
                    admin.addProduct(newProduct);
                }else if(option == 2){
                    //delete product
                    showOrders();

                    System.out.println("Please select the id of the product that you want to remove");
                    int id = scan.nextInt();
                    admin.removeProduct(id);
                }else{
                    break;
                }
            }
        }else{
            System.out.println("You entered a wrong username or password");
        }
    }

    public void loginCustomer(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the username");
        String username = scan.next();
        System.out.println("Please enter the password");
        String password = scan.next();
        boolean loggedIn = false;
        for(int i = 0 ; i < customers.size() ; i++){
            if(customers.elementAt(i).getUserName().equals(username)){
                if(customers.elementAt(i).getPassword().equals(password)){
                    //logged in
                    loggedIn = true;
                    while(true){
                        System.out.println("To add an order type 1");
                        System.out.println("To show your past orders type 2");
                        int option = scan.nextInt();
                        if(option == 1){
                            //add order
                            Vector<Product> cart = new Vector<Product>();
                            showOrders();
                            while(true){
                                System.out.println("Please enter the id of the product you want to buy or type -1 to checkout");
                                int id = scan.nextInt();
                                if(id == -1){
                                    break;
                                }else{
                                    int index = 0;
                                    for(int j = 0 ; j < admin.getProducts().size() ; j++)
                                    {
                                        if(admin.getProducts().elementAt(j).getId() == id){
                                            index = j;
                                            break;
                                        }
                                    }
                                    cart.add(admin.getProducts().elementAt(index));
                                }
                            }

                            customers.elementAt(i).addOrder(new Order(cart, getNearestStoreHouseId(customers.elementAt(i).getBlockNumber())));
                        }else if(option == 2){
                            Vector<Order>orders = customers.elementAt(i).getOrders();
                            for(int j = 0 ; j < orders.size() ; j++)
                            {
                                System.out.print("This order has been delivered from the store house which id is ");
                                System.out.println(orders.elementAt(j).getStoreHouseId());
                                System.out.println("And its products are");
                                Vector<Product> products = orders.elementAt(j).getProducts();
                                for(int k = 0 ; k < products.size() ; k++)
                                {
                                    System.out.print(products.elementAt(k).getName());
                                    System.out.print(" ");
                                    System.out.print(products.elementAt(k).getPrice());
                                }
                                System.out.println();

                            }
                        }else{
                            break;
                        }
                    }
                }else{
                    System.out.println("Sorry you entered a wrong username or password");
                    break;
                }
            }
        }
        if(!loggedIn){
            System.out.println("Sorry you entered a wrong username or password");
        }
    }

    public void register(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the username");
        String username = scan.next();
        System.out.println("Please enter the password");
        String password = scan.next();
        System.out.println("Please enter the your block number");
        int blockNumber = scan.nextInt();

        while (findUserName(username)){
            System.out.println("This username already exists please choose another");
            username = scan.next();
        }

        Customer newCustomer = new Customer(username, password, blockNumber);
        customers.add(newCustomer);
    }

    public Store(Vector<StoreHouse> storeHouses, Vector<Vector<Pair>> city, Vector<Product> products) {
        admin = new Admin("admin", "admin123admin", storeHouses);
        for(int i = 0 ; i < products.size() ; i++)
            admin.addProduct(products.elementAt(i));
        customers = new Vector<Customer>();
        this.city = city;
    }
}