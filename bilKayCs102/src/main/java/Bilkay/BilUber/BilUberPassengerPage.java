package Bilkay.BilUber;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BilUberPassengerPage extends JPanel{
    private JPanel BilUberPassengerPageMainPanel;
    private JButton button2;
    private JButton PassengerButton;
    private JList <Lift> PassengerList;
    private JPanel PassengerLiftPanel;
    private JPanel ButtonsPanel;
    private JButton AddLiftButton;
    private JButton RefreshButton;
    private JButton MainPageButton;
    private JButton DriverButton;
    private ArrayList<Lift> registeredLifts;
    private DefaultListModel model;
    private static Bilkay.user user;
    private static Passenger passenger;
    private static AddLiftPage addLiftPage;
    private BilUberMainPage mainPage;
    private BilUberDriverPage driverPage;
    private BilUberFrame frame;

    // Constructor
    public BilUberPassengerPage(Bilkay.user newUser)
    {
        user = newUser;
        passenger = new Passenger(user);
        this.mainPage = null;
        this.driverPage = null;
        this.frame = null;
        this.registeredLifts = passenger.getRegisteredLifts();
        this.model = new DefaultListModel<>();
        this.PassengerList.setModel(this.model);

        liftLister();
        RefreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(passenger.getRegisteredLifts().size() == 0)
                {
                    JOptionPane.showMessageDialog(null, "You have no lifts yet", "Attention!", JOptionPane.INFORMATION_MESSAGE);
                }
                liftLister();
            }
        });
        MainPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getFrame().setContentPane(getMainPage().getBilUberMainPanel());
            }
        });
        DriverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getFrame().setContentPane(getDriverPage().getBilUberDriverPageMainPanel());
            }
        });
    }

    // Getters and Setters
    public JPanel getBilUberPassengerPageMainPanel() {
        return BilUberPassengerPageMainPanel;
    }

    public void setBilUberPassengerPageMainPanel(JPanel bilUberPassengerPageMainPanel) {
        BilUberPassengerPageMainPanel = bilUberPassengerPageMainPanel;
    }
    public JList getPassengerList() {
        return PassengerList;
    }
    public void setPassengerList(JList<Lift> passengerList) {
        this.PassengerList = passengerList;
    }

    public JPanel getPassengerLiftPanel() {
        return PassengerLiftPanel;
    }

    public void setPassengerLiftPanel(JPanel passengerLiftPanel) {
        PassengerLiftPanel = passengerLiftPanel;
    }

    public JPanel getButtonsPanel() {
        return ButtonsPanel;
    }

    public void setButtonsPanel(JPanel buttonsPanel) {
        ButtonsPanel = buttonsPanel;
    }

    public ArrayList<Lift> getRegisteredLifts() {
        return registeredLifts;
    }

    public void setRegisteredLifts(ArrayList<Lift> registeredLifts) {
        this.registeredLifts = registeredLifts;
    }

    public static Bilkay.user getUser() {
        return user;
    }

    public static void setUser(Bilkay.user newUser) {
        user = newUser;
    }

    public BilUberMainPage getMainPage() {
        return mainPage;
    }

    public void setMainPage(BilUberMainPage mainPage) {
        this.mainPage = mainPage;
    }

    public BilUberFrame getFrame() {
        return frame;
    }

    public void setFrame(BilUberFrame frame) {
        this.frame = frame;
    }

    public BilUberDriverPage getDriverPage() {
        return driverPage;
    }

    public void setDriverPage(BilUberDriverPage driverPage) {
        this.driverPage = driverPage;
    }

    public void liftLister()
    {
        for(int i = 0; i < this.registeredLifts.size(); i++)
        {
            if(liftChecker(this.registeredLifts.get(i)))
            {
                this.model.addElement(this.registeredLifts.get(i));
            }
        }
    }
    public boolean liftChecker(Lift lift)
    {
        for (int i = 0 ; i< this.model.size() ;i++)
        {
            if(lift.equals(this.model.get(i)))
            {
                return false;
            }
        }
        return true;
    }




}
