package DataLayer;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static java.lang.Long.parseLong;

public class Order {

    private long orderId;
    private Date orderDate;//The date the order was created
    private Date deliveryDate; //The date the order was sent
    private OrderStatus status;
    private long customrId;

    public Order(String orderInfo) throws ParseException {
        String [] objectData = orderInfo.split(" ");
        DateFormat dateFormat  = new SimpleDateFormat("dd/MM/yyyy");
        Date tempOrderDate = dateFormat.parse(objectData[4]);
        //Date tempOrderDate = new SimpleDateFormat("dd/MM/yyyy").parse(objectData[4]);
        Date tempDeliveryDate = dateFormat.parse(objectData[7]);

        setCustomrId(parseLong(objectData[12]));
        setOrderId(parseLong(objectData[1]));
        setOrderDate(tempOrderDate);
        setDeliveryDate(tempDeliveryDate);
        setStatus(OrderStatus.valueOf(objectData[9]));

    }

    public Order(long Oid, Date OorderDate, Date OdeliveryDate, OrderStatus Ostatus, long OcustomrId)
    {
        setOrderId(Oid);
        setOrderDate(OorderDate);
        setDeliveryDate(OdeliveryDate);
        setStatus(Ostatus);
        setCustomrId(OcustomrId);
    }



    public String toString()
    {
        SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");
        return "order: "+ getOrderId() + " order date: "+ ft.format(getOrderDate()) +" delivery date: "+ ft.format(getDeliveryDate()) + " status: "+ getStatus() + " customr id: "+ getCustomrId()+"\n";
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public long getCustomrId() {
        return customrId;
    }

    public void setCustomrId(long customrId) {
        this.customrId = customrId;
    }
}