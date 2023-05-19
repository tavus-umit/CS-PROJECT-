package Bilkay.mainDashBoardScreens;

import Bilkay.UserRelatedServices.user;

import javax.swing.*;

public class mainHomeMenu {

    private final user currentUser;
    private  JPanel mainPanelForMenu;
    private JScrollPane mainJScrollPane;
    private JPanel mainJPanelForScroll;
    private final JFrame myMainFrame;

    public mainHomeMenu(JFrame myMainFrame, user currentUser) {
        this.currentUser = currentUser;
        this.myMainFrame = myMainFrame;
    }

    public JPanel getMainPanelForMenu() {
        return mainPanelForMenu;
    }

}
