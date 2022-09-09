import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class createOrder {
    public static void createOrder() throws Exception {
        int option;
        boolean found = false;
        //s is used for Integer and s1 is for String data
        Scanner s = new Scanner(System.in);
        Scanner s1 = new Scanner(System.in);
        File file = new File("Products.txt");
        ArrayList<Products> cart = new ArrayList<>();
        ObjectOutputStream oos;
        ObjectInputStream ois;
        ListIterator<Products> li;

        //Deserialize if file existed
        if (file.isFile()) {
            ois = new ObjectInputStream(new FileInputStream(file));
            cart = (ArrayList<Products>) ois.readObject();
            ois.close();
        }
        do {
            System.out.println("ENTER A NUMBER 1-5.\n");
            System.out.println("1. ADD A PRODUCT TO CART");
            System.out.println("2. DELETE A PRODUCT IN CART");
            System.out.println("3. SEARCH PRODUCTS (BY CATEGORY)");
            System.out.println("4. VIEW CART");
            System.out.println("5. PLACE ORDER AND CHECKOUT");
            System.out.println("0. EXIT");
            System.out.println("Enter your option: ");

            //validate input must be integer in range 0-5
            while (!s.hasNextInt() || s.nextInt() <= 0 || s.nextInt() > 5) {
                System.out.println("INVALID INPUT!\nChoose an option 0-5: ");
                s.next(); // this is important!

            }

            option = s.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter Product ID:");
                    int id = s.nextInt();

                    System.out.print("Enter category:");
                    String category = s1.next();

                    System.out.print("Enter name:");
                    String name = s1.next();

                    System.out.print("Enter quantity:");
                    int quantity = s.nextInt();

                    System.out.print("Enter price:");
                    int price = s.nextInt();

                    Products product = new Products(id, category, name, quantity, price);
                    cart.add(product);
                    break;
                case 2:
                    if (file.isFile()) {
                        ois = new ObjectInputStream(new FileInputStream(file));
                        cart = (ArrayList<Products>) ois.readObject();
                        ois.close();
                        System.out.print("Enter Product's name to Delete:");
                        String productName = s1.next();

                        System.out.println("_________________________________________________");
                        li = cart.listIterator();
                        while (li.hasNext()) {
                            Products p = li.next();
                            if ((p.getName()).equals(productName)) {
                                li.remove();
                                found = true;
                            }
                        }
                        if (found) {
                            oos = new ObjectOutputStream(new FileOutputStream(file));
                            oos.writeObject(cart);
                            oos.close();
                            System.out.println("Record is successfully deleted...!");
                        } else {
                            System.out.println("Records Not Found..!!!");
                        }
                        System.out.println("_________________________________________________");
                    } else {
                        System.out.println("File Not Found...!!!");
                    }
                    break;
                case 3:
                    if (file.isFile()) {
                        ois = new ObjectInputStream(new FileInputStream(file));
                        cart = (ArrayList<Products>) ois.readObject();
                        ois.close();

                        System.out.println("Enter Category to Search:");
                        category = s1.next();
                        System.out.println("_________________________________________________");
                        li = cart.listIterator();
                        while (li.hasNext()) {
                            Products p = li.next();
                            if ((p.getCategory()).equals(category)) {
                                System.out.println(p);
                                found = true;
                            }
                        }
                        if (!found)
                            System.out.println("Records Not Found..!!!");
                        System.out.println("_________________________________________________");
                    } else {
                        System.out.println("File Not Found...!!!");
                    }
                    break;
                case 4:
                    if (file.isFile()) {
                        ois = new ObjectInputStream(new FileInputStream(file));
                        cart = (ArrayList<Products>) ois.readObject();
                        ois.close();

                        System.out.println("_________________________________________________");
                        //use ListIterator to iterate through the file
                        li = cart.listIterator();
                        while (li.hasNext())
                            System.out.println(li.next());
                        System.out.println("_________________________________________________");
                    } else {
                        System.out.println("File Not Found...!!!");
                    }
                    break;
                case 5:
                    if (file.isFile()) {
                        ois = new ObjectInputStream(new FileInputStream(file));
                        cart =(ArrayList<Products>) ois.readObject();
                        ois.close();
                        for (Products c : cart) {
                            System.out.println(c);}
                    }

                    System.out.println("_________________________________________________");
                    //use ListIterator to iterate through the file
                    li = cart.listIterator();
                    String parseInt;
                    int totalPrice = 0;
                    while (li.hasNext()) {
                        String x = String.valueOf(li.next());

                        String[] split1 = x.split(
                                "Products\\{id='[a-zA-Z\\d]+', category='[a-zA-Z\\d]+', name='[a-zA-Z\\d]+', quantity='[\\d]+', ");
                        String[] split2 = new String[0];
                        String[] split3 = new String[0];
                        for (String c : split1) {
                            split2 = c.split("}");
                        }
                        for (String d : split2) {
                            parseInt = d.replaceAll("[^0-9]", "");
                            int unitPrice = Integer.parseInt(parseInt);
                            totalPrice += unitPrice;
                        }
                    }
                    System.out.println("Your total is: " + totalPrice);
                    System.out.println("Generating order...");
                    System.out.println("_________________________________________________");

                    //write Objects into file
                    oos = new ObjectOutputStream(new FileOutputStream(file));
                    oos.writeObject(cart);
                    oos.close();
            }

        } while (option != 0);
    }


    public static void main(String args[]) throws Exception {
        createOrder();
    }
}
