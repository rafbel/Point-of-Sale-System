import java.io.*;
import java.util.*;


public class PointOfSale {

	//attributes
	private double totalPrice;
	Inventory inventory = new Inventory();
	
	private List<Item> databaseItem = new ArrayList<Item>(); //creates a list of all items in the database
	private List<Item> transactionItem = new ArrayList<Item>(); //this list will store all items to be used in this sale
	
	//constructor
	public PointOfSale(){
		
	}
	
	
	public void startNew(String databaseFile) 
	{
		totalPrice = 0;
		
		int itemID; int amount;
		
		if (inventory.accessInventory(databaseFile, databaseItem) == true) //if can access inventory
		{
			Scanner cashierInput = new Scanner(System.in); //determines if there is more items to be added
			
			do //must register at least one item
			{
				//Cashier enters itemID and amount
				System.out.println("Enter itemID");
				itemID = Integer.parseInt(cashierInput.next());
				
				System.out.println("Enter amount");
				amount = Integer.parseInt(cashierInput.next());
				
				//Calls the enterItem method
				if (enterItem(itemID,amount) == false)
					System.out.println("Item not found. Press e to try again");
				
			} while (cashierInput.next().equals("e")); //press e to add more items
			
			cashierInput.close();
		
		}
		
		else
		{
			System.out.println("Can't access database.");		}
	}
	
	private boolean enterItem(int itemID, int amount) //might include in a "mother class" in the future
	{
		boolean foundItem = false;
		
		for (int counter = 0; counter < databaseItem.size() && foundItem == false; counter++)
		{
			if (databaseItem.get(counter).getItemID() == itemID) //checks if item is found on the database
			{
				transactionItem.add(new Item(itemID,databaseItem.get(counter).getItemName(),databaseItem.get(counter).getPrice(),amount));
				foundItem = true;
			}
		}
		
		if (foundItem == true)
			updateTotal();
		
		return foundItem;
	}
	
	private void updateTotal() 
	{
		
		//updates total value to be displayed on the screen
		totalPrice += transactionItem.get(transactionItem.size() - 1).getPrice()
				*transactionItem.get(transactionItem.size() - 1).getAmount();
		
		//shows running total on screen and item info
		for (int counter = 0; counter < transactionItem.size(); counter++)
			//prints item name - price
			System.out.format("%s x %d  --- $ %.2f\n", transactionItem.get(counter).getItemName(),
					transactionItem.get(counter).getAmount(), 
					transactionItem.get(counter).getPrice()*transactionItem.get(counter).getAmount());
			
		//prints running total
		System.out.format("Total: %.2f\n", totalPrice);

	}
	
	public void endPOS(double tax)
	{
				
				totalPrice = totalPrice*tax; //calculates price with tax
				//prints total with taxes
				System.out.format("Total with taxes: %.2f\n", totalPrice);
		
	}
	
		
}

