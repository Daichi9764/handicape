package apphandicaped.UI;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*; 


//class MainFrame a utiliser en tant que seule frame de app. (pour centraliser les pannels autour de une seule frame)
public class MainFrame extends JFrame {
    public MainFrame(){
        setTitle("MainFrame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,500);
        setVisible(true);

        
    }

    public void changePanel(JPanel destPanel){
        getContentPane().removeAll();
        add(destPanel);
        //reloaod frame
        invalidate();
        repaint();
        setVisible(true);
    }
}
