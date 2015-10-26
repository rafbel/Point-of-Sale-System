import java.util.Scanner;

public class Register {
	
	public static void main(String[] args)
	{
		Sale sale = new Sale();
		String choice;
		
		System.out.println("Welcome to SG Technologies POS System");
		System.out.println("Press s to start new sale");
		System.out.println("Press q to shut down system");
		
		Scanner cashierInput = new Scanner(System.in);
		
		while ( !(choice = cashierInput.next() ).equals("q"))
		{
			if (choice.equals("s")) //starts new sale
				sale.newSale();
			
			System.out.println("Welcome to SG Technologies POS System");
			System.out.println("Press s to start new sale");
			System.out.println("Press q to shut down system");
			
		}
		
		System.out.println("System shutting down");
	}
	
}

//in development

/* Notes
To be added in the future: 
	1.System shut down (make system add an item to a log.txt after each item is added)
	2.Customer asks for a item to be removed
	
Please comment everything that you added so others understand your code

*/
	