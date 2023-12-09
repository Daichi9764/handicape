package apphandicaped.UI;

import javax.swing.*;

import apphandicaped.Database.InterfaceMySQL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Commentaire extends JFrame {

    private JTextField descriptionTextField;

    public Commentaire(int requestID) {
        setTitle("Commentaire");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Champ de texte
        descriptionTextField = new JTextField();
        mainPanel.add(descriptionTextField);

        // Bouton "Ok"
        JButton okButton = new JButton("Ok");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String description = descriptionTextField.getText();
                try {
                    InterfaceMySQL.addCommentaire(requestID, description);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                dispose();
            }
        });
        mainPanel.add(okButton);

        // Ajout du panel principal à la fenêtre
        add(mainPanel);

        setLocationRelativeTo(null);
    }

    public static void main(String[] args, int RequestID) {
        SwingUtilities.invokeLater(() -> {
            Commentaire miniInterface = new Commentaire(RequestID);
            miniInterface.setVisible(true);
        });
    }
}
