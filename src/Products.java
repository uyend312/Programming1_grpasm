import java.util.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
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
            System.out.println("Do you want to add more products to the storage?(Yes: 1/No: 0)");
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
    public void updateProduct()
    {
        /////////phase 1: Identifying rows to update
        System.out.println("Enter the ID of product that you want to update.....");
        Scanner sc=new Scanner(System.in);
        int id_to_change=sc.nextInt();
        String to_change_column_names[]={"Category:","Name:","Quantity:","Price:"};
        int yes_no[]=new int[4];
        String to_update[]=new String[4];

        System.out.println("Select the columns that you want to update (Yes: 1/No: 0)");
        //Loop 4 times = 4 columns to confirm which is going to be updated
        for(int i=0;i<4;i++)
        {
            System.out.println(to_change_column_names[i]+"");
            int temp=sc.nextInt();
            yes_no[i]=temp;
        }

        ////////////////phase2: Updating values
        System.out.println("_______________________________________________________");
        System.out.println("Add new values to the columns");
        //Verifying which columns can be updated
        for(int i=0;i<4;i++)
        {
            System.out.println(to_change_column_names[i]+"");
            if(yes_no[i]==1)
            {
                Scanner sc1=new Scanner(System.in);
                String temp_val=sc1.nextLine();
                to_update[i]=temp_val;
                System.out.println();
            }
            else
            {
                System.out.println("Cannot be changed");
            }
        }

        //////////////////phase 3: Write into file updated values
        StringBuffer sb=new StringBuffer();
        try
        {
            BufferedReader br=new BufferedReader(new FileReader("Products.txt"));
            String s;
            while((s=br.readLine())!=null)
            {
                String data[];
                data=s.split(",");
                if(id_to_change==Integer.parseInt(data[0]))
                {
                    String row=data[0]+",";
                    //Going through 4 columns if 1 add updated value from phase 2, else keep moving on
                    for(int i=0;i<4;i++)
                    {
                        if(yes_no[i]==1)
                        {
                            row=row+to_update[i]+",";
                        }
                        else
                        {
                            row=row+data[i+1]+",";
                        }

                    }
                    sb.append(row);
                    sb.append("\n");
                }
                else
                {
                    sb.append(s);
                    sb.append("\n");
                }
            }
            ////////////////// phase 4: Show the updated information
            File f=new File("Products.txt");
            PrintWriter pw=new PrintWriter(new FileOutputStream(f,false));
            pw.print(sb.toString());
            pw.close();
        }
        catch(Exception e) {}
    }
    public void deleteProduct(){
        System.out.println("Enter the name of product that you want to delete.....");
        Scanner sc=new Scanner(System.in);
        String name_to_change=sc.next();
        try {
            File inputFile = new File("Products.txt");
            File tempFile = new File("myTempFile.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            String s;
            while((s=reader.readLine())!=null) {
                String data[] = s.split(",");
                String name = data[2];
                if (!name.equals(name_to_change)){
                    writer.write(s + "\n");
                }
            }
            writer.close();
            reader.close();
            inputFile.delete();
            tempFile.renameTo(inputFile);
        }
        catch(Exception e) {}
    }
    public void showProductByCategory(){
        ArrayList<String> records = new ArrayList<>();
        boolean found = false;
        String record;
        System.out.println("Please enter the category of product that you want to see...");
        Scanner sc =new Scanner(System.in);
        String searchCategory= sc.next();
        try{
            Scanner x;
            x = new Scanner(new File("Products.txt"));
            x.useDelimiter("[,\n]");

            while(x.hasNext()){
                category = x.next();
                if (category.equals(searchCategory)) {
                    id = x.next();
                    category = x.next();
                    name = x.next();
                    quantity = x.next();
                    price = x.next();
                    record = id + "," + category + "," + name + "," + quantity + "," + price;
                    records.add(record);
                    found = true;
                }
                else{
                    x.next();
                    x.next();
                }
            }
            if(!found){
                System.out.println("No records found");
            }

        }
        catch(Exception e) {}
        for(String str: records){
            System.out.println(str);
        }
    }

}
