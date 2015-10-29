import java.util.Scanner;

public class HandleReturns extends PointOfSale{
	
	private static final String rentedDatabaseFile = "..\\Database\\rentalDatabase.txt";
	private static final String saleDatabaseFile = "Database/itemDatabase.txt";
	private static float noTax = 1.0f;
	
	public void newReturn()
	{
		//Two scenarios: returning rented items or returning items not satisfied with
		
		//Note: Must check return date to calculate if user must apply additional prices
		Scanner input = new Scanner(System.in);
		String choice;
		
		System.out.println("Press 'r' if user is returning rented items.");
		System.out.println("Press 'i' if user is returning sale items that is not satisfied with.");
		
		choice = input.nextLine();
		if (choice.equals("r"))
		{
			//rented items
			startNew(rentedDatabaseFile);
			endPOS(noTax,rentedDatabaseFile,false);
		}
		
		else if (choice.equals("i"))
		{
			//Items not satisfied with
			startNew(saleDatabaseFile);
			endPOS(noTax, saleDatabaseFile,false);
		}
		
		else
		{
			System.out.println("Not a valid option");
		}
	}

}
