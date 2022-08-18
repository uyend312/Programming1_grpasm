import java.io.IOException;
import java.util.Scanner;
public class adminlogin {
    private String adminUserName;
    private String adminPassword;
    public adminlogin(){}

    public String getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public adminlogin(String adminUserName, String adminPassword)
    {
        this.adminUserName=adminUserName;
        this.adminPassword=adminPassword;
    }
    public static void admLogin() throws IOException {
        adminlogin adm = new adminlogin();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username");
        adm.setAdminUserName(scanner.nextLine());

        System.out.println("Enter password");
        adm.setAdminPassword(scanner.nextLine());

        checkAdminValidate(adm.getAdminUserName(), adm.getAdminPassword());

    }
    public static void checkAdminValidate(String adminUserName, String adminPassword) throws IOException {
        String username = "admin";
        String password = "1234";
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
