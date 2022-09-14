import java.io.*;
import java.lang.reflect.Member;
import java.util.Scanner;
import java.util.UUID;

public class RegisterDemo {
    public static void registerMember() throws IOException {

        register rgs = new register();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your first name: ");
        rgs.setFirstName(scanner.nextLine());
        rgs.setFirstName(checkValidName(rgs.getFirstName()));

        System.out.print("Enter your last name: ");
        rgs.setLastName(scanner.nextLine());
        rgs.setLastName(checkValidName(rgs.getLastName()));

        System.out.print("Enter your email: ");
        rgs.setUserEmail(scanner.nextLine());
        rgs.setUserEmail(checkEmailSyntax(rgs.getUserEmail()));
        rgs.setUserEmail(checkAvailable(rgs.getUserEmail()));

        System.out.print("Enter your address: ");
        rgs.setUserAddress(scanner.nextLine());
        rgs.setUserAddress(ValidateAddress(rgs.getUserAddress()));

        System.out.println("Enter your phone number: ");
        rgs.setUserPhone(scanner.nextLine());
        rgs.setUserPhone(ValidatePhone(rgs.getUserPhone()));

        System.out.println("Enter your password (At least 8 characters)");
        rgs.setUserPassword(scanner.nextLine());
        rgs.setUserPassword(checkPassword(rgs.getUserPassword()));

        rgs.setUserStatus(generateStatus(rgs.getUserStatus()));

        rgs.setTotalSpending(totalSpend(rgs.getTotalSpending()));
        //user confirmation
        System.out.println("All correct? Click 1 to finish your registration, 2 to cancel");
        int userChoice = scanner.nextInt();
        //store their information if they agreed
        if (userChoice == 1) {
            //generate userID
            rgs.setUserID(generateUID());
            //store data
            try (FileWriter fw = new FileWriter("userdata.txt", true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter pw = new PrintWriter(bw)) {

                pw.println(rgs.getUserID() + ";" + rgs.getFirstName() + ";" + rgs.getLastName() + ";" +rgs.getUserEmail() + ";"+rgs.getUserAddress() +";" + rgs.getUserPhone()+";"
                        +  rgs.getUserPassword() + ";" + rgs.getUserStatus()
                        + ";" + rgs.getTotalSpending());

                pw.flush();

            } catch (IOException e) {
                System.out.println("Error file cannot found");
            }
            System.out.println("Thank you! You have register to our site");
            System.out.println("Now you can log in");
            MemberLogin.customerLogin();
        } else if (userChoice == 2) {
            System.out.println("Thank you for visiting our site");
        }

    }

    //method
    public static String checkValidName (String input){
        while (!input.matches("[a-zA-Z]+")) {
            System.out.println("Invalid input, try again");
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();

        }
        return input;

    }

    public static String checkAvailable (String input) throws IOException {
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
            String[] values = line.split(";");
            if (input.equals(values[3]) && !input.isEmpty()) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("This email is available, try again");
                input = scanner.nextLine();
                checkEmailSyntax(input);

            }


        }
        return input;
    }

    public static String checkEmailSyntax (String input){
        while (!input.matches("^(.+)@[a-zA-Z\\d.-]+$")) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Invalid email, try again");
            input = scanner.nextLine();

        }
        return input;
    }


    public static String checkPassword (String input){
        Scanner scanner = new Scanner(System.in);
        while (!input.matches(".{8,}$")) {
            System.out.println("Password too short, re-enter please ");
            input = scanner.nextLine();
        }
        System.out.println("Re-Enter your password ");
        String reuserPassword = scanner.nextLine();
        while (!input.equals(reuserPassword)) {
            System.out.println("Password not match. Try again");
            reuserPassword = scanner.nextLine();
        }

        return input;

    }

    public static String generateUID () {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    // membership
    public static String generateStatus (String userStatus) throws FileNotFoundException {
        userStatus = "New Member";

        return userStatus;

    }

    public static double totalSpend ( double spending){
        spending = 0;


        return spending;
    }
    public static String ValidateAddress(String input)
    {
        Scanner scanner = new Scanner(System.in);
        while (!input.matches("^[^;]*$"))
        {
            System.out.println("Cannot include ';' inside");
            input = scanner.nextLine();
        }

        return input;
    }
    public static String ValidatePhone(String input)
    {
        Scanner scanner = new Scanner(System.in);
        while (!input.matches("[0-9]+"))
        {
            System.out.println("Cannot have character");
            input = scanner.nextLine();
        }
        return input;
    }
}



