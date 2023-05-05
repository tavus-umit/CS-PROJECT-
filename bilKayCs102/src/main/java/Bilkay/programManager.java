package Bilkay;
import javax.swing.*;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

public class programManager {

    public static void main(String[] args){
        FlatMacDarkLaf.setup();
        JFrame mainFrame = new JFrame("BilKay");
        mainFrame.setSize(1240,768);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.getContentPane().add(new mainLoginMenu().getMainPanelForMenu());
        mainFrame.setVisible(true);

    }




}
