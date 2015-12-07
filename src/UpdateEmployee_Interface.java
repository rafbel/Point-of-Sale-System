import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class UpdateEmployee_Interface extends JFrame implements ActionListener
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//GUI widgets for this interface
	private JTextField username;      
	private JPasswordField password;
	private JButton enterButton;
	private JButton exitButton;
	private JLabel userToolTip;
	private JLabel passToolTip;
	private JTextField position;
	private JLabel positionToolTip;
	private JTextField name;
	private JLabel nameToolTip;
	
	private Admin_Interface admin;
	
	public UpdateEmployee_Interface(Admin_Interface admin)
	{
		super ("SG Technologies - Update Employee Info");
		setLayout(null);
		setSize(520,300);
		setLocation(500,280);
		
		this.admin = admin;
		
		userToolTip = new JLabel("Username:");
		userToolTip.setBounds(90,30,150,20);
		add(userToolTip);
		
		nameToolTip = new JLabel("Name:");
		nameToolTip.setBounds(90,65,150,20);
		add(nameToolTip);
		
		passToolTip = new JLabel("Password:");
		passToolTip.setBounds(90,110,150,20);
		add(passToolTip);
		
		positionToolTip = new JLabel("Position:");
		positionToolTip.setBounds(90,145,150,20);
		add(positionToolTip);
		
		position = new JTextField(15);
		position.setBounds(180,145,150,20);
		add(position);
		
		username = new JTextField(15);
		username.setToolTipText("Username:");
		username.setBounds(180,30,150,20);
		add(username);
		
		name = new JTextField(15);
		name.setBounds(180,65,150,20);
		add(name);
		
		password = new JPasswordField(15);
		password.setToolTipText("Password");
		password.setBounds(180,110,150,20);
		add(password);
		
		enterButton = new JButton("Enter");
		enterButton.setBounds(170,200,80,20);
		add(enterButton);
		
		exitButton = new JButton("Exit");
		exitButton.setBounds(270,200,80,20);
		add(exitButton);
		
		//enterHandler enterHandler = new enterHandler();
		enterButton.addActionListener(this);
		exitButton.addActionListener(this);
		
	}
	
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent event)
	{
		
			//Once enter button is clicked, get username and password from both fields
			if (event.getSource() == enterButton) 
			{
				EmployeeManagement management = new EmployeeManagement();
				
				int result = management.update(username.getText(), password.getText(), position.getText(), name.getText());
				
				if (result == -1)
					JOptionPane.showMessageDialog(null, "Employee with such username doesn't exist");
				
				else if (result == -2)
					JOptionPane.showMessageDialog(null, "Invalid employee position");
				
				else 
				{
					admin.setVisible(false);
					admin.dispose();
					
                                        POSSystem sys=new POSSystem();
					admin = new Admin_Interface(sys);
					admin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					admin.setVisible(true);
				}
				
				
				this.setVisible(false);
				dispose();
			}
			
			if (event.getSource() == exitButton)
			{ //Exits the program
				this.setVisible(false);
				dispose();
				
			}
	}
}
