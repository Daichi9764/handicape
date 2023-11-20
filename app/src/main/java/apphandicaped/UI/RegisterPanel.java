package apphandicaped.UI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class RegisterPanel extends JPanel {
   protected JTextField FirstNameField;
   protected JTextField LastNameField;
   protected JTextField EmailField;
   protected JPasswordField PasswordField;
   protected JLabel jcomp5;
   protected JLabel jcomp6;
   protected JLabel jcomp7;
   protected JLabel jcomp8;
   protected JLabel jcomp9;
   protected JPasswordField PasswordVerificationField;
   protected JRadioButton NeedyCheckBox;
   protected JLabel jcomp12;
   protected JRadioButton VolunteerCheckBox;
   protected JRadioButton MedicCheckBox;
   protected JButton registerButton;
   protected ButtonGroup buttonGroup;
   private RegisterButtonHandler registerButtonHandler;

   public RegisterPanel() {

      // construct components
      FirstNameField = new JTextField(5);
      LastNameField = new JTextField(5);
      EmailField = new JTextField(5);
      PasswordField = new JPasswordField(5);
      jcomp5 = new JLabel("First Name");
      jcomp6 = new JLabel("Last Name");
      jcomp7 = new JLabel("Email");
      jcomp8 = new JLabel("Password");
      jcomp9 = new JLabel("Repeat Password");
      PasswordVerificationField = new JPasswordField(5);
      NeedyCheckBox = new JRadioButton("I Need Help");
      jcomp12 = new JLabel("Status");
      VolunteerCheckBox = new JRadioButton("I which to volunteer");
      MedicCheckBox = new JRadioButton("I am a health worker");
      registerButton = new JButton("Register");
      ButtonGroup buttonGroup = new ButtonGroup();
      buttonGroup.add(VolunteerCheckBox); buttonGroup.add(MedicCheckBox);buttonGroup.add(NeedyCheckBox);

      //Create the handler Class
      registerButtonHandler = new RegisterButtonHandler(registerButton, this);

      // set components properties
      FirstNameField.setToolTipText("First Name");
      LastNameField.setToolTipText("Last Name");
      EmailField.setToolTipText("Email");
      PasswordField.setToolTipText("Password");
      PasswordVerificationField.setToolTipText("Repeat Password");

      // adjust size and set layout
      setPreferredSize(new Dimension(450, 602));
      setLayout(null);
      add(registerButton);

      // add components
      add(FirstNameField);
      add(LastNameField);
      add(EmailField);
      add(PasswordField);
      add(jcomp5);
      add(jcomp6);
      add(jcomp7);
      add(jcomp8);
      add(jcomp9);
      add(PasswordVerificationField);
      add(NeedyCheckBox);
      add(jcomp12);
      add(VolunteerCheckBox);
      add(MedicCheckBox);

      // set component bounds (only needed by Absolute Positioning)
      FirstNameField.setBounds(165, 115, 100, 25);
      LastNameField.setBounds(165, 165, 100, 25);
      EmailField.setBounds(165, 220, 100, 25);
      PasswordField.setBounds(165, 270, 100, 25);
      jcomp5.setBounds(165, 90, 100, 25);
      jcomp6.setBounds(165, 140, 100, 25);
      jcomp7.setBounds(165, 190, 100, 25);
      jcomp8.setBounds(165, 245, 100, 25);
      jcomp9.setBounds(160, 295, 115, 25);
      PasswordVerificationField.setBounds(165, 320, 100, 25);
      NeedyCheckBox.setBounds(165, 370, 100, 25);
      jcomp12.setBounds(165, 345, 100, 25);
      VolunteerCheckBox.setBounds(165, 395, 145, 25);
      MedicCheckBox.setBounds(165, 420, 155, 25);
      registerButton.setBounds(165, 465, 100, 25);


   }

}
