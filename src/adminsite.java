import java.io.*;
import java.util.*;
public class adminsite {
    public static void adminModify() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select 1 to view information of products\nSelect 2 to view information of orders\n" +
                "Select 3 to view information of members\nSelect 4 to cancel");
        int admSelect = sc.nextInt();

        switch (admSelect) {
            case (1):
                try {
                    ProductsDemo.ProductsDemo();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case (2):
                viewOrdersInformation();
                break;
            case (3):
                viewMembersInformation();
                break;
            case(4):
                break;
            default:
                System.out.println("Invalid input");
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
    }
    public static void viewMembersInformation() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select 1 to view members information \nSelect 2 to delete members");
        int a = sc.nextInt();

        switch (a) {
            case (1):
                System.out.println("First name, Last name, Email, Password, Membership, User ID");
                viewFile("userdata.txt");

                break;
            case (2):
                deleteElement("userdata.txt");
                break;
            default:
                System.out.println("Invalid input");
        }
    }
    public static void viewFile(String filename)
    {
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
        adminsite.adminModify();
    }
}
