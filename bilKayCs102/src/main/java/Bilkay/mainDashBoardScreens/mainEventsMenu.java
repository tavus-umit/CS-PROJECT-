package Bilkay.mainDashBoardScreens;

import Bilkay.BilUber.Lift;
import Bilkay.BilUber.Route;
import Bilkay.Email_Keyboard_DatabaseServices.DatabaseManager;
import Bilkay.Events.Event;
import Bilkay.UserRelatedServices.user;
import Bilkay.UserRelatedServices.userProfileDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static Bilkay.UserRelatedServices.userDbPull.*;
import static Bilkay.UserRelatedServices.userPointProcess.decreaseUserPoints;
import static Bilkay.UserRelatedServices.userPointProcess.increaseUserPoints;

public class mainEventsMenu {

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
    private JPanel oferLiftMostOuterJpanel;
    private JLabel offerLiftJlabel;
    private JPanel offerLiftValuesJpanel;
    private JLabel startingLocationJlabel;
    private JTextField startingLocationJtextField;
    private JTextField finalDestinationJText;
    private JLabel dateTimeJlabel;
    private JFormattedTextField formattedDayTimeTextField1;
    private JButton offerLiftButton;
    private JFormattedTextField availableSeatsJText;
    private JLabel currentDateTimeJlabel;
    private JPanel givenLiftsOuterJpanel;
    private JPanel nothJpanelForGivenLifts;
    private JLabel inboxJLabel;
    private JPanel centralPanelForGivenLifts;
    private JScrollPane scrollPaneForMessages;
    private JPanel curretsLiftsGivenJpanel;
    private JRadioButton dormEventsRadioButton;
    private JRadioButton clubRadioButton;
    private JLabel availableSeatsJLabel;
    private JLabel eventNameJlabel;
    private JFormattedTextField eventNameJtext;
    private JTextField eventCapacityJText;
    private JRadioButton clubEventChoiceRadio;
    private JRadioButton dormEventChoiceRadio;
    private JScrollPane scrollPaneForDormEventsScrollPane;
    private JPanel dromEventsJpanel;
    private boolean FirstTimeForDateTime;

    private JPanel rightMainDashboardPanel;
    private final SimpleDateFormat dateAndHourWithoutMilliseconds = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public mainEventsMenu( JFrame myMainFrame, user currentUser, JPanel rightMainDashboardPanel) {
        this.currentUser = currentUser;
        this.myMainFrame = myMainFrame;
        this.rightMainDashboardPanel = rightMainDashboardPanel;




        if (!currentUser.getRole().equals("creator")) {
            clubEventChoiceRadio.setEnabled(false);
        }

        scrollPaneForDormEventsScrollPane.setVisible(false);
        scrollPaneForBilUber.setVisible(true);
        scrollPaneForBilUber.revalidate();
        centerJpanel.revalidate();
        centerJpanel.repaint();


        dromEventsJpanel.setLayout(new BoxLayout(dromEventsJpanel, BoxLayout.Y_AXIS));
        curretsLiftsGivenJpanel.setLayout(new BoxLayout(curretsLiftsGivenJpanel, BoxLayout.Y_AXIS));
        liftsAcceptedViewJPanel.setLayout(new BoxLayout(liftsAcceptedViewJPanel, BoxLayout.Y_AXIS));
        currentLiftsJpanel.setLayout(new BoxLayout(currentLiftsJpanel, BoxLayout.Y_AXIS));

        FirstTimeForDateTime = true;

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


        offerLiftButton.addActionListener(e -> {
            if (startingLocationJtextField.getText().isEmpty() || eventNameJtext.getText().isEmpty() || formattedDayTimeTextField1.getText().isEmpty() || eventCapacityJText.getText().isEmpty()) {
                JOptionPane.showMessageDialog(myMainFrame, "Please Enter All Information", "Offer A Lift", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String eventName = eventNameJtext.getText();
            String eventLoc = startingLocationJtextField.getText();
            String dateTime = formattedDayTimeTextField1.getText();
            int eventCapacity = Integer.parseInt(eventCapacityJText.getText());

            String eventType = null;
            if (clubEventChoiceRadio.isSelected()) {
                eventType = "Club Event";

            } else if (dormEventChoiceRadio.isSelected()) {
                eventType = "Dorm Event";

            }

            Event createdEvent = new Event(currentUser.getNameSurname(),currentUser.getUserID(),dateTime,eventCapacity,eventLoc,eventName,eventType);

            try {
                Connection connection = DatabaseManager.getConnection();
                Statement statement = connection.createStatement();


                String insertQuery = "INSERT INTO events (creator_id, event_name, event_date, event_location, event_capacity, eventType,creatorNameSurname) VALUES (?,?,?, ?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

                preparedStatement.setInt(1, currentUser.getUserID());
                preparedStatement.setString(2, eventName);
                preparedStatement.setString(3, dateTime);
                preparedStatement.setString(4, eventLoc);
                preparedStatement.setInt(5, eventCapacity);
                preparedStatement.setString(6, eventType);
                preparedStatement.setString(7, currentUser.getNameSurname());

                if (preparedStatement.executeUpdate() == 1) {
                    JOptionPane.showMessageDialog(myMainFrame, "Event Has Been Created", "Create An Event", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(myMainFrame, "There Has Been A Problem While Creating The Event", "Create An Event", JOptionPane.ERROR_MESSAGE);
                }


                startingLocationJtextField.setText("");
                eventNameJtext.setText("");
                formattedDayTimeTextField1.setText("");
                eventCapacityJText.setText("");

                oferLiftMostOuterJpanel.revalidate();
                oferLiftMostOuterJpanel.repaint();

                updateUI();
                increaseUserPoints(currentUser, 10);

                preparedStatement.close();
                statement.close();
            } catch (SQLException k) {
                k.printStackTrace();
            }







        });
        formattedDayTimeTextField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (FirstTimeForDateTime) {
                    formattedDayTimeTextField1.setText("");
                    formattedDayTimeTextField1.setForeground(new Color(40, 40, 43));
                    FirstTimeForDateTime = false;
                }

            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (formattedDayTimeTextField1.getText().isEmpty() || FirstTimeForDateTime) {
                    String timeStampOfLift = dateAndHourWithoutMilliseconds.format(new Timestamp(System.currentTimeMillis()));
                    formattedDayTimeTextField1.setText(timeStampOfLift);
                    formattedDayTimeTextField1.setForeground(Color.lightGray);
                }
            }
        });
        clubRadioButton.addActionListener(e -> {

            scrollPaneForDormEventsScrollPane.setVisible(false);
            scrollPaneForBilUber.setVisible(true);
            scrollPaneForBilUber.revalidate();
            centerJpanel.revalidate();
            centerJpanel.repaint();

        });
        dormEventsRadioButton.addActionListener(e -> {
            scrollPaneForDormEventsScrollPane.setVisible(true);
            scrollPaneForBilUber.setVisible(false);
            scrollPaneForDormEventsScrollPane.revalidate();
            centerJpanel.revalidate();
            centerJpanel.repaint();
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

    private void updateUI() {

        try {

            deleteRelationshipsOfOldLiftFromDB();
            deleteOldLiftsFromDB();

            pullTheClubEventssAndDisplay();
            pullTheDormEventssAndDisplay();
            
            
            pullFollowedLiftsAndDisplay();

            pullOfferedLiftAndDisplay();


        } catch (SQLException | ParseException e) {
            throw new RuntimeException(e);
        }
        updateCurrentTimeAndDate();


    }

    private void pullTheDormEventssAndDisplay() throws SQLException {
        Connection connection = DatabaseManager.getConnection();

        String pullingCurrentLiftsWithoutFollows = "SELECT * from events where event_id NOT IN (SELECT event_id from event_participants where user_id =?) and creator_id !=? and event_capacity > 0 and eventType != 'Club Event' ORDER BY event_date ASC ";

        PreparedStatement pSForCurrentLifts = connection.prepareStatement(pullingCurrentLiftsWithoutFollows);

        pSForCurrentLifts.setInt(1, currentUser.getUserID());
        pSForCurrentLifts.setInt(2, currentUser.getUserID());

        ResultSet resultsSetForPulledOfferedLifts = pSForCurrentLifts.executeQuery();

        dromEventsJpanel.removeAll();
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


            displayCurrentLifts(pulledCreatedEvent, dromEventsJpanel);

        }

        pSForCurrentLifts.close();
        resultsSetForPulledOfferedLifts.close();
    }

    private void pullTheClubEventssAndDisplay() throws SQLException {

        Connection connection = DatabaseManager.getConnection();

        String pullingCurrentLiftsWithoutFollows = "SELECT * from events where event_id NOT IN (SELECT event_id from event_participants where user_id =?) and creator_id !=? and event_capacity > 0 and eventType != 'Dorm Event' ORDER BY event_date ASC ";

        PreparedStatement pSForCurrentLifts = connection.prepareStatement(pullingCurrentLiftsWithoutFollows);

        pSForCurrentLifts.setInt(1, currentUser.getUserID());
        pSForCurrentLifts.setInt(2, currentUser.getUserID());

        ResultSet resultsSetForPulledOfferedLifts = pSForCurrentLifts.executeQuery();

        currentLiftsJpanel.removeAll();
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


            displayCurrentLifts(pulledCreatedEvent, currentLiftsJpanel);

        }

        pSForCurrentLifts.close();
        resultsSetForPulledOfferedLifts.close();
    }

    private void displayCurrentLifts(Event chosenEvent, JPanel jpanelToUse) throws SQLException {
        Font liftInformationFont = new Font("Times New Roman", Font.BOLD, 18);
        Font buttonFont = new Font("Times New Roman", Font.BOLD, 14);

        JPanel individualLiftRow = new JPanel();
        individualLiftRow.setLayout(new BorderLayout());
        individualLiftRow.setForeground(new Color(255, 255, 235));
        individualLiftRow.setBackground(new Color(40, 40, 43));
        JLabel liftInfoLabel = new JLabel(chosenEvent.toString());
        Box boxForUserNameAndPP = Box.createHorizontalBox();
        JLabel JLabelForDriverPP = new JLabel();
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



        followButton.addActionListener(e -> {


            SwingWorker<Void, Void> followButtonThread = new SwingWorker<>() {
                @Override
                protected Void doInBackground() {
                    try {
                        addRelationToChosenLift(chosenEvent);
                        decreaseTheAvailableSeatNumber(chosenEvent);
                        updateUI();
                        increaseUserPoints(currentUser);

                        JOptionPane.showMessageDialog(myMainFrame, "Event with these details has been followed! \n" + chosenEvent);

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

    private void decreaseTheAvailableSeatNumber(Event chosenEvent) throws SQLException {
        Connection connection = DatabaseManager.getConnection();
        Statement statement = connection.createStatement();

        String changePasswordSql = "UPDATE events SET event_capacity = ? WHERE event_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(changePasswordSql);
        preparedStatement.setInt(1, chosenEvent.getEventCapacity() - 1);
        preparedStatement.setInt(2, chosenEvent.getIdOfTheEvent());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        statement.close();


    }

    private void addRelationToChosenLift(Event chosenEvent) throws SQLException {

        Connection connection = DatabaseManager.getConnection();
        Statement statement = connection.createStatement();

        String insertQuery = "INSERT INTO event_participants (event_id,user_id) VALUES (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        preparedStatement.setInt(1, chosenEvent.getIdOfTheEvent());
        preparedStatement.setInt(2, currentUser.getUserID());

        preparedStatement.executeUpdate();
        preparedStatement.close();
        statement.close();
    }

    private void pullFollowedLiftsAndDisplay() throws SQLException {


        Connection connection = DatabaseManager.getConnection();

        String pullingFollowedLifts = "SELECT * from events where event_id IN (SELECT event_id from event_participants where user_id =?) ORDER BY event_date ASC ";

        PreparedStatement pSForOfferedLifts = connection.prepareStatement(pullingFollowedLifts);

        pSForOfferedLifts.setInt(1, currentUser.getUserID());

        ResultSet resultsSetForPulledOfferedLifts = pSForOfferedLifts.executeQuery();

        liftsAcceptedViewJPanel.removeAll();
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

            displayFollowedLifts(pulledCreatedEvent, liftsAcceptedViewJPanel);

        }

        pSForOfferedLifts.close();
        resultsSetForPulledOfferedLifts.close();


    }

    private void displayFollowedLifts(Event chosenEvent, JPanel jpanelToUse) throws SQLException {
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
                        deleteRelationshipFromChosenLiftAndUserID(chosenEvent, currentUser.getUserID());
                        increaseTheAvailableSeatNumberOfTheChosenLift(chosenEvent);
                        decreaseUserPoints(currentUser);
                        updateUI();
                        JOptionPane.showMessageDialog(myMainFrame, "Event with these details has been unfollowed! \n" + chosenEvent);
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

    private void increaseTheAvailableSeatNumberOfTheChosenLift(Event chosenEvent) throws SQLException {

        Connection connection = DatabaseManager.getConnection();
        Statement statement = connection.createStatement();

        String changePasswordSql = "UPDATE events SET event_capacity = ? WHERE event_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(changePasswordSql);
        preparedStatement.setInt(1, chosenEvent.getEventCapacity() + 1);
        preparedStatement.setInt(2, chosenEvent.getIdOfTheEvent());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        statement.close();


    }

    private void deleteRelationshipFromChosenLiftAndUserID(Event chosenEvent, int userID) throws SQLException {

        String sqlForDeleteChosenRelation = "DELETE FROM event_participants WHERE user_id =? and event_id =?";

        Connection connection = DatabaseManager.getConnection();
        PreparedStatement deleteOldLifts = connection.prepareStatement(sqlForDeleteChosenRelation);
        deleteOldLifts.setInt(1, userID);
        deleteOldLifts.setInt(2, chosenEvent.getIdOfTheEvent());

        deleteOldLifts.executeUpdate();
        deleteOldLifts.close();
    }


    private void deleteRelationshipsOfOldLiftFromDB() throws SQLException {

        String sqlForDeleteOldLifts = "DELETE FROM event_participants WHERE event_id IN (SELECT event_id from events WHERE event_date <= ?)";

        String timeStampOfLift = dateAndHourWithoutMilliseconds.format(new Timestamp(System.currentTimeMillis()));

        Connection connection = DatabaseManager.getConnection();
        PreparedStatement deleteOldLifts = connection.prepareStatement(sqlForDeleteOldLifts);
        deleteOldLifts.setString(1, timeStampOfLift);

        deleteOldLifts.executeUpdate();
        deleteOldLifts.close();
    }

    private void deleteOldLiftsFromDB() throws SQLException {

        String sqlForDeleteOldLifts = "DELETE FROM events WHERE event_date <= ?";

        String timeStampOfLift = dateAndHourWithoutMilliseconds.format(new Timestamp(System.currentTimeMillis()));

        Connection connection = DatabaseManager.getConnection();
        PreparedStatement deleteOldLifts = connection.prepareStatement(sqlForDeleteOldLifts);
        deleteOldLifts.setString(1, timeStampOfLift);

        deleteOldLifts.executeUpdate();
        deleteOldLifts.close();


    }

    private void updateCurrentTimeAndDate() {
        String timeStampOfLift = dateAndHourWithoutMilliseconds.format(new Timestamp(System.currentTimeMillis()));
        currentDateTimeJlabel.setText("Current Date and Time is: " + timeStampOfLift);
    }

    private void pullOfferedLiftAndDisplay() throws SQLException, ParseException {


        Connection connection = DatabaseManager.getConnection();

        String queryForPullingOfferedLifts = "SELECT * from events where creator_id= ? ORDER BY event_date ASC ";

        PreparedStatement preparedStatementForExercises = connection.prepareStatement(queryForPullingOfferedLifts);

        preparedStatementForExercises.setInt(1, currentUser.getUserID());

        ResultSet resultsSetForPulledOfferedLifts = preparedStatementForExercises.executeQuery();

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


            displayLiftsOfferedByYou(pulledCreatedEvent, curretsLiftsGivenJpanel);

        }

        preparedStatementForExercises.close();
        resultsSetForPulledOfferedLifts.close();
    }

    private void displayLiftsOfferedByYou(Event createdEvent, JPanel jpanelToUse) throws SQLException {
        Font liftInformationFont = new Font("Times New Roman", Font.BOLD, 18);
        Font buttonFont = new Font("Times New Roman", Font.BOLD, 14);

        JPanel individualLiftRow = new JPanel();
        individualLiftRow.setLayout(new BorderLayout());
        individualLiftRow.setForeground(new Color(255, 255, 235));
        individualLiftRow.setBackground(new Color(40, 40, 43));
        JLabel liftInfoLabel = new JLabel(createdEvent.toString());
        Box boxForUserNameAndPP = Box.createHorizontalBox();
        JLabel JLabelForDriverPP = new JLabel();
        ImageIcon iconPP = new ImageIcon(new ImageIcon(getPictureFromUserID(createdEvent.getCreatorID())).getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH));
        JLabelForDriverPP.setIcon(iconPP);
        boxForUserNameAndPP.add(JLabelForDriverPP);
        JLabel liftInfo = new JLabel(createdEvent.toString());
        liftInfo.setFont(liftInformationFont);
        liftInfo.setForeground(new Color(255, 255, 235));
        liftInfo.setBackground(new Color(40, 40, 43));
        liftInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        boxForUserNameAndPP.add(liftInfo);
        boxForUserNameAndPP.setPreferredSize(new Dimension(100, 48));
        individualLiftRow.revalidate();
        boxForUserNameAndPP.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));


        JButton cancelTheLiftButton = new JButton();
        cancelTheLiftButton.setFont(buttonFont);
        cancelTheLiftButton.setForeground(new Color(255, 255, 235));
        cancelTheLiftButton.setBackground(new Color(40, 40, 43));
        cancelTheLiftButton.setIcon(new ImageIcon("./src\\main\\resources\\iconsForApp\\crossIcon.png"));
        cancelTheLiftButton.setVerticalAlignment(SwingConstants.CENTER);
        cancelTheLiftButton.setHorizontalAlignment(SwingConstants.CENTER);


        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setPreferredSize(new Dimension(jpanelToUse.getWidth(), 5));
        separator.setBackground(new Color(40, 40, 43));
        separator.setForeground(new Color(255, 255, 235));

        individualLiftRow.add(boxForUserNameAndPP, BorderLayout.CENTER);
        individualLiftRow.add(cancelTheLiftButton, BorderLayout.EAST);
        individualLiftRow.add(separator, BorderLayout.SOUTH);
        individualLiftRow.setForeground(new Color(255, 255, 235));
        individualLiftRow.setBackground(new Color(40, 40, 43));
        individualLiftRow.revalidate();


        cancelTheLiftButton.addActionListener(e -> {


            SwingWorker<Void, Void> cancelButtonBackground = new SwingWorker<>() {
                @Override
                protected Void doInBackground() {
                    try {
                        deleteTheChosenLiftFromDB(createdEvent);
                        updateUI();
                        decreaseUserPoints(currentUser, 10);
                        JOptionPane.showMessageDialog(myMainFrame, "Event with these details has been canceled! \n" + createdEvent);
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

        }
        );

        liftInfoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        liftInfoLabel.setBackground(new Color(40, 40, 43));
        liftInfoLabel.setForeground(new Color(255, 255, 235));
        liftInfoLabel.setFont(liftInformationFont);

        jpanelToUse.add(individualLiftRow);
        jpanelToUse.revalidate();
        jpanelToUse.repaint();
    }

    private void deleteTheChosenLiftFromDB(Event createdEvent) throws SQLException {

        String sqlForDeleteOfferedLift = "DELETE FROM events WHERE event_id = ?";
        String sqlForDeleteOfferedLiftRelations = "DELETE FROM event_participants WHERE event_id = ?";

        Connection connection = DatabaseManager.getConnection();

        PreparedStatement deleteOfferedLiftsRelations = connection.prepareStatement(sqlForDeleteOfferedLiftRelations);
        deleteOfferedLiftsRelations.setInt(1, createdEvent.getIdOfTheEvent());
        deleteOfferedLiftsRelations.executeUpdate();


        PreparedStatement deleteOfferedLifts = connection.prepareStatement(sqlForDeleteOfferedLift);
        deleteOfferedLifts.setInt(1, createdEvent.getIdOfTheEvent());
        deleteOfferedLifts.executeUpdate();


        deleteOfferedLifts.close();
        deleteOfferedLiftsRelations.close();
    }

    private String getPictureFromUserID(int inputID) throws SQLException {

        return getPictureFromUserIDMain(inputID);
    }


    public JPanel getMainPanelForMenu() {
        return mainPanelForMenu;
    }



}
