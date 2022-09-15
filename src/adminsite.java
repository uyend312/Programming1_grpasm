import java.io.*;
import java.util.*;

import static java.lang.System.exit;

public class adminsite {
    public static void adminModify() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select 1 to view information of products\nSelect 2 to view information of orders\n" +
                "Select 3 to view information of members\nSelect 4 to go back\nSelect 5 to end");
        String admSelect = sc.next();
        while (!admSelect.matches("[1-5]"))
        { System.out.println("Invalid input, try again");
            admSelect = sc.next();}

        switch (admSelect) {
            case ("1"):
                try {
                    ProductsDemo.ProductsDemo();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case ("2"):
                viewOrdersInformation();
                break;
            case ("3"):
                viewMembersInformation();
                break;
            case("5"):
                exit(0);
                break;
            case ("4"):
                adminModify();
            default:
                System.out.println("Invalid input, try again");
                adminModify();
                break;
        }
    }

    public static void viewOrdersInformation() throws Exception {
        File file = new File("order.txt");
        List<Order> al;
        ObjectOutputStream oos;
        ObjectInputStream ois;
        ListIterator<Order> li;

        //check for existing file to load data to arraylist
        if (file.isFile()) {
            ois = new ObjectInputStream(new FileInputStream(file));
            al = (List<Order>) ois.readObject();
            ois.close();
            System.out.println("_________________________________________________");
            System.out.println("Product List:");
            //use ListIterator to iterate through the file
            li = al.listIterator();
            while (li.hasNext())
                System.out.println(li.next());
            System.out.println("_________________________________________________");
        }
        System.out.println("Would you like to continue with the selection? Press 5 to end if you would like to stop");
        adminsite.adminModify();
    }
    public static void viewMembersInformation() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select 1 to view members information \nSelect 2 to delete members\nSelect 3 to go back" +
                "to the main selection");
        int a = sc.nextInt();

        switch (a) {
            case (1):
                System.out.println("First name, Last name, Email, Password, Membership, User ID");
                viewFile("userdata.txt");

                break;
            case (2):
                deleteElement("userdata.txt");
                break;
            case 3:
                adminModify();
            default:
                System.out.println("Invalid input, try again");

                adminsite.viewMembersInformation();
        }
        System.out.println("Would you like to continue with the selection? Press 5 to end if you would like to stop");
        adminsite.adminModify();
    }
    public static void viewFile(String filename) throws Exception {
        try {
            String line;
            String[] data;
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);

            while ((line = br.readLine()) != null) {
                data = line.split(";");

                System.out.println(Arrays.toString(data));


            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Would you like to continue with the selection? Press 5 to end if you would like to stop");
        adminsite.adminModify();
    }
    public static void deleteElement(String filename) throws Exception {
        System.out.println("Enter the ID to search");
        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();
        File oldfile = new File("userdata.txt");
        File newfile = new File("temp.txt");
        try {
            String line;
            String[] data;
            FileWriter fileWriter = new FileWriter(newfile,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedWriter);
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);

            while ((line = br.readLine()) != null) {
                data = line.split(";");



                if (!id.equals(data[0])) {
                    printWriter.println(line);
                }



            }
            printWriter.flush();
            printWriter.close();
            fr.close();
            br.close();
            bufferedWriter.close();
            fileWriter.close();
            oldfile.delete();
            File newName = new File(filename);
            newfile.renameTo(newName);
            System.out.println("Deleted");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Would you like to continue with the selection? Press 5 to end if you would like to stop");
        adminsite.adminModify();
    }
}
