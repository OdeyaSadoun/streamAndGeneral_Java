package DataLayer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class DataSource {

    public static List<Customer> allCustomers;
    public static List<Order> allOrders;
    public static List<Product> allProducts;
    public static List<OrderProduct> allOrderProducts;
    // Update this path according to your data files location
    public static String basePath = "src/";
    public static String customersPath = basePath +"customers.txt";
    public static String ordersPath = basePath +"orders.txt";
    public static String productsPath = basePath +"products.txt";
    public static String orderProductPath = basePath +"orderProduct.txt";

    static
    {
        try {
            allCustomers = readCustomersfromFile();
            allOrders = readOrdersfromFile();
            allProducts = readProductsfromFile();
            allOrderProducts = readOrderProductsfromFile();
        } catch (IOException e) { e.printStackTrace(); }
    }
    public static List<Customer> readCustomersfromFile() throws IOException {
        try
        {
            Stream<String> lines = Files.lines(Paths.get(customersPath));
            List<Customer> customersfromFile = lines
                    .map(l-> new Customer(l))
                    .collect(Collectors.toList());
            return customersfromFile;
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return null;
   }

    public static List<Order> readOrdersfromFile() throws IOException {
        try
        {
            Stream<String> lines = Files.lines(Paths.get(ordersPath));
            List<Order> ordersfromFile = lines
                    .map(l-> {
                        try {
                            return new Order(l);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        return null;
                    })
                    .collect(Collectors.toList());
            return ordersfromFile;
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    public static List<Product> readProductsfromFile() throws IOException {
        try
        {
            Stream<String> lines = Files.lines(Paths.get(productsPath));
            List<Product> productsfromFile = lines
                    .map(l-> new Product(l))
                    .collect(Collectors.toList());
            return productsfromFile;
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    public static List<OrderProduct> readOrderProductsfromFile() throws IOException {
        try
        {
            Stream<String> lines = Files.lines(Paths.get(orderProductPath));
            List<OrderProduct> orderProductsfromFile = lines
                    .map(l-> new OrderProduct(l))
                    .collect(Collectors.toList());
            return orderProductsfromFile;
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            DataSource.readCustomersfromFile();

        }catch (IOException e){}
    }
}


