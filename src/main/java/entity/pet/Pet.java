package entity.pet;

import java.util.ArrayList;

public class Pet {
    long id;
    Category category;
    String name;
    ArrayList<String> photoUrls;
    ArrayList<Tag> tags;
    Status status;

    public Pet(long id, Category category, String name, ArrayList<String> photoUrls, ArrayList<Tag> tags, Status status) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.photoUrls = photoUrls;
        this.tags = tags;
        this.status = status;
    }

    public Pet() {
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", photoUrls=" + photoUrls +
                ", tags=" + tags +
                ", status=" + status +
                '}';
    }
}
