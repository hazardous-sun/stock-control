package universal_stock_management;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private int code;
    private String typeUnity;
    private double priceUnity;
    private int amountInStock;
    private Supplier supplier;

    public Product(String name, int code, String typeUnity, double priceUnity, int amountInStock, Supplier supplier) {
        this.name = name;
        this.code = code;
        this.typeUnity = typeUnity;
        this.priceUnity = priceUnity;
        this.amountInStock = amountInStock;
        this.supplier = supplier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeUnity() {
        return typeUnity;
    }

    public void setTypeUnity(String typeUnity) {
        this.typeUnity = typeUnity;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public double getPriceUnity() {
        return priceUnity;
    }

    public void setPriceUnity(double priceUnity) {
        this.priceUnity = priceUnity;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public int getAmountInStock() {
        return amountInStock;
    }

    public void setAmountInStock(int amountInStock) {
        this.amountInStock = amountInStock;
    }

    @Override
    public String toString() {
        return code + " | " + name;
    }
}
