package entity.order;

public enum OrderStatus {
    AVAILABLE("approved"),
    PENDING("pending"),
    SOLD("delivered");
    private String name;

    OrderStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "'" + name + "'";
    }

}