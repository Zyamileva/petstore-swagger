package entity.pet;

import com.google.gson.annotations.SerializedName;

public enum Status {
    @SerializedName("available")
    AVAILABLE("available"),
    @SerializedName("pending")
    PENDING("pending"),
    @SerializedName("sold")
    SOLD("sold");
    private String name;

    Status(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Status{" +
                "name='" + name + '\'' +
                '}';
    }
}