package universal_stock_management;

import java.io.Serializable;

public class Supplier implements Serializable {
    private String name;
    private String address;
    private String cnpj;  // Brazilian national certificate of legal entity
    private int code;

    public Supplier(String name, String address, String cnpj, int code) {
        this.name = name;
        this.address = address;
        this.cnpj = cnpj;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code + " | " + name;
    }
}
