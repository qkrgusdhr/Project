import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Main extends JFrame {
    private DefaultTableModel tableModel;
    private JButton prevButton;
    private JButton nextButton;
    private JLabel currentPageLabel;
    private int currentPage;
    private int itemsPerPage;

    public Main() {
        super("Pagination Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Create table
        String[] columnNames = {"ID", "Name", "Description"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);

        // Create pagination controls
        JPanel paginationPanel = new JPanel();
        prevButton = new JButton("Prev");
        nextButton = new JButton("Next");
        currentPageLabel = new JLabel("Page: ");
        paginationPanel.add(prevButton);
        paginationPanel.add(currentPageLabel);
        paginationPanel.add(nextButton);

        // Add components to frame
        getContentPane().add(new JScrollPane(table));
        getContentPane().add(paginationPanel, BorderLayout.SOUTH);

        // Initialize page variables
        currentPage = 1;
        itemsPerPage = 10;

        // Populate table with initial data
        populateTable(currentPage, itemsPerPage);

        // Add action listeners to pagination buttons
        prevButton.addActionListener(e -> {
            if (currentPage > 1) {
                currentPage--;
                populateTable(currentPage, itemsPerPage);
            }
        });

        nextButton.addActionListener(e -> {
            currentPage++;
            populateTable(currentPage, itemsPerPage);
        });
    }

    private void populateTable(int currentPage, int itemsPerPage) {
        // Clear table
        tableModel.setRowCount(0);

        // Fetch data from database based on currentPage and itemsPerPage
        // Here, you would fetch data from your database
        // For this example, we'll just generate some dummy data
        for (int i = 0; i < itemsPerPage; i++) {
            // Calculate item index based on currentPage and itemsPerPage
            int itemIndex = (currentPage - 1) * itemsPerPage + i;

            // Add dummy data to table
            tableModel.addRow(new Object[]{itemIndex + 1, "Item " + (itemIndex + 1), "Description " + (itemIndex + 1)});
        }

        // Update current page label
        currentPageLabel.setText("Page: " + currentPage);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main example = new Main();
            example.setVisible(true);
        });
    }
}