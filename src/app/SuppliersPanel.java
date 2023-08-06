package app;

import javax.swing.*;

import universal_stock_management.Stock;
import universal_stock_management.Supplier;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SuppliersPanel extends JFrame {
    // TODO complete the SuppliersPanel

    private JPanel SuppliersPanel;
    private JLabel lbName;
    private JLabel lbAddress;
    private JLabel lbCode;
    private JLabel lbCnpj;
    private JTextField tfName;
    private JTextField tfAddress;
    private JTextField tfCode;
    private JTextField tfCnpj;
    private JList listSuppliers;
    private JButton btnSave;
    private JButton btnRemove;
    private JLabel lbTitle;
    private JTextField tfSearch;
    private JButton btnSearch;
    private JLabel lbSearch;
    private JButton btnSortCode;
    private JButton sortByNameButton;

    public void saveStockBin(Main origin) {
        Stock stock = origin.getStock();
        String currentPath = System.getProperty("user.dir");
        String filePath = currentPath.concat("/file.bin");
        try (FileOutputStream fileStream = new FileOutputStream(filePath); ObjectOutputStream objectStream = new ObjectOutputStream(fileStream)) {
            // Write the object to the file
            objectStream.writeObject(stock);
            listSuppliers.setListData(origin.getStock().getArraySuppliers());
            System.out.println("Object saved successfully.");
        } catch (java.io.FileNotFoundException e) {
            System.out.println("Error: Could not find 'file.bin' in the following path\n" + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SuppliersPanel(Main origin) {
        setContentPane(SuppliersPanel);
        setTitle("Suppliers Management");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setResizable(true);
        setLocationRelativeTo(null);
        listSuppliers.setListData(origin.getStock().getArraySuppliers());
        setVisible(true);
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int tempCode = Integer.parseInt(tfCode.getText());
                    String tempName = tfName.getText();
                    String tempAddress = tfAddress.getText();
                    String tempCnpj = tfCnpj.getText();
                    Supplier tempSupplier = origin.getStock().searchSupplier(tempCode);
                    if (tempSupplier != null) {
                        tempSupplier.setCode(tempCode);
                        tempSupplier.setName(tempName);
                        tempSupplier.setAddress(tempAddress);
                        tempSupplier.setCnpj(tempCnpj);
                        origin.addToComboBox(tempSupplier);
                        saveStockBin(origin);
                        System.out.println("Suppliers updated\n");
                    } else {
                        tempSupplier = new Supplier(
                                tempName,
                                tempAddress,
                                tempCnpj,
                                tempCode
                        );
                        origin.getStock().addSupplier(tempSupplier);
                        origin.addToComboBox(tempSupplier);
                        saveStockBin(origin);
                        System.out.println("Suppliers updated\n");
                    }
                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(SuppliersPanel.this, "Error: Invalid values");
                }
            }
        });
        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int tempCode = Integer.parseInt(tfCode.getText());
                    Supplier tempSupplier = origin.getStock().searchSupplier(tempCode);
                    if (tempSupplier != null) {
                        origin.getStock().removeSupplier(tempSupplier);
                        origin.removeFromComboBox(tempSupplier);
                        saveStockBin(origin);
                    } else {
                        JOptionPane.showMessageDialog(SuppliersPanel.this, "Error: Supplier does not exist in the stock");
                    }
                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(SuppliersPanel.this, "Error: Invalid values");
                }
            }
        });
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int tempCode = Integer.parseInt(tfSearch.getText());
                    Supplier tempSupplier = origin.getStock().searchSupplier(tempCode);
                    if (tempSupplier != null) {
                        tfName.setText(String.valueOf(tempSupplier.getName()));
                        tfCode.setText(String.valueOf(tempCode));
                        tfAddress.setText(String.valueOf(tempSupplier.getAddress()));
                        tfCnpj.setText(String.valueOf(tempSupplier.getCnpj()));
                    } else {
                        JOptionPane.showMessageDialog(SuppliersPanel.this, "Error: Invalid supplier code provided");
                    }
                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(SuppliersPanel.this, "Error: Invalid values");
                }
            }
        });
        btnSortCode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                origin.getStock().sortSuppliersByCode();
                saveStockBin(origin);
            }
        });
        sortByNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                origin.getStock().sortSuppliers();
                saveStockBin(origin);
            }
        });
    }
}
