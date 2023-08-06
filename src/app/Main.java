package app;

import universal_stock_management.Product;
import universal_stock_management.Stock;
import universal_stock_management.Supplier;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main extends JFrame {
    // Data storage variables
    private final Stock stock;

    // Panel variables
    private JPanel panelMain;

    // Text fields variables
    private JTextField tfName;
    private JTextField tfCode;
    private JTextField tfUnity;
    private JTextField tfPrice;
    private JTextField tfAmount;
    private JTextField tfSearch;

    // Buttons variables
    private JButton btnSearch;
    private JButton btnSave;
    private JButton btnDelete;
    private JButton btnManageSuppliers;
    private JButton btnSortName;
    private JButton btnSortCode;
    private JButton btnProductDescription;

    // Check boxes variables
    private JCheckBox autoFillCheckBox;
    private JCheckBox checkName;
    private JCheckBox checkCode;

    // Combo box variables
    private JComboBox<Supplier> cbSupplier;

    // List variables
    private JList<Product> listProducts;

    // Logic control variables
    private boolean autoFill = false;
    private int typeSearch = 1;

    public Stock getStock() {
        return stock;
    }

    public JList<Product> getListProducts() {
        return listProducts;
    }

    public Stock loadStockBin() {
        String currentPath = System.getProperty("user.dir");
        String filePath = currentPath.concat("/file.bin");
        System.out.println("\n\nFile loaded from: " + filePath + "\n");

        // Tries to read file.bin
        try (FileInputStream fileStream = new FileInputStream(filePath); ObjectInputStream objectStream = new ObjectInputStream(fileStream)) {
            // Read the object from the file
            Object obj = objectStream.readObject();

            if (obj instanceof Stock) {
                Stock myObject = (Stock) obj;
                System.out.println("Object loaded successfully.");
                listProducts.setListData(myObject.getArrayStockItems());
                return myObject;
            } else {
                System.out.println("Invalid object type.");
                return new Stock();
            }
        } catch (java.io.FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
            return new Stock();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\n\n\n\n\n\n");
            return new Stock();
        }
    }

    public void saveStockBin() {
        String currentPath = System.getProperty("user.dir");
        String filePath = currentPath.concat("/file.bin");
        try (FileOutputStream fileStream = new FileOutputStream(filePath); ObjectOutputStream objectStream = new ObjectOutputStream(fileStream)) {
            // Write the object to the file
            objectStream.writeObject(this.stock);
            System.out.println("Object saved successfully.");
            listProducts.setListData(stock.getArrayStockItems());
        } catch (java.io.FileNotFoundException e) {
            System.out.println("Error: Could not find 'file.bin' in the following path\n" + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addToComboBox(Supplier newSupplier) {
        cbSupplier.addItem(newSupplier);
    }

    public void removeFromComboBox(Supplier removedSupplier) {
        cbSupplier.removeItem(removedSupplier);
    }

    public Main() {
        this.stock = loadStockBin();
        stock.sortSuppliersByCode();
        for (Supplier supplier : stock.getStockSuppliers()) {
            cbSupplier.addItem(supplier);
        }
        setContentPane(panelMain);
        setTitle("Universal Stock Control");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 600);
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);

        // Listeners
        btnSave.addActionListener(e -> {
            int tempCode;
            int tempAmount;
            double tempPrice;
            String tempName;
            String tempUnity;
            Supplier tempSupplier;

            if (autoFill) {
                tempCode = stock.getStockItems().size() + 1;
            } else {
                tempCode = Integer.parseInt(tfCode.getText());
            }

            try {
                tempAmount = Integer.parseInt(tfAmount.getText());
                tempPrice = Double.parseDouble(tfPrice.getText());
                tempName = tfName.getText();
                tempUnity = tfUnity.getText();
                tempSupplier = stock.searchSupplier(cbSupplier.getSelectedIndex() + 1);

                Product tempProduct = stock.searchInStock(tempCode);
                if (tempProduct != null) {
                    tempProduct.setName(tempName);
                    tempProduct.setAmountInStock(tempAmount);
                    tempProduct.setPriceUnity(tempPrice);
                    tempProduct.setTypeUnity(tempUnity);
                    tempProduct.setCode(tempCode);
                    tempProduct.setSupplier(tempSupplier);
                    stock.sortStockItems();
                    saveStockBin();
                    System.out.println("Stock updated\n");
                } else {
                    tempProduct = new Product(
                            tempName,
                            tempCode,
                            tempUnity,
                            tempPrice,
                            tempAmount,
                            tempSupplier
                    );
                    stock.addProduct(tempProduct);
                    stock.sortStockItems();
                    saveStockBin();
                    System.out.printf("Stock updated\n");
                }

            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(Main.this, "Error: Invalid values");
            }
        });
        btnSearch.addActionListener(e -> {
            // No search criteria selected
            if (typeSearch == 0) {
                JOptionPane.showMessageDialog(Main.this, "Error: No search criteria selected");
            }
            // Search product in stock by name
            else if (typeSearch == 1) {
                String tempName = tfSearch.getText();
                Product tempProduct = stock.searchByName(tempName);
                // Found a product with the same name
                if (tempProduct != null) {
                    tfName.setText(String.valueOf(tempProduct.getName()));
                    tfCode.setText(String.valueOf(tempProduct.getCode()));
                    tfUnity.setText(String.valueOf(tempProduct.getTypeUnity()));
                    tfPrice.setText(String.valueOf(tempProduct.getPriceUnity()));
                    tfAmount.setText(String.valueOf(tempProduct.getAmountInStock()));
                    cbSupplier.setSelectedItem(tempProduct.getSupplier());
                }
                // Did not find a product with the same name
                else {
                    JOptionPane.showMessageDialog(Main.this, "Error: Product not found in stock");
                }
            }
            // Search product in stock by code
            else if (typeSearch == 2) {
                try {
                    int tempInt = Integer.parseInt(tfSearch.getText());
                    Product tempProduct = stock.searchInStock(tempInt);
                    // Found a product with the same code
                    if (tempProduct != null) {
                        tfName.setText(String.valueOf(tempProduct.getName()));
                        tfCode.setText(String.valueOf(tempProduct.getCode()));
                        tfUnity.setText(String.valueOf(tempProduct.getTypeUnity()));
                        tfPrice.setText(String.valueOf(tempProduct.getPriceUnity()));
                        tfAmount.setText(String.valueOf(tempProduct.getAmountInStock()));
                        cbSupplier.setSelectedItem(tempProduct.getSupplier());
                    }
                    // Did not find a product with the same code
                    else {
                        JOptionPane.showMessageDialog(Main.this, "Error: Product not found in stock");
                    }
                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(Main.this, "Error: Invalid code provided (only integers are allowed)");
                }
            }
            // No idea how could this be raised, but who knows
            else {
                JOptionPane.showMessageDialog(Main.this, "Error: Something unexpected happened");
            }
        });
        btnDelete.addActionListener(e -> {
            try {
                int tempCode = Integer.parseInt(tfCode.getText());
                Product tempProduct = stock.searchInStock(tempCode);
                if (tempProduct != null) {
                    stock.removeFromStock(tempProduct);
                    stock.sortStockItems();
                    saveStockBin();
                } else {
                    JOptionPane.showMessageDialog(Main.this, "Error: Product does not exist in the stock");
                }
            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(Main.this, "Error: Invalid values");
            }
        });
        btnManageSuppliers.addActionListener(e -> new SuppliersPanel(Main.this));
        btnSortCode.addActionListener(e -> {
            stock.sortStockItemsByCode();
            saveStockBin();
        });
        btnSortName.addActionListener(e -> {
            stock.sortStockItems();
            saveStockBin();
        });
        btnProductDescription.addActionListener(e -> new ProductDescription(Main.this));
        autoFillCheckBox.addActionListener(e -> {
                    if (!autoFill) {
                        autoFill = true;
                        tfCode.setEditable(false);
                        tfCode.setText("");
                        System.out.println("autoFill = " + autoFill);
                    } else {
                        autoFill = false;
                        tfCode.setEditable(true);
                        System.out.println("autoFill = " + autoFill);
                    }
                }
        );
        checkName.addActionListener(e -> {
            typeSearch = 1;
            checkCode.setEnabled(!checkCode.isEnabled());
            if (checkCode.isEnabled()) {
                typeSearch = 0;
            }
        });
        checkCode.addActionListener(e -> {
            typeSearch = 2;
            checkName.setEnabled(!checkName.isEnabled());
            if (checkName.isEnabled()) {
                typeSearch = 0;
            }
        });
    }

    public static void main(String[] args) {
        new Main();
    }
}
