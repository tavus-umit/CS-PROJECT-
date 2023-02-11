import java.util.HashMap;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

public class LoginPage implements ActionListener{
	
	JFrame frame = new JFrame();
	JLabel label2 = new JLabel("Sign in to your Bilko account:");

	ImageIcon homeIcon = new ImageIcon("hme.png");
	JButton loginButton = new JButton("Login");
	
	JButton resetButton = new JButton("Reset");
	JTextField userIDField = new JTextField();
	JPasswordField userPasswordField = new JPasswordField();
	JLabel userIDLabel = new JLabel("Bilkent ID:");
	JLabel userPasswordLabel = new JLabel("Password:");
	JLabel messageLabel = new JLabel("Sign in to your Bilko account:");
	HashMap<String,String> logininfo = new HashMap<String,String>();
	
	LoginPage(HashMap<String,String> loginInfoData){
		
		logininfo = loginInfoData;
		Font fontx = new Font("Arial", Font.BOLD, 18);
		
		userIDLabel.setBounds(50,100,75,25);
		userIDLabel.setFont(new Font("Arial", Font.BOLD, 15));
		userPasswordLabel.setBounds(50,150,75,25);
		userPasswordLabel.setFont(new Font("Arial", Font.BOLD, 15));
		
		messageLabel.setBounds(60,50,550,35);
		messageLabel.setFont(fontx);
		
		userIDField.setBounds(125,100,200,25);
		userPasswordField.setBounds(125,150,200,25);
		
		loginButton.setBounds(125,200,100,25);
		loginButton.setFont(fontx);
		loginButton.setBackground(new ColorUIResource(10, 50, 200));
		loginButton.setOpaque(true);
		loginButton.setFocusable(false);
		loginButton.addActionListener(this);
		
		resetButton.setBounds(225,200,100,25);
		resetButton.setFocusable(false);
		resetButton.addActionListener(this);
		resetButton.setFont(fontx);
		
		frame.setBackground(new ColorUIResource(10, 50, 200));
		
		frame.add(userIDLabel);
		frame.add(userPasswordLabel);
		frame.add(messageLabel);
		frame.add(userIDField);
		frame.add(userPasswordField);
		frame.add(loginButton);
		frame.add(label2);
		frame.add(resetButton);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420,420);
		frame.setLayout(null);
		frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Font fontx = new Font("Arial", Font.BOLD, 18);
		if(e.getSource()==resetButton) {
			userIDField.setText("");
			userPasswordField.setText("");
		}
		
		if(e.getSource()==loginButton) {
			
			String userID = userIDField.getText();
			String password = String.valueOf(userPasswordField.getPassword()); //since we're using a password field
			
			if(logininfo.containsKey(userID)) {
				if(logininfo.get(userID).equals(password)) {
					messageLabel.setForeground(Color.green);
					messageLabel.setFont(fontx);
					messageLabel.setText("Login successful!");
					frame.dispose();
					WelcomePage welcomePage = new WelcomePage(userID);
				}
				else {
					messageLabel.setForeground(Color.red);
					messageLabel.setFont(fontx);
					messageLabel.setText("Wrong password!");
				}

			}
			else if(userID.equals("") || password.equals("")){
				messageLabel.setFont(fontx);
				messageLabel.setText("Please enter your user ID and password.");
			}
			else {
				messageLabel.setForeground(Color.red);
				messageLabel.setFont(fontx);
				messageLabel.setText("Username not found!");
			}
		}
	}	
}