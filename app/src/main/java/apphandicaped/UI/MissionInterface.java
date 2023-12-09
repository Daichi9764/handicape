package apphandicaped.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import apphandicaped.Database.InterfaceMySQL;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class MissionInterface extends JPanel {
    private static JTable requestsTable;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date now = new Date();
    private int CurrentHelperID;
    private Timer timer;

    public MissionInterface(int CurrentHelperID) {
        this.CurrentHelperID = CurrentHelperID;

        String[] columnNames = {"RequestID", "Description", "Date", "State", "Comment"};
        DefaultTableModel tableModel = new DefaultTableModel(null, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Désactivez l'édition des cellules
            }
        };

        requestsTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(requestsTable);

        requestsTable.setRowHeight(40);
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        // Chargez les données depuis la base de données
        loadTableData();

        // Ajoutez un écouteur de clic à la table
        requestsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = requestsTable.rowAtPoint(e.getPoint());
                int col = requestsTable.columnAtPoint(e.getPoint());
                if (row >= 0 && col >= 0) {
                    // Affichez une popup pour la cellule sélectionnée
                    try {
                        showPopup(row, col);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        // Initialisez le Timer pour rafraîchir toutes les 3 secondes
        timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTableData();
            }
        });
        timer.start();
    }

    private void showPopup(int row, int col) throws SQLException {
        if (col == 0) { // Vérifiez si la colonne est celle des IDs de requête
            Object requestID = requestsTable.getValueAt(row, col);
            Object description = requestsTable.getValueAt(row, 1); // Colonne de description
            Object comment = requestsTable.getValueAt(row, 4); // Colonne de commentaire
    
            int choice = JOptionPane.showConfirmDialog(this,
                    "Voulez-vous accepter la mission avec l'ID de requête: " + requestID + "\n"
                            + "Description: " + description + "\n"
                            + "Commentaire: " + comment,
                    "Confirmation d'acceptation de mission",
                    JOptionPane.YES_NO_OPTION);
    
            if (choice == JOptionPane.YES_OPTION) {
                InterfaceMySQL.requestAccepted(CurrentHelperID,((Integer) requestID).intValue());
            }
        }
    }

    private void refreshTableData() {
        DefaultTableModel model = (DefaultTableModel) requestsTable.getModel();
        model.setRowCount(0); // Effacez toutes les lignes existantes dans le modèle

        // Chargez les nouvelles données depuis la base de données
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
                    row.add("Pas de Description");
                    row.add(formatter.format(now));
                    row.add(requestStatus);
                    row.add("Pas de commentaire");
                    model.addRow(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Mission Interface");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            MissionInterface missionInterface = new MissionInterface(10);
            frame.add(missionInterface);

            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
