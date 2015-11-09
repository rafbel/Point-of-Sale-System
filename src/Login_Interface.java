import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;

public class Login_Interface extends JFrame implements ActionListener{

		//GUI widgets for this interface
		private JTextField username;      
		private JPasswordField password;
		private JButton loginButton;
		private JButton exitButton;
		private String userAuth = "";
		private String passwordAuth = "";
		
		POSSystem System = new POSSystem();
		
		//Interface for employee login once that option is selected
		public Login_Interface()
		{
			super ("SG Technologies - Login Authentication");
			setLayout(null);
			setSize(300,200);
			setLocation(500,280);

			username = new JTextField(15);
			username.setToolTipText("Username:");
			username.setBounds(70,30,150,20);
			add(username);
			
			password = new JPasswordField(15);
			password.setToolTipText("Password");
			password.setBounds(70,65,150,20);
			add(password);
			
			loginButton = new JButton("Login");
			loginButton.setBounds(60,100,80,20);
			add(loginButton);
			
			exitButton = new JButton("Exit");
			exitButton.setBounds(160,100,80,20);
			add(exitButton);
			
			//LoginHandler loginHandler = new LoginHandler();
			loginButton.addActionListener(this);
			exitButton.addActionListener(this);
			
			
			
		}
		
		//handler for login
		//private class LoginHandler implements ActionListener{
			
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent event)
			{
				//Once login button is clicked, get username and password from both fields
				if (event.getSource() == loginButton) {
					userAuth = username.getText();
					passwordAuth = password.getText();
					if (System.logIn(userAuth, passwordAuth) == 1)		//Enters the system as cashier
					{
						//Starts the cashier interface
						System.cashier();
						this.setVisible(false);
						dispose();
					}
					else if (System.logIn(userAuth, passwordAuth) == 2)	//Enters the system as admin
					{
						//Starts the admin interface
						System.admin();
						this.setVisible(false);
						dispose();
						
					}
					else	//Makes user re enter authentication:
					{
						JOptionPane.showMessageDialog(null,"Wrong Authentication Credentials");
						username.setText("");
						password.setText("");
						username.requestFocus();
	
					}
				}
				
				if (event.getSource() == exitButton){ //Exits the program
					dispose();
					
				}
				
				
			}
}



