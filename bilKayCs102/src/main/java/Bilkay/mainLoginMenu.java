package Bilkay;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Arrays;

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

    public Account accountOfTheUser;


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


        accountOfTheUser = theAuthenticatedAccount(username, password);




    }

    private Account theAuthenticatedAccount(String username, String password) {
        return null;
    }

    public JPanel getMainPanelForMenu() {
        return mainPanelForMenu;
    }


}
