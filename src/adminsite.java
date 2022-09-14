import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
public class adminsite {
    public static void adminModify() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Select 1 to view information of products\n Select 2 to view information of orders\n " +
                "Select 3 to view information of members");
        int admSelect = sc.nextInt();


        switch (admSelect) {
            case (1):
                viewProductInformation();
                break;
            case (2):
                viewOrdersInformation();
                break;
            case (3):
                viewMembersInformation();
                break;
            default:
                System.out.println("Invalid input");
        }
    }
    public static void viewProductInformation()
    {
        System.out.println("Product ID, Category, Name, Quantity, Price (VND)");
        viewFile("Products.txt");

    }
    public static void viewOrdersInformation()
    {
        viewFile("order.txt");
    }
    public static void viewMembersInformation() throws IOException {
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
                data = line.split(",");

                System.out.println(Arrays.toString(data));


            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void deleteElement(String filename)
    {
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
                data = line.split(",");



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

    }
}
