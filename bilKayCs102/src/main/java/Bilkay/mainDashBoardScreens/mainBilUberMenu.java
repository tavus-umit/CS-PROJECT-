package Bilkay.mainDashBoardScreens;

import Bilkay.BilUber.Lift;
import Bilkay.UserRelatedServices.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainBilUberMenu {
    private final user currentUser;
    private final JFrame myMainFrame;
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
    private JLabel inboxJLabel;
    private JScrollPane scrollPaneForMessages;
    private JPanel curretsLiftsGivenJpanel;
    private JPanel nothJpanelForGivenLifts;
    private JPanel centralPanelForGivenLifts;
    private JPanel givenLiftsOuterJpanel;
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
    private JFormattedTextField formattedTextField1;
    private JLabel dateTimeJlabel;
    private JTextField finalDestinationJText;
    private JButton offerLiftButton;

    public mainBilUberMenu(JFrame myMainFrame, user currentUser) {
        this.currentUser = currentUser;
        this.myMainFrame = myMainFrame;
        bilUberJpanel.setLayout(new BoxLayout(bilUberJpanel, BoxLayout.Y_AXIS));


    }


    public void displayLiftsThatYouOffer(Lift chosenLift) {

        displayForLiftWithCancelButton(chosenLift, curretsLiftsGivenJpanel);
    }

    public void displayChosenUsedLifts(Lift chosenLift) {

        displayForLiftWithCancelButton(chosenLift, liftsAcceptedViewJPanel);
    }

    private void displayForLiftWithCancelButton(Lift chosenLift, JPanel curretsLiftsGivenJpanel) {
        Font liftInformationFont = new Font("Times New Roman", Font.BOLD, 20);
        Font buttonFont = new Font("Times New Roman", Font.BOLD, 16);

        JPanel individualLiftRow = new JPanel();
        individualLiftRow.setLayout(new BorderLayout());
        individualLiftRow.setForeground(new Color(255,255,235));
        individualLiftRow.setBackground(new Color(40,40,43));

        JLabel liftInfoLabel = new JLabel(chosenLift.toString());


        JButton cancelTheLiftButton = new JButton("Cancel");
        cancelTheLiftButton.setFont(buttonFont);
        cancelTheLiftButton.setBackground(new Color(255,255,235));
        cancelTheLiftButton.setForeground(new Color(40,40,43));


        JPanel panelForButtons = new JPanel();
        panelForButtons.setLayout(new GridLayout(1, 2));
        panelForButtons.add(cancelTheLiftButton);

        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setPreferredSize(new Dimension(curretsLiftsGivenJpanel.getWidth(), 5));
        separator.setBackground(new Color(40, 40, 43));
        separator.setForeground(new Color(255, 255, 235));

        individualLiftRow.add(liftInfoLabel, BorderLayout.CENTER);
        individualLiftRow.add(panelForButtons, BorderLayout.EAST);
        individualLiftRow.add(separator, BorderLayout.SOUTH);

        liftInfoLabel.setVerticalAlignment(SwingConstants.CENTER);

        cancelTheLiftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(individualLiftRow, "Lift canceled!");
            }
        });

        liftInfoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        liftInfoLabel.setBackground(new Color(40, 40, 43));
        liftInfoLabel.setForeground(new Color(255, 255, 235));
        liftInfoLabel.setFont(liftInformationFont);

        curretsLiftsGivenJpanel.add(individualLiftRow);
        curretsLiftsGivenJpanel.revalidate();
        curretsLiftsGivenJpanel.repaint();
    }



    public void displayCurrentlyAvailableLifts(Lift chosenLift) {

        Font liftInformationFont = new Font("Times New Roman", Font.BOLD, 20);
        Font buttonFont = new Font("Times New Roman", Font.BOLD, 16);

        JPanel individualLiftRowForCurrentLifts = new JPanel();
        individualLiftRowForCurrentLifts.setLayout(new BorderLayout());
        individualLiftRowForCurrentLifts.setForeground(new Color(255,255,235));
        individualLiftRowForCurrentLifts.setBackground(new Color(40,40,43));

        JLabel liftInfoLabel = new JLabel(chosenLift.toString());

        JButton addButton = new JButton();
        addButton.setFont(buttonFont);
        addButton.setForeground(new Color(40,40,43));
        addButton.setBackground(new Color(40,40,43));
        addButton.setIcon(new ImageIcon("./src\\main\\resources\\iconsForApp\\checkIcon.png"));


        JPanel panelForButtons = new JPanel();
        panelForButtons.setLayout(new GridLayout(1, 2));
        panelForButtons.add(addButton);

        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setPreferredSize(new Dimension(bilUberJpanel.getWidth(), 5));
        separator.setBackground(new Color(40, 40, 43));
        separator.setForeground(new Color(255, 255, 235));

        individualLiftRowForCurrentLifts.add(liftInfoLabel, BorderLayout.CENTER);
        individualLiftRowForCurrentLifts.add(panelForButtons, BorderLayout.EAST);
        individualLiftRowForCurrentLifts.add(separator, BorderLayout.SOUTH);

        liftInfoLabel.setVerticalAlignment(SwingConstants.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(individualLiftRowForCurrentLifts, "Lift confirmed!");
            }
        });

        liftInfoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        liftInfoLabel.setBackground(new Color(40, 40, 43));
        liftInfoLabel.setForeground(new Color(255, 255, 235));
        liftInfoLabel.setFont(liftInformationFont);

        bilUberJpanel.add(individualLiftRowForCurrentLifts);
        bilUberJpanel.revalidate();
        bilUberJpanel.repaint();
    }

    public JPanel getMainPanelForMenu() {
        return mainPanelForMenu;
    }
}
