
public class Products {
    private int id;
    private String category;
    private String name;
    private int quantity;
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public Products(int id, String category, String name, int quantity, int price) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.quantity = quantity;
        this.price = price;

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
