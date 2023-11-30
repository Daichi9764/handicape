package apphandicaped.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

public class MissionInterface extends JFrame {
    private JTable requestsTable;

    public MissionInterface() {
        super("Mission Interface");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Simulons des données de demande (à remplacer par vos propres données)
        String[] columnNames = {"RequestID", "Description", "Date", "State", "Comment"};
        Object[][] data = {
                {1, "Task 1", "2023-01-01", "Pending", "Comment 1"},
                {2, "Task 2", "2023-01-02", "Approved", "Comment 2"},
                {3, "Task 3", "2023-01-03", "Completed", "Comment 3"}
        };

        // Créez un modèle de tableau
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        requestsTable = new JTable(tableModel);

        // Ajoutez la table à un JScrollPane pour permettre le défilement
        JScrollPane scrollPane = new JScrollPane(requestsTable);

        // Configurez le gestionnaire de disposition
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        // Configurez la taille de la fenêtre
        setSize(600, 400);

        // Centrez la fenêtre sur l'écran
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MissionInterface missionInterface = new MissionInterface();
            missionInterface.setVisible(true);
        });
    }
}
