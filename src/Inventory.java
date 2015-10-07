import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;

public class Inventory 
{
	//constructor
	public Inventory() {}
	
	//methods
	public boolean accessInventory(String databaseFile, List <Item> databaseItem)
	{
		boolean ableToOpen = true;
		
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
				databaseItem.add(new Item(Integer.parseInt(lineSort[0]),lineSort[1],Float.parseFloat(lineSort[2]),
						Integer.parseInt(lineSort[3])));
				numLine++;
			}
			textReader.close();
			
		}
		
		//catches exceptions
		 catch(FileNotFoundException ex) {
	            System.out.println(
	                "Unable to open file '" + 
	                		databaseFile + "'"); 
	            ableToOpen = false;
	        }
	        catch(IOException ex) {
	            System.out.println(
	                "Error reading file '" 
	                + databaseFile + "'");  
	            ableToOpen = false;
	        }
		
		
		return ableToOpen;
	}
	
	public void updateInventory(String databaseFile, List <Item> transactionItem, List <Item> databaseItem)
	{
		int counter2;
		int newAmount; //stores new amount (number of items in database - number of items in transaction)
		
		
		//updates inventory list
		for (int counter = 0 ; counter < transactionItem.size(); counter++) //for every item on this transaction
		{
			for (counter2 = 0; counter2 < databaseItem.size(); counter2++) //for every item on the database
			{
				if (transactionItem.get(counter).getItemID() == databaseItem.get(counter2).getItemID()) //if itemIDs are equal, update new amount on the list
				{
					newAmount = databaseItem.get(counter2).getAmount() - transactionItem.get(counter).getAmount();
					databaseItem.get(counter2).updateAmount(newAmount);
					break; //breaks when item is found
				}
			}
		}
		
		
		//saves databaseItem list -> database.txt file (to implement)
		//overwrites file and reinserts all items (with amount updated)
		try
		{
			File file = new File(databaseFile);		
			FileWriter fileR = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bWriter = new BufferedWriter(fileR);
			PrintWriter writer = new PrintWriter(bWriter);
			
			for (int wCounter = 0; wCounter < databaseItem.size() ; ++wCounter)
			{
				writer.println(String.valueOf(databaseItem.get(wCounter).getItemID()) + " " + databaseItem.get(wCounter).getItemName() + " "
						+ String.valueOf(databaseItem.get(wCounter).getPrice() ) + " " +
						String.valueOf( databaseItem.get(wCounter).getAmount()) );
			}
			
			bWriter.close(); //closes writer
		}
		
		catch(IOException e){}
		{
		}
		
	
	}
	
}	
	

		
