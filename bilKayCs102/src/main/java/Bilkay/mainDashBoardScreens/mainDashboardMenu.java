package Bilkay.mainDashBoardScreens;

import Bilkay.UserRelatedServices.user;
import Bilkay.UserRelatedServices.userProfileDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import static Bilkay.mainDashBoardScreens.mainBilUberMenu.getPictureFromUserIDMain;

public class mainDashboardMenu {

    private final JFrame myMainFrame;
    private final user currentUser;
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


    public mainDashboardMenu(JFrame myMainFrameInput, user currentUser) {
        this.myMainFrame = myMainFrameInput;
        this.currentUser = currentUser;

        rightMainDashboardPanel.removeAll();
        updateInfo();

        SwingWorker<Void, Void> updateUITask = new SwingWorker<Void, Void>() {
            @SuppressWarnings({"BusyWait", "InfiniteLoopStatement"})
            @Override
            protected Void doInBackground() throws Exception {
                while (true) {
                    updateInfo();
                    Thread.sleep(60000);
                }
            }
        };
        updateUITask.execute();

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
                updateInfo();

                rightMainDashboardPanel.removeAll();
                rightMainDashboardPanel.add(new mainHomeMenu(myMainFrame, currentUser).getMainPanelForMenu());

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
                updateInfo();


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
                updateInfo();

                rightMainDashboardPanel.removeAll();
                rightMainDashboardPanel.add(new mainEventsMenu(myMainFrame, currentUser).getMainPanelForMenu());

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
                updateInfo();

                rightMainDashboardPanel.removeAll();
                rightMainDashboardPanel.add(new mainBilUberMenu(myMainFrame, currentUser).getMainPanelForMenu());

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
                updateInfo();

                rightMainDashboardPanel.removeAll();
                rightMainDashboardPanel.add(new mainMailMenu(myMainFrame, currentUser).getMainPanelForMenu());

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
                updateInfo();

                rightMainDashboardPanel.removeAll();
                rightMainDashboardPanel.add(new mainGymMenu(myMainFrame, currentUser).getMainPanelForMenu());

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
                updateInfo();

                rightMainDashboardPanel.removeAll();
                rightMainDashboardPanel.add(new mainSettingsMenu(myMainFrame, currentUser).getMainPanelForMenu());
                myMainFrame.revalidate();
                myMainFrame.repaint();


            }
        });
        userPP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                userProfileDialog test = new userProfileDialog(myMainFrame,currentUser);

                updateInfo();


            }
        });
    }

    private String getPictureFromUserID(int inputID) throws SQLException {

        return getPictureFromUserIDMain(inputID);
    }

    public JPanel getMainPanelForMenu() {
        return mainPanelForMenu;
    }

    public void refreshPageForGym() {
        rightMainDashboardPanel.removeAll();
        rightMainDashboardPanel.add(new mainGymMenu(myMainFrame, currentUser).getMainPanelForMenu());
    }

    public void updateInfo() {
        rightMainDashboardPanel.add(new mainHomeMenu(myMainFrame, currentUser).getMainPanelForMenu());
        ImageIcon iconPP = null;
        try {
            iconPP = new ImageIcon(new ImageIcon(getPictureFromUserID(currentUser.getUserID())).getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        userPP.setIcon(iconPP);
        userPP.setHorizontalAlignment(SwingConstants.CENTER);
        userPP.setVerticalAlignment(SwingConstants.CENTER);
        nameOfTheUserLabel.setText(currentUser.getNameSurname());
        pointsOfTheUserLabel.setText(currentUser.getBilkayPoints() + " points");

    }


}
