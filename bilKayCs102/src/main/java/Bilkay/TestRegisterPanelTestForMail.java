package Bilkay;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TestRegisterPanelTestForMail extends JPanel {
    JLabel email;
    JTextField emailField;
    JLabel password;
    JTextField passwordField;
    RButton register;
    long VerifyCode;

    TestRegisterPanelTestForMail()
    {
        this.VerifyCode = 0;
        email = new JLabel("Webmail: ");
        this.add(email);
        emailField = new JTextField("umit.tavus@ug.bilkent.edu.tr");
        emailField.setSize(50,50);
        this.add(emailField);
        password = new JLabel("Password: ");
        this.add(password);
        passwordField = new JTextField("zattirizortzort_2525");
        passwordField.setSize(50,50);
        this.add(passwordField);
        register = new RButton();
        register.setSize(100,50);
        register.setText("Register");
        register.addActionListener(register);
        this.add(register);
    }

    public class RButton extends JButton implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == this)
            {
                if(isEmailValid(emailField.getText()))
                {
                    Random rand = new Random();
                    VerifyCode = rand.nextLong(100000,1000000);
                    SendingVerificationEmail se = new SendingVerificationEmail(emailField.getText());
                    se.setProperties();
                    se.setMessage(VerifyCode);
                    if (se.sendMail())
                    {
                        String code = JOptionPane.showInputDialog(null, "Enter the verification code", "Verification Code", 1);
                        if(Long.parseLong(code) == VerifyCode)
                        {
                            JOptionPane.showMessageDialog(null, "Your webmail is successfully verified", "Webmail Verification", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"Wrong Verification Code", "Webmail Validation Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null,"Please use your Bilkent Webmail", "Webmail Validation Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        }
    }
    public boolean isEmailValid(String email)
    {
        String domainAddress = email.substring(email.length()-14,email.length());
        if(domainAddress.equals("bilkent.edu.tr"))
        {
            return true;
        }
        else{
            return false;
        }

    }
}
