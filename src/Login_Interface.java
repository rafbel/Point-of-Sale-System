import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;

public class Login_Interface extends JFrame implements ActionListener{

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		//GUI widgets for this interface
		private JTextField username;      
		private JPasswordField password;
		private JButton loginButton;
		private JButton exitButton;
		private String userAuth = "";
		private String passwordAuth = "";
		private JLabel userToolTip;
		private JLabel passToolTip;
		
		POSSystem System = new POSSystem();
		
		//Interface for employee login once that option is selected
		public Login_Interface()
		{
			super ("SG Technologies - Login Authentication");
			setLayout(null);
			setSize(520,200);
			setLocation(500,280);
			
			
			userToolTip = new JLabel("Username:");
			userToolTip.setBounds(90,30,150,20);
			add(userToolTip);
			
			passToolTip = new JLabel("Password:");
			passToolTip.setBounds(90,65,150,20);
			add(passToolTip);
			
			username = new JTextField(15);
			username.setToolTipText("Username:");
			username.setBounds(180,30,150,20);
			add(username);
			
			password = new JPasswordField(15);
			password.setToolTipText("Password");
			password.setBounds(180,65,150,20);
			add(password);
			
			loginButton = new JButton("Login");
			loginButton.setBounds(170,100,80,20);
			add(loginButton);
			
			exitButton = new JButton("Exit");
			exitButton.setBounds(270,100,80,20);
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
						//System.cashier();
						Cashier_Interface cashier = new Cashier_Interface(System);
						cashier.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						cashier.setVisible(true);
						this.setVisible(false);
						dispose();
					}
					else if (System.logIn(userAuth, passwordAuth) == 2)	//Enters the system as admin
					{
						//Starts the admin interface
						Admin_Interface admin = new Admin_Interface(System);
						admin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						admin.setVisible(true);
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



