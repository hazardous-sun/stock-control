# Universal Stock Management

This project implements a basic stock management system in Java. It allows you to add, remove, search, and edit product and supplier information.

## File Structure

- Product.java: This file defines the `Product` class, which represents a product in the stock. It includes attributes such as name, code, unit type, price per unit, amount in stock, and supplier information.
- Stock.java: This file defines the `Stock` class, which manages the collection of products and suppliers. It provides methods for adding, removing, searching, and sorting products and suppliers.
- Supplier.java: This file defines the `Supplier` class, which represents a supplier of products for the stock. It includes attributes such as name, address, CNPJ (Brazilian national certificate of legal entity), and code.
- Main.java: This file is the main application class. It creates the user interface, interacts with the `Stock` class to manage products and suppliers, and handles data persistence using file I/O.
- ProductDescription.java: This file defines the `ProductDescription` class, which creates a window to display detailed information about a selected product.
- SuppliersPanel.java: This file defines the `SuppliersPanel` class, which creates a window to manage suppliers. It allows adding, removing, searching, and sorting suppliers.

## Dependencies

This project uses the following libraries:

- Java Swing for the graphical user interface

## Running the application

1. Compile the Java source files using a Java compiler. You can use a command like `javac -cp .:./lib/* Main.java Product.java Stock.java Supplier.java ProductDescription.java SuppliersPanel.java` (assuming the libraries are in a lib folder).
2. Run the application using the `java` command: `java -cp .:./lib/* Main`

## Features

- Add, remove, search, and edit product information (name, code, unit type, price per unit,
amount in stock, and supplier)
- Add, remove, search, and edit supplier information (name, address, CNPJ, and code)
- Sort products and suppliers by name or code
- Auto-generate product codes
- Load and save stock data from a file (`file.bin`)

## Additional Notes

- The CNPJ attribute in the Supplier class is specific to Brazil. You can modify it to fit the supplier identification standard in your region.
- The application uses a basic file I/O approach to store data persistently. Consider using a database for larger-scale applications.
