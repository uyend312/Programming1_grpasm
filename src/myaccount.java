import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;


public class myaccount {

    public static void userAccount(String userEmail) throws IOException {

        String line;
        String[] data;
        while (userEmail.isEmpty()) {
            System.out.println("You need to login first");
            MemberLogin.customerLogin();

        }
        // newStatus(userEmail, data[5]);

        try {
            FileReader fr = new FileReader("userdata.txt");
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                data = line.split(",");

                if (userEmail.equals(data[3])) {
                    data = line.split(",");
                    System.out.println("<--------------Your Account information-------------->");
                    System.out.println("ID: " + data[0]);
                    System.out.println("First name: " + data[1]);
                    System.out.println("Last name: " + data[2]);
                    System.out.println("Email: " + data[3]);
                    String status = newStatus(data[5]);
                    System.out.println("Level of member: " + status);
                    System.out.println("Total spending: " + data[6] + "VND");
                }

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        }
    }

    public static String newStatus(String userStatus) {
        float silverMember = 5000000;
        float goldMember = 10000000;
        float platinumMember = 25000000;


        String line;
        String[] data;

        try {

            FileReader fr = new FileReader("userdata.txt");
            BufferedReader br = new BufferedReader(fr);


            while ((line = br.readLine()) != null) {
                data = line.split(",");
                // if (userEmail.equals(data[3])) {
//                if (Float.parseFloat(data[6]) < silverMember) {
//                    userStatus = "New Member";
//
//                }

                Path of = Path.of("userdata.txt");
                if (Float.parseFloat(data[6]) > silverMember && Float.parseFloat(data[6]) < goldMember) {
                    userStatus = data[5].replace(data[5], "Silver Member");
//                    data[5] = userStatus;

                    Charset charset = StandardCharsets.UTF_8;

                    String content = Files.readString(of, charset);
                    content = content.replaceAll(data[5], "Silver Member");
                    Files.writeString(of, content, charset);


                } else if (Float.parseFloat(data[6]) > goldMember && Float.parseFloat(data[6]) < platinumMember) {
                    userStatus = data[5].replace(data[5], "Gold Member");
                    //                   data[5] = userStatus;
                    Charset charset = StandardCharsets.UTF_8;

                    String content = Files.readString(of, charset);
                    content = content.replaceAll(data[5], "Gold Member");
                    Files.writeString(of, content, charset);


                } else if (Float.parseFloat(data[6]) > platinumMember) {
                    userStatus = data[5].replace(data[5], "Platinum Member");
                    //                  data[5] = userStatus;
                    Charset charset = StandardCharsets.UTF_8;

                    String content = Files.readString(of, charset);
                    content = content.replaceAll(data[5], "Platinum Member");
                    Files.writeString(of, content, charset);

                }



            }

            //           userStatus = userStatusBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return userStatus;
    }}
