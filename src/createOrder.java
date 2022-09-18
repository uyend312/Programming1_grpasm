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
        ObjectInputStream ois1;
        ObjectInputStream ois2;
        ListIterator<Products> li;


        //deserialize file if file exists
        if (file.isFile()) {
            ois1 = new ObjectInputStream(new FileInputStream(file));
            productList = (ArrayList<Products>) ois1.readObject();
            ois1.close();

        }
        if (orderFile.isFile()) {
            ois2 = new ObjectInputStream(new FileInputStream(orderFile));
            order = (ArrayList<Order>) ois2.readObject();
            ois2.close();
        }

        do {
            System.out.println("ENTER A NUMBER 1-9.\n");
            System.out.println("1. DISPLAY ACCOUNT INFORMATION");
            System.out.println("2. VIEW ORDER HISTORY");
            System.out.println("3. DISPLAY ALL AVAILABLE PRODUCTS");
            System.out.println("4. ADD A PRODUCT TO CART");
            System.out.println("5. DELETE A PRODUCT IN CART");
            System.out.println("6. SEARCH AVAILABLE PRODUCTS (BY CATEGORY)");
            System.out.println("7. VIEW CART");
            System.out.println("8. PLACE ORDER AND CHECKOUT");
            System.out.println("9. LOG OUT");
            System.out.println("0. EXIT");
            System.out.println("Enter your option: ");

            //validate input must be integer in range 0-5
            while (!s.hasNextInt()) {
                System.out.println("INVALID INPUT!\nChoose an option 1-9, or 0 to end program: ");
                s.next(); // this is important!

            }

            option = s.nextInt();
            System.out.println("_________________________________________________");
            switch (option) {
                case 1:
                    myaccount.userAccount(login.userEmail);
                    break;
                case 2:
                    if (orderFile.isFile()) {
                        //find user ID to print orders having that ID
                        String email = login.userEmail;
                        //String email = "asfasf@ahfa.com";
                        String line;
                        String[] data;
                        String userId = null;

                        FileReader fr = new FileReader("userdata.txt");
                        BufferedReader br = new BufferedReader(fr);
                        // get user ID
                        while ((line = br.readLine()) != null) {
                            data = line.split(";");
                            if (email.equals(data[3])) {
                                userId = data[0];
                            }
                        }

                        //find Order that contains userId and display it
                        Order ord;
                        found = false;
                        ListIterator<Order> orderListIterator = order.listIterator();
                        while (orderListIterator.hasNext()) {

                            ord = orderListIterator.next();
                            System.out.println(ord);
                            if ((ord.getOrderID()).equals(userId)) {
                                System.out.println(ord);
                                found = true;
                            }
                        }
                        if (!found) {
                            System.out.println("Your order history is empty...");
                        }
                    }
                    else {
                        System.out.println("Order list file Not Found...!!!");
                    }
                    break;
                case 3:
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
                case 4:
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
                case 5:
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
                case 6:
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
                case 7:
                    if (cart.isEmpty()) {
                        System.out.println("Cart empty.");
                    }
                    for (Products c : cart) {
                        System.out.println(c);
                    }
                    System.out.println("_________________________________________________");
                    break;
                case 8:
                    if (cart.isEmpty()) {
                        System.out.println("Cart is empty! Cannot generate your order request!");
                    }
                    else {

                        ois1 = new ObjectInputStream(new FileInputStream(orderFile));
                        order = (ArrayList<Order>) ois1.readObject();
                        ois1.close();
                        //String email = login.userEmail;
                        String email = "abc@abc.com";

//                        String email = login.userEmail;
                        //String email = "asfasf@ahfa.com";
                        String line;
                        String[] data;
                        String userId = null;
                        String firstName = null;
                        String lastName = null;
                        String address = null;
                        String phone = null;
                        String password = null;
                        String status = null;
                        String orderID = generateUUID();
                        double spending = 0;

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
                                //email = data 3
                                address = data[4];
                                phone = data[5];
                                password = data[6];
                                status = myaccount.newStatus(email, data[7]);
                                spending = Double.parseDouble(data[8]);
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
                        System.out.println("_________________________________________________");

                        //write new order into file
                        Order newOrder = new Order(orderID, userId, firstName, lastName, address, phone, status, "Waiting", cart);
                        order.add(newOrder);
                        oos = new ObjectOutputStream(new FileOutputStream(orderFile));
                        oos.writeObject(order);
                        oos.close();
                        //update total spending
                        double totalSpend = spending + totalPrice;

                        Scanner sc = new Scanner(new File("userdata.txt"));
                        //instantiating the StringBuffer class
                        StringBuffer buffer = new StringBuffer();
                        //Reading lines of the file and appending them to StringBuffer
                        while (sc.hasNextLine()) {
                            buffer.append(sc.nextLine()+System.lineSeparator());
                        }
                        String fileContents = buffer.toString();
                        //closing the Scanner object
                        sc.close();
                        //update total spending
                        String oldLine = userId + ";" + firstName + ";" + lastName + ";" + email + ";"+ address +";" + phone + ";"
                                +  password + ";" + status
                                + ";" + spending;

                        String newLine = userId + ";" + firstName + ";" + lastName + ";" + email + ";"+ address +";" + phone + ";"
                                +  password + ";" + status
                                + ";" + totalSpend;
                        //Replacing the old line with new line
                        fileContents = fileContents.replaceAll(oldLine, newLine);
                        //instantiating the FileWriter class
                        FileWriter writer = new FileWriter("userdata.txt");
                        writer.append(fileContents);
                        writer.flush();
                    }
                    break;
                case 9:
                    login.userEmail = null;
                    Main.mainPage();
                    break;
                default:
                    if (option != 0) {
                        System.out.println("Option " + option +  " not available.");
                    }
                    break;
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
