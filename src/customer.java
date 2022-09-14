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
        ArrayList<Products> products = new ArrayList<>();
        ObjectOutputStream oos;
        ObjectInputStream ois;
        ListIterator<Products> li;

        //Deserialize if file existed
        if (file.isFile()) {
            ois = new ObjectInputStream(new FileInputStream(file));
            products = (ArrayList<Products>) ois.readObject();
            ois.close();
        }

        do {
            System.out.println("1. VIEW ALL AVAILABLE PRODUCTS");
            System.out.println("2. SEARCH PRODUCTS (BY CATEGORY)");
            System.out.println("3. SORT PRODUCTS BY PRICE (Ascending)- On Screen only");
            System.out.println("4. SORT PRODUCTS BY PRICE (Ascending)- In file");
            System.out.println("5. ADD AN ITEM TO CART");
            System.out.println("0. EXIT");
            System.out.println("Enter your option: ");
            option = s.nextInt();
            switch (option) {
                case 1:
                    if (file.isFile()) {
                        ois = new ObjectInputStream(new FileInputStream(file));
                        products = (ArrayList<Products>) ois.readObject();
                        ois.close();

                        System.out.println("_________________________________________________");
                        //use ListIterator to iterate through the file
                        li = products.listIterator();
                        while (li.hasNext())
                            System.out.println(li.next());
                        System.out.println("_________________________________________________");
                    } else {
                        System.out.println("File Not Found...!!!");
                    }
                case 2:
                    if (file.isFile()) {
                        ois = new ObjectInputStream(new FileInputStream(file));
                        products = (ArrayList<Products>) ois.readObject();
                        ois.close();
                        found = false;
                        System.out.println("Enter Category to Search:");
                        String category = s1.next();
                        System.out.println("_________________________________________________");
                        li = products.listIterator();
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
                case 3:
                    if (file.isFile()) {
                        ois = new ObjectInputStream(new FileInputStream(file));
                        products = (ArrayList<Products>) ois.readObject();
                        ois.close();

                        Collections.sort(products, new Comparator<Products>() {
                            @Override
                            public int compare(Products p1, Products p2) {

                                return (int) (p1.getPrice()- p2.getPrice());
                            }
                        });

                        System.out.println("_________________________________________________");
                        //use ListIterator to iterate through the file
                        li = products.listIterator();
                        while (li.hasNext())
                            System.out.println(li.next());
                        System.out.println("_________________________________________________");
                    } else {
                        System.out.println("File Not Found...!!!");
                    }
                case 4:
                    if (file.isFile()) {
                        ois = new ObjectInputStream(new FileInputStream(file));
                        products = (ArrayList<Products>) ois.readObject();
                        ois.close();

                        Collections.sort(products, new Comparator<Products>() {
                            @Override
                            public int compare(Products p1, Products p2) {

                                return (int) (p1.getPrice()- p2.getPrice());
                            }
                        });
                        //This block of code will write the sorted data into file
                        oos = new ObjectOutputStream(new FileOutputStream(file));
                        oos.writeObject(products);
                        oos.close();

                        System.out.println("_________________________________________________");
                        //use ListIterator to iterate through the file
                        li = products.listIterator();
                        while (li.hasNext())
                            System.out.println(li.next());
                        System.out.println("_________________________________________________");
                    } else {
                        System.out.println("File Not Found...!!!");
                    }
                case 5:
                    //redirect to login java
            }
        } while (option != 0);
    }
    public static void main(String args[]) throws Exception {
    }
}
