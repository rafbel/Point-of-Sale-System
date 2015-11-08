import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;

public class Interface extends JFrame{
	
	private JTextField username;
	private JPasswordField password;
	private JButton loginButton;
	private String userAuth = "";
	private String passwordAuth = "";
	
	POSSystem System = new POSSystem();
	
	public Interface()
	{
		super ("SG Technologies");
		setLayout(new FlowLayout());
		setSize(300,200);
		setLocation(500,280);
	}
	
	//Interface for employee login once that option is selected
	public boolean employeeLogin()
	{
		username = new JTextField();
		username.setToolTipText("Username:");
		add(username);
		
		password = new JPasswordField();
		password.setToolTipText("Password");
		add(password);
		
		loginButton = new JButton();
		loginButton.setToolTipText("Login");
		add(loginButton);
		
		LoginHandler loginHandler = new LoginHandler();
		loginButton.addActionListener(loginHandler);
		
		
		
		return true; //able to login
	}
	
	//handler for login
	private class LoginHandler implements ActionListener{
		
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent event)
		{
			//Once button is clicked, get username and password from both fields
			userAuth = username.getText();
			passwordAuth = password.getText();
			if (System.logIn(userAuth, passwordAuth) == 1)		//Enters the system as cashier
			{
				System.cashier();
			}
			else if (System.logIn(userAuth, passwordAuth) == 2)	//Enters the system as admin
			{
				System.admin();
				
			}
			else	//Makes user re enter authentication:
			{
				JOptionPane.showMessageDialog(null,"Wrong Authentication Credentials");
				username.setText("");
				password.setText("");
				username.requestFocus();
			}
			
			
		}
	}

}
