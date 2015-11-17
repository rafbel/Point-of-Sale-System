import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Admin_Interface extends JFrame implements ActionListener{
	
	private JButton addButton;
	private JButton adminButton;
	private JButton removeButton;
	private JButton updateButton;
	private JButton cashierButton;
	private JButton LogOutButton;
	private JTextArea textShow;
	private JScrollPane scroll;
	
	private List <Employee> employeeList;
	
	private EmployeeManagement management = new EmployeeManagement();
	
	
	public Admin_Interface()
	{
		super ("SG Technologies - Administrator View");
		setLayout(null);
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth());
		int ySize = ((int) tk.getScreenSize().getHeight());
		
		setSize(xSize,ySize);
		
		addButton = new JButton("Add Cashier");
		addButton.setBounds(xSize*4/5,ySize/7,150,80);
		add(addButton);
		
		adminButton = new JButton("Add Admin");
		adminButton.setBounds(xSize*4/5,ySize*2/7,150,80);
		add(adminButton);
		
		removeButton = new JButton("Remove Employee");
		removeButton.setBounds(xSize*4/5,ySize*3/7,150,80);
		add(removeButton);
		
		updateButton = new JButton("Update Employee");
		updateButton.setBounds(xSize*4/5,ySize*4/7,150,80);
		add(updateButton);
		
		cashierButton = new JButton("Cashier View");
		cashierButton.setBounds(xSize*4/5,ySize*5/7,150,80);
		add(cashierButton);
		
		LogOutButton = new JButton("Log Out");
		LogOutButton.setBounds(xSize*4/5,ySize*6/7,150,80);
		add(LogOutButton);
		
		textShow=new JTextArea();  
		textShow.setBackground(Color.white);  
		textShow.setForeground(Color.black);  
		textShow.setEditable(false);
		Font font = textShow.getFont();
		float size = font.getSize() + 5.0f;
		textShow.setFont( font.deriveFont(size) );
		
		scroll = new JScrollPane (textShow, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBounds(xSize/16,ySize/16,3*xSize/5,4*ySize/5);  
		add(scroll);
		
		updateTextArea();
		
		addButton.addActionListener(this);
		adminButton.addActionListener(this);
		removeButton.addActionListener(this);
		updateButton.addActionListener(this);
		cashierButton.addActionListener(this);
		LogOutButton.addActionListener(this);
	}
	
	private void updateTextArea()
	{
		textShow.setText(null);
		employeeList = management.getEmployeeList();
		for (Employee temp: employeeList)
		{
			String employeeString = temp.getUsername() + "\t" + temp.getPosition() + " \t" + "x" + temp.getName() + "\t" + temp.getPassword() + "\n";
			textShow.append(employeeString);
		}
	}
	
	public void actionPerformed(ActionEvent event)
	{
		if (event.getSource() == addButton)
		{
			
		}
		
		if (event.getSource() == adminButton)
		{
			
		}
		
		if (event.getSource() == removeButton)
		{
			
		}
		
		if (event.getSource() == updateButton)
		{
			
		}
		
		if (event.getSource() == cashierButton) //enter cashier interface
		{
			Cashier_Interface cashier = new Cashier_Interface();
			cashier.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			cashier.setVisible(true);
			
			this.setVisible(false);
			dispose();
		}
		
		if (event.getSource() == LogOutButton) //logs out
		{
			Login_Interface login = new Login_Interface();
			login.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			login.setVisible(true);
			
			this.setVisible(false);
			dispose();
		}
		
	}

}
