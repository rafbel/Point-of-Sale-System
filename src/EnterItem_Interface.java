import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EnterItem_Interface extends JFrame implements ActionListener
{
	//GUI widgets for this interface
			private JTextField itemID;      
			private JTextField amount;
			private JButton enterButton;
			private JButton exitButton;
			private String ID = "";
			private String quantity = "";
			private PointOfSale transaction;
			private boolean checkFlag;
			private JLabel amountToolTip;
			private JLabel itemToolTip;
			
			private JTextArea transDialog;
			
	public EnterItem_Interface(PointOfSale transac,boolean addFlag,JTextArea transactionDialog)
	{
		super ("SG Technologies - Enter Item");
		setLayout(null);
		setSize(520,200);
		setLocation(500,280);
		
		transaction = transac;
		checkFlag = addFlag;
		transDialog = transactionDialog;
		
		enterButton = new JButton("Enter");
		enterButton.setBounds(170,100,80,20);
		add(enterButton);
		
		exitButton = new JButton("Exit");
		exitButton.setBounds(270,100,80,20);
		add(exitButton);
		
		itemID = new JTextField(15);
		itemID.setToolTipText("itemID:");
		itemID.setBounds(180,30,150,20);
		add(itemID);
		
		itemToolTip = new JLabel("Item ID:");
		itemToolTip.setBounds(90,30,150,20);
		add(itemToolTip);
		
		if (true){ //if it is adding an item to list, also requests amount
		amount = new JTextField(15);
		amount.setToolTipText("amount");
		amount.setBounds(180,65,150,20);
		add(amount);
		
		amountToolTip = new JLabel("Amount:");
		amountToolTip.setBounds(90,65,150,20);
		add(amountToolTip);
		}
		
		//LoginHandler loginHandler = new LoginHandler();
		enterButton.addActionListener(this);
		exitButton.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent event)
	{
		//If enter button is pressed:
		if (event.getSource() == enterButton)
		{
			ID = itemID.getText();
			if (checkFlag) //adds item to list
			{
				quantity = amount.getText();
				if (!transaction.enterItem(getItemID(), getAmount()))
					JOptionPane.showMessageDialog(null, "Item not found on inventory");

				else //Add line to textbox
				{
					Item lastItem = transaction.lastAddedItem();
					String itemString = lastItem.getItemName() + "  " + "x" + lastItem.getAmount() + "   $" + lastItem.getPrice() + "\n";
					transDialog.append(itemString);
				}
					
			}
			
			else //removes item from list
			{
				if (!transaction.removeItems(getItemID()))
					JOptionPane.showMessageDialog(null, "No such item on cart");

				else //Remove line from textbox
					{}
			}
			this.setVisible(false);
			dispose();
		}
		
		if (event.getSource() == exitButton){ //Cancels
			this.setVisible(false);
			dispose();
		
		}
	}
	
	public int getItemID() {return Integer.parseInt(ID); }
	public int getAmount() {return Integer.parseInt(quantity); }
	public void disposeThis() {dispose(); }
}
