package Bilkay;

import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;

public class SendingVerificationEmail {

	private String userEmail;
	private final String adminEmail = "bilkayadmn@gmail.com";
	private final String adminPassword =  "ftohrufjibdxzdpi";
	private Properties prop;
	private Session session;
	private MimeMessage message;


	public SendingVerificationEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setProperties()
	{
		prop = new Properties();

		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");

		session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(adminEmail, adminPassword);
			}
		});


	}
	public void setMessage(long code)
	{
		try{
			message = new MimeMessage(session);
			message.setFrom(new InternetAddress(adminEmail));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
			message.setSubject("Webmail Verification Code");
			message.setText("Verification code: " + code);

		}catch (Exception e){

		}

	}
	public boolean sendMail() {

		try {
			Transport.send(message);
			JOptionPane.showMessageDialog(null,  "The verification code has been sent successfully", "Verification Code", JOptionPane.INFORMATION_MESSAGE);
			return true;

		}catch (Exception e){

			JOptionPane.showMessageDialog(null,  "There is an error occurred while sending the verification code","Sorry" , JOptionPane.ERROR_MESSAGE);
			return false;
		}

	}

}
