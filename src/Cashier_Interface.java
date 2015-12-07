import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Cashier_Interface extends JFrame implements ActionListener{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton saleButton;
	private JButton rentalButton;
	private JButton returnButton;
	private JButton LogOutButton;
	private Transaction_Interface transaction;
        POSSystem system1;
	
	public Cashier_Interface(POSSystem system2)
	{
            
		super ("SG Technologies - Cashier View");
		setLayout(null);
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth());
		int ySize = ((int) tk.getScreenSize().getHeight());
		
		setSize(xSize,ySize);
		//setLocation(500,280);
		
                this.system1=system2;
                
		saleButton = new JButton("Sale");
		saleButton.setBounds(0,ySize/5,xSize,100);
		add(saleButton);
		
		rentalButton = new JButton("Rental");
		rentalButton.setBounds(0,ySize*2/5,xSize,100);
		add(rentalButton);
		
		returnButton = new JButton("Returns");
		returnButton.setBounds(0,ySize*3/5,xSize,100);
		add(returnButton);
		
		LogOutButton = new JButton("Log Out");
		LogOutButton.setBounds(0,ySize*4/5,xSize,100);
		add(LogOutButton);
		
		saleButton.addActionListener(this);
		rentalButton.addActionListener(this);
		returnButton.addActionListener(this);
		LogOutButton.addActionListener(this);
		
		POSSystem system = new POSSystem();
		if (system.checkTemp())
		{
			int choice;
			Object[] options = {"Yes", "No"};
			
			choice = JOptionPane.showOptionDialog(null, 
	            "System was able to restore an unfinished transaction. Would you like to retrieve it?", 
	            "Choose an option", 
	            JOptionPane.YES_NO_OPTION, 
	            JOptionPane.QUESTION_MESSAGE, 
	            null, 
	            options, 
	            options[1]);
			
			if (choice == 1)
			{
				Management management = new Management();
				long phoneNum;
				String phone = JOptionPane.showInputDialog("Please enter customer's phone number");
				while ((phoneNum = Long.parseLong(phone)) > 9999999999l || (phoneNum < 1000000000l))
				{
					JOptionPane.showMessageDialog(null, "Invalid phone number. Please enter again");
					phone = JOptionPane.showInputDialog("Please enter customer's phone number");
				}
				 if (!management.checkUser(phoneNum))
				 {
					 if(management.createUser(phoneNum))
						 JOptionPane.showMessageDialog(null, "New customer was registered");
					 else
						 JOptionPane.showMessageDialog(null, "New customer couldn't be registered");
				 }
				
				String operation = system.continueFromTemp(phoneNum);
				
				transaction = new Transaction_Interface(operation);
				transaction.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				transaction.setVisible(true);
				this.setVisible(false);
				dispose();
				
			}
			
		}
		
		
	}
	
	public void actionPerformed(ActionEvent event)
	{
		//If sale button is pressed:
		if (event.getSource() == saleButton)
		{
			transaction = new Transaction_Interface("Sale");
			transaction.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			transaction.setVisible(true);
			this.setVisible(false);
			dispose();
		}
		
		//If rental button is pressed
		if (event.getSource() == rentalButton)
		{
			transaction = new Transaction_Interface("Rental");
			transaction.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			transaction.setVisible(true);
			this.setVisible(false);
			dispose();
		}
			
		//If return button is pressed
		if (event.getSource() == returnButton)
		{
			transaction = new Transaction_Interface("Return");
			transaction.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			transaction.setVisible(true);
			this.setVisible(false);
			dispose();
		}
			
		//If logout button is pressed
		if (event.getSource() == LogOutButton)
		{
			//Registering log out in log
			/*POSSystem system = new POSSystem();
			system.logOutToFile(username,name,"Cashier",cal);*/
			system1.logOut("Cashier");
			
			Login_Interface login = new Login_Interface();
			login.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			login.setVisible(true);
			
			this.setVisible(false);
			dispose();
			
		}
		
	}
}


