import java.util.Scanner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Register {
	
	public static void main(String[] args)
	{
		DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, ''yy");
		Calendar cal = Calendar.getInstance();
		Sale sale = new Sale();
		Rental rental = new Rental();
		String choice;
		
		System.out.print("Welcome to SG Technologies POS System	");
		System.out.println(dateFormat.format(cal.getTime()));
		System.out.println("Press s to start new sale");
		System.out.println("Press s to start new rental");
		System.out.println("Press q to shut down system");
		
		Scanner cashierInput = new Scanner(System.in);
		
		while ( !(choice = cashierInput.next() ).equals("q"))
		{
			if (choice.equals("s")) //starts new sale
				sale.newSale();
			
			if (choice.equals("r"))
				rental.newRental();
				
			System.out.print("Welcome to SG Technologies POS System	 ");
			System.out.println(dateFormat.format(cal.getTime()));	
			System.out.println("Press s to start new sale");
			System.out.println("Press s to start new rental");
			System.out.println("Press q to shut down system");
			
			
		}
		
		System.out.println("System shutting down");
		cashierInput.close();
	}
	
}

//in development

/* Notes
To be added in the future: 
	1.System shut down (make system add an item to a log.txt after each item is added)
	2.Customer asks for an item to be removed
	3.Create Tax Calculator
	4.Make system return return date for items
	
Please comment everything that you added so others understand your code

*/
	