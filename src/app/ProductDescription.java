package app;

import universal_stock_management.Product;

import javax.swing.*;

public class ProductDescription extends JFrame {
    private JPanel ProductDescriptionPanel;
    private JLabel lbProductName;
    private JLabel lbProductUnity;
    private JLabel lbProductCode;
    private JLabel lbProductPrice;
    private JLabel lbProductAmount;
    private JLabel lbSupplierTitle;
    private JLabel lbProductTitle;
    private JLabel lbSupplierName;
    private JLabel lbSupplierAddress;
    private JLabel lbSupplierCnpj;
    private JLabel lbProductCodeData;
    private JLabel lbProductNameData;
    private JLabel lbProductPriceData;
    private JLabel lbProductUnityData;
    private JLabel lbProductAmountData;
    private JLabel lbSupplierCodeData;
    private JLabel lbSupplierNameData;
    private JLabel lbSupplierAddressData;
    private JLabel lbSupplierCnpjData;

    private void setProductValues(Product product) {
        lbProductCodeData.setText(String.valueOf(product.getCode()));
        lbProductNameData.setText(product.getName());
        lbProductPriceData.setText(String.valueOf(product.getPriceUnity()));
        lbProductUnityData.setText(product.getTypeUnity());
        lbProductAmountData.setText(String.valueOf(product.getAmountInStock()));

        lbSupplierCodeData.setText(String.valueOf(product.getSupplier().getCode()));
        lbSupplierNameData.setText(String.valueOf(product.getSupplier().getName()));
        lbSupplierAddressData.setText(String.valueOf(product.getSupplier().getAddress()));
        lbSupplierCnpjData.setText(String.valueOf(product.getSupplier().getCnpj()));
    }

    public ProductDescription(Main origin) {
        try {
            Product tempProduct = origin.getListProducts().getSelectedValue();
            setProductValues(tempProduct);

            setContentPane(ProductDescriptionPanel);
            setTitle("Product " + tempProduct.getCode() + " description");
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setSize(400, 400);
            setResizable(false);
            setLocationRelativeTo(null);
            setVisible(true);
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(ProductDescription.this, "Error: No product selected");
        }
    }
}
