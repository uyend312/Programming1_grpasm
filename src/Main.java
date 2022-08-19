import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
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
