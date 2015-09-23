import java.io.*;

public class sale {

	//attributes
	private float runningTotal;
	private int numItem;
	private static final String databaseFile = "itemDatabase.txt";
	
	//methods
	
	//constructor
	public sale() {
		String line = null;
		String[] lineSort;
		int numLine = 0;
		salesItem[] item = null; //creates an array of salesItem objects
		
		//Checks database file for the item		
		try {
			FileReader fileR = new FileReader(databaseFile);
			BufferedReader textReader = new BufferedReader(fileR);
			//reads the entire database
			while ((line = textReader.readLine()) != null)
			{
				lineSort = line.split(" "); //separates words
				item[numLine] = new salesItem(Integer.parseInt(lineSort[0]),lineSort[1],Float.parseFloat(lineSort[2]));
				numLine++;
			}
			
			textReader.close();
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
		
		runningTotal = 0;
		numItem = 0;
	}
	
	
	public void enterItem(int itemID, int amount)
	{
	
		
			
		
	}
		
		
}
	

