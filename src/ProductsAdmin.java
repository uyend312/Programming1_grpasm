import java.io.*;
import java.util.*;

public class ProductsAdmin{

    public static void ProductsAdmin() throws Exception {
        int option;
        //s is used for Integer and s1 is for String data
        Scanner s = new Scanner(System.in);
        Scanner s1 = new Scanner(System.in);
        File file = new File("Products.txt");
        ArrayList<Products> al = new ArrayList<>();
        ObjectOutputStream oos;
        ObjectInputStream ois;
        ListIterator<Products> li;

        //check for existing file to load data to arraylist
        if (file.isFile()) {
            ois = new ObjectInputStream(new FileInputStream(file));
            al = (ArrayList<Products>) ois.readObject();
            ois.close();
            System.out.println("_________________________________________________");
            System.out.println("Product List:");
            //use ListIterator to iterate through the file
            li = al.listIterator();
            while (li.hasNext())
                System.out.println(li.next());
            System.out.println("_________________________________________________");
        }

        do {
            System.out.println("PLEASE CHOOSE OPTIONS FROM 0-9 BELOW");
            System.out.println("1. ADD PRODUCTS");
            System.out.println("2. SHOW PRODUCTS");
            System.out.println("3. UPDATE PRODUCTS");
            System.out.println("4. SEARCH PRODUCTS (BY CATEGORY)");
            System.out.println("5. DELETE PRODUCTS");
            System.out.println("6. SORT PRODUCTS BY PRICE (Ascending)- On Screen only");
            System.out.println("7. SORT PRODUCTS BY PRICE (Ascending)- In file");
            System.out.println("8. SORT PRODUCTS BY QUANTITY (Ascending)- On Screen only");
            System.out.println("9. SORT PRODUCTS BY QUANTITY (Ascending)- In file");
            System.out.println("0. EXIT TO PREVIOUS PAGE");
            System.out.println("Enter your option: ");
            //validate integer input so that program not crash
            while (!s.hasNextInt()) {
                System.out.println("INVALID INPUT!\nChoose an option 0-9: ");

                s.next();
            }
                option = s.nextInt();
            switch (option) {
                case 1:
                    System.out.println("How many products do you want to add?");
                    int amount = s.nextInt();
                    for (int i = 0; i < amount; i++) {

                        System.out.print("Enter product name: ");
                        String name = s1.next();

                        System.out.print("Enter product category: ");
                        String category = s1.next();

                        System.out.print("Enter quantity: ");
                        while (!s.hasNextInt()) {
                            System.out.println("INVALID INPUT!\nPlease enter a number: ");

                            s.next();
                        }
                        int quantity = s.nextInt();
                        System.out.print("Enter product price: ");
                        while (!s.hasNextDouble()) {
                            System.out.println("INVALID INPUT!\nPlease enter a number: ");

                            s.next();
                        }
                        double price = s.nextDouble();
                        String id = generateUUID();
                        Products products = new Products(id, category, name, quantity, price);
                        al.add(products);
                        System.out.println();
                    }
                    System.out.println("_________________________________________________");
                    //write Objects into file
                    oos = new ObjectOutputStream(new FileOutputStream(file));
                    oos.writeObject(al);
                    oos.close();

                    break;
                case 2:
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
                case 3:
                    if (file.isFile()) {
                        ois = new ObjectInputStream(new FileInputStream(file));
                        al = (ArrayList<Products>) ois.readObject();
                        ois.close();
                        boolean found = false;
                        System.out.println("Copy and Enter Product ID to Update:");
                         String id = s1.next();
                        System.out.println("_________________________________________________");
                        li = al.listIterator();
                        while (li.hasNext()) {
                            Products p = li.next();
                            if (p.getId().equals(id)) {
                                System.out.print("Enter new product category: ");
                                String category = s1.next();
                                System.out.println("Enter new product name: ");
                                String name = s1.next();
                                System.out.print("Enter new product quantity: ");
                                int quantity = s.nextInt();
                                System.out.print("Enter new product price: ");
                                double price = s.nextDouble();
                                li.set(new Products(id, category, name, quantity, price));
                                found = true;
                            }
                        }
                        if (found) {
                            oos = new ObjectOutputStream(new FileOutputStream(file));
                            oos.writeObject(al);
                            oos.close();
                            System.out.println("Record is successfully updated...!");
                        } else {
                            System.out.println("Records Not Found..!!!");
                        }


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
                        boolean found = false;
                        System.out.println("Enter Category to Search:");
                        String category = s1.next();
                        System.out.println("_________________________________________________");
                        li = al.listIterator();
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
                case 5:
                    if (file.isFile()) {
                        ois = new ObjectInputStream(new FileInputStream(file));
                        al = (ArrayList<Products>) ois.readObject();
                        ois.close();
                        boolean found = false;
                        System.out.print("Enter Product's name to Delete:");
                        String name = s1.next();

                        System.out.println("_________________________________________________");
                        li = al.listIterator();
                        while (li.hasNext()) {
                            Products p = li.next();
                            if ((p.getName()).equals(name)) {
                                li.remove();
                                found = true;
                            }
                        }
                        if (found) {
                            oos = new ObjectOutputStream(new FileOutputStream(file));
                            oos.writeObject(al);
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
                case 6:
                    if (file.isFile()) {
                        ois = new ObjectInputStream(new FileInputStream(file));
                        al = (ArrayList<Products>) ois.readObject();
                        ois.close();

                        Collections.sort(al, new Comparator<Products>() {
                            @Override
                            public int compare(Products p1, Products p2) {

                                return (int) (p1.getPrice()- p2.getPrice());
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
                case 7:
                    if (file.isFile()) {
                        ois = new ObjectInputStream(new FileInputStream(file));
                        al = (ArrayList<Products>) ois.readObject();
                        ois.close();

                        Collections.sort(al, new Comparator<Products>() {
                            @Override
                            public int compare(Products p1, Products p2) {

                                return (int) (p1.getPrice()- p2.getPrice());
                            }
                        });
                        //This block of code will write the sorted data into file
                        oos = new ObjectOutputStream(new FileOutputStream(file));
                        oos.writeObject(al);
                        oos.close();

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

                case 8:
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
                case 9:
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
                        //This block of code will write the sorted data into file
                        oos = new ObjectOutputStream(new FileOutputStream(file));
                        oos.writeObject(al);
                        oos.close();

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
                case 0:
                    adminsite.adminModify();
                    break;
            }
        } while (option!=0);
    }
    public static String generateUUID () {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
    public static void main(String[] args) throws Exception{
        ProductsAdmin();
    }
}
