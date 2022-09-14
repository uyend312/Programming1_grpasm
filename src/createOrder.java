import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class createOrder {
    public static void createOrder() throws Exception {
        int option;
        boolean found = false;
        String category;
        //s is used for Integer and s1 is for String data
        Scanner s = new Scanner(System.in);
        Scanner s1 = new Scanner(System.in);
        File file = new File("Products.txt");
        File orderFile = new File("order.txt");
        ArrayList<Products> productList = new ArrayList<>();
        ArrayList<Products> cart = new ArrayList<>();
        ArrayList<Order> order = new ArrayList<>();
        ObjectOutputStream oos;
        ObjectInputStream ois;
        ListIterator<Products> li;

        if (file.isFile()) {
            ois = new ObjectInputStream(new FileInputStream(file));
            productList = (ArrayList<Products>) ois.readObject();
            ois.close();
        }

        do {
            System.out.println("ENTER A NUMBER 1-5.\n");
            System.out.println("1. VIEW ALL AVAILABLE PRODUCTS");
            System.out.println("2. ADD A PRODUCT TO CART");
            System.out.println("3. DELETE A PRODUCT IN CART");
            System.out.println("4. SEARCH AVAILABLE PRODUCTS (BY CATEGORY)");
            System.out.println("5. VIEW CART");
            System.out.println("6. PLACE ORDER AND CHECKOUT");
            System.out.println("0. EXIT");
            System.out.println("Enter your option: ");

            //validate input must be integer in range 0-5
            while (!s.hasNextInt()) {
                System.out.println("INVALID INPUT!\nChoose an option 0-5: ");
                s.next(); // this is important!

            }

            option = s.nextInt();
            System.out.println("_________________________________________________");
            switch (option) {
                case 1:
                    if (file.isFile()) {
                        //use ListIterator to iterate through the file
                        li = productList.listIterator();
                        while (li.hasNext())
                            System.out.println(li.next());
                        System.out.println("_________________________________________________");
                    } else {
                        System.out.println("Product list file Not Found...!!!");
                    }
                    break;
                case 2:
                    if (file.isFile()) {
                        found = false;
                        System.out.println("Enter product name:");
                        String name = s1.next();
                        li = productList.listIterator();
                        while (li.hasNext()) {
                            Products p = li.next();
                            if ((p.getName()).equals(name.toLowerCase())) {
                                System.out.println(p);
                                cart.add(p);
                                System.out.println("Product added to cart.");
                                found = true;
                            }
                        }
                        if (!found)
                            System.out.println("Product Name Not Found..!!!");
                        System.out.println("_________________________________________________");
                    } else {
                        System.out.println("Product List file Not Found...!!!");
                    }
                    break;
                case 3:
                    System.out.print("Enter the Product name to Delete: ");
                    String name = s1.next();
                    for (Products c : cart) {
                        if ((c.getName()).equals(name.toLowerCase())) {
                            cart.remove(c);
                            System.out.println("Product removed.");
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("Cannot find product to delete!");
                    }
                    System.out.println("_________________________________________________");
                    break;
                case 4:
                    if (file.isFile()) {
                        System.out.println("Enter Category to Search:");
                        category = s1.next();
                        li = productList.listIterator();
                        while (li.hasNext()) {
                            Products p = li.next();
                            if ((p.getCategory()).equals(category.toLowerCase())) {
                                System.out.println(p);
                                found = true;
                            }
                        }
                        if (!found)
                            System.out.println("Category Not Found!");
                        System.out.println("_________________________________________________");
                    } else {
                        System.out.println("Product list file Not Found...!!!");
                    }
                    break;
                case 5:
                    if (cart.isEmpty()) {
                        System.out.println("Cart empty.");
                    }
                    for (Products c : cart) {
                        System.out.println(c);
                    }
                    System.out.println("_________________________________________________");
                    break;
                case 6:
                    if (cart.isEmpty()) {
                        System.out.println("Cart is empty! Cannot generate your order request!");
                    }
                    else {
                        String email = login.userEmail; //need this to work, didnt check yet
                        //String email = "abc@abc.com";
                        String line;
                        String[] data;
                        String userId = null;
                        String firstName = null;
                        String lastName = null;
                        String address = null;
                        String phone = null;
                        String status = null;
                        String orderID = generateUUID();

                        FileReader fr = new FileReader("userdata.txt");
                        BufferedReader br = new BufferedReader(fr);

                        double totalPrice = 0;
                        //calculate total price
                        for (Products c : cart) {
                            double productPrice = c.getPrice();
                            totalPrice += productPrice;
                        }
                        // get user info
                        while ((line = br.readLine()) != null) {

                            data = line.split(";");

                            if (email.equals(data[3])) {
                                userId = data[0];
                                firstName = data[1];
                                lastName = data[2];
                                address = data[4];
                                phone = data[5];

                                status = myaccount.newStatus(email, data[7]);
                                break;
                            }
                        }
                        //Print order information
                        System.out.println("<------------ Your Order information ------------>");
                        System.out.println(
                                "orderID: " + orderID + "\nuserID: " + userId + "\nFirst name: " + firstName + "\nLast name: " + lastName
                                        + "\nEmail: " + email + "\nAddress: " + address + "\nPhone: " + phone);

                        System.out.println("Level of member: " + status);
                        if (status == "Platinum Member") {
                            System.out.println("You receive a 15% discount on your total!");
                            System.out.println("Your initial total is: " + totalPrice + "VND.");
                            totalPrice = totalPrice * 0.85;
                        } else if (status == "Gold Member") {
                            System.out.println("You receive a 10% discount on your total!");
                            System.out.println("Your initial total is: " + totalPrice + "VND.");
                            totalPrice = totalPrice * 0.9;
                        } else if (status == "Silver Member") {
                            System.out.println("You receive a 5% discount on your total!");
                            System.out.println("Your initial total is: " + totalPrice + "VND.");
                            totalPrice = totalPrice * 0.95;
                        } else {
                            System.out.println("No discount available.");
                            System.out.println("Your initial total is: " + totalPrice + "VND.");
                        }

                        System.out.println("Your total after discount is: " + totalPrice + "VND.");
                        System.out.println("\nGenerating order...");
                        System.out.println("SUCCESS!");
                        System.out.println("_________________________________________________");

                        //write new order into file
                        Order newOrder = new Order(orderID, userId, firstName, lastName, address, phone, status, cart);
                        order.add(newOrder);
                        oos = new ObjectOutputStream(new FileOutputStream(orderFile));
                        oos.writeObject(newOrder);
                        oos.close();
                    }
                default:
                    System.out.println("");
            }
        } while (option != 0);
    }


    public static String generateUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static void main(String[] args) throws Exception {
        createOrder();
    }
}
