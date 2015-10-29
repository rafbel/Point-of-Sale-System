
public class Item {
	
	//attributes
	private int itemID;
	private String itemName;
	private float price;
	private int amount; //amount of items on inventory
	
	//methods
	public Item(int itemID,String itemName, float price, int amount)
	{
		this.itemID = itemID; this.itemName = itemName; this.price = price; this.amount = amount;
	}
	
	String getItemName() {return itemName;}
	int getItemID() {return itemID;}
	float getPrice() {return price;}
	int getAmount() {return amount;} 
	
	public void updateAmount(int amount) {this.amount = amount;}
					
}
