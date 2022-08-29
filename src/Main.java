import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("COSC2081 GROUP ASSIGNMENT");
        System.out.println("STORE ORDER MANAGEMENT SYSTEM");
        System.out.println("Instructor: Mr. Minh Vu ");
        System.out.println("Group: 16");
        System.out.println("s3924821, Nhat Tran Minh ");
        System.out.println("s3904418, Uyen Duong ");
        System.out.println("s3904419, Phong Nguyen ");
        System.out.println("<-------------------------------------->");

        System.out.println("Welcome to our shop! Type 1 if you already have an account, or 2 to register");

        System.out.println("Login as an admin, choose 3: ");

        Scanner scanner = new Scanner(System.in);
        String userChoice = scanner.nextLine();
        switch (userChoice) {
            case ("1"):
                System.out.println("<-------------------------------------->");
                MemberLogin.customerLogin();
                break;
            case ("2"):
                System.out.println("<-------------------------------------->");
                RegisterDemo.registerMember();
                break;
            case ("3"):
                System.out.println("<-------------------------------------->");
                loginAsAdmin.admLogin();
                break;
            default:
                System.out.println("Invalid input");

        }
    }
}
