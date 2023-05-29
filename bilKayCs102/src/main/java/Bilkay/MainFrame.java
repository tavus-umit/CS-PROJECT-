package Bilkay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainFrame extends JFrame {

    private JTextField dateTimeField;
    private JButton updateButton;
    private SimpleDateFormat dateFormat;

    public MainFrame() {
        setTitle("Date Time Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        dateTimeField = new JTextField(20);
        dateTimeField.setText("yyyy-MM-dd HH:mm:ss"); // Set initial placeholder text
        dateTimeField.setForeground(Color.GRAY); // Set placeholder text color
        dateTimeField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dateTimeField.setText(""); // Clear placeholder text when the field is focused
                dateTimeField.setForeground(Color.BLACK); // Change text color to black
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (dateTimeField.getText().isEmpty()) {
                    dateTimeField.setText("yyyy-MM-dd HH:mm:ss"); // Restore placeholder text if the field is left empty
                    dateTimeField.setForeground(Color.GRAY); // Change text color to gray
                }
            }
        });

        updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDateTime();
            }
        });

        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(dateTimeField);
        getContentPane().add(updateButton);

        pack();
        setLocationRelativeTo(null);
    }

    private void updateDateTime() {
        String inputDateTime = dateTimeField.getText();
        try {
            Date parsedDate = dateFormat.parse(inputDateTime);
            String formattedDateTime = dateFormat.format(parsedDate);
            dateTimeField.setText(formattedDateTime);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Invalid date and time format!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
