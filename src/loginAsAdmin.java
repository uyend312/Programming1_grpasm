import java.io.IOException;
import java.util.Scanner;

public class loginAsAdmin {
    public static void admLogin() throws Exception {
        adminlogin adm = new adminlogin();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username");
        adm.setAdminUserName(scanner.nextLine());

        System.out.println("Enter password");
        adm.setAdminPassword(scanner.nextLine());

        checkAdminValidate(adm.getAdminUserName(), adm.getAdminPassword());

    }
    public static void checkAdminValidate(String adminUserName, String adminPassword) throws Exception {
        String username = "admin";
        String password = "admin";
        if ((adminUserName.equals(username))&&(adminPassword.equals(password)))
        {
            System.out.println("Welcome admin! ");

            adminsite.adminModify();

        }
        else
        {
            System.out.println("Wrong input, try again");
            admLogin();
        }
    }
}


