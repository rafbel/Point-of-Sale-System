import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Transaction_Interface extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PointOfSale transaction;
	private Management management = new Management();
	
	private JButton addItem;
	private JButton removeItem;
	private JButton endTransaction;
	private JButton cancelTransaction;
	private String phone ="";	
	private long phoneNum;
	private JTextArea transactionDialog;
	
        
        public boolean returnOrNot;
	private String databaseFile;
	
	String operation = "";
	
	JScrollPane scroll;
	
	private int choice = 3;
	
	public Transaction_Interface(String operation)
	{
		super ("SG Technologies - Transaction View");
		setLayout(null);
		
                
		
		this.operation = operation;
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth());
		int ySize = ((int) tk.getScreenSize().getHeight());
		
		setSize(xSize,ySize);
		//setLocation(500,280);
		
		addItem = new JButton("Add Item");
		addItem.setBounds(xSize*4/5,ySize/6,150,80);
		add(addItem);
		
		removeItem = new JButton("Remove Item");
		removeItem.setBounds(xSize*4/5,ySize*2/6,150,80);
		add(removeItem);
		
		endTransaction = new JButton("End");
		endTransaction.setBounds(xSize*4/5,ySize*3/6,150,80);
		add(endTransaction);
		
		cancelTransaction = new JButton("Cancel");
		cancelTransaction.setBounds(xSize*4/5,ySize*4/6,150,80);
		add(cancelTransaction);
		
		addItem.addActionListener(this);
		removeItem.addActionListener(this);
		endTransaction.addActionListener(this);
		cancelTransaction.addActionListener(this);
		
		
		transactionDialog=new JTextArea();  
		transactionDialog.setBackground(Color.white);  
		transactionDialog.setForeground(Color.black);  
		transactionDialog.setEditable(false);
		Font font = transactionDialog.getFont();
		float size = font.getSize() + 5.0f;
		transactionDialog.setFont( font.deriveFont(size) );
		
		scroll = new JScrollPane (transactionDialog, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBounds(xSize/16,ySize/16,3*xSize/5,4*ySize/5);  
		add(scroll);
		
		if (operation.equals("Sale"))
		{
                    returnOrNot=false;
			transaction = new POS();
                        
			databaseFile = "Database/itemDatabase.txt";
		}
		
		if (operation.equals("Rental"))
		{
                    returnOrNot=false;
			getCustomerPhone();
			databaseFile = "Database/rentalDatabase.txt";
			transaction = new POR(phoneNum);
		}
		
		if (operation.equals("Return"))
		{

			returnOrNot=true;

			Object[] options = {"Rented Items",
                    "Unsatisfactory items"};
			
			choice = JOptionPane.showOptionDialog(null, 
                    "Returning rented items or unsatisfactory items?", 
                    "Choose an option", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.QUESTION_MESSAGE, 
                    null, 
                    options, 
                    options[0]);
			
			if (choice == 0){
				databaseFile = "Database/rentalDatabase.txt";
                                
                getCustomerPhone();
                transaction = new POH(phoneNum);
                transaction.returnSale=false;

            }
            else{
                  transaction = new POH();
                  databaseFile = "Database/itemDatabase.txt";
                  transaction.returnSale=true;
                  phone = "0000000000";
                }
		}
		
		transaction.startNew(databaseFile);
		
		if (operation.equals("Return") && choice != 0)
		{
			//Resets unecessary info for this operation
			databaseFile = "";
			this.operation = "Unsatisfactory";
		}
		
	}
	
	public void actionPerformed(ActionEvent event)
	{
		if (event.getSource() == addItem)
		{
			EnterItem_Interface enterItem = new EnterItem_Interface(transaction,true,transactionDialog,operation, choice);
			enterItem.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			enterItem.setVisible(true);
				
		}
		if (event.getSource() == removeItem)
		{
			EnterItem_Interface removeItem = new EnterItem_Interface(transaction,false,transactionDialog,operation, choice);
			removeItem.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			removeItem.setVisible(true);
			
		}
		
		if (event.getSource() == endTransaction) //goes to payment screen
		{
			if (transaction.getCartSize() > 0)
			{
				String coupon ="";
				if (operation.equals("Sale"))
				{
					coupon = JOptionPane.showInputDialog("Enter coupon code if user has one");
					if (!coupon.equals(""))
						if (!transaction.coupon(coupon))
							JOptionPane.showMessageDialog(null, "Invalid coupon");
				}
				
				if (operation.equals("Unsatisfactory"))
				{
					transaction.endPOS(databaseFile);
					JOptionPane.showMessageDialog(null, "Returning items is complete");
		             POSSystem sys=new POSSystem();
		 			Cashier_Interface cashier = new Cashier_Interface(sys);
		 			cashier.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		 			cashier.setVisible(true);
		 			
		 			this.setVisible(false);
		 			this.dispose();
				}
				
				
				else {

					Payment_Interface payment = new Payment_Interface(transaction,databaseFile,operation,phone,returnOrNot);

					payment.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					payment.setVisible(true);
					
					this.setVisible(false);
					this.dispose();
				}
			}
			
			else
				JOptionPane.showMessageDialog(null, "Cart is currently empty. Please add items before ending transaction");
		}
		
		
		if (event.getSource() == cancelTransaction) //cancels transaction for customer
		{
			JOptionPane.showMessageDialog(null,"Transaction Has Been Cancelled");
                        POSSystem sys=new POSSystem();
			Cashier_Interface cashier = new Cashier_Interface(sys);
			cashier.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			cashier.setVisible(true);
			
			this.setVisible(false);
			dispose();
		}
	}
	
	private void getCustomerPhone()
	{
		phone = JOptionPane.showInputDialog("Please enter customer's phone number");
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
	}
}
