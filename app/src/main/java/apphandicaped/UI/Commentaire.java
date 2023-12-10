package apphandicaped.UI;

import javax.swing.*;

import apphandicaped.Database.InterfaceMySQL;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Commentaire extends JFrame {

    private JTextField descriptionTextField;

    public Commentaire(int requestID,String Status) {
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
                    InterfaceMySQL.addCommentaire(requestID, description, Status);
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                dispose();
            }
        });
        descriptionTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                            // Déclenchez l'action du bouton "Login" lorsque la touche "Entrée" est pressée
               okButton.doClick();
             }
          });
        mainPanel.add(okButton);

        // Ajout du panel principal à la fenêtre
        add(mainPanel);

        setLocationRelativeTo(null);
    }

    public static void main(String[] args, int RequestID,String Status) {
        SwingUtilities.invokeLater(() -> {
            Commentaire miniInterface = new Commentaire(RequestID,Status);
            miniInterface.setVisible(true);
        });
    }
}
