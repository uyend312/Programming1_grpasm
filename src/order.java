import java.io.*;
import java.util.Scanner;
import java.util.UUID;

public class order {
        private int productId;

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }
        private String productName;
        private float unitPrice;
        private int quantity;
        private String category;

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

        public float getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(float unitPrice) {
            this.unitPrice = unitPrice;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

}
