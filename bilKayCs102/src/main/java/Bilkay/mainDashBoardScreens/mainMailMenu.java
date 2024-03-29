package Bilkay.mainDashBoardScreens;

import Bilkay.Email_Keyboard_DatabaseServices.DatabaseManager;
import Bilkay.UserRelatedServices.user;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class mainMailMenu {
    private final user currentUser;
    private final JFrame myMainFrame;
    private JPanel mainPanelForMenu;
    private JLabel logoJPanel;
    private JScrollPane mainJScrollPane;
    private JPanel mainJPanelForScroll;
    private JPanel InboxProgramPanel;
    private JLabel inboxJLabel;
    private JLabel userIconJLabel;
    private JPanel northJpanel;
    private JPanel centerJpanel;
    private JPanel messageJpanel;
    private JPanel sendMessageMainPanel;
    private JPanel MessageInputJpanel;
    private JButton sumbitInterests;
    private JScrollPane scrollPaneForMessages;
    private JLabel sendMessageJlabel;
    private JTextField sendUsernameJText;
    private JTextArea messageTextArea;
    private JLabel chooseUserJLabel;

    private JPanel rightMainDashboardPanel;
    private String userName;




    public mainMailMenu(JFrame myMainFrame, user currentUser, JPanel rightMainDashboardPanel, String userName) {

        this(myMainFrame, currentUser, rightMainDashboardPanel);
        JScrollBar scrollPaneBar = mainJScrollPane.getVerticalScrollBar();
        this.userName = userName;
        sendUsernameJText.setText(userName);
        scrollPaneBar.setValue( scrollPaneBar.getMaximum() );
    }

    public mainMailMenu(JFrame myMainFrame, user currentUser, JPanel rightMainDashboardPanel) {
        this.rightMainDashboardPanel = rightMainDashboardPanel;
        this.currentUser = currentUser;
        this.myMainFrame = myMainFrame;
        messageJpanel.setLayout(new BoxLayout(messageJpanel, BoxLayout.Y_AXIS));

        try {
            displayTheCurrentInbox();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        SwingWorker<Void, Void> updateInbox = new SwingWorker<Void, Void>() {
            @SuppressWarnings({"BusyWait", "InfiniteLoopStatement"})
            @Override
            protected Void doInBackground() throws Exception {
                while (true) {
                    displayTheCurrentInbox();
                    Thread.sleep(10000);
                }
            }
        };

        updateInbox.execute();


        sumbitInterests.addActionListener(e -> {

            try {
                if (checkTheNewUsername()) {

                    if (sendUsernameJText.getText().isEmpty() || messageTextArea.getText().isEmpty()) {

                        JOptionPane.showMessageDialog(myMainFrame, "Please Enter Valid Username and Message", "Send Message", JOptionPane.ERROR_MESSAGE);
                        return;
                    }


                    String toName = sendUsernameJText.getText();
                    String messageContent = messageTextArea.getText();

                    int idOfTheConvo = checkExistingConvoAmongUsers();

                    if (idOfTheConvo > 0) {

                        sendMessageToUser(idOfTheConvo);

                    } else {
                        createNewConvo();
                        idOfTheConvo = checkExistingConvoAmongUsers();
                        sendMessageToUser(idOfTheConvo);

                    }
                    messageJpanel.removeAll();

                    try {
                        displayTheCurrentInbox();
                    } catch (SQLException d) {
                        throw new RuntimeException(d);
                    }

                    messageJpanel.revalidate();
                    messageJpanel.repaint();

                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        });
    }

    private void displayTheCurrentInbox() throws SQLException {

        String checkTheCurrentMessages = "SELECT * FROM messages_in_convo WHERE reciever_id = ? ORDER BY timestamp DESC";

        Connection connection = DatabaseManager.getConnection();
        PreparedStatement preparedStatementForExercises = connection.prepareStatement(checkTheCurrentMessages);
        preparedStatementForExercises.setInt(1, currentUser.getUserID());

        ResultSet resultsSetForExerciseNames = preparedStatementForExercises.executeQuery();

        messageJpanel.removeAll();

        while (resultsSetForExerciseNames.next()) {
            String messageBody = resultsSetForExerciseNames.getString("content_of_message");
            String fromUserID = resultsSetForExerciseNames.getString("sender_id");
            String timeStampOfTheMessage = resultsSetForExerciseNames.getString("timestamp");
            String senderUserName = findSenderUserName(fromUserID);

            if (senderUserName != null) {
                displayMessage(senderUserName, messageBody, timeStampOfTheMessage);
            }


        }

        preparedStatementForExercises.close();
        resultsSetForExerciseNames.close();


    }

    private String findSenderUserName(String fromUserID) throws SQLException {
        String senderUsernameFound = null;

        Connection connection = DatabaseManager.getConnection();

        String queryForExercisesFromProgram = "SELECT username from users where user_id= ?";

        PreparedStatement preparedStatementForExercises = connection.prepareStatement(queryForExercisesFromProgram);

        preparedStatementForExercises.setString(1, fromUserID);

        ResultSet resultsSetForExerciseNames = preparedStatementForExercises.executeQuery();


        while (resultsSetForExerciseNames.next()) {
            senderUsernameFound = resultsSetForExerciseNames.getString("username");
            return senderUsernameFound;
        }

        preparedStatementForExercises.close();
        resultsSetForExerciseNames.close();
        return senderUsernameFound;

    }

    private void sendMessageToUser(int idMessageOfConvo) throws SQLException {

        String toUsername = sendUsernameJText.getText();
        int toUserID = getToUserIDFromUsername(toUsername);
        int fromUserId = currentUser.getUserID();
        String messageContent = messageTextArea.getText();

        SimpleDateFormat dateAndHourWithoutMilliseconds = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeStampOfTheMessage = dateAndHourWithoutMilliseconds.format(new Timestamp(System.currentTimeMillis()));


        Connection connection = DatabaseManager.getConnection();
        Statement statement = connection.createStatement();


        String insertQuery = "INSERT INTO messages_in_convo (conversation_id,sender_id,reciever_id,content_of_message,timestamp) VALUES (?, ?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        preparedStatement.setInt(1, idMessageOfConvo);
        preparedStatement.setInt(2, fromUserId);
        preparedStatement.setInt(3, toUserID);
        preparedStatement.setString(4, messageContent);
        preparedStatement.setString(5, timeStampOfTheMessage);


        if (preparedStatement.executeUpdate() == 1) {

            JOptionPane.showMessageDialog(myMainFrame, "Message Has Been Sent", "Send Message", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(myMainFrame, "A Problem Occurred While Sending The Message, Please Try Again Later!", "Send Message", JOptionPane.ERROR_MESSAGE);
        }
        preparedStatement.close();
        statement.close();

    }

    private int checkExistingConvoAmongUsers() throws SQLException {

        String toUsername = sendUsernameJText.getText();

        Connection connection = DatabaseManager.getConnection();

        String queryForExercisesFromProgram = "SELECT conversation_id FROM conversations WHERE user2_id OR user1_id IN (SELECT user_id from users where username= ?)";

        PreparedStatement preparedStatementForExercises = connection.prepareStatement(queryForExercisesFromProgram);

        preparedStatementForExercises.setString(1, toUsername);

        ResultSet resultsSetForExerciseNames = preparedStatementForExercises.executeQuery();

        int value = -1;


        while (resultsSetForExerciseNames.next()) {
            value = resultsSetForExerciseNames.getInt("conversation_id");
            return value;
        }


        preparedStatementForExercises.close();
        resultsSetForExerciseNames.close();
        return value;
    }

    private void createNewConvo() {
        try {
            Connection connection = DatabaseManager.getConnection();
            Statement statement = connection.createStatement();

            String toUsername = sendUsernameJText.getText();
            int toUserID = getToUserIDFromUsername(toUsername);

            String insertQuery = "INSERT INTO conversations (user1_id,user2_id) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, currentUser.getUserID());
            preparedStatement.setInt(2, toUserID);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getToUserIDFromUsername(String toUsername) throws SQLException {


        Connection connection = DatabaseManager.getConnection();

        String queryForExercisesFromProgram = "SELECT user_id from users where username= ?";

        PreparedStatement preparedStatementForExercises = connection.prepareStatement(queryForExercisesFromProgram);

        preparedStatementForExercises.setString(1, toUsername);

        ResultSet resultsSetForExerciseNames = preparedStatementForExercises.executeQuery();

        int value = -1;


        while (resultsSetForExerciseNames.next()) {
            value = resultsSetForExerciseNames.getInt("user_id");
            return value;

        }

        preparedStatementForExercises.close();
        resultsSetForExerciseNames.close();
        return value;
    }

    private boolean checkTheNewUsername() throws SQLException {

        String toUsername = sendUsernameJText.getText();
        String checkUsernames = "SELECT COUNT(*) FROM users WHERE username = ?";

        Connection connection = DatabaseManager.getConnection();
        PreparedStatement checkUsernameStatement = connection.prepareStatement(checkUsernames);

        checkUsernameStatement.setString(1, toUsername);
        ResultSet resultSet = checkUsernameStatement.executeQuery();
        resultSet.next();

        if (resultSet.getInt(1) > 0) {
            return true;
        }
        resultSet.close();
        JOptionPane.showMessageDialog(myMainFrame, "Username Doest Not Exist", "Username Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    public void displayMessage(String senderName, String message, String timeStamp) {


        String formattedMessage = String.format("<html><pre>From: %-15s                    Date/Time: %-20s\n\n%s</pre></html>", senderName, timeStamp, message);


        JLabel messageLabel = new JLabel(formattedMessage);
        messageLabel.setText(formattedMessage);
        messageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        messageLabel.setBackground(new Color(40, 40, 43));
        messageLabel.setForeground(new Color(255, 255, 235));
        Font font = new Font("Times New Roman", Font.BOLD, 18);
        messageLabel.setFont(font);


        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setPreferredSize(new Dimension(messageJpanel.getWidth(), 5));
        separator.setBackground(new Color(40, 40, 43));
        separator.setForeground(new Color(255, 255, 235));

        messageJpanel.add(messageLabel);
        messageJpanel.add(separator);

        messageJpanel.revalidate();
        messageJpanel.repaint();
    }


    public JPanel getMainPanelForMenu() {
        return mainPanelForMenu;
    }

}
