package Bilkay.mainDashBoardScreens;

import Bilkay.BilUber.Lift;
import Bilkay.BilUber.Route;
import Bilkay.Email_Keyboard_DatabaseServices.DatabaseManager;
import Bilkay.Events.Event;
import Bilkay.UserRelatedServices.user;
import Bilkay.UserRelatedServices.userProfileDialog;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.text.ParseException;

import static Bilkay.UserRelatedServices.userDbPull.*;
import static Bilkay.UserRelatedServices.userDbPull.getChosenSubCategoriesFromUserID;
import static Bilkay.UserRelatedServices.userPointProcess.decreaseUserPoints;
import static Bilkay.UserRelatedServices.userPointProcess.increaseUserPoints;

public class mainHomeMenu {

    private final user currentUser;
    private final JFrame myMainFrame;
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
    private JPanel givenLiftsOuterJpanel;
    private JPanel nothJpanelForGivenLifts;
    private JLabel inboxJLabel;
    private JPanel centralPanelForGivenLifts;
    private JScrollPane scrollPaneForMessages;
    private JPanel curretsLiftsGivenJpanel;
    private JPanel rightMainDashboardPanel;

    public mainHomeMenu(JFrame myMainFrame, user currentUser,JPanel rightMainDashboardPanel) {
        this.currentUser = currentUser;
        this.myMainFrame = myMainFrame;
        this.rightMainDashboardPanel = rightMainDashboardPanel;
        curretsLiftsGivenJpanel.setLayout(new BoxLayout(curretsLiftsGivenJpanel, BoxLayout.Y_AXIS));
        liftsAcceptedViewJPanel.setLayout(new BoxLayout(liftsAcceptedViewJPanel, BoxLayout.Y_AXIS));
        currentLiftsJpanel.setLayout(new BoxLayout(currentLiftsJpanel, BoxLayout.Y_AXIS));


        SwingWorker<Void, Void> updateUITask = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                while (true) {
                    updateUI();
                    Thread.sleep(30000);
                }
            }

        };

        updateUITask.execute();
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

    private void updateUI() {

        try {
            pullFollowedLiftsAndDisplay();
            currentMatches();
            showEvents();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    private void pullFollowedLiftsAndDisplay() throws SQLException {


        Connection connection = DatabaseManager.getConnection();

        String pullingFollowedLifts = "SELECT * from biluber_lifts where lift_id IN (SELECT lift_id from biluber_user_lift_relation where user_id =?) ORDER BY start_time ASC ";

        PreparedStatement pSForOfferedLifts = connection.prepareStatement(pullingFollowedLifts);

        pSForOfferedLifts.setInt(1, currentUser.getUserID());

        ResultSet resultsSetForPulledOfferedLifts = pSForOfferedLifts.executeQuery();

        currentLiftsJpanel.removeAll();
        while (resultsSetForPulledOfferedLifts.next()) {
            String startLocationOfTheLift = resultsSetForPulledOfferedLifts.getString("start_location");
            String endLocationOfTheLift = resultsSetForPulledOfferedLifts.getString("end_location");
            String pulledTimestampAsString = resultsSetForPulledOfferedLifts.getString("start_time");
            String formattedTimestampForLift = pulledTimestampAsString.substring(0, pulledTimestampAsString.length() - 3);
            int availableSeats = resultsSetForPulledOfferedLifts.getInt("avaiable_seats");
            int idOfTheDriver = resultsSetForPulledOfferedLifts.getInt("driverUserID");
            String nameSurnameOfTheDriver = resultsSetForPulledOfferedLifts.getString("driverNameSurname");
            int idOfTheLift = resultsSetForPulledOfferedLifts.getInt("lift_id");

            Route createdRouteForNewLift = new Route(startLocationOfTheLift, endLocationOfTheLift);
            Lift createdOfferedLift = new Lift(idOfTheLift, nameSurnameOfTheDriver, idOfTheDriver, formattedTimestampForLift, createdRouteForNewLift, availableSeats);

            displayFollowedLifts(createdOfferedLift, currentLiftsJpanel);

        }

        pSForOfferedLifts.close();
        resultsSetForPulledOfferedLifts.close();


    }

    private void displayFollowedLifts(Lift chosenLift, JPanel jpanelToUse) throws SQLException {
        Font liftInformationFont = new Font("Times New Roman", Font.BOLD, 18);
        Font buttonFont = new Font("Times New Roman", Font.BOLD, 14);

        JPanel individualLiftRow = new JPanel();
        individualLiftRow.setLayout(new BorderLayout());
        individualLiftRow.setForeground(new Color(255, 255, 235));
        individualLiftRow.setBackground(new Color(40, 40, 43));
        JLabel liftInfoLabel = new JLabel(chosenLift.toString());
        Box boxForUserNameAndPP = Box.createHorizontalBox();
        JButton JLabelForDriverPP = new JButton();
        ImageIcon iconPP = new ImageIcon(new ImageIcon(getPictureFromUserID(chosenLift.getDriverID())).getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH));
        JLabelForDriverPP.setIcon(iconPP);
        boxForUserNameAndPP.add(JLabelForDriverPP);
        JLabel liftInfo = new JLabel(chosenLift.toString());
        liftInfo.setFont(liftInformationFont);
        liftInfo.setForeground(new Color(255, 255, 235));
        liftInfo.setBackground(new Color(40, 40, 43));
        liftInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        boxForUserNameAndPP.add(liftInfo);
        boxForUserNameAndPP.setPreferredSize(new Dimension(100, 48));
        individualLiftRow.revalidate();
        boxForUserNameAndPP.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0,2));


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

        String userNameOfTheCreator = idToUserNameDB(chosenLift.getDriverID());

        user creatorOBJ = getUserObjFromUserID(chosenLift.getDriverID());
        assert creatorOBJ != null;
        creatorOBJ.setChosenCategories(getChosenCategoriesFromUserID(chosenLift.getDriverID()));

        creatorOBJ.setChosenSubCategories(getChosenSubCategoriesFromUserID(chosenLift.getDriverID()));

        JLabelForDriverPP.addActionListener(e -> {


            SwingWorker<Void, Void> showPP = new SwingWorker<>() {
                @Override
                protected Void doInBackground() {

                    userProfileDialog test = new userProfileDialog(myMainFrame,creatorOBJ);


                    myMainFrame.revalidate();
                    myMainFrame.repaint();


                    return null;
                };

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
                    rightMainDashboardPanel.add(new mainMailMenu(myMainFrame, currentUser,rightMainDashboardPanel,userNameOfTheCreator).getMainPanelForMenu());

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
                        JOptionPane.showMessageDialog(myMainFrame, "Lift with these details has been unfollowed! \n" + chosenLift);
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

    private void currentMatches() throws SQLException {



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


    //*****************

    private void showEvents() throws SQLException {


        Connection connection = DatabaseManager.getConnection();

        String pullingFollowedLifts = "SELECT * from events where event_id IN (SELECT event_id from event_participants where user_id =?) ORDER BY event_date ASC ";

        PreparedStatement pSForOfferedLifts = connection.prepareStatement(pullingFollowedLifts);

        pSForOfferedLifts.setInt(1, currentUser.getUserID());

        ResultSet resultsSetForPulledOfferedLifts = pSForOfferedLifts.executeQuery();

        curretsLiftsGivenJpanel.removeAll();
        while (resultsSetForPulledOfferedLifts.next()) {
            String eventLoc = resultsSetForPulledOfferedLifts.getString("event_location");
            String eventName = resultsSetForPulledOfferedLifts.getString("event_name");

            String pulledTimestampAsString = resultsSetForPulledOfferedLifts.getString("event_date");
            String formattedDateForEvent = pulledTimestampAsString.substring(0, pulledTimestampAsString.length() - 3);

            int eventCapacity = resultsSetForPulledOfferedLifts.getInt("event_capacity");

            int idOfTheCreator = resultsSetForPulledOfferedLifts.getInt("creator_id");

            String creatorNameSurname = resultsSetForPulledOfferedLifts.getString("creatorNameSurname");

            int idOfTheEvent = resultsSetForPulledOfferedLifts.getInt("event_id");

            String eventType = resultsSetForPulledOfferedLifts.getString("eventType");

            Event pulledCreatedEvent = new Event(idOfTheEvent, creatorNameSurname, idOfTheCreator, formattedDateForEvent, eventCapacity, eventName, eventLoc, eventType);

            displayEvents(pulledCreatedEvent, curretsLiftsGivenJpanel);

        }

        pSForOfferedLifts.close();
        resultsSetForPulledOfferedLifts.close();


    }

    private void displayEvents(Event chosenEvent, JPanel jpanelToUse) throws SQLException {
        Font liftInformationFont = new Font("Times New Roman", Font.BOLD, 18);
        Font buttonFont = new Font("Times New Roman", Font.BOLD, 14);

        JPanel individualLiftRow = new JPanel();
        individualLiftRow.setLayout(new BorderLayout());
        individualLiftRow.setForeground(new Color(255, 255, 235));
        individualLiftRow.setBackground(new Color(40, 40, 43));
        JLabel liftInfoLabel = new JLabel(chosenEvent.toString());
        Box boxForUserNameAndPP = Box.createHorizontalBox();
        JButton JLabelForDriverPP = new JButton();
        ImageIcon iconPP = new ImageIcon(new ImageIcon(getPictureFromUserID(chosenEvent.getCreatorID())).getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH));
        JLabelForDriverPP.setIcon(iconPP);
        boxForUserNameAndPP.add(JLabelForDriverPP);
        JLabel liftInfo = new JLabel(chosenEvent.toString());
        liftInfo.setFont(liftInformationFont);
        liftInfo.setForeground(new Color(255, 255, 235));
        liftInfo.setBackground(new Color(40, 40, 43));
        liftInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        boxForUserNameAndPP.add(liftInfo);
        boxForUserNameAndPP.setPreferredSize(new Dimension(100, 48));
        individualLiftRow.revalidate();
        boxForUserNameAndPP.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0,2));


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

        String userNameOfTheCreator = idToUserNameDB(chosenEvent.getCreatorID());


        user creatorOBJ = getUserObjFromUserID(chosenEvent.getCreatorID());
        assert creatorOBJ != null;
        creatorOBJ.setChosenCategories(getChosenCategoriesFromUserID(chosenEvent.getCreatorID()));

        creatorOBJ.setChosenSubCategories(getChosenSubCategoriesFromUserID(chosenEvent.getCreatorID()));

        JLabelForDriverPP.addActionListener(e -> {


            SwingWorker<Void, Void> showPP = new SwingWorker<>() {
                @Override
                protected Void doInBackground() {

                    userProfileDialog test = new userProfileDialog(myMainFrame,creatorOBJ);


                    myMainFrame.revalidate();
                    myMainFrame.repaint();


                    return null;
                };

                @Override
                protected void done() {
                    jpanelToUse.revalidate();
                    jpanelToUse.repaint();
                }
            };


            showPP.execute();


        });

        liftInfoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        liftInfoLabel.setBackground(new Color(40, 40, 43));
        liftInfoLabel.setForeground(new Color(255, 255, 235));
        liftInfoLabel.setFont(liftInformationFont);

        jpanelToUse.add(individualLiftRow);
        jpanelToUse.revalidate();
        jpanelToUse.repaint();

}}
