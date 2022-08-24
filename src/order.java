import java.io.*;
import java.util.*;

public class order {
        private String productId;
        private String productName;
        private float price;
        private int quantity;
        private String category;

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public static String generateUID() {    
            UUID uuid = UUID.randomUUID();
            // String uID = uuid.toString();
            return uuid.toString();
        }

        public order(String productId, String productName, float price, String category) {
            this.productId = productId;
            this.productName = productName;
            this.price = price;
            this.category = category;
    }

    @Override
    public String toString() {
        return "productID = " + productId + ", name = " + productName +
                ", category = " + category + ", quantity = " + quantity +
                ", price = " + price;
    }

}
