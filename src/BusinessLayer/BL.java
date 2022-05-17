package BusinessLayer;

import DataLayer.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BL implements IBL {
    @Override
    public Product getProductById(long productId) {
        //Returns product according to product code
        return DataSource.allProducts.stream()
                .filter(product -> product.getProductId() == productId).findFirst().orElse(null);
    }

    @Override
    public Order getOrderById(long orderId) {
        //Returns an order according to an order code
        return DataSource.allOrders.stream()
                .filter(order -> order.getOrderId() == orderId).findFirst().orElse(null);
    }

    @Override
    public Customer getCustomerById(long customerId) {
        //Returns customer according to customer code
        return DataSource.allCustomers.stream()
                .filter(customer -> customer.getId() == customerId).findFirst().orElse(null);
    }


    @Override
    public List<Product> getProducts(ProductCategory cat, double price) {
        //The function returns the list of products sorted in ascending order that are in the cat category and their price is equal to or less than price.
        return DataSource.allProducts.stream().
                filter(product -> product.getCategory()==cat && product.getPrice()<=price)
                .sorted(Comparator.comparing(Product::getProductId)).collect(Collectors.toList());
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
        //The function will return the customer order list with customerId ID. The list will be sorted in ascending order.
        return DataSource.allOrders.stream().
                filter(Order -> Order.getCustomrId()==customerId)
                .sorted(Comparator.comparing(Order::getOrderId)).collect(Collectors.toList());
    }

    @Override
    public long numberOfProductInOrder(long orderId) {
        //The function will return the number of products ordered in the orderId number.
        return getOrderProducts(orderId).stream().count();
    }

    @Override
    public List<Product> getPopularOrderedProduct(int orderedtimes) {
        //
        Map<Long, List<Product>> productsByProductId = DataSource.allOrderProducts.stream()
                .map(orderProduct -> getProductById(orderProduct.getProductId()))
                .collect(Collectors.groupingBy(Product::getProductId));

        return productsByProductId.entrySet().stream()
                .filter(entry -> entry.getValue().size() >= orderedtimes)
                .map(longListEntry -> getProductById(longListEntry.getKey()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> getOrderProducts(long orderId)
    {
        //The function will return the product list to a specific order with orderId.
        //The list will be sorted in ascending order by product identification number.
        return DataSource.allOrderProducts.stream()
                .filter(orderProduct -> orderProduct.getOrderId() == orderId)
                .map(orderProduct -> getProductById(orderProduct.getProductId()))
                .sorted(Comparator.comparing(Product::getProductId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Customer> getCustomersWhoOrderedProduct(long productId) {
        //???
        //The function will return the list of customers who purchased a product with a productId code.
        // The list will be sorted in ascending order by customer ID number.
        return DataSource.allOrderProducts.stream()
                .filter(orderProduct -> orderProduct.getProductId()==productId)
                .peek(System.out::println)
                .map(orderProduct-> getOrderById(orderProduct.getOrderId()))
                .map(order->getCustomerById(order.getCustomrId()))
                .sorted(Comparator.comparing(Customer::getId))
                .collect(Collectors.toList());
    }


//    @Override
//    public Product getMaxOrderedProduct() {
//        //The function will return the product that has been ordered the most times.
//        return DataSource.allOrderProducts.stream()
//                .map(orderProduct -> new Product(getProductById(orderProduct.getProductId()).toString()))
//                .max(Comparator.comparing(Product::getProductId)).orElse(new Product(""));
//    }

    @Override
    public Product getMaxOrderedProduct() {
        //The function will return the product that has been ordered the most times.
        Map<Long, List<Product>> productsByProductId = DataSource.allOrderProducts.stream()
                .map(orderProduct -> getProductById(orderProduct.getProductId()))
                .collect(Collectors.groupingBy(Product::getProductId));

        Long productId = productsByProductId.entrySet().stream()
                .max(Comparator.comparingInt(entry -> entry.getValue().size()))
                .map(Map.Entry::getKey)
                .orElse(null);
        return getProductById(productId);
    }

    @Override
    public double sumOfOrder(long orderID) {
        //???
        //The function returns the total price of an order with the orderID order number.
        Map<Long, Integer> productToQuantity = DataSource.allOrderProducts.stream()
                .filter(orderProduct -> orderProduct.getOrderId() == orderID)
                .peek(System.out::println)
                .collect(Collectors.toMap(OrderProduct::getProductId, OrderProduct::getQuantity, (r1, r2) -> r1));


        return DataSource.allOrderProducts.stream()
                .filter(orderProduct -> orderProduct.getOrderId()==orderID)
                .map(orderProduct -> getProductById(orderProduct.getProductId()))
                .mapToDouble(p -> p.getPrice()*productToQuantity.get(p.getProductId()))
                .sum();
    }


    @Override
    public List<Order> getExpensiveOrders(double price) {
        //???
        //The function returns the list of orders whose cost is greater than the price sorted in ascending order.
        return DataSource.allOrders.stream()
                .filter(order -> sumOfOrder(order.getOrderId()) > price)
                .sorted(Comparator.comparing(Order::getOrderId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Customer> ThreeTierCustomerWithMaxOrders() {
        Map<Long,List<Order>> threeTierCustomerOrders = DataSource.allOrders.stream()
                .filter(order -> getCustomerById(order.getCustomrId()).getTier() == 3)
                .collect(Collectors.groupingBy(Order::getCustomrId));

        Map<Long, Integer> threeTierCustomerOrderCount = threeTierCustomerOrders.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().size()));

        return threeTierCustomerOrderCount.entrySet().stream()
                .filter(entry -> entry.getValue() == Collections.max(threeTierCustomerOrderCount.values()))
                .map(Map.Entry::getKey).map(ci -> getCustomerById(ci))
                .sorted(Comparator.comparing(Customer::getId))
                .collect(Collectors.toList());

    }

}
