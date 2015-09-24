import java.io.*;
import java.util.Scanner;

public class Sale extends PointOfSale{

	//attributes
	private static final String databaseFile = "Database/itemDatabase.txt"; //determines the name of the databaseFile for sale
	
	//methods
	
	//constructor
	public Sale() {}
	
	public void newSale()
	{
		if (accessInventory(databaseFile) == true)
			startNew();
	}
	


		
}
	

