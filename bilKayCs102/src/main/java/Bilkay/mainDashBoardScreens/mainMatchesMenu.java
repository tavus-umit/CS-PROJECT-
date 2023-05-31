package Bilkay.mainDashBoardScreens;

import Bilkay.BilUber.Lift;
import Bilkay.Email_Keyboard_DatabaseServices.DatabaseManager;
import Bilkay.Events.Event;
import Bilkay.IndividualMatcher;
import Bilkay.UserRelatedServices.user;
import Bilkay.UserRelatedServices.userProfileDialog;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.text.ParseException;
import java.util.Random;

import static Bilkay.UserRelatedServices.userDbPull.*;
import static Bilkay.UserRelatedServices.userDbPull.getChosenSubCategoriesFromUserID;
import static Bilkay.UserRelatedServices.userPointProcess.decreaseUserPoints;
import static Bilkay.UserRelatedServices.userPointProcess.increaseUserPoints;

public class mainMatchesMenu {
    private final user currentUser;
    private final JFrame myMainFrame;
    private final JPanel rightMainDashboardPanel;
    private JPanel mainPanelForMenu;
    private JLabel logoJPanel;
    private JScrollPane mainJScrollPane;
    private JPanel mainJPanelForScroll;
    private JPanel bilUberPanel;
    private JPanel northJpanel;
    private JLabel BilUberIconJLabel;
    private JLabel BilUberJLabel;
    private JLabel CurrentOfferedLiftsJlabel;
    private JPanel centerJpanel;
    private JScrollPane scrollPaneForBilUber;
    private JPanel currentLiftsJpanel;
    private JPanel acceptedLiftsJPanel;
    private JPanel northAcceptedLiftsJPanel;
    private JLabel acceptedLiftsJlabel;
    private JPanel centerAcceptedLiftsJpanel;
    private JPanel liftsAcceptedViewJPanel;
    private JButton matchButton;

    public mainMatchesMenu(JFrame myMainFrame, user currentUser, JPanel rightMainDashboardPanel) {
        this.currentUser = currentUser;
        this.myMainFrame = myMainFrame;
        this.rightMainDashboardPanel = rightMainDashboardPanel;

        liftsAcceptedViewJPanel.setLayout(new BoxLayout(liftsAcceptedViewJPanel, BoxLayout.Y_AXIS));
        currentLiftsJpanel.setLayout(new BoxLayout(currentLiftsJpanel, BoxLayout.Y_AXIS));


        SwingWorker<Void, Void> updateUITask = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                while (true) {
                    updateUI();
                    Thread.sleep(30000000);
                }
            }

        };


        updateUITask.execute();


        matchButton.addActionListener(e -> {


            try {


                Connection connection = DatabaseManager.getConnection();

                String pullingCurrentLiftsWithoutFollows = "SELECT user_id from users where user_id != ? and user_id not in (select user1_id from matches where user2_id = ?) and user_id not in (select user2_id from matches where user1_id = ?) order by rand() ";

                PreparedStatement pSForCurrentLifts = connection.prepareStatement(pullingCurrentLiftsWithoutFollows);

                pSForCurrentLifts.setInt(1, currentUser.getUserID());

                pSForCurrentLifts.setInt(2, currentUser.getUserID());

                pSForCurrentLifts.setInt(3, currentUser.getUserID());

                ResultSet resultsSetForPulledOfferedLifts = pSForCurrentLifts.executeQuery();

                user mostSuitableUser = null;
                int maxScore = 0;


                while (resultsSetForPulledOfferedLifts.next()) {
                    int idOfTheChosenUser = resultsSetForPulledOfferedLifts.getInt("user_id");
                    user chosenUser = getUserObjFromUserID(idOfTheChosenUser);
                    assert chosenUser != null;
                    chosenUser.setChosenCategories(getChosenCategoriesFromUserID(idOfTheChosenUser));
                    chosenUser.setChosenSubCategories(getChosenSubCategoriesFromUserID(idOfTheChosenUser));

                    IndividualMatcher im = new IndividualMatcher(currentUser, chosenUser);
                    int currentScore = im.calcMatchScore();
                    if (currentScore >= maxScore) {
                        maxScore = currentScore;
                        mostSuitableUser = chosenUser;
                    }

                }

                currentLiftsJpanel.removeAll();
                assert mostSuitableUser != null;
                displayCurrentLifts(mostSuitableUser, currentLiftsJpanel);
                increaseUserPoints(currentUser);

            } catch (SQLException k) {
                k.printStackTrace();
            }

        });
    }

    static String getPictureFromUserIDMain(int inputID) throws SQLException {
        String pathToProfilePicture;

        Connection connection = DatabaseManager.getConnection();

        String fromIDtoPP = "SELECT profile_picture_path from users where user_id= ?";

        PreparedStatement preparedStatementForExercises = connection.prepareStatement(fromIDtoPP);

        preparedStatementForExercises.setInt(1, inputID);

        ResultSet resultsSetForExerciseNames = preparedStatementForExercises.executeQuery();


        if (resultsSetForExerciseNames.next()) {
            pathToProfilePicture = resultsSetForExerciseNames.getString("profile_picture_path");
            if (pathToProfilePicture != null) {
                return pathToProfilePicture;

            }
        }

        preparedStatementForExercises.close();
        resultsSetForExerciseNames.close();
        pathToProfilePicture = "./src\\main\\resources\\iconsForApp\\userIconDashboard.png";
        return pathToProfilePicture;
    }

    private void addMatchesRelation(user matchedUser) throws SQLException {

        Connection connection = DatabaseManager.getConnection();
        Statement statement = connection.createStatement();

        String insertQuery = "INSERT INTO matches (user1_id,user2_id,user2_accepted) VALUES (?,?,1)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        preparedStatement.setInt(1, matchedUser.getUserID());
        preparedStatement.setInt(2, currentUser.getUserID());

        preparedStatement.executeUpdate();
        preparedStatement.close();
        statement.close();
    }

    private void updateUI() {

        try {


            pullFollowedLiftsAndDisplay();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    private void displayCurrentLifts(user matchedUser, JPanel jpanelToUse) throws SQLException {
        Font liftInformationFont = new Font("Times New Roman", Font.BOLD, 18);
        Font buttonFont = new Font("Times New Roman", Font.BOLD, 14);

        JPanel individualLiftRow = new JPanel();
        individualLiftRow.setLayout(new BorderLayout());
        individualLiftRow.setForeground(new Color(255, 255, 235));
        individualLiftRow.setBackground(new Color(40, 40, 43));
        JLabel liftInfoLabel = new JLabel(matchedUser.getNameSurname());
        Box boxForUserNameAndPP = Box.createHorizontalBox();
        JButton JLabelForDriverPP = new JButton();
        ImageIcon iconPP = new ImageIcon(new ImageIcon(getPictureFromUserID(matchedUser.getUserID())).getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH));
        JLabelForDriverPP.setIcon(iconPP);
        boxForUserNameAndPP.add(JLabelForDriverPP);
        JLabel liftInfo = new JLabel(matchedUser.getNameSurname());
        liftInfo.setFont(liftInformationFont);
        liftInfo.setForeground(new Color(255, 255, 235));
        liftInfo.setBackground(new Color(40, 40, 43));
        liftInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        boxForUserNameAndPP.add(liftInfo);
        boxForUserNameAndPP.setPreferredSize(new Dimension(100, 48));
        individualLiftRow.revalidate();
        boxForUserNameAndPP.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        JButton followButton = new JButton();
        followButton.setFont(buttonFont);
        followButton.setForeground(new Color(255, 255, 235));
        followButton.setBackground(new Color(40, 40, 43));
        followButton.setIcon(new ImageIcon("./src\\main\\resources\\iconsForApp\\checkIcon.png"));
        followButton.setVerticalAlignment(SwingConstants.CENTER);
        followButton.setHorizontalAlignment(SwingConstants.CENTER);


        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setPreferredSize(new Dimension(jpanelToUse.getWidth(), 5));
        separator.setBackground(new Color(40, 40, 43));
        separator.setForeground(new Color(255, 255, 235));

        individualLiftRow.add(boxForUserNameAndPP, BorderLayout.CENTER);
        individualLiftRow.add(followButton, BorderLayout.EAST);
        individualLiftRow.add(separator, BorderLayout.SOUTH);
        individualLiftRow.setForeground(new Color(255, 255, 235));
        individualLiftRow.setBackground(new Color(40, 40, 43));
        individualLiftRow.revalidate();


        JLabelForDriverPP.addActionListener(e -> {


            SwingWorker<Void, Void> showPP = new SwingWorker<>() {
                @Override
                protected Void doInBackground() {

                    userProfileDialog test = new userProfileDialog(myMainFrame, matchedUser);


                    myMainFrame.revalidate();
                    myMainFrame.repaint();


                    return null;
                }

                @Override
                protected void done() {
                    jpanelToUse.revalidate();
                    jpanelToUse.repaint();
                }
            };


            showPP.execute();


        });


        followButton.addActionListener(e -> {


            SwingWorker<Void, Void> followButtonThread = new SwingWorker<>() {
                @Override
                protected Void doInBackground() {
                    try {
                        try {
                            addMatchesRelation(matchedUser);


                            currentLiftsJpanel.removeAll();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        updateUI();
                        increaseUserPoints(currentUser);

                        JOptionPane.showMessageDialog(myMainFrame, "You Have Been Matched! \n");

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    return null;
                }

                @Override
                protected void done() {
                    jpanelToUse.revalidate();
                    jpanelToUse.repaint();
                }
            };


            followButtonThread.execute();


        });

        liftInfoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        liftInfoLabel.setBackground(new Color(40, 40, 43));
        liftInfoLabel.setForeground(new Color(255, 255, 235));
        liftInfoLabel.setFont(liftInformationFont);

        jpanelToUse.add(individualLiftRow);
        jpanelToUse.revalidate();
        jpanelToUse.repaint();
    }



    private void pullFollowedLiftsAndDisplay() throws SQLException {


        Connection connection = DatabaseManager.getConnection();

        String pullingCurrentLiftsWithoutFollows = "SELECT user_id from users where user_id != ? and user_id in (select user1_id from matches where user2_id = ? and user1_accepted = 1) or user_id  in (select user2_id from matches where user1_id = ? and user2_accepted = 1)   order by rand() ";


        PreparedStatement pSForCurrentLifts = connection.prepareStatement(pullingCurrentLiftsWithoutFollows);

        pSForCurrentLifts.setInt(1, currentUser.getUserID());

        pSForCurrentLifts.setInt(2, currentUser.getUserID());

        pSForCurrentLifts.setInt(3, currentUser.getUserID());

        ResultSet resultsSetForPulledOfferedLifts = pSForCurrentLifts.executeQuery();


        liftsAcceptedViewJPanel.removeAll();
        while (resultsSetForPulledOfferedLifts.next()) {
            int idOfTheChosenUser = resultsSetForPulledOfferedLifts.getInt("user_id");
            user chosenUser = getUserObjFromUserID(idOfTheChosenUser);
            assert chosenUser != null;
            chosenUser.setChosenCategories(getChosenCategoriesFromUserID(idOfTheChosenUser));
            chosenUser.setChosenSubCategories(getChosenSubCategoriesFromUserID(idOfTheChosenUser));




            displayFollowedLifts(chosenUser, liftsAcceptedViewJPanel);

        }

        pSForCurrentLifts.close();
        resultsSetForPulledOfferedLifts.close();


    }

    private void displayFollowedLifts(user chosenUser, JPanel jpanelToUse) throws SQLException {
        Font liftInformationFont = new Font("Times New Roman", Font.BOLD, 18);
        Font buttonFont = new Font("Times New Roman", Font.BOLD, 14);

        JPanel individualLiftRow = new JPanel();
        individualLiftRow.setLayout(new BorderLayout());
        individualLiftRow.setForeground(new Color(255, 255, 235));
        individualLiftRow.setBackground(new Color(40, 40, 43));
        JLabel liftInfoLabel = new JLabel(chosenUser.toString());
        Box boxForUserNameAndPP = Box.createHorizontalBox();
        JButton JLabelForDriverPP = new JButton();
        ImageIcon iconPP = new ImageIcon(new ImageIcon(getPictureFromUserID(chosenUser.getUserID())).getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH));
        JLabelForDriverPP.setIcon(iconPP);
        boxForUserNameAndPP.add(JLabelForDriverPP);
        JLabel liftInfo = new JLabel(chosenUser.getNameSurname());
        liftInfo.setFont(liftInformationFont);
        liftInfo.setForeground(new Color(255, 255, 235));
        liftInfo.setBackground(new Color(40, 40, 43));
        liftInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        boxForUserNameAndPP.add(liftInfo);
        boxForUserNameAndPP.setPreferredSize(new Dimension(100, 48));
        individualLiftRow.revalidate();
        boxForUserNameAndPP.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 2));


        JButton cancelTheFollow = new JButton();
        cancelTheFollow.setFont(buttonFont);
        cancelTheFollow.setForeground(new Color(255, 255, 235));
        cancelTheFollow.setBackground(new Color(40, 40, 43));
        cancelTheFollow.setIcon(new ImageIcon("./src\\main\\resources\\iconsForApp\\crossIcon.png"));
        cancelTheFollow.setVerticalAlignment(SwingConstants.CENTER);
        cancelTheFollow.setHorizontalAlignment(SwingConstants.CENTER);

        JButton sendMessage = new JButton();
        sendMessage.setFont(buttonFont);
        sendMessage.setForeground(new Color(255, 255, 235));
        sendMessage.setBackground(new Color(40, 40, 43));
        sendMessage.setIcon(new ImageIcon("./src\\main\\resources\\iconsForApp\\messagesIcon.png"));
        sendMessage.setVerticalAlignment(SwingConstants.CENTER);
        sendMessage.setHorizontalAlignment(SwingConstants.CENTER);


        buttonPanel.add(sendMessage);
        buttonPanel.add(cancelTheFollow);

        individualLiftRow.revalidate();

        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setPreferredSize(new Dimension(jpanelToUse.getWidth(), 5));
        separator.setBackground(new Color(40, 40, 43));
        separator.setForeground(new Color(255, 255, 235));

        individualLiftRow.add(boxForUserNameAndPP, BorderLayout.CENTER);
        individualLiftRow.add(buttonPanel, BorderLayout.EAST);
        individualLiftRow.add(separator, BorderLayout.SOUTH);
        individualLiftRow.setForeground(new Color(255, 255, 235));
        individualLiftRow.setBackground(new Color(40, 40, 43));
        individualLiftRow.revalidate();


        JLabelForDriverPP.addActionListener(e -> {


            SwingWorker<Void, Void> showPP = new SwingWorker<>() {
                @Override
                protected Void doInBackground() {

                    userProfileDialog test = new userProfileDialog(myMainFrame, chosenUser);


                    myMainFrame.revalidate();
                    myMainFrame.repaint();


                    return null;
                }

                @Override
                protected void done() {
                    jpanelToUse.revalidate();
                    jpanelToUse.repaint();
                }
            };


            showPP.execute();


        });

        sendMessage.addActionListener(e -> {


            SwingWorker<Void, Void> sendMessageBackground = new SwingWorker<>() {
                @Override
                protected Void doInBackground() {

                    rightMainDashboardPanel.removeAll();
                    rightMainDashboardPanel.add(new mainMailMenu(myMainFrame, currentUser, rightMainDashboardPanel, chosenUser.getUsername()).getMainPanelForMenu());

                    myMainFrame.revalidate();
                    myMainFrame.repaint();


                    return null;
                }

                @Override
                protected void done() {
                    jpanelToUse.revalidate();
                    jpanelToUse.repaint();
                }
            };


            sendMessageBackground.execute();


        });


        cancelTheFollow.addActionListener(e -> {


            SwingWorker<Void, Void> cancelButtonBackground = new SwingWorker<>() {
                @Override
                protected Void doInBackground() {
                    try {

                        decreaseUserPoints(currentUser);
                        updateUI();
                        JOptionPane.showMessageDialog(myMainFrame, "Match Has Been Cancelled! \n");
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    return null;
                }

                @Override
                protected void done() {
                    jpanelToUse.revalidate();
                    jpanelToUse.repaint();
                }
            };


            cancelButtonBackground.execute();


        });

        liftInfoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        liftInfoLabel.setBackground(new Color(40, 40, 43));
        liftInfoLabel.setForeground(new Color(255, 255, 235));
        liftInfoLabel.setFont(liftInformationFont);

        jpanelToUse.add(individualLiftRow);
        jpanelToUse.revalidate();
        jpanelToUse.repaint();
    }


    private String getPictureFromUserID(int inputID) throws SQLException {

        return getPictureFromUserIDMain(inputID);
    }


    public JPanel getMainPanelForMenu() {
        return mainPanelForMenu;
    }


}
