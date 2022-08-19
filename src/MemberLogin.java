import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MemberLogin {
    public static void customerLogin() throws IOException {
        login lg = new login();
        System.out.println("Enter email");
        Scanner scanner = new Scanner(System.in);
        lg.setUserEmail(scanner.nextLine());
        lg.setUserEmail(RegisterDemo.checkEmailSyntax(lg.getUserEmail()));

        System.out.println("Enter password");
        lg.setUserPassword(scanner.nextLine());
        //checkLogin(userEmail, userPassword);

        checkLogin(lg.getUserEmail(), lg.getUserPassword());


    }

    //method
    public static void checkLogin(String userEmail, String userPassword) throws IOException {
        String line;
        String[] data;

        try {
            FileReader fr = new FileReader("userdata.txt");
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                data = line.split(",");
                if (data[3].equals(userEmail) && data[4].equals(userPassword)) {

                    System.out.println("Login success, Welcome " + data[1] + " " + data[2]);

                    System.out.println("Go to your account page, press 1");
                    Scanner scanner = new Scanner(System.in);
                    String userChoice = scanner.nextLine();
                    switch (userChoice) {
                        // Go to user account
                        case ("1"):
                            myaccount.userAccount(userEmail);
                            break;
                        // Go to the main order page
                        case ("2"):
                            // register.userRegister();

                            break;
                        default:
                            System.out.println("Invalid input");


                    }

                    return;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Login failed, invalid user-email or password, try again");
        customerLogin();

    }

}

