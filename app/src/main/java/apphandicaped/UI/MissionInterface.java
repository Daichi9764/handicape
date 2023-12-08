package apphandicaped.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

import apphandicaped.Database.InterfaceMySQL;

import java.awt.*;
import java.util.Date;  
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class MissionInterface extends JPanel {
    private static JTable requestsTable;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
    Date now = new Date();
    public MissionInterface() {
        String[] columnNames = {"RequestID", "Description", "Date", "State", "Comment", "Help"};
        DefaultTableModel tableModel = new DefaultTableModel(null, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5; // Rend la colonne "Help" éditable
            }
        };

        requestsTable = new JTable(tableModel);

        // Personnalisez le rendu et l'éditeur de la colonne "Help"
        TableColumn helpColumn = requestsTable.getColumnModel().getColumn(5);
        helpColumn.setCellRenderer(new ButtonRenderer());
        helpColumn.setCellEditor(new ButtonEditor(new JCheckBox()));

        JScrollPane scrollPane = new JScrollPane(requestsTable);

        requestsTable.setRowHeight(40);
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        // Chargez les données depuis la base de données
        loadTableData();
    }

    private void loadTableData() {
        try {
            Connection connection = InterfaceMySQL.Connect();
            String query = "SELECT RequestsID, RequestStatus FROM Requests";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    int requestID = resultSet.getInt("RequestsID");
                    String requestStatus = resultSet.getString("RequestStatus");

                    DefaultTableModel model = (DefaultTableModel) requestsTable.getModel();
                    Vector<Object> row = new Vector<>();
                    row.add(requestID);
                    row.add("Pas de Description"); // Ajoutez une cellule vide pour la colonne "Description"
                    row.add(formatter.format(now)); // Ajoutez une cellule vide pour la colonne "Date"
                    row.add(requestStatus);
                    row.add("Pas de commentaire"); // Ajoutez une cellule vide pour la colonne "Comment"
                    row.add(""); // Ajoutez une cellule vide pour la colonne "Help"
                    model.addRow(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static boolean tableDisabled = false;

    // Classe pour le rendu du bouton
    static class ButtonRenderer extends DefaultTableCellRenderer {
        
        private JButton button;

        public ButtonRenderer() {
            button = new JButton("Help");
            button.setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return button;
        }
    }

// Classe pour l'édition du bouton
static class ButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
    private JButton button;
    private boolean clicked;
    private JTable table;

    public ButtonEditor(JCheckBox checkBox) {
        button = new JButton("Help");
        button.addActionListener(this);
        button.setOpaque(true);
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.table = table;
        clicked = false;
        return button;
    }

    public Object getCellEditorValue() {
        return clicked ? "Proceeding" : "Help";
    }

    public void actionPerformed(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1 && !clicked) {
            // Modifiez le texte de la cellule lorsqu'on clique sur le bouton
            clicked = true;
            button.setText("Proceeding");
            button.setEnabled(false);  // Désactivez le bouton après le clic
            fireEditingStopped();
            disableHelpColumn();    // Désactivez la JTable
        }
        button.setEnabled(false);
    }
}
private static void disableHelpColumn() {
    // Supprimez l'éditeur de la cellule de la colonne "Help"
    requestsTable.getColumnModel().getColumn(5).setCellEditor(null);
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Mission Interface");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            MissionInterface missionInterface = new MissionInterface();
            frame.add(missionInterface);

            frame.setSize(1400, 800);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
