package Bilkay.mainDashBoardScreens;

import Bilkay.UserRelatedServices.user;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

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

    public mainMailMenu(JFrame myMainFrame, user currentUser) {
        this.currentUser = currentUser;
        this.myMainFrame = myMainFrame;

        messageJpanel.setLayout(new BoxLayout(messageJpanel, BoxLayout.Y_AXIS));
        for (int i = 0; i < 100; i++) {
            displayMessage("Kilic", "Naberrr" ,"12-10-2023");
        }


    }

    public void displayMessage(String senderName, String message, String timeStamp) {


        String formattedMessage = String.format("<html><pre>From: %-15s              Date/Time: %-20s\n\n%s</pre></html>",
                senderName, timeStamp, message);


        JLabel messageLabel = new JLabel(formattedMessage);
        messageLabel.setText(formattedMessage);
        messageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        messageLabel.setBackground(new Color(40,40,43));
        messageLabel.setForeground(new Color(255,255,235));
        Font font = new Font("Times New Roman", Font.BOLD, 18);
        messageLabel.setFont(font);


        // Add a separator after each message
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setPreferredSize(new Dimension(messageJpanel.getWidth(), 5));
        separator.setBackground(new Color(40, 40, 43));
        separator.setForeground(new Color(255,255,235));

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
