package Bilkay;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import javax.swing.*;

public class mainFrame {


    public mainFrame() {

        FlatMacDarkLaf.setup();
        JFrame mainFrameOfApp = new JFrame("BilKay");
        mainFrameOfApp.setSize(1240,768);
        mainFrameOfApp.setLocationRelativeTo(null);
        mainFrameOfApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrameOfApp.getContentPane().add(new mainLoginMenu(mainFrameOfApp).getMainPanelForMenu());
        mainFrameOfApp.setVisible(true);
    }
}
