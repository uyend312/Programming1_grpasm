import java.io.*;
import java.util.*;

import static java.lang.System.exit;

public class customer {
    public static void customer() throws Exception {
        int option;
        boolean found = false;
        //s is used for Integer and s1 is for String data
        Scanner s = new Scanner(System.in);
        Scanner s1 = new Scanner(System.in);
        File file = new File("Products.txt");
        ArrayList<Products> productList = new ArrayList<>();
        ObjectOutputStream oos;
        ObjectInputStream ois;
        ListIterator<Products> li;

        //Deserialize if file existed
        if (file.isFile()) {
            ois = new ObjectInputStream(new FileInputStream(file));
            productList = (ArrayList<Products>) ois.readObject();
            ois.close();
        }

        do {
            System.out.println("1. VIEW ALL AVAILABLE PRODUCTS");
            System.out.println("2. SEARCH PRODUCTS (BY CATEGORY)");
            System.out.println("3. SORT PRODUCTS BY PRICE (Ascending)");
            System.out.println("4. ADD A PRODUCT TO CART");
            System.out.println("0. EXIT");
            System.out.println("Enter your option: ");

            //validate input must be integer
            while (!s.hasNextInt()) {
                System.out.println("INVALID INPUT!\nChoose an option 1-4, or 0 to end program: ");
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
                        System.out.println("Enter Category to Search:");
                        String category = s1.next();
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
                case 3:
                    if (file.isFile()) {
                        productList.sort((p1, p2) -> (int) (p1.getPrice() - p2.getPrice()));
                        //use ListIterator to iterate through the file
                        li = productList.listIterator();
                        while (li.hasNext())
                            System.out.println(li.next());
                        System.out.println("_________________________________________________");
                    } else {
                        System.out.println("File Not Found...!!!");
                    }
                    break;
                case 4:
                    System.out.println("Only members can add items to cart and checkout!");
                    System.out.println("Enter 1 to login.");
                    System.out.println("Enter 2 to register an account.");
                    System.out.println("Enter 3 to keep browsing our products.");
                    int userChoice = 0;

                    while (userChoice != 1 && userChoice != 2 && userChoice != 3) {
                        System.out.println("Enter your option 1-3:");
                        Scanner scanner = new Scanner(System.in);

                        //validate input must be integer
                        while (!scanner.hasNextInt()) {
                            System.out.println("INVALID INPUT!\nChoose an option 1-3: ");
                            scanner.next();
                        }

                        userChoice = scanner.nextInt();
                        switch (userChoice) {
                            case 1:
                                MemberLogin.memberLogin();
                                break;
                            case 2:
                                userRegister.registerMember();
                                break;
                            default:
                                if (userChoice != 3) {
                                    System.out.println("Invalid input " + userChoice);
                                    break;
                                }
                        } while (userChoice != 3);
                    }
                    break;
                case 0:
                    exit(0);
            }
        } while (true);
    }

    public static void main(String[] args) throws Exception {
        customer();
    }
}
