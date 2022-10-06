package entity.pet;

public enum Status {
    AVAILABLE("available"),
    PENDING("pending"),
    SOLD("sold");
    private String name;

    Status(String name) {
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