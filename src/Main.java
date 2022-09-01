import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("COSC2081 GROUP ASSIGNMENT \n" +
                "STORE ORDER MANAGEMENT SYSTEM \n" +
                "Instructor: Mr. Minh Vu \n" +
                "Group: Group 16 \n" +
                "s3924821, Nhat Tran Minh \n" +
                "s3904419, Phong Nguyen \n" +
                "s3904418, Uyen Duong");
        System.out.println("Welcome to our shop! Click 1 if you already have an account, or 2 to register");
        System.out.println("Login as an admin, choose 3");
        System.out.println("<-------------------------------------->");
        Scanner scanner = new Scanner(System.in);
        String userChoice = scanner.nextLine();
        switch (userChoice) {
            case ("1"):
                MemberLogin.customerLogin();
                break;
            case ("2"):
                RegisterDemo.registerMember();
                break;
            case ("3"):
                loginAsAdmin.admLogin();
                break;
            default:
                System.out.println("Invalid input");

        }
    }
}
