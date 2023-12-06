package apphandicaped.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class AddRequestButtonHandler {

    JButton requestJButton;


    public AddRequestButtonHandler(JButton requestJButton, AddRequestInterface addRequestInterface) {
      this.requestJButton = requestJButton;
      requestJButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent arg0) {
            System.out.println("RequestButtonPressed");
         }
      });
    }
    
}
    