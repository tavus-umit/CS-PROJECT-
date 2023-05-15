package Bilkay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JPanel homeMenuPanel;
    private JPanel profileMenuPanel;
    private JPanel mathcesMenuPanel;
    private JPanel eventsMenuPanel;
    private JPanel biluberMenuPanel;
    private JPanel emailMenuPanel;
    private JPanel gymMenuPanel;
    private JPanel settingsMenuPanel;

    private user currentUser;

    public mainDashboardMenu(JFrame myMainFrameInput, user currentUser) {
        this.myMainFrame = myMainFrameInput;
        this.currentUser = currentUser;


        ImageIcon iconPP = new ImageIcon(new ImageIcon("C:\\Users\\emirh\\OneDrive\\Masaüstü\\CS-PROJECT-\\bilKayCs102\\src\\main\\resources\\test.jpeg").getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
        userPP.setIcon(iconPP);


        nameOfTheUserLabel.setText(currentUser.getNameSurname());
        pointsOfTheUserLabel.setText(String.valueOf(currentUser.getBilkayPoints()));


        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeButton.setSelected(true);
                settingsButton.setSelected(false);
                gymButton.setSelected(false);
                webButton.setSelected(false);
                peopleButton.setSelected(false);
                mailButton.setSelected(false);
                bilUberButton.setSelected(false);
                userPP.setSelected(false);

                rightMainDashboardPanel.removeAll();
                rightMainDashboardPanel.add(homeMenuPanel);
                myMainFrame.revalidate();
                myMainFrame.repaint();

            }
        });
        peopleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeButton.setSelected(false);
                settingsButton.setSelected(false);
                gymButton.setSelected(false);
                webButton.setSelected(false);
                peopleButton.setSelected(true);
                mailButton.setSelected(false);
                bilUberButton.setSelected(false);
                userPP.setSelected(false);

                rightMainDashboardPanel.removeAll();
                rightMainDashboardPanel.add(mathcesMenuPanel);
                myMainFrame.revalidate();
                myMainFrame.repaint();
            }
        });
        webButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeButton.setSelected(false);
                settingsButton.setSelected(false);
                gymButton.setSelected(false);
                webButton.setSelected(true);
                peopleButton.setSelected(false);
                mailButton.setSelected(false);
                bilUberButton.setSelected(false);
                userPP.setSelected(false);

                rightMainDashboardPanel.removeAll();
                rightMainDashboardPanel.add(eventsMenuPanel);
                myMainFrame.revalidate();
                myMainFrame.repaint();
            }
        });
        bilUberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeButton.setSelected(false);
                settingsButton.setSelected(false);
                gymButton.setSelected(false);
                webButton.setSelected(false);
                peopleButton.setSelected(false);
                mailButton.setSelected(false);
                bilUberButton.setSelected(true);
                userPP.setSelected(false);

                rightMainDashboardPanel.removeAll();
                rightMainDashboardPanel.add(biluberMenuPanel);
                myMainFrame.revalidate();
                myMainFrame.repaint();
            }
        });
        mailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeButton.setSelected(false);
                settingsButton.setSelected(false);
                gymButton.setSelected(false);
                webButton.setSelected(false);
                peopleButton.setSelected(false);
                mailButton.setSelected(true);
                bilUberButton.setSelected(false);
                userPP.setSelected(false);

                rightMainDashboardPanel.removeAll();
                rightMainDashboardPanel.add(emailMenuPanel);
                myMainFrame.revalidate();
                myMainFrame.repaint();
            }
        });
        gymButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeButton.setSelected(false);
                settingsButton.setSelected(false);
                gymButton.setSelected(true);
                webButton.setSelected(false);
                peopleButton.setSelected(false);
                mailButton.setSelected(false);
                bilUberButton.setSelected(false);
                userPP.setSelected(false);

                rightMainDashboardPanel.removeAll();
                rightMainDashboardPanel.add(gymMenuPanel);
                myMainFrame.revalidate();
                myMainFrame.repaint();
            }
        });
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeButton.setSelected(false);
                settingsButton.setSelected(true);
                gymButton.setSelected(false);
                webButton.setSelected(false);
                peopleButton.setSelected(false);
                mailButton.setSelected(false);
                bilUberButton.setSelected(false);
                userPP.setSelected(false);

                rightMainDashboardPanel.removeAll();
                rightMainDashboardPanel.add(settingsMenuPanel);
                myMainFrame.revalidate();
                myMainFrame.repaint();
            }
        });
        userPP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                homeButton.setSelected(false);
                settingsButton.setSelected(false);
                gymButton.setSelected(false);
                webButton.setSelected(false);
                peopleButton.setSelected(false);
                mailButton.setSelected(false);
                bilUberButton.setSelected(false);
                userPP.setSelected(true);

                rightMainDashboardPanel.removeAll();
                rightMainDashboardPanel.add(profileMenuPanel);
                myMainFrame.revalidate();
                myMainFrame.repaint();
            }
        });
    }

    public JPanel getMainPanelForMenu() {
        return mainPanelForMenu;
    }


}
