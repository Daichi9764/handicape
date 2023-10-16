package apphandicaped.UI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class RegisterInterface extends JPanel {
   private JTextField FirstNameFIeld;
   private JTextField LastNameField;
   private JTextField EmailField;
   private JPasswordField PasswordField;
   private JLabel jcomp5;
   private JLabel jcomp6;
   private JLabel jcomp7;
   private JLabel jcomp8;
   private JLabel jcomp9;
   private JTextField PasswordVerificationField;
   private JRadioButton NeedyCheckBox;
   private JLabel jcomp12;
   private JRadioButton VolunteerCheckBox;
   private JRadioButton MedicCheckBox;
   private JButton registerButton;

   public RegisterInterface() {

      // construct components
      FirstNameFIeld = new JTextField(5);
      LastNameField = new JTextField(5);
      EmailField = new JTextField(5);
      PasswordField = new JPasswordField(5);
      jcomp5 = new JLabel("First Name");
      jcomp6 = new JLabel("Last Name");
      jcomp7 = new JLabel("Email");
      jcomp8 = new JLabel("Password");
      jcomp9 = new JLabel("Repeat Password");
      PasswordVerificationField = new JTextField(5);
      NeedyCheckBox = new JRadioButton("I Need Help");
      jcomp12 = new JLabel("Status");
      VolunteerCheckBox = new JRadioButton("I which to volunteer");
      MedicCheckBox = new JRadioButton("I am a health worker");
      registerButton = new JButton("Register");
      ButtonGroup buttonGroup = new ButtonGroup();
      buttonGroup.add(VolunteerCheckBox); buttonGroup.add(MedicCheckBox);buttonGroup.add(NeedyCheckBox);

      // set components properties
      FirstNameFIeld.setToolTipText("First Name");
      LastNameField.setToolTipText("Last Name");
      EmailField.setToolTipText("Email");
      PasswordField.setToolTipText("Password");
      PasswordVerificationField.setToolTipText("Repeat Password");

      // adjust size and set layout
      setPreferredSize(new Dimension(450, 602));
      setLayout(null);
      add(registerButton);

      // add components
      add(FirstNameFIeld);
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
      FirstNameFIeld.setBounds(165, 115, 100, 25);
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

   public static void main(String[] args) {
      JFrame frame = new JFrame("MyPanel");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(new RegisterInterface());
      frame.pack();
      frame.setVisible(true);
   }
}
