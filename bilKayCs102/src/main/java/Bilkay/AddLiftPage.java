package Bilkay;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddLiftPage extends JFrame {
    private JPanel AddLiftPageMainPanel;
    private JTextField DateField;
    private JLabel Time;
    private JTextField TimeField;
    private JLabel From;
    private JTextField FromField;
    private JTextField ToField;
    private JTextField FeeField;
    private JButton confirmButton;
    private JLabel To;
    private JLabel Fee;
    private BilUberDriverPage page;

    // Constructor
    public AddLiftPage(Driver driver, BilUberDriverPage page)
    {

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dateStr = DateField.getText();
                int day = Integer.parseInt(dateStr.substring(0, 2));
                int month = Integer.parseInt(dateStr.substring(3, 5));
                int year = Integer.parseInt(dateStr.substring(6));
                Date date = new Date(day, month, year);

                String timeStr = TimeField.getText();
                String hours = timeStr.substring(0, 2);
                String minutes = timeStr.substring(3);
                Time time = new Time(hours, minutes);

                String from = FromField.getText();
                String to = ToField.getText();
                Route route = new Route(from, to);

                int fee = Integer.parseInt(FeeField.getText());
                driver.journeyCreator(date,time,route,fee);
                driver.journeyUploader();
                dispose();
                page.liftLister();
                JOptionPane.showMessageDialog(null,"A New Journey Is Added Successfully", "Confirm Journey", JOptionPane.INFORMATION_MESSAGE);

            }
        });
    }
    // Getters and Setters
    public JPanel getAddLiftPageMainPanel() {
        return AddLiftPageMainPanel;
    }

    public void setAddLiftPageMainPanel(JPanel addLiftPageMainPanel) {
        AddLiftPageMainPanel = addLiftPageMainPanel;
    }

    public JTextField getDateField() {
        return DateField;
    }

    public void setDateField(JTextField dateField) {
        DateField = dateField;
    }

    public JLabel getTime() {
        return Time;
    }

    public void setTime(JLabel time) {
        Time = time;
    }

    public JTextField getTimeField() {
        return TimeField;
    }

    public void setTimeField(JTextField timeField) {
        TimeField = timeField;
    }

    public JLabel getFrom() {
        return From;
    }

    public void setFrom(JLabel from) {
        From = from;
    }

    public JTextField getFromField() {
        return FromField;
    }

    public void setFromField(JTextField fromField) {
        FromField = fromField;
    }

    public JTextField getToField() {
        return ToField;
    }

    public void setToField(JTextField toField) {
        ToField = toField;
    }

    public JTextField getFeeField() {
        return FeeField;
    }

    public void setFeeField(JTextField feeField) {
        FeeField = feeField;
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }

    public void setConfirmButton(JButton confirmButton) {
        this.confirmButton = confirmButton;
    }

    public JLabel getTo() {
        return To;
    }

    public void setTo(JLabel to) {
        To = to;
    }

    public JLabel getFee() {
        return Fee;
    }

    public void setFee(JLabel fee) {
        Fee = fee;
    }
}
