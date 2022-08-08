import java.io.*;
import java.util.Scanner;

public class register {
    private String firstName;
    private String lastName;
    private String userEmail;
    private String userPassword;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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

    public register(String firstName, String lastName, String userEmail, String userPassword){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }
    public static void userRegister() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();
        checkValidName(firstName);
        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();
        checkValidName(lastName);
        System.out.print("Enter your email: ");
        String userEmail = scanner.nextLine();
        while (!userEmail.matches("^(.+)@(\\S+)$")) {
            System.out.println("Invalid email");
        }
        System.out.println("Enter your password (At least 8 characters)");
        String userPassword = scanner.nextLine();
        while (!userEmail.matches(".{8,}$"))
        {
            System.out.println("Password too short, re-enter please ");
        }
        System.out.println("Re-Enter your password ");
        String reuserPassword = scanner.nextLine();
        while (!userPassword.equals(reuserPassword))
        {
            System.out.println("Password not match");
        }
        System.out.println("All correct? Click 1 to finish your registration, 2 to cancel");
        int userChoice = scanner.nextInt();
        if (userChoice ==1 ) {
            try (FileWriter fw = new FileWriter("userdata.txt", true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println(firstName + "," + lastName + "," + userEmail + "," + userPassword);

            } catch (IOException e) {
                //exception handling
            }
            System.out.println("Thank you! You have register to our site");
        }
        else if (userChoice ==2)
        {
            System.out.println("Thank you for visiting our site");
        }


    }
    public static void checkValidName(String input)
    {
        while (!input.matches("[a-zA-Z]+"))
        {
            System.out.println("Invalid input");
        }

    }


}

