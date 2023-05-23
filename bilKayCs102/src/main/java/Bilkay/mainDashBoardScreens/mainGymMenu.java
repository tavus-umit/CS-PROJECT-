package Bilkay.mainDashBoardScreens;

import Bilkay.UserRelatedServices.user;

import javax.swing.*;

public class mainGymMenu {
    private final user currentUser;
    private final JFrame myMainFrame;
    private JPanel mainPanelForMenu;
    private JLabel logoJPanel;
    private JScrollPane mainJScrollPane;
    private JPanel mainJPanelForScroll;
    private JPanel gymProgramPanel;
    private JLabel userIcon;
    private JPanel gymProgramJpanel;
    private JLabel day1Jlabel;
    private JSeparator upperSeperator;
    private JLabel day2Jlabel;
    private JLabel day3Jlabel;
    private JLabel day4JLabel;
    private JLabel day5Jlabel;
    private JButton newProgramButton;
    private JLabel d1e1;
    private JLabel d1e2;
    private JLabel d1e3;
    private JLabel d1e4;
    private JLabel d1e5;
    private JLabel d2e1;
    private JLabel d2e2;
    private JLabel d2e3;
    private JLabel d2e4;
    private JLabel d2e5;
    private JLabel d3e1;
    private JLabel d3e2;
    private JLabel d3e3;
    private JLabel d3e4;
    private JLabel d3e5;
    private JLabel d4e1;
    private JLabel d4e2;
    private JLabel d4e3;
    private JLabel d4e4;
    private JLabel d4e5;
    private JLabel d5e1;
    private JLabel d5e2;
    private JLabel d5e3;
    private JLabel d5e4;
    private JLabel d5e5;

    public mainGymMenu(JFrame myMainFrame, user currentUser) {
        this.currentUser = currentUser;
        this.myMainFrame = myMainFrame;
    }

    public JPanel getMainPanelForMenu() {
        return mainPanelForMenu;
    }
}
