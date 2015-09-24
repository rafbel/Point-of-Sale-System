import java.io.*;
import java.util.Scanner;

public class sale extends pointOfSale {

	//attributes
	private float runningTotal;
	private int numItem;
	private int databaseItems;
	private static final String databaseFile = "itemDatabase.txt";
	private salesItem[] item = null; //creates an array of all items in the database
	private salesItem[] itemsOnSale = null; //this array will store all items to be used in this sale
	//methods
	
	//constructor
	public sale() {
		
		String line = null;
		String[] lineSort;
		int numLine = 0;
		
		//Checks database file for the item		
		try {
			FileReader fileR = new FileReader(databaseFile);
			BufferedReader textReader = new BufferedReader(fileR);
			//reads the entire database
			while ((line = textReader.readLine()) != null)
			{
				lineSort = line.split(" "); //separates words
				item[numLine] = new salesItem(Integer.parseInt(lineSort[0]),lineSort[1],Float.parseFloat(lineSort[2]), Integer.parseInt(lineSort[3]));
				numLine++;
			}
			
			textReader.close();
			databaseItems = numLine;
		}
		
		//catches exceptions
		 catch(FileNotFoundException ex) {
	            System.out.println(
	                "Unable to open file '" + 
	                		databaseFile + "'");                
	        }
	        catch(IOException ex) {
	            System.out.println(
	                "Error reading file '" 
	                + databaseFile + "'");         
	        }
		
		
	}
	
	public void newSale()
	{
		this.runningTotal = 0;
		this.numItem = 0;
		
		int itemID; int amount;
		
		Scanner cashierInput = new Scanner(System.in); //determines if there is more items to be added
		
		do
		{
			//Cashier enters itemID and amount
			System.out.println("Enter itemID");
			itemID = Integer.parseInt(cashierInput.next());
			
			System.out.println("Enter amount");
			amount = Integer.parseInt(cashierInput.next());
			
			//Calls the enterItem method
			if (enterItem(itemID,amount, item,itemsOnSale, numItem, databaseItems) == false)
				System.out.println("Item not found. Press e to try again");
			
			
			
		} while (cashierInput.next() == "e"); //press e to add more items
		
		cashierInput.close();
	}
	
	private void updateRunningTotal()
	{
		//shows runningtotal on screen and item info
	}
		
		
}
	

