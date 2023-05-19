package Bilkay.BilUber;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BilUberMainPage extends JPanel{
    private JPanel BilUberMainPageMainPanel;
    private JButton PassengerButton;
    private JList <Lift> LiftList;
    private JPanel CurrentLiftPanel;
    private JPanel ButtonsPanel;
    private JButton AddLiftButton;
    private JButton RefreshButton;
    private JButton MainPageButton;
    private JButton DriverButton;
    private ArrayList<Lift> currentLifts;
    private DefaultListModel model;
    private static Bilkay.user user;
    private static Driver driver;
    private BilUberDriverPage driverPage;
    private BilUberPassengerPage passengerPage;
    private BilUberFrame frame;


    // Constructor
    public BilUberMainPage(Bilkay.user newUser)
    {
        user = newUser;
        driver = new Driver(user);
        this.currentLifts = BilUber.availableLifts;
        this.model = new DefaultListModel<>();
        this.LiftList.setModel(this.model);
        this.driverPage = null;
        this.passengerPage = null;
        this.frame = null;

        liftLister();

        RefreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getCurrentLifts().size() == 0)
                {
                    JOptionPane.showMessageDialog(null, "There are not any available lifts formed", "Attention!", JOptionPane.INFORMATION_MESSAGE);
                }
                liftLister();
            }
        });
        MainPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        DriverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getFrame().setContentPane(getDriverPage().getBilUberDriverPageMainPanel());
            }
        });
        PassengerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getFrame().setContentPane(getPassengerPage().getBilUberPassengerPageMainPanel());
            }
        });
    }

    // Getters and Setters
    public JPanel getBilUberMainPanel() {
        return BilUberMainPageMainPanel;
    }
    public void setBilUberMainPanel(JPanel bilUberMainPanel) {
        BilUberMainPageMainPanel = bilUberMainPanel;
    }

    public JPanel getCurrentLiftPanel() {
        return CurrentLiftPanel;
    }

    public void setCurrentLiftPanel(JPanel currentLiftPanel) {
        CurrentLiftPanel = currentLiftPanel;
    }

    public BilUberDriverPage getDriverPage() {
        return driverPage;
    }

    public void setDriverPage(BilUberDriverPage driverPage) {
        this.driverPage = driverPage;
    }

    public BilUberPassengerPage getPassengerPage() {
        return passengerPage;
    }

    public void setPassengerPage(BilUberPassengerPage passengerPage) {
        this.passengerPage = passengerPage;
    }

    public BilUberFrame getFrame() {
        return frame;
    }

    public void setFrame(BilUberFrame frame) {
        this.frame = frame;
    }

    public JList getLiftList1() {
        return LiftList;
    }
    public void setLiftList1(JList<Lift> Liftlist) {
        this.LiftList = Liftlist;
    }
    public ArrayList<Lift> getCurrentLifts() {
        return currentLifts;
    }
    public void setCurrentLifts(ArrayList<Lift> currentlifts) {
        this.currentLifts = currentlifts;
    }

    public static Bilkay.user getUser() {
        return user;
    }

    public static void setUser(Bilkay.user newUser) {
        user = newUser;
    }

    public void liftLister()
    {
        for(int i = 0; i < this.currentLifts.size();i++)
        {
            if(liftChecker(this.currentLifts.get(i)))
            {
                this.model.addElement(this.currentLifts.get(i));
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
