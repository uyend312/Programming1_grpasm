import java.util.*;
import java.io.*;
public class Products {
    private String id;
    private String category;
    private String name;
    private String quantity;
    private String price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public Products(String id, String category, String name, String quantity, String price) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.quantity = quantity;
        this.price = price;

    }

    public void addProduct() {
        Scanner sc = new Scanner(System.in);
        int exit = 1;
        while(exit!=0) {
            System.out.println("Enter id of product:");
            id = sc.next();
            System.out.println("Enter category:");
            category = sc.next();
            System.out.println("Enter name:");
            name = sc.next();
            System.out.println("Enter quantity:");
            quantity = sc.next();
            System.out.println("Enter price:");
            price = sc.next();

            try{
                File f = new File("Products.txt");
                PrintWriter pw = new PrintWriter(new FileOutputStream(f, true));
                pw.append("\n"+id+","+category+","+name+","+quantity+","+price);
                pw.close();
            }
            catch(Exception e){}
            System.out.println("Do you want to add more products to the storage?(Yes:1/No:0)");
            exit = sc.nextInt();
        }

    }
    public void showProduct() {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("Products.txt"));
            String s = "";
            while ((s = br.readLine()) != null) {
                String data[] = new String[5];
                data = s.split(",");
                for (int i = 0; i < 5; i++) {
                    System.out.println(data[i] + " ");
                }
                System.out.println();
            }
        }
        catch(Exception e){}
    }
}
