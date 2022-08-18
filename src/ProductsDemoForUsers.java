import java.io.*;
import java.util.*;

public class ProductsDemoForUsers {
    public static void main(String args[]) throws Exception {
        int option = -1;
        //s is used for Integer and s1 is for String data
        Scanner s = new Scanner(System.in);
        Scanner s1 = new Scanner(System.in);
        File file = new File("Products.txt");
        ArrayList<Products> al = new ArrayList<>();
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        ListIterator li = null;

        //check for existing file to load data to arraylist
        if (file.isFile()) {
            ois = new ObjectInputStream(new FileInputStream(file));
            al = (ArrayList<Products>) ois.readObject();
            ois.close();
        }

        do {
            System.out.println("1.SHOW PRODUCTS");
            System.out.println("2.SEARCH PRODUCTS (BY CATEGORY)");
            System.out.println("3.SORT PRODUCTS BY PRICE (Ascending)- On Screen only");
            System.out.println("4.SORT PRODUCTS BY ID (Ascending)- On Screen only");
            System.out.println("5.SORT PRODUCTS BY QUANTITY (Ascending)- On Screen only");
            System.out.println("0.EXIT");
            System.out.println("Enter your option: ");
            option = s.nextInt();
        } while (option!=0);
        switch (option) {

            case 1:
                if (file.isFile()) {
                    ois = new ObjectInputStream(new FileInputStream(file));
                    al = (ArrayList<Products>) ois.readObject();
                    ois.close();
                    System.out.println("_________________________________________________");
                    //use ListIterator to iterate through the file
                    li = al.listIterator();
                    while (li.hasNext())
                        System.out.println(li.next());
                    System.out.println("_________________________________________________");
                } else {
                    System.out.println("File Not Found...!!!");
                }
                break;

            case 2:
                if (file.isFile()) {
                    ois = new ObjectInputStream(new FileInputStream(file));
                    al = (ArrayList<Products>) ois.readObject();
                    ois.close();
                    boolean found = false;
                    System.out.println("Enter Category to Search:");
                    String category = s1.next();
                    System.out.println("_________________________________________________");
                    li = al.listIterator();
                    while (li.hasNext()) {
                        Products p = (Products) li.next();
                        if (p.getCategory() == category) {
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

            case 3:
                if (file.isFile()) {
                    ois = new ObjectInputStream(new FileInputStream(file));
                    al = (ArrayList<Products>) ois.readObject();
                    ois.close();

                    Collections.sort(al, new Comparator<Products>() {
                        @Override
                        public int compare(Products p1, Products p2) {

                            return p1.getPrice()- p2.getPrice();
                        }
                    });

                    System.out.println("_________________________________________________");
                    //use ListIterator to iterate through the file
                    li = al.listIterator();
                    while (li.hasNext())
                        System.out.println(li.next());
                    System.out.println("_________________________________________________");
                } else {
                    System.out.println("File Not Found...!!!");
                }
                break;

            case 4:
                if (file.isFile()) {
                    ois = new ObjectInputStream(new FileInputStream(file));
                    al = (ArrayList<Products>) ois.readObject();
                    ois.close();

                    Collections.sort(al, new Comparator<Products>() {
                        @Override
                        public int compare(Products p1, Products p2) {

                            return p1.getId()- p2.getId();
                        }
                    });

                    System.out.println("_________________________________________________");
                    //use ListIterator to iterate through the file
                    li = al.listIterator();
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
                    al = (ArrayList<Products>) ois.readObject();
                    ois.close();

                    Collections.sort(al, new Comparator<Products>() {
                        @Override
                        public int compare(Products p1, Products p2) {

                            return p1.getQuantity()- p2.getQuantity();
                        }
                    });

                    System.out.println("_________________________________________________");
                    //use ListIterator to iterate through the file
                    li = al.listIterator();
                    while (li.hasNext())
                        System.out.println(li.next());
                    System.out.println("_________________________________________________");
                } else {
                    System.out.println("File Not Found...!!!");
                }
                break;

        }
    }
}
