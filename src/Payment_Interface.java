import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Payment_Interface extends JFrame implements ActionListener
{
	
	private JButton PayCash;
	private JButton PayElectronic;
	private JButton cancelTransaction;
	private String phone;	
	private long phoneNum;
	private JTextArea transactionDialog;
	
	private String databaseFile;
	
	JScrollPane scroll;
	
	PointOfSale transaction;
	String database;
	
	
	public Payment_Interface(PointOfSale transaction, String databaseFile)
	{
		super ("SG Technologies - Payment View");
		setLayout(null);
		
		this.transaction = transaction;
		this.database = databaseFile;
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth());
		int ySize = ((int) tk.getScreenSize().getHeight());
		
		setSize(xSize,ySize);
		
		PayCash = new JButton("Cash Payment");
		PayCash.setBounds(xSize*4/5,ySize/4,150,80);
		add(PayCash);
		
		PayElectronic = new JButton("Pay Electronically");
		PayElectronic.setBounds(xSize*4/5,ySize*2/4,150,80);
		add(PayElectronic);
		
		cancelTransaction = new JButton("Cancel");
		cancelTransaction.setBounds(xSize*4/5,ySize*3/4,150,80);
		add(cancelTransaction);
		
		PayCash.addActionListener(this);
		PayElectronic.addActionListener(this);
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
		
		updateText();
	}
	
	public void actionPerformed(ActionEvent event)
	{
		if (event.getSource() == cancelTransaction) //cancels transaction for customer
		{
			JOptionPane.showMessageDialog(null,"Transaction Has Been Cancelled");
			Cashier_Interface cashier = new Cashier_Interface();
			cashier.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			cashier.setVisible(true);
			
			this.setVisible(false);
			dispose();
		}
	}
	
	private void updateText()
	{
		transactionDialog.setText(null);
		List <Item> transactionItem = transaction.getCart();
		for (Item temp: transactionItem)
		{
			String itemString = temp.getItemID() + "\t" + temp.getItemName() + " \t" + "x" + temp.getAmount() + "\t$" + String.format("%.2f", temp.getAmount()*temp.getPrice()) + "\n";
			transactionDialog.append(itemString);
		}
		transactionDialog.append("\nTotal: $" + String.format("%.2f", transaction.getTotal()) + "\n" );
		
		double totalWithTax = transaction.endPOS(database);
		transactionDialog.append("Total with taxes: $" + String.format("%.2", totalWithTax + "\n"));
	}
}
