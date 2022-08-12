import java.io.*;
import java.util.Scanner;
import java.util.UUID;

public class register {
    private String firstName;
    private String lastName;
    private String userEmail;
    private String userPassword;
    private String userStatus;
    private String userID;



    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }




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


    public register(String firstName, String lastName, String userEmail, String userPassword, String userStatus, String userID){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userStatus = userStatus;
        this.userID = userID;
    }
    public static void userRegister() throws IOException {
        //user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();
        firstName = checkValidName(firstName);

        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();
        lastName = checkValidName(lastName);

        System.out.print("Enter your email: ");
        String userEmail = scanner.nextLine();
        userEmail = checkEmailSyntax(userEmail);
        userEmail = checkAvailable(userEmail);

        System.out.println("Enter your password (At least 8 characters)");
        String userPassword = scanner.nextLine();
        userPassword = checkPassword(userPassword);

        String userStatus = "new member";

        //user confirmation
        System.out.println("All correct? Click 1 to finish your registration, 2 to cancel");
        int userChoice = scanner.nextInt();
        //store their information if they agreed
        if (userChoice == 1) {
            //generate userID
            UUID uuid = UUID.randomUUID();
            String userID = uuid.toString();
            //store data
            try (FileWriter fw = new FileWriter("userdata.txt", true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter pw = new PrintWriter(bw)) {

                pw.println(firstName + "," + lastName + "," + userEmail + "," + userPassword + "," + userStatus +","+ userID);
                pw.flush();

            } catch (IOException e) {
                System.out.println("Error file cannot found");
            }
            System.out.println("Thank you! You have register to our site");
        } else if (userChoice == 2) {
            System.out.println("Thank you for visiting our site");
        }

    }

    //method
    public static String checkValidName(String input)
    {
        while (!input.matches("[a-zA-Z]+"))
        {
            System.out.println("Invalid input, try again");
            Scanner scanner =new Scanner(System.in);
            input = scanner.nextLine();

        }
        return input;

    }
    public static String checkAvailable(String input) throws IOException {
        String fileName = "userdata.txt";
        File file = new File(fileName);
        FileReader fr;
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            if (input.equals(values[2])&&!input.isEmpty()) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("This email is available, try again");
                input = scanner.nextLine();
                checkEmailSyntax(input);

            }

        }
        return input;
    }
    public static String checkEmailSyntax(String input)
    {
        while (!input.matches("^(.+)@(\\S+)$")) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Invalid email, try again");
            input = scanner.nextLine();

        }
        return input;
    }

    //            public static void userStatus()
//            {
//
//            }
    public static String checkPassword(String input)
    {
        Scanner scanner = new Scanner(System.in);
        while (!input.matches(".{8,}$"))
        {
            System.out.println("Password too short, re-enter please ");
            input = scanner.nextLine();
        }
        System.out.println("Re-Enter your password ");
        String reuserPassword = scanner.nextLine();
        while (!input.equals(reuserPassword))
        {
            System.out.println("Password not match. Try again");
            reuserPassword = scanner.nextLine();
        }

        return input;

    }
}




