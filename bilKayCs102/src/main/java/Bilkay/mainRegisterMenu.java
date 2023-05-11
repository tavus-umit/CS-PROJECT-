package Bilkay;

import javax.swing.*;
import java.awt.event.*;


public class mainRegisterMenu {

    private final JFrame myMainFrame;

    private final keyboardControl keyboardController;

    private JPanel mainPanelForMenu;
    private JPanel rightPanel;
    private JPanel rightCenter;
    private JTextField usernameTextField;
    private JLabel passwordLabel;
    private JButton registerButton;
    private JPasswordField passwordPasswordField;
    private JLabel userIcon;
    private JPanel leftPanel;
    private JPanel topLeftPanel;
    private JLabel logoLabel;
    private JPanel bottomLeftPanel;
    private JLabel mottoLabel;
    private JList<String> interestsJList;
    private JScrollPane interestScrollPane;
    private JPasswordField confirmPasswordField;
    private JLabel passwordLabel2;
    private JLabel emailIconLabel;
    private JTextField emailJtextField;
    private JLabel nameSurbaneJlabel;
    private JTextField nameSurnameJTextField;


    public mainRegisterMenu(JFrame myMainFrameInput) {
        this.myMainFrame = myMainFrameInput;
        this.keyboardController = new keyboardControl();
        fillTheJList();

        interestsJList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                keyboardController.holdCTRL();
            }
        });
        interestsJList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                keyboardController.releaseCTRL();
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                registerTheUser();

            }
        });
    }

    private void registerTheUser() {
        String username = usernameTextField.getText();

        char[] passwordArray = passwordPasswordField.getPassword();
        StringBuilder passwordBuilder = new StringBuilder();
        for (char c : passwordArray) {
            passwordBuilder.append(c);
        }
        String password = passwordBuilder.toString();

        char[] confirmPasswordArray = confirmPasswordField.getPassword();
        StringBuilder confirmPasswordBuilder = new StringBuilder();
        for (char c : confirmPasswordArray) {
            confirmPasswordBuilder.append(c);
        }
        String confirmedPassword = confirmPasswordBuilder.toString();

        if (!password.equals(confirmedPassword)) {
            JOptionPane.showMessageDialog(myMainFrame, "Passwords do not match.", "Register Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (interestsJList.getSelectedValuesList().isEmpty()) {
            JOptionPane.showMessageDialog(myMainFrame, "Your interests list is empty, please choose one or more.", "Register Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        myMainFrame.setContentPane(new mainDashboardMenu(myMainFrame).getMainPanelForMenu());
        myMainFrame.revalidate();
        myMainFrame.repaint();
    }



    private void fillTheJList() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String like : Account.likes) {
            listModel.addElement(like);
        }
        interestsJList.setModel(listModel);

    }

    public JPanel getMainPanelForMenu() {
        return mainPanelForMenu;
    }


}
