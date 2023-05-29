package Bilkay.mainDashBoardScreens;

import Bilkay.BilUber.Lift;
import Bilkay.BilUber.Route;
import Bilkay.Email_Keyboard_DatabaseServices.DatabaseManager;
import Bilkay.UserRelatedServices.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static Bilkay.UserRelatedServices.userPointProcess.decreaseUserPoints;
import static Bilkay.UserRelatedServices.userPointProcess.increaseUserPoints;


public class mainBilUberMenu {
    private final user currentUser;
    private final JFrame myMainFrame;
    private final SimpleDateFormat dateAndHourWithoutMilliseconds = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private JPanel mainPanelForMenu;
    private JLabel logoJPanel;
    private JScrollPane mainJScrollPane;
    private JPanel mainJPanelForScroll;
    private JPanel bilUberPanel;
    private JPanel northJpanel;
    private JLabel BilUberIconJLabel;
    private JLabel BilUberJLabel;
    private JPanel centerJpanel;
    private JScrollPane scrollPaneForBilUber;
    private JPanel bilUberJpanel;
    private JLabel CurrentOfferedLiftsJlabel;
    private JPanel curretsLiftsGivenJpanel;
    private JPanel acceptedLiftsJPanel;
    private JPanel northAcceptedLiftsJPanel;
    private JLabel acceptedLiftsJlabel;
    private JPanel centerAcceptedLiftsJpanel;
    private JPanel liftsAcceptedViewJPanel;
    private JPanel oferLiftMostOuterJpanel;
    private JLabel offerLiftJlabel;
    private JPanel offerLiftValuesJpanel;
    private JLabel startingLocationJlabel;
    private JLabel finalDestinationJlabel;
    private JTextField startingLocationJtextField;
    private JFormattedTextField formattedDayTimeTextField1;
    private JLabel dateTimeJlabel;
    private JTextField finalDestinationJText;
    private JButton offerLiftButton;
    private JLabel availableSeatsJLabel;
    private JFormattedTextField availableSeatsJText;
    private JLabel currentDateTimeJlabel;
    private JPanel givenLiftsOuterJpanel;
    private JPanel nothJpanelForGivenLifts;
    private JLabel inboxJLabel;
    private JPanel centralPanelForGivenLifts;
    private JScrollPane scrollPaneForMessages;
    private JPanel currentLiftsJpanel;
    private boolean FirstTimeForDateTime;


    //Todo Tüm liftleri dbden çek, idye göre offered mı yoksa genel mi ona göre ekle
    //Todo takip edilenleri çekip yükle

    public mainBilUberMenu(JFrame myMainFrame, user currentUser) {
        this.currentUser = currentUser;
        this.myMainFrame = myMainFrame;
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
            if (startingLocationJtextField.getText().isEmpty() || finalDestinationJText.getText().isEmpty() || formattedDayTimeTextField1.getText().isEmpty() || availableSeatsJText.getText().isEmpty()) {
                JOptionPane.showMessageDialog(myMainFrame, "Please Enter All Information", "Offer A Lift", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String startingLoc = startingLocationJtextField.getText();
            String endingLoc = finalDestinationJText.getText();
            String dateTime = formattedDayTimeTextField1.getText();
            int availableSeatNumber = Integer.parseInt(availableSeatsJText.getText());

            Route routeOfTheLift = new Route(startingLoc, endingLoc);
            Lift currentlyOfferedLift = new Lift(currentUser.getNameSurname(), currentUser.getUserID(), dateTime, routeOfTheLift, availableSeatNumber);


            try {
                Connection connection = DatabaseManager.getConnection();
                Statement statement = connection.createStatement();

                String insertQuery = "INSERT INTO biluber_lifts (start_location,end_location,start_time,avaiable_seats,driverUserID,driverNameSurname) VALUES (?,?, ?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setString(1, startingLoc);
                preparedStatement.setString(2, endingLoc);
                preparedStatement.setString(3, dateTime);
                preparedStatement.setInt(4, availableSeatNumber);
                preparedStatement.setInt(5, currentUser.getUserID());
                preparedStatement.setString(6, currentUser.getNameSurname());


                if (preparedStatement.executeUpdate() == 1) {
                    JOptionPane.showMessageDialog(myMainFrame, "Lift Has Been Offered", "Offer A Lift", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(myMainFrame, "There Has Been A Problem While Offering A Lift", "Offer A Lift", JOptionPane.ERROR_MESSAGE);
                }
                startingLocationJtextField.setText("");
                finalDestinationJText.setText("");
                formattedDayTimeTextField1.setText("");
                availableSeatsJText.setText("");

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
            pullTheCurrentLiftsAndDisplay();
            pullFollowedLiftsAndDisplay();
            pullOfferedLiftAndDisplay();


        } catch (SQLException | ParseException e) {
            throw new RuntimeException(e);
        }
        updateCurrentTimeAndDate();


    }

    private void pullTheCurrentLiftsAndDisplay() throws SQLException {

        Connection connection = DatabaseManager.getConnection();

        String pullingCurrentLiftsWithoutFollows = "SELECT * from biluber_lifts where lift_id NOT IN (SELECT lift_id from biluber_user_lift_relation where user_id =?) and driverUserID !=? and avaiable_seats > 0 ORDER BY start_time ASC ";

        PreparedStatement pSForCurrentLifts = connection.prepareStatement(pullingCurrentLiftsWithoutFollows);

        pSForCurrentLifts.setInt(1, currentUser.getUserID());
        pSForCurrentLifts.setInt(2, currentUser.getUserID());

        ResultSet resultsSetForPulledOfferedLifts = pSForCurrentLifts.executeQuery();

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

            displayCurrentLifts(createdOfferedLift, currentLiftsJpanel);

        }

        pSForCurrentLifts.close();
        resultsSetForPulledOfferedLifts.close();
    }

    private void displayCurrentLifts(Lift chosenLift, JPanel jpanelToUse) throws SQLException {
        Font liftInformationFont = new Font("Times New Roman", Font.BOLD, 18);
        Font buttonFont = new Font("Times New Roman", Font.BOLD, 14);

        JPanel individualLiftRow = new JPanel();
        individualLiftRow.setLayout(new BorderLayout());
        individualLiftRow.setForeground(new Color(255, 255, 235));
        individualLiftRow.setBackground(new Color(40, 40, 43));
        JLabel liftInfoLabel = new JLabel(chosenLift.toString());
        Box boxForUserNameAndPP = Box.createHorizontalBox();
        JLabel JLabelForDriverPP = new JLabel();
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
                        addRelationToChosenLift(chosenLift);
                        decreaseTheAvailableSeatNumber(chosenLift);
                        updateUI();
                        increaseUserPoints(currentUser);

                        JOptionPane.showMessageDialog(myMainFrame, "Lift with these details has been followed! \n" + chosenLift);

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

    private void decreaseTheAvailableSeatNumber(Lift chosenLift) throws SQLException {
        Connection connection = DatabaseManager.getConnection();
        Statement statement = connection.createStatement();

        String changePasswordSql = "UPDATE biluber_lifts SET avaiable_seats = ? WHERE lift_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(changePasswordSql);
        preparedStatement.setInt(1, chosenLift.getAvailableSeat() - 1);
        preparedStatement.setInt(2, chosenLift.getIdOfTheLift());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        statement.close();


    }

    private void addRelationToChosenLift(Lift chosenLift) throws SQLException {

        Connection connection = DatabaseManager.getConnection();
        Statement statement = connection.createStatement();

        String insertQuery = "INSERT INTO biluber_user_lift_relation (lift_id,user_id) VALUES (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        preparedStatement.setInt(1, chosenLift.getIdOfTheLift());
        preparedStatement.setInt(2, currentUser.getUserID());

        preparedStatement.executeUpdate();
        preparedStatement.close();
        statement.close();
    }

    private void pullFollowedLiftsAndDisplay() throws SQLException {


        Connection connection = DatabaseManager.getConnection();

        String pullingFollowedLifts = "SELECT * from biluber_lifts where lift_id IN (SELECT lift_id from biluber_user_lift_relation where user_id =?) ORDER BY start_time ASC ";

        PreparedStatement pSForOfferedLifts = connection.prepareStatement(pullingFollowedLifts);

        pSForOfferedLifts.setInt(1, currentUser.getUserID());

        ResultSet resultsSetForPulledOfferedLifts = pSForOfferedLifts.executeQuery();

        liftsAcceptedViewJPanel.removeAll();
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

            displayFollowedLifts(createdOfferedLift, liftsAcceptedViewJPanel);

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
        JLabel JLabelForDriverPP = new JLabel();
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

        JButton cancelTheFollow = new JButton();
        cancelTheFollow.setFont(buttonFont);
        cancelTheFollow.setForeground(new Color(255, 255, 235));
        cancelTheFollow.setBackground(new Color(40, 40, 43));
        cancelTheFollow.setIcon(new ImageIcon("./src\\main\\resources\\iconsForApp\\crossIcon.png"));
        cancelTheFollow.setVerticalAlignment(SwingConstants.CENTER);
        cancelTheFollow.setHorizontalAlignment(SwingConstants.CENTER);


        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setPreferredSize(new Dimension(jpanelToUse.getWidth(), 5));
        separator.setBackground(new Color(40, 40, 43));
        separator.setForeground(new Color(255, 255, 235));

        individualLiftRow.add(boxForUserNameAndPP, BorderLayout.CENTER);
        individualLiftRow.add(cancelTheFollow, BorderLayout.EAST);
        individualLiftRow.add(separator, BorderLayout.SOUTH);
        individualLiftRow.setForeground(new Color(255, 255, 235));
        individualLiftRow.setBackground(new Color(40, 40, 43));
        individualLiftRow.revalidate();


        cancelTheFollow.addActionListener(e -> {


            SwingWorker<Void, Void> cancelButtonBackground = new SwingWorker<>() {
                @Override
                protected Void doInBackground() {
                    try {
                        deleteRelationshipFromChosenLiftAndUserID(chosenLift, currentUser.getUserID());
                        increaseTheAvailableSeatNumberOfTheChosenLift(chosenLift);
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

    private void increaseTheAvailableSeatNumberOfTheChosenLift(Lift chosenLift) throws SQLException {

        Connection connection = DatabaseManager.getConnection();
        Statement statement = connection.createStatement();

        String changePasswordSql = "UPDATE biluber_lifts SET avaiable_seats = ? WHERE lift_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(changePasswordSql);
        preparedStatement.setInt(1, chosenLift.getAvailableSeat() + 1);
        preparedStatement.setInt(2, chosenLift.getIdOfTheLift());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        statement.close();


    }

    private void deleteRelationshipFromChosenLiftAndUserID(Lift chosenLift, int userID) throws SQLException {

        String sqlForDeleteChosenRelation = "DELETE FROM biluber_user_lift_relation WHERE user_id =? and lift_id =?";

        Connection connection = DatabaseManager.getConnection();
        PreparedStatement deleteOldLifts = connection.prepareStatement(sqlForDeleteChosenRelation);
        deleteOldLifts.setInt(1, userID);
        deleteOldLifts.setInt(2, chosenLift.getIdOfTheLift());

        deleteOldLifts.executeUpdate();
        deleteOldLifts.close();
    }


    private void deleteRelationshipsOfOldLiftFromDB() throws SQLException {

        String sqlForDeleteOldLifts = "DELETE FROM biluber_user_lift_relation WHERE lift_id IN (SELECT lift_id from biluber_lifts WHERE start_time <= ?)";

        String timeStampOfLift = dateAndHourWithoutMilliseconds.format(new Timestamp(System.currentTimeMillis()));

        Connection connection = DatabaseManager.getConnection();
        PreparedStatement deleteOldLifts = connection.prepareStatement(sqlForDeleteOldLifts);
        deleteOldLifts.setString(1, timeStampOfLift);

        deleteOldLifts.executeUpdate();
        deleteOldLifts.close();
    }

    private void deleteOldLiftsFromDB() throws SQLException {

        String sqlForDeleteOldLifts = "DELETE FROM biluber_lifts WHERE start_time <= ?";

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

        String queryForPullingOfferedLifts = "SELECT * from biluber_lifts where driverUserID= ? ORDER BY start_time ASC ";

        PreparedStatement preparedStatementForExercises = connection.prepareStatement(queryForPullingOfferedLifts);

        preparedStatementForExercises.setInt(1, currentUser.getUserID());

        ResultSet resultsSetForPulledOfferedLifts = preparedStatementForExercises.executeQuery();

        curretsLiftsGivenJpanel.removeAll();
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

            displayLiftsOfferedByYou(createdOfferedLift, curretsLiftsGivenJpanel);
        }

        preparedStatementForExercises.close();
        resultsSetForPulledOfferedLifts.close();
    }

    private void displayLiftsOfferedByYou(Lift chosenLift, JPanel jpanelToUse) throws SQLException {
        Font liftInformationFont = new Font("Times New Roman", Font.BOLD, 18);
        Font buttonFont = new Font("Times New Roman", Font.BOLD, 14);

        JPanel individualLiftRow = new JPanel();
        individualLiftRow.setLayout(new BorderLayout());
        individualLiftRow.setForeground(new Color(255, 255, 235));
        individualLiftRow.setBackground(new Color(40, 40, 43));
        JLabel liftInfoLabel = new JLabel(chosenLift.toString());
        Box boxForUserNameAndPP = Box.createHorizontalBox();
        JLabel JLabelForDriverPP = new JLabel();
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
                        deleteTheChosenLiftFromDB(chosenLift);
                        updateUI();
                        decreaseUserPoints(currentUser, 10);
                        JOptionPane.showMessageDialog(myMainFrame, "Lift with these details has been canceled! \n" + chosenLift);
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

    private void deleteTheChosenLiftFromDB(Lift chosenLift) throws SQLException {

        String sqlForDeleteOfferedLift = "DELETE FROM biluber_lifts WHERE lift_id = ?";
        String sqlForDeleteOfferedLiftRelations = "DELETE FROM biluber_user_lift_relation WHERE lift_id = ?";

        Connection connection = DatabaseManager.getConnection();

        PreparedStatement deleteOfferedLiftsRelations = connection.prepareStatement(sqlForDeleteOfferedLiftRelations);
        deleteOfferedLiftsRelations.setInt(1, chosenLift.getIdOfTheLift());
        deleteOfferedLiftsRelations.executeUpdate();


        PreparedStatement deleteOfferedLifts = connection.prepareStatement(sqlForDeleteOfferedLift);
        deleteOfferedLifts.setInt(1, chosenLift.getIdOfTheLift());
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
