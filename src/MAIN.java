import java.util.Scanner;
import java.util.Vector;

public class MAIN {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int numberOfStoreHouses = scan.nextInt();
        Vector<StoreHouse> storeHouses = new Vector<>();
        for(int i = 0 ; i < numberOfStoreHouses ; i++)
            storeHouses.add(new StoreHouse(scan.nextInt()));

        int nodes = scan.nextInt(), edges = scan.nextInt();
        Vector<Vector<Pair>> city = new Vector<>();
        for(int i = 0 ; i <= nodes ; i++)
            city.add(new Vector<Pair>());

        for(int i = 0 ; i < edges ; i++)
        {
            int u = scan.nextInt(), v = scan.nextInt(), w = scan.nextInt();
            city.elementAt(u).add(new Pair(v, w));
            city.elementAt(v).add(new Pair(u, w));
        }

        Vector<Product>products = new Vector<>();
        int nProducts = scan.nextInt();
        for(int i = 0 ; i < nProducts ; i++)
        {
            products.add(new Product(scan.next(), scan.nextDouble()));
        }

        Store store = new Store(storeHouses, city, products);
        while (true){
            System.out.println("To Login type 1");
            System.out.println("To Register type 2");
            int option = scan.nextInt();
            if(option == 1){
                //login
                System.out.println("if you are an admin type 1");
                System.out.println("if you are a customer type 2");
                int type = scan.nextInt();
                if(type == 1){
                    store.loginAdmin();
                }else{
                    store.loginCustomer();
                }
            }
            else if(option == 2){
                store.register();
            }
            else{
                break;
            }
        }
    }
}