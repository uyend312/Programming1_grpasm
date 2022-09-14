import java.io.*;
import java.util.*;

public class customer {
    public static void customer() throws Exception{
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
            option = s.nextInt();
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

                        System.out.println("_________________________________________________");
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
                    break;
            }
        } while (option != 0);
    }
    public static void main(String args[]) throws Exception {
        customer();
    }
}
