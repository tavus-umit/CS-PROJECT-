package Bilkay;

import javax.swing.*;

public class mainDashboardMenu {

    private final JFrame myMainFrame;
    private JPanel mainPanelForMenu;
    private JPanel leftSideMenuPanel;
    private JPanel rightMainDashboardPanel;
    private JButton button1;
    private JButton button2;

    public mainDashboardMenu(JFrame myMainFrameInput) {
        this.myMainFrame = myMainFrameInput;
    }

    public JPanel getMainPanelForMenu() {
        return mainPanelForMenu;
    }

}
