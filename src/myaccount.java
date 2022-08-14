import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class myaccount {
    private String firstName;
    private String lastName;
    private String userEmail;
    private String userID;
    private String userOrder;

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    private String userStatus;

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

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(String userOrder) {
        this.userOrder = userOrder;
    }

    public myaccount(String firstName, String lastName, String userEmail, String userStatus, String userID, String userOrder) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userEmail = userEmail;
        this.userStatus = userStatus;
        this.userID = userID;
        this.userOrder = userOrder;
    }

    public static String userAccount(String userEmail)  {
        String line;
        String[] data;

        try {
            FileReader fr = new FileReader("userdata.txt");
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                data = line.split(",");
                if (userEmail.equals(data[2])) {
                    data = line.split(",");
                    System.out.println("<--------------Your Account information-------------->");
                    System.out.println("ID " + data[5]);
                    System.out.println("First name " + data[0]);
                    System.out.println("Last name " + data[1]);
                    System.out.println("Email " + data[2]);
                    System.out.println("Level of member " + data[4]);
                }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return userEmail;
    }
}
