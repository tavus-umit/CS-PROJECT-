package Bilkay.LoginAndRegister;

import Bilkay.Email_Keyboard_DatabaseServices.DatabaseManager;
import Bilkay.Email_Keyboard_DatabaseServices.emilSenderBilkay;
import Bilkay.UserRelatedServices.Category;
import Bilkay.UserRelatedServices.SubCategory;
import Bilkay.UserRelatedServices.user;
import Bilkay.mainDashBoardScreens.mainDashboardMenu;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;

public class mainLoginMenu {

    private final JFrame myMainFrame;
    public user currentUser;
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


    public mainLoginMenu(JFrame myMainFrameInput) {

        this.myMainFrame = myMainFrameInput;

        loginButton.addActionListener(e -> loginTheUser());
        registerButton.addActionListener(e -> openTheRegisterMenu());
        forgotPasswordButton.addActionListener(e -> restartUsernameAndPassword());
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


        if (username.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(myMainFrame, "Username or password is empty.", "Login Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


        currentUser = getTheAccountFromTheDB(username, password);

        if (currentUser != null) {
            currentUser.setChosenCategories(getChosenCategories());
            currentUser.setChosenSubCategories(getChosenSubCategories());

            myMainFrame.setContentPane(new mainDashboardMenu(myMainFrame, currentUser).getMainPanelForMenu());
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

            if (resultsSetForCurrentUser.next()) {
                currentUser = new user(Integer.parseInt(resultsSetForCurrentUser.getString("user_id")), resultsSetForCurrentUser.getString("name_surname"), resultsSetForCurrentUser.getString("username"), resultsSetForCurrentUser.getString("password"), resultsSetForCurrentUser.getString("webmail"), null, null, resultsSetForCurrentUser.getString("role"));
                if (resultsSetForCurrentUser.getString("profile_picture_path") != null) {
                    currentUser.setPathToPP(resultsSetForCurrentUser.getString("profile_picture_path"));
                }
                else {
                    currentUser.setPathToPP("./src\\main\\resources\\iconsForApp\\userIconDashboard.png");
                }

                if (resultsSetForCurrentUser.getString("age") != null) {
                    currentUser.setAge(Integer.parseInt(resultsSetForCurrentUser.getString("age")));
                }
                if (resultsSetForCurrentUser.getString("grade") != null) {
                    currentUser.setGrade(resultsSetForCurrentUser.getString("grade"));
                }
                if (resultsSetForCurrentUser.getString("department") != null) {
                    currentUser.setDepartment(resultsSetForCurrentUser.getString("department"));
                }
                if (resultsSetForCurrentUser.getString("gender") != null) {
                    currentUser.setGender(resultsSetForCurrentUser.getString("gender"));
                }
                if (resultsSetForCurrentUser.getString("bil_Kay_points") != null) {
                    currentUser.setBilkayPoints(resultsSetForCurrentUser.getInt("bil_Kay_points"));
                }


            } else {
                JOptionPane.showMessageDialog(myMainFrame, "Username or password is invalid.", "Login Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            preparedStatement.close();
            resultsSetForCurrentUser.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentUser;
    }

    private ArrayList<SubCategory> getChosenSubCategories() {

        ArrayList<SubCategory> chosenSubCategories = new ArrayList<SubCategory>();

        try {
            Connection connection = DatabaseManager.getConnection();
            Statement statement = connection.createStatement();

            String queryForSubCategoryID = "SELECT interestSubCategory_name FROM interestsubcategory WHERE interestSubCategory_id IN (select interestSubCategory_id From user_interestsubcategory_relation Where user_id = ? )";

            PreparedStatement preparedStatementForSubCatID = connection.prepareStatement(queryForSubCategoryID);
            preparedStatementForSubCatID.setInt(1, currentUser.getUserID());

            ResultSet resultsSetForSubCatIDS = preparedStatementForSubCatID.executeQuery();

            while (resultsSetForSubCatIDS.next()) {
                String value = resultsSetForSubCatIDS.getString("interestSubCategory_name");
                chosenSubCategories.add(new SubCategory(value));
            }

            SubCategory.resetSubCatID();


            preparedStatementForSubCatID.close();
            resultsSetForSubCatIDS.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chosenSubCategories;
    }

    private ArrayList<Category> getChosenCategories() {

        ArrayList<Category> chosenCategories = new ArrayList<Category>();

        try {
            Connection connection = DatabaseManager.getConnection();
            Statement statement = connection.createStatement();

            String queryForCategoryID = "SELECT interestCategory_name FROM interestscategory WHERE interestCategory_id IN (select interestCategory_id From user_interestscategory_relation Where user_id = ? )";

            PreparedStatement preparedStatementForCatID = connection.prepareStatement(queryForCategoryID);
            preparedStatementForCatID.setInt(1, currentUser.getUserID());

            ResultSet resultsSetForCatIDS = preparedStatementForCatID.executeQuery();

            while (resultsSetForCatIDS.next()) {
                String value = resultsSetForCatIDS.getString("interestCategory_name");
                chosenCategories.add(new Category(value));
            }

            Category.resetCategoryIDs();


            preparedStatementForCatID.close();
            resultsSetForCatIDS.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chosenCategories;
    }


    public JPanel getMainPanelForMenu() {
        return mainPanelForMenu;
    }


}
