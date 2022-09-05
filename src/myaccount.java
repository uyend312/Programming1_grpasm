import java.io.*;
import java.util.Arrays;

public class myaccount {

    public static void userAccount(String userEmail) throws IOException {
        try {
            String line;
            String[] data;
            while (userEmail.isEmpty()) {
                System.out.println("You need to login first");
                MemberLogin.customerLogin();

            }
            FileReader fr = new FileReader("userdata.txt");
            BufferedReader br = new BufferedReader(fr);


            while ((line = br.readLine()) != null) {

                data = line.split(",");

                if (userEmail.equals(data[3])) {
                    for (String s : Arrays.asList(
                            "<--------------Your Account information-------------->",
                            "ID: " + data[0], "First name: " + data[1], "Last name: " + data[2],
                            "Email: " + data[3])) {
                        System.out.println(s);
                    }
                    String status = newStatus(userEmail, data[5]);
                    System.out.println("Level of member: " + status);
                    System.out.println("Total spending: " + data[6] + " VND");
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
                data = line.split(",");
                //System.out.println(line);

                if (userEmail.equals(data[3])) {

                    if (Float.parseFloat(data[6]) < silverMember) {
                        userStatus = "New Member";
                        data[5] = userStatus;

                    } else if (Float.parseFloat(data[6]) >= silverMember && Float.parseFloat(data[6]) < goldMember) {
                        userStatus = data[5].replace(data[5], "Silver Member");
                        data[5] = userStatus;
                        userStatus = "Silver Member";

                    } else if ((Float.parseFloat(data[6]) >= goldMember && Float.parseFloat(data[6]) < platinumMember)) {
                        userStatus = data[5].replace(data[5], "Gold Member");
                        data[5] = userStatus;
                        userStatus = "Gold Member";

                    } else if ((Float.parseFloat(data[6]) >= platinumMember)) {
                        //   userStatus = data[5].replace(data[5], "Platinum Member");
                        userStatus = data[5].replace(data[5], "Platinum Member");
                        data[5] = userStatus;
                        userStatus = "Platinum Member";

                    }

                    String row = data[0] + "," + data[1] + "," + data[2] + "," + data[3] + "," + data[4] + "," + data[5] + "," + data[data.length - 1];
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
    }}
