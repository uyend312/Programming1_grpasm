import java.io.*;
import java.util.Scanner;
public class login {
    private String userEmail;
    private String userPassword;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public login(String userEmail, String userPassword) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public static void customerLogin() throws IOException {
        System.out.println("Enter email");
        Scanner scanner = new Scanner(System.in);
        String userEmail = scanner.nextLine();
        userEmail = register.checkEmailSyntax(userEmail);

        System.out.println("Enter password");
        String userPassword = scanner.nextLine();


        checkLogin(userEmail, userPassword);


        //check availability for user email and password
    }
    public static String checkLogin(String userEmail, String userPassword) throws IOException {
        String line;
        String[] data;

        try {
            FileReader fileReader = new FileReader("userdata.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null)
            {
                data = line.split(",");
                if (data[2].equals(userEmail) && data[3].equals(userPassword))
                {

                    System.out.println( "Login success, Welcome " + data[0]);
                    return userEmail;


                }


            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Login failed, try again");
        customerLogin();
        return userEmail;

    }

}







