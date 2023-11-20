package apphandicaped.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class RegisterButtonHandler {
   JButton registerJButton;
   private String LastName;
   private String FirstName;
   private String Email;
   private char[] Password;
   private char[] PasswordVerification;
   private enum StatusEnum {
      NEEDY, HELPER, MEDIC
   } 
   private StatusEnum Status;



   
   public RegisterButtonHandler(JButton registerJButton, RegisterPanel originalJPanel) {
      this.registerJButton = registerJButton;
      registerJButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent arg0) {
            FirstName=originalJPanel.FirstNameField.getText();
            LastName=originalJPanel.LastNameField.getText();
            Email=originalJPanel.EmailField.getText();
            Password=originalJPanel.PasswordField.getPassword();
            PasswordVerification=originalJPanel.PasswordVerificationField.getPassword();
            
            if (originalJPanel.NeedyCheckBox.isSelected()){
               Status=StatusEnum.NEEDY;
            }
            if (originalJPanel.VolunteerCheckBox.isSelected()){
               Status=StatusEnum.HELPER;
            }
            if (originalJPanel.MedicCheckBox.isSelected()){
               Status=StatusEnum.MEDIC;
            }

               System.out.println("Register info : " + FirstName + LastName+ Email);
               System.out.println("Password : " + Password.toString());
               System.out.println("PasswordVerification : " + PasswordVerification.toString());
               System.out.println("Status : " + Status);

         }
      });
   }

}
