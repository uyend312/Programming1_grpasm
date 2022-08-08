import java.util.*;
import java.util.ArrayList;
import java.io.*;
public class Products {
    final static String outputFilePath
            = "Products.txt";
    private String category;
    private String name;
    private String stock;
    private String price;


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Products(){
        this.category = "Default";
        this.name = "Default";
        this.stock = "Default";
        this.price = "Default";
    }
    public Products( String category, String name, String stock, String price) {
        this.category = category;
        this.name = name;
        this.stock = stock;
        this.price = price;

    }

    public void addProduct() {
        HashMap<Integer, List<String>> products = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("How many products do you want to add?");
        int id = sc.nextInt();
        for (int i = 1; i <= id; i++) {
            List<String> editProduct = products.get(i);
            System.out.println("Enter category:");
            category = sc.next();
            System.out.println("Enter name:");
            name = sc.next();
            System.out.println("Enter stock:");
            stock = sc.next();
            System.out.println("Enter price:");
            price = sc.next();
            if (editProduct == null) {
                editProduct = new ArrayList<String>();
                editProduct.add(category);
                editProduct.add(name);
                editProduct.add(stock);
                editProduct.add(price);
                products.put(i, editProduct);
            } else {
                System.out.println("You typed this already!");
            }
        }
        System.out.println(products);
        //write to a file
        File file = new File(outputFilePath);

        BufferedWriter bf = null;

        try {

            // create new BufferedWriter for the output file
            bf = new BufferedWriter(new FileWriter(file));

            // iterate map entries
            for (Map.Entry<Integer, List<String>> entry :
                    products.entrySet()) {

                // put key and value separated by a colon
                bf.write(entry.getKey() + ":"
                        + entry.getValue());

                // new line
                bf.newLine();
            }

            bf.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {

                // always close the writer
                bf.close();
            } catch (Exception e) {
            }
        }
    }
    public void showProduct() throws Exception{
        File file = new File(
                "Products.txt");

        BufferedReader br
                = new BufferedReader(new FileReader(file));

        // Declaring a string variable
        String st;
        // Condition holds true till
        // there is character in a string
        while ((st = br.readLine()) != null)

            // Print the string
            System.out.println(st);
    }
}
