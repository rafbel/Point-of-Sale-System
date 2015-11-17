import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Cashier_Interface extends JFrame implements ActionListener{
	
	private JButton saleButton;
	private JButton rentalButton;
	private JButton returnButton;
	private JButton LogOutButton;
	private Transaction_Interface transaction;
	
	public Cashier_Interface()
	{
		super ("SG Technologies - Cashier View");
		setLayout(null);
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth());
		int ySize = ((int) tk.getScreenSize().getHeight());
		
		setSize(xSize,ySize);
		//setLocation(500,280);
		
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
			Login_Interface login = new Login_Interface();
			login.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			login.setVisible(true);
			
			this.setVisible(false);
			dispose();
			
		}
		
	}
}


