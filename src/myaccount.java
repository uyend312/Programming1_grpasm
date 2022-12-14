import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;

public class myaccount {

    public static void userAccount(String userEmail) throws Exception {
        try {
            String line;
            String[] data;
            while (userEmail.isEmpty()) {
                System.out.println("You need to login first");
                MemberLogin.memberLogin();
            }
            FileReader fr = new FileReader("userdata.txt");
            BufferedReader br = new BufferedReader(fr);

            //new
            File orderFile = new File("order.txt");
            ArrayList<Order> orderList = new ArrayList<>();
            ObjectOutputStream oos;
            ObjectInputStream ois;
            ListIterator<Order> li;
            //deserialize file if file exists
            if (orderFile.isFile()) {
                ois = new ObjectInputStream(new FileInputStream(orderFile));
                orderList = (ArrayList<Order>) ois.readObject();
                ois.close();
            }

            while ((line = br.readLine()) != null) {
                data = line.split(";");
                if (userEmail.equals(data[3])) {
                    for (String s : Arrays.asList(
                            "<--------------Your Account information-------------->",
                            "ID: " + data[0], "First name: " + data[1], "Last name: " + data[2],
                            "Email: " + data[3], "Address: "+ data[4], "Phone: "+data[5])) {
                        System.out.println(s);
                    }
                    String status = newStatus(userEmail, data[7]);
                    System.out.println("Level of member: " + status);
                    System.out.println("Total spending: " + data[8] + " VND");
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static String newStatus(String userEmail, String userStatus) throws FileNotFoundException {
        float silverMember = 5000000;
        float goldMember = 10000000;
        float platinumMember = 25000000;

        StringBuilder stringBuffer;
        try {
            stringBuffer = new StringBuilder();
            String line;
            String[] data;
            FileReader fr = new FileReader("userdata.txt");
            BufferedReader br = new BufferedReader(fr);

            while ((line = br.readLine()) != null) {
                data = line.split(";");

                if (userEmail.equals(data[3])) {

                    if (Double.parseDouble(data[8]) < silverMember) {
                        userStatus = "New Member";
                        data[7] = userStatus;

                    } else if (Double.parseDouble(data[8]) >= silverMember && Double.parseDouble(data[8]) < goldMember) {
                        userStatus = data[7].replace(data[7], "Silver Member");
                        data[7] = userStatus;
                        userStatus = "Silver Member";

                    } else if ((Double.parseDouble(data[8]) >= goldMember && Double.parseDouble(data[8]) < platinumMember)) {
                        userStatus = data[7].replace(data[7], "Gold Member");
                        data[7] = userStatus;
                        userStatus = "Gold Member";

                    } else if ((Double.parseDouble(data[8]) >= platinumMember)) {
                        userStatus = data[7].replace(data[7], "Platinum Member");
                        data[7] = userStatus;
                        userStatus = "Platinum Member";

                    }

                    String row = data[0] + ";" + data[1] + ";" + data[2] + ";" + data[3] + ";" + data[4] + ";" + data[5] + ";"+
                            data[6]+";"+data[7]+";" + data[data.length - 1];
                    stringBuffer.append(row);
                } else {
                    stringBuffer.append(line);
                }
                stringBuffer.append("\n");
            }

            PrintWriter printWriter = new PrintWriter(new FileOutputStream("userdata.txt", false));
            printWriter.print(stringBuffer);
            printWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return userStatus;
    }
    public static void main(String[] args) throws Exception {
        userAccount("abc@abc.com");
    }
}
