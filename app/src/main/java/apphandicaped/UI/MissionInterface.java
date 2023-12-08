package apphandicaped.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

import apphandicaped.Database.InterfaceMySQL;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class MissionInterface extends JPanel {
    private JTable requestsTable;
   LocalDateTime now = LocalDateTime.now();  
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
                    row.add(now.toString()); // Ajoutez une cellule vide pour la colonne "Date"
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
    private JTable table;

    // Utilisez un ensemble pour stocker les indices des lignes avec des boutons déjà cliqués
    private Set<Integer> clickedRows = new HashSet<>();

    public ButtonEditor(JCheckBox checkBox) {
        button = new JButton("Help");
        button.addActionListener(this);
        button.setOpaque(true);
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.table = table;
        // Vérifiez si le bouton a déjà été cliqué pour cette ligne
        if (clickedRows.contains(row)) {
            button.setEnabled(false);  // Si déjà cliqué, désactivez le bouton
        } else {
            button.setEnabled(true);   // Sinon, activez le bouton
        }
        return button;
    }

    public Object getCellEditorValue() {
        return "Proceeding"; // Peu importe la valeur retournée, car le bouton est désactivé après le clic
    }

    public void actionPerformed(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1 && !clickedRows.contains(selectedRow)) {
            // Modifiez le texte de la cellule lorsqu'on clique sur le bouton
            table.setValueAt("Proceeding", selectedRow, 5);
            // Ajoutez l'indice de la ligne à l'ensemble des lignes cliquées
            clickedRows.add(selectedRow);
            button.setEnabled(false);  // Désactivez le bouton après le clic
        }
        fireEditingStopped();
    }
}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Mission Interface");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            MissionInterface missionInterface = new MissionInterface();
            frame.add(missionInterface);

            frame.setSize(700, 400);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
