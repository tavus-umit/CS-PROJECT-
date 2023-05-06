package Bilkay;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class mainLoginMenu {


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


    public mainLoginMenu() {


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginTheUser();

            }
        });
    }

    private void loginTheUser() {
    }

    public JPanel getMainPanelForMenu() {
        return mainPanelForMenu;
    }


}
