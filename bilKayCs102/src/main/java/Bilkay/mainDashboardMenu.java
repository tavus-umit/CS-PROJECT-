package Bilkay;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class mainDashboardMenu {

    private final JFrame myMainFrame;
    private JPanel mainPanelForMenu;
    private JPanel leftSideMenuBackgroundPanel;
    private JPanel rightMainDashboardPanel;
    private JPanel leftSideMenuForegroundPanel;
    private JButton homeButton;
    private JPanel topUserPanel;
    private JPanel bottomMenuPanel;
    private JButton userPP;
    private JLabel nameOfTheUserLabel;
    private JLabel pointsOfTheUserLabel;
    private JSeparator seperatorJseperator;
    private JButton settingsButton;
    private JButton gymButton;
    private JButton webButton;
    private JButton peopleButton;
    private JButton mailButton;
    private JButton bilUberButton;

    private user currentUser;

    public mainDashboardMenu(JFrame myMainFrameInput, user currentUser) {
        this.myMainFrame = myMainFrameInput;
        this.currentUser = currentUser;


        ImageIcon iconPP = new ImageIcon(new ImageIcon("C:\\Users\\emirh\\OneDrive\\Masaüstü\\CS-PROJECT-\\bilKayCs102\\src\\main\\resources\\test.jpeg").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
        userPP.setIcon(iconPP);


        nameOfTheUserLabel.setText(currentUser.getNameSurname());
        pointsOfTheUserLabel.setText(String.valueOf(currentUser.getBilkayPoints()));


    }

    public JPanel getMainPanelForMenu() {
        return mainPanelForMenu;
    }


}
