package BusinessLayer;

import DataLayer.*;

import java.util.List;

import static java.util.Collections.reverseOrder;
import static java.util.Map.Entry.comparingByValue;
import static java.util.function.UnaryOperator.identity;

public class BL implements IBL {
    @Override
    public Product getProductById(long productId) {
//        //Product prod = new Product("");
//        for (int i = 0; i < DataSource.allProducts.size(); i++) {
//            if (productId == i)
//                return DataSource.allProducts.get(i);
//        }
//        //else
//        return null;
//      User match = users.stream().filter((user) -> user.getId() == 1).findAny().get();
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
        for (int i = 0; i < DataSource.allCustomers.size(); i++) {
            if (customerId == i)
                return DataSource.allCustomers.get(i);
        }
        //else
        return null;
    }


    @Override
    public List<Product> getProducts(ProductCategory cat, double price) {
        //To do
        return null;
    }

    @Override
    public List<Customer> popularCustomers() {
        //To do
        return null;
    }

    @Override
    public List<Order> getCustomerOrders(long customerId) {
        //To do
        return null;
    }

    @Override
    public long numberOfProductInOrder(long orderId) {
        //To do
        return 0;
    }

    @Override
    public List<Product> getPopularOrderedProduct(int orderedtimes) {
        //To do
        return null;
    }

    @Override
    public List<Product> getOrderProducts(long orderId)
    {
        //To do
        return null;
    }

    @Override
    public List<Customer> getCustomersWhoOrderedProduct(long productId) {
        //To do
        return null;
    }

    @Override
    public Product getMaxOrderedProduct() {
        //To do
        return null;

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
