package Bilkay;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class mainDashboardMenu {

    private final JFrame myMainFrame;
    private JPanel mainPanelForMenu;
    private JPanel leftSideMenuBackgroundPanel;
    private JPanel rightMainDashboardPanel;
    private JButton button2;
    private JPanel leftSideMenuForegroundPanel;
    private JButton buttonForThesubButton;
    private JPanel topUserPanel;
    private JPanel bottomMenuPanel;
    private JLabel userPP;
    private JLabel nameOfTheUserLabel;
    private JLabel pointsOfTheUserLabel;

    public mainDashboardMenu(JFrame myMainFrameInput) {
        this.myMainFrame = myMainFrameInput;
        leftSideMenuForegroundPanel.setMixingCutoutShape(new Rectangle2D.Double());


        ImageIcon iconPP = new ImageIcon(new ImageIcon("C:\\Users\\emirh\\OneDrive\\Masaüstü\\CS-PROJECT-\\bilKayCs102\\src\\main\\resources\\test.jpeg").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
        userPP.setIcon(iconPP);

        nameOfTheUserLabel.setText("Emirhan Kılıç");
        pointsOfTheUserLabel.setText("46 pnts");

    }

    public JPanel getMainPanelForMenu() {
        return mainPanelForMenu;
    }

}
