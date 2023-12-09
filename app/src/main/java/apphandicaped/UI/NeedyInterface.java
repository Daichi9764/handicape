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

public class NeedyInterface extends JPanel {
    private static JTable requestsTable;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date now = new Date();
    private Timer timer;
    private int CurrentNeedyID;

    public NeedyInterface(int CurrentNeedyID) {
        this.CurrentNeedyID = CurrentNeedyID;

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

        // Ajoutez le bouton "Create Mission" en bas du centre
        JButton createMissionButton = new JButton("Create Mission");
        createMissionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               CreateRequest.main(null,CurrentNeedyID);
            }
        });
        add(createMissionButton, BorderLayout.SOUTH);

        // Initialisez le Timer pour rafraîchir toutes les 3 secondes
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTableData();
            }
        });
        timer.start();
    }

    public void loadTableData() {
        try {
            Connection connection = InterfaceMySQL.Connect();
            String query = "SELECT RequestsID, RequestStatus, RequestDate, Description, Commentaire FROM Requests";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    int requestID = resultSet.getInt("RequestsID");
                    String requestStatus = resultSet.getString("RequestStatus");
                    java.sql.Date RequestDate = resultSet.getDate("RequestDate");
                    String Description = resultSet.getString("Description");
                    String Commentaire = resultSet.getString("Commentaire");

                    DefaultTableModel model = (DefaultTableModel) requestsTable.getModel();
                    Vector<Object> row = new Vector<>();                        row.add(requestID);
                    row.add(Description);
                    row.add(RequestDate);
                    row.add(requestStatus);
                    if(requestStatus.equals("INPROGRESS")) row.add("Requete Valide");
                    else row.add("Pas de Commentaire");
                    model.addRow(row);


                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void refreshTableData() {
        DefaultTableModel model = (DefaultTableModel) requestsTable.getModel();
        model.setRowCount(0); // Effacez toutes les lignes existantes dans le modèle

        // Chargez les nouvelles données depuis la base de données
        loadTableData();
    }

    public static void main(String[] args,int NeedyID) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Needy Interface");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            NeedyInterface needyInterface = new NeedyInterface(NeedyID);
            frame.add(needyInterface);

            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
