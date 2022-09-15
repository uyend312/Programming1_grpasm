import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        mainPage();
    }
    public static void mainPage() throws Exception {
        System.out.println("\nCOSC2081 GROUP ASSIGNMENT\n" +
                "STORE ORDER MANAGEMENT SYSTEM\n" +
                "Instructor: Mr. Minh Vu\n" +
                "Group:s 16\n" +
                "s3924821, Nhat Tran Minh\n" +
                "s3904419, Phong Nguyen\n" +
                "s3904418, Uyen Duong");
        System.out.println("<-------------------------------------->");
        System.out.println("Welcome to our shop!\n");
        System.out.println("Enter 1 to browse and view our products.");
        System.out.println("Enter 2 to login if you already have an account.");
        System.out.println("Enter 3 to register a new account.");
        System.out.println("Login as an admin, enter 4.");

        int userChoice = 0;

        while (userChoice != 1 && userChoice != 2 && userChoice != 3 && userChoice != 4) {
            System.out.println("Enter your option 1-4:");
            Scanner scanner = new Scanner(System.in);

            //validate input must be integer
            while (!scanner.hasNextInt()) {
                System.out.println("INVALID INPUT!\nChoose an option 1-4: ");
                scanner.next();
            }

            userChoice = scanner.nextInt();
            switch (userChoice) {
                case 1:
                    customer.customer();
                case 2:
                    MemberLogin.memberLogin();
                    break;
                case 3:
                    RegisterDemo.registerMember();
                    break;
                case 4:
                    loginAsAdmin.admLogin();
                    break;
                default:
                    System.out.println("Invalid input " + userChoice);
            }

        }}}