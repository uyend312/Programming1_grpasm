
public class register {
    private String firstName;
    private String lastName;
    private String userEmail;
    private String userPassword;
    private String userStatus;
    private float totalSpending;

    public register() {

    }

    public float getTotalSpending() {
        return totalSpending;
    }

    public void setTotalSpending(float totalSpending) {
        this.totalSpending = totalSpending;
    }

    @Override
    public String toString() {
        return "register{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userStatus='" + userStatus + '\'' +
                ", totalSpending=" + totalSpending +
                ", userID='" + userID + '\'' +
                '}';
    }

    public register(String firstName, String lastName, String userEmail, String userPassword, String userStatus, String userID, float totalSpending) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userStatus = userStatus;
        this.userID = userID;
        this.totalSpending = totalSpending;
    }


    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    private String userID;

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


}