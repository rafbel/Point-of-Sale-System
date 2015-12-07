import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AddEmployee_Interface extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField name;      
	private JPasswordField password;
	private JButton enterButton;
	private JButton exitButton;
	private JLabel passwordToolTip;
	private JLabel nameToolTip;
	
	Admin_Interface admin;
	
	
	private EmployeeManagement management = new EmployeeManagement();
	
	private boolean registeringCashier;
	
	public AddEmployee_Interface(boolean regCashier,Admin_Interface admin)
	{
		super ("SG Technologies - Register Employee");
		setLayout(null);
		setSize(520,200);
		setLocation(500,280);
		
		registeringCashier = regCashier;
		this.admin = admin;
	
		enterButton = new JButton("Enter");
		enterButton.setBounds(170,100,80,20);
		add(enterButton);
		
		exitButton = new JButton("Exit");
		exitButton.setBounds(270,100,80,20);
		add(exitButton);
		
		name = new JTextField(15);
		name.setToolTipText("Name:");
		name.setBounds(180,30,150,20);
		add(name);
		
		nameToolTip = new JLabel("Name:");
		nameToolTip.setBounds(90,30,150,20);
		add(nameToolTip);
		
		password = new JPasswordField(15);
		password.setToolTipText("Password");
		password.setBounds(180,65,150,20);
		add(password);
			
		passwordToolTip = new JLabel("Password:");
		passwordToolTip.setBounds(90,65,150,20);
		add(passwordToolTip);
		
		//LoginHandler loginHandler = new LoginHandler();
		enterButton.addActionListener(this);
		exitButton.addActionListener(this);
	}
	
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent event)
	{
		if (event.getSource() == enterButton)
		{
			management.add(name.getText(),password.getText(),registeringCashier);
			admin.setVisible(false);
			admin.dispose();
			
                        POSSystem sys=new POSSystem();
			admin = new Admin_Interface(sys);
			admin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			admin.setVisible(true);
			
			this.setVisible(false);
			dispose();
		}
		
		if (event.getSource() == exitButton){ //Cancels
			this.setVisible(false);
			dispose();
		}
	}
	
}
