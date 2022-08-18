import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class myaccount {
    public static void userAccount(String userEmail) throws IOException {

        String line;
        String[] data;
        while (userEmail.isEmpty())
        {
            System.out.println("You need to login first");
            login.customerLogin();

        }

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
                    System.out.println("Level of member: " + data[5]);
                }

            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        }}}


