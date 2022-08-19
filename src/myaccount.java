import java.io.*;
import java.lang.reflect.Member;

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

        File oldFile = new File("userdata.txt");
        File newFile = new File("tempo.txt");

        String line;
        String[] data;

        try {
            FileWriter fileWriter = new FileWriter(newFile,true);
            FileReader fr = new FileReader("userdata.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            BufferedReader br = new BufferedReader(fr);
            PrintWriter pw = new PrintWriter(bufferedWriter);

            while ((line = br.readLine()) != null) {
                data = line.split(",");
                // if (userEmail.equals(data[3])) {
//                if (Float.parseFloat(data[6]) < silverMember) {
//                    userStatus = "New Member";
//
//                }

                if (Float.parseFloat(data[6]) > silverMember && Float.parseFloat(data[6]) < goldMember) {
                    userStatus = data[5].replace(data[5], "Silver Member");
//                    data[5] = userStatus;


                } else if (Float.parseFloat(data[6]) > goldMember && Float.parseFloat(data[6]) < platinumMember) {
                    userStatus = data[5].replace(data[5], "Gold Member");
                    //                   data[5] = userStatus;


                } else if (Float.parseFloat(data[6]) > platinumMember) {
                    userStatus = data[5].replace(data[5], "Platinum Member");
                    //                  data[5] = userStatus;

                }

                if (data[5].equals(userStatus))
                {
                    pw.println(userStatus);
                }
                else {
                    pw.println(data[5]);
                }




            }
            pw.flush();
            pw.close();
            fr.close();
            br.close();
            bufferedWriter.close();
            bufferedWriter.close();
            fileWriter.close();
            oldFile.delete();
            File delete = new File("userdata.txt");
            newFile.renameTo(delete);

            //           userStatus = userStatusBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return userStatus;
    }}
