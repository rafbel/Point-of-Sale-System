
public class pointOfSale {

	public pointOfSale(){}
	
	public boolean enterItem(int itemID, int amount, salesItem[] databaseItems, salesItem[] items, int numItem, int numDatabaseItems) //might include in a "mother class" in the future
	{
		boolean foundItem = false;
		
		for (int counter = 0; counter < numDatabaseItems && foundItem == false; counter++)
		{
			if (databaseItems[counter].getItemID() == itemID) //checks if item is found on the database
			{
				items[numItem] = new salesItem(itemID,databaseItems[counter].getItemName(),databaseItems[counter].getPrice(),amount);
				numItem++;
				foundItem = true;
			}
		}
		return foundItem;
	}
	
}
