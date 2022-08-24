import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class createOrder {
    public static void createOrder() throws Exception {
        int option;
        //s is used for Integer and s1 is for String data
        Scanner s = new Scanner(System.in);
        Scanner s1 = new Scanner(System.in);

        ArrayList<order> al = new ArrayList<>();
        boolean found = false;
        option = 1;

        while (option != 0) {
            System.out.println("1.ADD PRODUCT BY ID");
            System.out.println("2.ADD PRODUCT BY NAME");
            System.out.println("3.VIEW CART");
            System.out.println("4.PROCEED TO CHECKOUT");
            System.out.println("5.DELETE PRODUCT");
            System.out.println("0.EXIT");

            System.out.println("Enter your option: ");
            option = s.nextInt();

            Scanner fileInput = new Scanner(new File("items.txt"));
            FileWriter fileOutput = new FileWriter("order.txt");

            if (option == 1) {
                System.out.println("Enter product ID: ");
                String itemID = s.next();

                while (fileInput.hasNextLine()) {
                    String line = new String();
                    line = fileInput.nextLine();
                    String[] info = line.split(", ");
                    for (int i = 0; i <= info.length - 1; i++) {
                        if (Objects.equals(itemID, info[i])) {
                            String productID = info[i];
                            String productName = info[i+1];
                            Float price =  Float.parseFloat(info[i+2]);
                            String category = info[i+3];

                            al.add(new order(productID, productName, price, category));
                            fileOutput.write(String.valueOf(al));

                            System.out.println(al);

                        }

                    }

                    //System.out.println(fileInput.nextLine());
                }
            }
            if (option == 2) {
            }
            if (option == 3) {
            }
            if (option == 4) {
            }
            if (option == 5) {
            }
        }
    }


    public static void main(String args[]) throws Exception {
        createOrder();
    }
}
