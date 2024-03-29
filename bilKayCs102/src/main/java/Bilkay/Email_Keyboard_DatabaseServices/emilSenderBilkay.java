package Bilkay.Email_Keyboard_DatabaseServices;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class emilSenderBilkay {
    private final String mailFrom = "deletedForPrivacyMatters";
    private final String appPasswordForGmailSMTP = "deletedForPrivacyMatters";

    public emilSenderBilkay() {
    }

    public static boolean isEmailValid(String email) {
        String domainAddress = email.substring(email.length() - 14);
        return domainAddress.equals("bilkent.edu.tr");

    }

    public boolean sendEmail(String toEmailGmail, String subjectOfTheMail, String bodyOfTheMail) {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session currentSession = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(mailFrom, appPasswordForGmailSMTP);
            }
        });

        try {
            MimeMessage messageToSend = new MimeMessage(currentSession);
            messageToSend.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmailGmail, true));
            messageToSend.setSubject(subjectOfTheMail);
            messageToSend.setText(bodyOfTheMail);
            Transport.send(messageToSend);

            return true;
        } catch (MessagingException e) {
            return false;
        }


    }
}
