package apphandicaped.UI;

import javax.swing.*;

import apphandicaped.Database.InterfaceMySQL;
import apphandicaped.Database.Request.RequestStatus;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateRequest extends JFrame {

    private JTextField descriptionTextField;

    public CreateRequest(int Needyid) {
        setTitle("Create Request");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Panel pour les champs de texte
        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        inputPanel.add(new JLabel("Description:"));
        descriptionTextField = new JTextField();
        inputPanel.add(descriptionTextField);

        // Panel pour les boutons
        JPanel buttonPanel = new JPanel();
        JButton createButton = new JButton("Create");
        JButton dropButton = new JButton("Drop");

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String description = descriptionTextField.getText();
                    InterfaceMySQL.addRequest(RequestStatus.PENDING, Needyid,description);
                    
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                dispose();
            }
        });

        dropButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Ferme la fenêtre sans créer de requête
            }
        });

        buttonPanel.add(createButton);
        buttonPanel.add(dropButton);

        // Ajout des panneaux à la fenêtre principale
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args,int NeedyId) {
        SwingUtilities.invokeLater(() -> {
            CreateRequest createRequestInterface = new CreateRequest(NeedyId);
            createRequestInterface.setVisible(true);
        });
    }
}
