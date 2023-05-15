package Bilkay;

import javax.swing.*;

public class mainSettingsMenu {
    private final user currentUser;
    private  JPanel mainPanelForMenu;
    private JScrollPane mainJScrollPane;
    private JPanel mainJPanelForScroll;
    private JButton button1;
    private JPanel testPanel;
    private final JFrame myMainFrame;

    public mainSettingsMenu(JFrame myMainFrame, user currentUser) {
        this.currentUser = currentUser;
        this.myMainFrame = myMainFrame;

    }

    public JPanel getMainPanelForMenu() {
        return mainPanelForMenu;
    }
}
