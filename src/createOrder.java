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
        File cartFile = new File("cart.txt");
        File orderFile = new File("order.txt");
        ArrayList<Products> productList = new ArrayList<>();
        ArrayList<Products> cart = new ArrayList<>();
        ArrayList<Order> order = new ArrayList<>();
        ObjectOutputStream oos;
        ObjectInputStream ois;
        ListIterator<Products> li;

        //check for existing file to load data to arraylist
        if (file.isFile()) {
            ois = new ObjectInputStream(new FileInputStream(file));
            productList = (ArrayList<Products>) ois.readObject();
            ois.close();
            System.out.println("_________________________________________________");
            //use ListIterator to iterate through the file
            li = cart.listIterator();
            while (li.hasNext())
                System.out.println(li.next());
            System.out.println("_________________________________________________");
        }

        do {
            System.out.println("ENTER A NUMBER 1-5.\n");
            System.out.println("1. ADD A PRODUCT TO CART");
            System.out.println("2. DELETE A PRODUCT IN CART");
            System.out.println("3. SEARCH AVAILABLE PRODUCTS (BY CATEGORY)");
            System.out.println("4. VIEW CART");
            System.out.println("5. PLACE ORDER AND CHECKOUT");
            System.out.println("0. EXIT");
            System.out.println("Enter your option: ");

            //validate input must be integer in range 0-5
//            while (!s.hasNextInt() || s.nextInt() <= 0 || s.nextInt() > 5) {
//                System.out.println("INVALID INPUT!\nChoose an option 0-5: ");
//                s.next(); // this is important!
//
//            }

            option = s.nextInt();

            switch (option) {
                case 1:
                    break;
                case 2:
                    if (file.isFile()) {
                        ois = new ObjectInputStream(new FileInputStream(file));
                        productList= (ArrayList<Products>) ois.readObject();
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
                        productList= (ArrayList<Products>) ois.readObject();
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
                    if (cartFile.isFile()) {
                        if (cartFile.length() == 0) {
                            System.out.println("Cart is empty!");
                            break;
                        }
                        ois = new ObjectInputStream(new FileInputStream(cartFile));
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
                        productList = (ArrayList<Products>) ois.readObject();
                        ois.close();
                        for (Products c : cart) {
                            System.out.println(c);}
                    }

                    System.out.println("_________________________________________________");
                    //use ListIterator to iterate through the file
                    li = cart.listIterator();
                    String parseInt;
                    double totalPrice = 0;
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
                            double unitPrice = Integer.parseInt(parseInt);
                            totalPrice += unitPrice;
                        }
                    }
                    //String email = login.userEmail; //need this to work, didnt check yet
                    String email = "aoi.umi.mu@gmail.com";
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

                    while ((line = br.readLine()) != null) {

                        data = line.split(",");

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

                    System.out.println("<------------Your Order information------------>");
                    System.out.println(
                            "ID:" + userId + "\nFirst name: " + firstName + "\nLast name: " + lastName
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
                    System.out.println("Generating order...");
                    System.out.println("_________________________________________________");

                    //write Objects into file
                    Order newOrder = new Order(orderID, userId, firstName, lastName, address, phone, status, cart);
                    order.add(newOrder);
                    oos = new ObjectOutputStream(new FileOutputStream("order.txt"));
                    oos.writeObject(newOrder);
                    oos.close();
                    for (Order c : order) {
                        System.out.println(c);}
            }

        } while (option != 0);
    }


    public static String generateUUID () {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
    public static void main(String args[]) throws Exception {
        createOrder();
    }
}
