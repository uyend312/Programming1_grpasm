import java.io.*;

public class Products implements Serializable{

    private String id;
    private String category;
    private String name;

    private double price;

    public Products(String id, String category, String name, double price) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }


    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id='" + id + '\'' +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

}

