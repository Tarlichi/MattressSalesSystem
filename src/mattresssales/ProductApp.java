package mattresssales;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.GridLayout;

public class ProductApp extends JFrame {
    private JTextField txtCustomerName, txtPrice;
    private JComboBox<String> cmbProduct;
    private JButton btnProcess;

    public ProductApp() {
        // Window Setup
        setTitle("Product App");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 20));
        setLocationRelativeTo(null); // Centers the window

        // Menu Setup
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem processItem = new JMenuItem("Process");
        JMenuItem clearItem = new JMenuItem("Clear");
        toolsMenu.add(processItem);
        toolsMenu.add(clearItem);
        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        setJMenuBar(menuBar);

        // Form Components
        add(new JLabel("  Customer Name:"));
        txtCustomerName = new JTextField();
        add(txtCustomerName);

        add(new JLabel("  Select Product:"));
        String[] products = {"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"};
        cmbProduct = new JComboBox<>(products);
        add(cmbProduct);

        add(new JLabel("  Enter Price:"));
        txtPrice = new JTextField();
        add(txtPrice);

        add(new JLabel("")); 
        btnProcess = new JButton("PROCESS");
        add(btnProcess);

        // Button & Menu Actions
        btnProcess.addActionListener(e -> processSale());
        processItem.addActionListener(e -> processSale());
        clearItem.addActionListener(e -> {
            txtCustomerName.setText("");
            txtPrice.setText("");
            cmbProduct.setSelectedIndex(0);
        });
    }

    private void processSale() {
        try {
            String name = txtCustomerName.getText();
            String product = (String) cmbProduct.getSelectedItem();
            double price = Double.parseDouble(txtPrice.getText());

            // Call the calculation class we made earlier
            ProductCalculations calc = new ProductCalculations(price);
            double vat = calc.GetVat();
            double commission = calc.GetCommission();
            double total = calc.GetTotal();

            // Show Message Popup
            String message = String.format("Customer Name: %s\nProduct: %s\nVAT: R %.1f\nCOMMISSION: R %.1f\nTOTAL: R %.1f",
                    name, product, vat, commission, total);
            JOptionPane.showMessageDialog(this, message, "Message", JOptionPane.INFORMATION_MESSAGE);

            // Save to file
            saveToFile(name, product, vat, commission, total);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for price.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveToFile(String name, String product, double vat, double commission, double total) {
        try (PrintWriter out = new PrintWriter(new FileWriter("data.txt", true))) {
            out.println("DATA LOG");
            out.println("*********************************");
            out.println("Customer Name: " + name);
            out.println("Product: " + product);
            out.println("VAT: R " + vat);
            out.println("COMMISSION: R " + commission);
            out.println("TOTAL: R " + total);
            out.println("*********************************\n");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
        }
    }

    // Main method to launch the GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ProductApp().setVisible(true);
        });
    }
}