
public class ReturnItem {

	
		//attributes
		private int itemID;
		private int daysSinceReturn;
		
		public ReturnItem(int itemID,int daysSinceReturn)
		{
			this.itemID = itemID; this.daysSinceReturn = daysSinceReturn;
		}
		
		public int getItemID(){return itemID;}
		public int getDays(){return daysSinceReturn;}
}
