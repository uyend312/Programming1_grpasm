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

    @Override
    public String toString() {
        return "adminlogin{" +
                "adminUserName='" + adminUserName + '\'' +
                ", adminPassword='" + adminPassword + '\'' +
                '}';
    }}
