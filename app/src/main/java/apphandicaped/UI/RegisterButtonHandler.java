package apphandicaped.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import apphandicaped.controller.*;

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



   
   public RegisterButtonHandler(JButton registerJButton, RegisterInterface originalJPanel) throws SQLException {
      this.registerJButton = registerJButton;
      registerJButton.addActionListener(new ActionListener() {
         Controller controller = Controller.getInstance();
         MainInterfaceController MIC = MainInterfaceController.getInstance();
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
            try {
            Controller.RegisterResult rResult = controller.Register(FirstName, LastName, Password, PasswordVerification);
            switch (rResult) {
               case Registered:
               JOptionPane.showInternalMessageDialog(MIC.frame, "Register succesful, please login to access our services");
                  
                  break;
               case wrongpassword:
               JOptionPane.showInternalMessageDialog(MIC.frame, "The first password you typed does not correspond to the first");
                  
                  break;
               case AlreadyExist:
               JOptionPane.showInternalMessageDialog(MIC.frame, "User already exists");
                  
                  break;
               default:
                  break;
            }
            } catch (Exception e) {
               e.printStackTrace();
               JOptionPane.showInternalMessageDialog(MIC.frame, "A problem occured while registering your account");
            }

               System.out.println("Register info : " + "Name : " + FirstName + "LastName : " + LastName+  "Mail : " + Email);
               System.out.println("Password : " + Arrays.toString(Password));
               System.out.println("PasswordVerification : " +Arrays.toString(PasswordVerification));
               System.out.println("Status : " + Status);


         }
      });
   }

}
