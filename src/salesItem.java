
public class salesItem {
	
	//attributes
	private int itemID;
	private String itemName;
	private float price;
	private int amount; //amount of items on inventory
	
	//methods
	public salesItem(int itemID,String itemName, float price)
	{
		this.itemID = itemID; this.itemName = itemName; this.price = price;
	}
	
	String getItemName() {return itemName;}
	int getItemID() {return itemID;}
	float getPrice() {return price;}
	int getAmount() {return amount;} 
	
	public void updateInventory(int amount) {this.amount = amount;}
					
}
