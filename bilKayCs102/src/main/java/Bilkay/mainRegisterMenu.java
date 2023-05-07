package Bilkay;

import javax.swing.*;
import java.awt.*;
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
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myMainFrame.setContentPane(new mainDashboardMenu(myMainFrame).getMainPanelForMenu());
                myMainFrame.revalidate();
                myMainFrame.repaint();
            }
        });
    }

    private void registerTheUser() {
        System.out.println(interestsJList.getSelectedValuesList().getClass());

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
