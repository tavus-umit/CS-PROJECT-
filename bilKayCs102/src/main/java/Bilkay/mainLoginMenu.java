package Bilkay;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class mainLoginMenu {

    private final JFrame myMainFrame;
    private JPanel mainPanelForMenu;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JPanel topLeftPanel;
    private JPanel bottomLeftPanel;
    private JLabel logoLabel;
    private JLabel mottoLabel;
    private JPanel rightCenter;
    private JTextField usernameTextField;
    private JButton loginButton;
    private JButton registerButton;
    private JButton forgotPasswordButton;
    private JPasswordField passwordPasswordField;
    private JLabel userIcon;
    private JLabel passwordLabel;

    public user currentUser;


    public mainLoginMenu(JFrame myMainFrameInput) {

        this.myMainFrame = myMainFrameInput;

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginTheUser();

            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openTheRegisterMenu();
            }
        });
        forgotPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartUsernameAndPassword();
            }
        });
    }

    private void restartUsernameAndPassword() {
        emilSenderBilkay sendEmails = new emilSenderBilkay();

        String webmailForRESTART = JOptionPane.showInputDialog(myMainFrame, "Enter your webmail address connected to your account", "Forgot Password", JOptionPane.INFORMATION_MESSAGE);

        if (webmailForRESTART.length() < 14) {
            JOptionPane.showMessageDialog(myMainFrame, "Enter a valid webmail address", "Forgot Password", JOptionPane.INFORMATION_MESSAGE);

            return;
        }

        String emailBodyTextForCode = "Your Bilkay Username and Password are: ";
        String emailSubjectTextForCode = "Bilkay Username and Password";

        if (sendEmails.sendEmail(webmailForRESTART, emailSubjectTextForCode, emailBodyTextForCode)) {
            JOptionPane.showMessageDialog(myMainFrame, "Your credentials are sent to you webmail address", "Forgot Password", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(myMainFrame, "Error in the process, please try again later", "Forgot Password", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
    }
    private void openTheRegisterMenu() {
        myMainFrame.setContentPane(new mainRegisterMenu(myMainFrame).getMainPanelForMenu());
        myMainFrame.revalidate();
        myMainFrame.repaint();
    }

    private void loginTheUser() {
        String username = usernameTextField.getText();


        char[] passwordArray = passwordPasswordField.getPassword();
        StringBuilder passwordBuilder = new StringBuilder();
        for (char c : passwordArray) {
            passwordBuilder.append(c);
        }

        String password = passwordBuilder.toString();
        System.out.println(password);
        System.out.println(username);

        if (username.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(myMainFrame, "Username or password is empty.", "Login Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


        currentUser = getTheAccountFromTheDB(username, password);

        if (currentUser != null) {
            myMainFrame.setContentPane(new mainDashboardMenu(myMainFrame,currentUser).getMainPanelForMenu());
            myMainFrame.revalidate();
            myMainFrame.repaint();
        }


    }

    private user getTheAccountFromTheDB(String username, String password) {
        user currentUser = null;

        try {
            Connection connection = DatabaseManager.getConnection();
            Statement statement = connection.createStatement();

            String insertQuery = "SELECT * FROM users WHERE username=? AND password=?";

            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultsSetForCurrentUser = preparedStatement.executeQuery();

            if ( resultsSetForCurrentUser.next()) {
                currentUser = new user(resultsSetForCurrentUser.getString("name_surname"),
                                        resultsSetForCurrentUser.getString("username"),
                                        resultsSetForCurrentUser.getString("password"),
                        resultsSetForCurrentUser.getString("webmail"),
                        null,
                        null,
                        resultsSetForCurrentUser.getString("role"));
            }

            resultsSetForCurrentUser.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentUser;
    }



    public JPanel getMainPanelForMenu() {
        return mainPanelForMenu;
    }


}
