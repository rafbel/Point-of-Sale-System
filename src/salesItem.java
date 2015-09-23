
public class salesItem {
	
	//attributes
	private int itemID;
	private String itemName;
	private float price;
	
	//methods
	public salesItem(int itemID,String itemName, float price)
	{
		this.itemID = itemID; this.itemName = itemName; this.price = price;
	}
	
	String getItemName() {return itemName;}
	int getItemID() {return itemID;}
	float getPrice() {return price;}
					
}
