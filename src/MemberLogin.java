import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MemberLogin {
    public static void memberLogin() throws IOException {
        login lg = new login();
        System.out.println("Enter email");
        Scanner scanner = new Scanner(System.in);
        lg.setUserEmail(scanner.nextLine());
        lg.setUserEmail(RegisterDemo.checkEmailSyntax(lg.getUserEmail()));

        System.out.println("Enter password");
        lg.setUserPassword(scanner.nextLine());

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
                data = line.split(";");
                if (data[3].equals(userEmail) && data[6].equals(userPassword)) {

                    System.out.println("Login success, Welcome " + data[1] + " " + data[2]);

                    System.out.println("Go to your account page, press 1\nGo to main page, press 2");
                    Scanner scanner = new Scanner(System.in);
                    String userChoice = scanner.nextLine();
                    switch (userChoice) {
                        // Go to user account
                        case ("1"):
                            myaccount.userAccount(userEmail);
                            System.out.println("_________________________________________________");
                            break;
                        // Go to the main page to shop
                        case ("2"):
                          createOrder.createOrder();
                            System.out.println("_________________________________________________");
                      //      ProductsDemoForUsers.ProductsDemoForUsers();
                            break;
                        default:
                            System.out.println("Invalid input");

                    }

                    return;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("Login failed, invalid user-email or password, try again");
        memberLogin();

    }

}

