package entity.order;

import java.time.LocalDateTime;

public class Order {
    long id;
    long petId;
    int quantity;
    String shipDate;
    OrderStatus status;
    boolean complete;

    public Order(long id, long petId, int quantity, String shipDate, OrderStatus status, boolean complete) {
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.shipDate = shipDate;
        this.status = status;
        this.complete = complete;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", petId=" + petId +
                ", quantity=" + quantity +
                ", shipDate='" + shipDate + '\'' +
                ", status=" + status +
                ", complete=" + complete +
                '}';
    }
}
