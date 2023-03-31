import java.awt.*;
import javax.swing.*;

public class WelcomePage {

	JFrame frame = new JFrame();
	ImageIcon homeIcon = new ImageIcon("hme.png");

	JButton homeButton = new JButton(homeIcon);
	//homeButton.setIcon(icon);
	

	JLabel welcomeLabel = new JLabel("Hello!");
	
	WelcomePage(String userID){
		
		welcomeLabel.setBounds(100,30,200,35);
		welcomeLabel.setFont(new Font( "Arial",Font.BOLD,25));
		welcomeLabel.setText("Hello "+ userID);
		
		frame.add(welcomeLabel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 420);
		frame.setLayout(null);
		frame.setVisible(true);
	}
}
//*