import java.io.*;

public class Products implements Serializable{

    private int id;
    private String category;
    private String name;
    private int quantity;
    private int price;

    public Products(int id, String category, String name, int quantity, int price) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.quantity = quantity;
        this.price = price;

    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id='" + id + '\'' +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", quantity='" + quantity + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

}

