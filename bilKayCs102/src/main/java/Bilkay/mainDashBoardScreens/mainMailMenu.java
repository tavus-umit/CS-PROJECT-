package Bilkay.mainDashBoardScreens;

import Bilkay.Email_Keyboard_DatabaseServices.DatabaseManager;
import Bilkay.UserRelatedServices.user;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;

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

    public mainMailMenu(JFrame myMainFrame, user currentUser) {
        this.currentUser = currentUser;
        this.myMainFrame = myMainFrame;

        messageJpanel.setLayout(new BoxLayout(messageJpanel, BoxLayout.Y_AXIS));
        for (int i = 0; i < 5; i++) {
            displayMessage("Kilic", "Naberrr", "12-10-2023");
        }


        sumbitInterests.addActionListener(e -> {

            try {
                if (checkTheNewUsername()) {

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

                    System.out.println("done");

                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        });
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
        connection.close();

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
        connection.close();
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
            connection.close();
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
        connection.close();
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
        connection.close();
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


        // Add a separator after each message
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setPreferredSize(new Dimension(messageJpanel.getWidth(), 5));
        separator.setBackground(new Color(40, 40, 43));
        separator.setForeground(new Color(255, 255, 235));

        // Add the message label and separator to the messagePanel
        messageJpanel.add(messageLabel);
        messageJpanel.add(separator);

        // Refresh the layout and scroll to the bottom
        messageJpanel.revalidate();
        messageJpanel.repaint();
    }


    public JPanel getMainPanelForMenu() {
        return mainPanelForMenu;
    }
}
