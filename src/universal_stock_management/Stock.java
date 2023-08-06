package universal_stock_management;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Stock implements Serializable {
    private final ArrayList<Product> stockItems;
    private final ArrayList<Supplier> stockSuppliers;

    public Stock() {
        this.stockItems = new ArrayList<>();
        this.stockSuppliers = new ArrayList<>();
    }

    // Used in ManualSetBinValues.java
    public Stock(ArrayList<Product> stockItems, ArrayList<Supplier> stockSuppliers) {
        this.stockItems = stockItems;
        this.stockSuppliers = stockSuppliers;
    }

    public ArrayList<Product> getStockItems() {
        return stockItems;
    }

    public Product[] getArrayStockItems() {
        return getStockItems().toArray(new Product[0]);
    }

    public Supplier[] getArraySuppliers() {
        return getStockSuppliers().toArray(new Supplier[0]);
    }

    public void sortStockItems() {
        Collections.sort(stockItems, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return p1.getName().compareTo(p2.getName());
            }
        });
    }

    public void sortStockItemsByCode() {
        Collections.sort(stockItems, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Integer.compare(p1.getCode(), p2.getCode());
            }
        });
    }

    public boolean addProduct(Product newProduct) {
        if (stockItems.add(newProduct)) {
            sortStockItems();
            return true;
        } else {
            System.out.println("Error: Could not add the following product to the stock\n" + newProduct);
            return false;
        }
    }

    public boolean removeFromStock(Product removedProduct) {
        if (stockItems.remove(removedProduct)) {
            return true;
        } else {
            System.out.println("Error: Could not remove the following product from the stock\n" + removedProduct);
            return false;
        }
    }

    public Product searchInStock(int productCode) {
        for (Product product : stockItems) {
            if (product.getCode() == productCode) {
                return product;
            }
        }
        return null;
    }

    public Product searchByName(String productName) {
        for (Product product : stockItems) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null;
    }

    public ArrayList<Supplier> getStockSuppliers() {
        return stockSuppliers;
    }

    public void sortSuppliers() {
        Collections.sort(stockSuppliers, new Comparator<Supplier>() {
            @Override
            public int compare(Supplier s1, Supplier s2) {
                return s1.getName().compareTo(s2.getName());
            }
        });
    }

    public void sortSuppliersByCode() {
        Collections.sort(stockSuppliers, new Comparator<Supplier>() {
            @Override
            public int compare(Supplier s1, Supplier s2) {
                return Integer.compare(s1.getCode(), s2.getCode());
            }
        });
    }

    public Supplier searchSupplier(int supplierCode) {
        for (Supplier supplier : stockSuppliers) {
            if (supplier.getCode() == supplierCode) {
                return supplier;
            }
        }
        return null;
    }

    public boolean addSupplier(Supplier newSupplier) {
        if (stockSuppliers.add(newSupplier)) {
            sortSuppliers();
            return true;
        } else {
            System.out.println("Error: Could not add the following supplier to the list\n" + newSupplier);
            return false;
        }
    }

    public boolean removeSupplier(Supplier removedSupplier) {
        if (stockSuppliers.remove(removedSupplier)) {
            return true;
        } else {
            System.out.println("Error: Could not remove the following supplier from the list\n" + removedSupplier);
            return false;
        }
    }

    @Override
    public String toString() {
        return "Stock{" +
                "\nstockItems=" + stockItems +
                ", \nsuppliers=" + stockSuppliers +
                "\n}";
    }
}
