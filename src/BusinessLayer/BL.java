package BusinessLayer;

import DataLayer.*;

import javax.swing.text.html.parser.Parser;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.reverseOrder;
import static java.util.Map.Entry.comparingByValue;
import static java.util.function.UnaryOperator.identity;

public class BL implements IBL {
    @Override
    public Product getProductById(long productId) {
        //Returns product according to product code
        return DataSource.allProducts.stream()
                .filter(product -> product.getProductId() == productId).findFirst().orElse(null);
    }

    @Override
    public Order getOrderById(long orderId) {
        //To do
        return null;
    }

    @Override
    public Customer getCustomerById(long customerId) {
        //Returns customer according to customer code
        return DataSource.allCustomers.stream()
                .filter(customer -> customer.getId() == customerId).findFirst().orElse(null);
    }


    @Override
    public List<Product> getProducts(ProductCategory cat, double price) {
        //To do
        return null;
    }

    @Override
    public List<Customer> popularCustomers() {
        //The function will return the list of popular customers sorted in ascending order.
        return DataSource.allCustomers.stream()
                .filter(customer -> customer.getTier() == 3 && getCustomerOrders(customer.getId()).size() > 10)
                .sorted(Comparator.comparing(Customer::getId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> getCustomerOrders(long customerId) {
        //To do
        return null;
    }

    @Override
    public long numberOfProductInOrder(long orderId) {
        //The function will return the number of products ordered in the orderId number.
        return getOrderProducts(orderId).stream().count();
    }

    @Override
    public List<Product> getPopularOrderedProduct(int orderedtimes) {
        //To do
        return null;
    }

    @Override
    public List<Product> getOrderProducts(long orderId)
    {
        //The function will return the product list to a specific order with orderId.
        //The list will be sorted in ascending order by product identification number.
        return DataSource.allOrderProducts.stream()
                .filter(orderProduct -> orderProduct.getOrderId() == orderId)
                .map(orderProduct -> new Product(getProductById(orderProduct.getProductId()).toString()))
                .sorted(Comparator.comparing(Product::getProductId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Customer> getCustomersWhoOrderedProduct(long productId) {
        //To do
        return null;
    }

    @Override
    public Product getMaxOrderedProduct() {
        //The function will return the product that has been ordered the most times.
//        return DataSource.allOrderProducts.stream()
//                .max(Comparator.comparing(OrderProduct::getQuantity))
//                .map(orderProduct -> new Product(getProductById()))
        return  null;

    }
    @Override
    public double sumOfOrder(long orderID) {
        //To do
        return 0;
    }

    @Override
    public List<Order> getExpensiveOrders(double price) {
        //To do
        return null;
    }

    @Override
    public List<Customer> ThreeTierCustomerWithMaxOrders() {
        //To do
        return null;

    }

}
