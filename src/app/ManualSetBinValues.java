package app;

import universal_stock_management.Product;
import universal_stock_management.Stock;
import universal_stock_management.Supplier;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class ManualSetBinValues {
    public static void main(String[] args) {
        Stock stock = new Stock();

        // Set suppliers
        stock.addSupplier(new Supplier("Supplier 1", "Address 1", "123", 1));
        stock.addSupplier(new Supplier("Supplier 2", "Address 2", "123", 2));
        stock.addSupplier(new Supplier("Supplier 3", "Address 3", "123", 3));
        stock.addSupplier(new Supplier("Supplier 4", "Address 3", "123", 4));
        stock.addSupplier(new Supplier("Supplier 5", "Address 3", "123", 5));
        stock.addSupplier(new Supplier("Supplier 6", "Address 3", "123", 6));
        stock.addSupplier(new Supplier("Supplier 7", "Address 3", "123", 7));
        stock.addSupplier(new Supplier("Supplier 8", "Address 3", "123", 8));
        stock.addSupplier(new Supplier("Supplier 9", "Address 3", "123", 9));
        stock.addSupplier(new Supplier("Supplier 10", "Address 3", "123", 10));
        stock.addSupplier(new Supplier("Supplier 11", "Address 3", "123", 11));
        stock.addSupplier(new Supplier("Supplier 12", "Address 3", "123", 12));
        stock.addSupplier(new Supplier("Supplier 13", "Address 3", "123", 13));
        stock.addSupplier(new Supplier("Supplier 14", "Address 3", "123", 14));
        stock.addSupplier(new Supplier("Supplier 15", "Address 3", "123", 15));
        stock.addSupplier(new Supplier("Supplier 16", "Address 3", "123", 16));
        stock.addSupplier(new Supplier("Supplier 17", "Address 3", "123", 17));
        stock.addSupplier(new Supplier("Supplier 18", "Address 3", "123", 18));
        stock.addSupplier(new Supplier("Supplier 19", "Address 3", "123", 19));

        // Set products
        stock.addProduct(new Product("Product 1", 1, "unity", 1, 1, stock.searchSupplier(1)));
        stock.addProduct(new Product("Product 2", 2, "unity", 2, 2, stock.searchSupplier(2)));
        stock.addProduct(new Product("Product 3", 3, "unity", 3, 3, stock.searchSupplier(3)));
        stock.addProduct(new Product("Product 4", 4, "unity", 4, 4, stock.searchSupplier(4)));
        stock.addProduct(new Product("Product 5", 5, "unity", 5, 5, stock.searchSupplier(5)));
        stock.addProduct(new Product("Product 6", 6, "unity", 6, 6, stock.searchSupplier(6)));
        stock.addProduct(new Product("Product 7", 7, "unity", 7, 7, stock.searchSupplier(7)));
        stock.addProduct(new Product("Product 8", 8, "unity", 8, 8, stock.searchSupplier(8)));
        stock.addProduct(new Product("Product 9", 9, "unity", 9, 9, stock.searchSupplier(9)));
        stock.addProduct(new Product("Product 10", 10, "unity", 10, 10, stock.searchSupplier(10)));
        stock.addProduct(new Product("Product 11", 11, "unity", 11, 11, stock.searchSupplier(11)));
        stock.addProduct(new Product("Product 12", 12, "unity", 12, 12, stock.searchSupplier(12)));
        stock.addProduct(new Product("Product 13", 13, "unity", 13, 13, stock.searchSupplier(13)));
        stock.addProduct(new Product("Product 14", 14, "unity", 14, 14, stock.searchSupplier(14)));
        stock.addProduct(new Product("Product 15", 15, "unity", 15, 15, stock.searchSupplier(15)));
        stock.addProduct(new Product("Product 16", 16, "unity", 16, 16, stock.searchSupplier(16)));
        stock.addProduct(new Product("Product 17", 17, "unity", 17, 17, stock.searchSupplier(17)));
        stock.addProduct(new Product("Product 18", 18, "unity", 18, 18, stock.searchSupplier(18)));
        stock.addProduct(new Product("Product 19", 19, "unity", 19, 19, stock.searchSupplier(19)));



        String currentPath = System.getProperty("user.dir");
        String filePath = currentPath.concat("/file.bin");

        try (FileOutputStream fileStream = new FileOutputStream(filePath);
             ObjectOutputStream objectStream = new ObjectOutputStream(fileStream)) {
            // Write the object to the file
            objectStream.writeObject(stock);
            System.out.println("Object saved successfully.");
        } catch (java.io.FileNotFoundException e) {
            System.out.println("Error: Could not find 'file.bin' in the following path\n" + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}