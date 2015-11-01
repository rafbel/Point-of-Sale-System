import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HandleReturns extends PointOfSale{
	
	private static final String rentedDatabaseFile = "..\\Database\\rentalDatabase.txt";
	private static final String saleDatabaseFile = "Database/itemDatabase.txt";
	private static float noTax = 1.0f;
	private double totalPrice;
	private Management management = new Management();
	
	//Note: Missing taking out returned items from the userDB
	
	public void newReturn()
	{
		//Two scenarios: returning rented items or returning items not satisfied with
		
		Scanner input = new Scanner(System.in);
		String choice;
		List<ReturnItem> returnList = new ArrayList<ReturnItem>();
		List<Item> returningNow = new ArrayList<Item>();
		long phone;
		
		System.out.println("Enter customer's phone number");//assumes customer is in the database, we'll need to add an add customer method later
		   Scanner s = new Scanner(System.in);
		   while (((phone = s.nextLong())>9999999999l)||(phone<1000000000l)){ //checks to make sure phone is a 10 digit integer
		     System.out.println("invalid phone num, please try a 10 digit integer");
		   }
		   s.close();
		   if (!management.checkUser(phone)){
		     System.out.println("user doesnt exist in userDatabase, create new user? y - yes");
		     s = new Scanner(System.in);
		     String newUser = s.nextLine();
		     s.close();
		     
		     if(newUser.equals("y")){
		       //System.out.println("creating new user..");
		       if(management.createUser(phone)){
		         System.out.println("New user created, continuing with rental..");

		        System.out.println("Press 'r' if user is returning rented items.");
		 		System.out.println("Press 'i' if user is returning sale items that is not satisfied with.");
		 		
		 		choice = input.nextLine();
		 		if (choice.equals("r"))
		 		{
		 			//rented items (check return date)
		 			returningNow = startNew(rentedDatabaseFile);
		 			returnList = management.getLatestReturnDate(phone);
		 			
		 			
		 			//Check user database to check if user delivered items after the return date
		 			//If did add a rate to the total to be paid
		 		}
		 		
		 		else if (choice.equals("i"))
		 		{
		 			//Items not satisfied with
		 			startNew(saleDatabaseFile);
		 			endPOS(noTax, saleDatabaseFile,false,null);
		 		}
		 		
		 		else
		 		{
		 			System.out.println("Not a valid option");
		 		}
		       }
		       else{
		        System.out.println("Couldn't create new user.."); 
		         
		       }
		     }
		     else{
		       System.out.println("You need an account to rent something.");
		     }
		       
		   }
		   else{
			   
			   System.out.println("Press 'r' if user is returning rented items.");
			   System.out.println("Press 'i' if user is returning sale items that is not satisfied with.");
				
				choice = input.nextLine();
				if (choice.equals("r"))
				{
					//rented items (check return date)
					startNew(rentedDatabaseFile);
					returnList = management.getLatestReturnDate(phone);
					endPOS(noTax,rentedDatabaseFile,false,returnList);
					
					//Check user database to check if user delivered items after the return date
					//If did add a rate to the total to be paid
				}
				
				else if (choice.equals("i"))
				{
					//Items not satisfied with
					startNew(saleDatabaseFile);
					endPOS(noTax, saleDatabaseFile,false,null);
				}
				
				else
				{
					System.out.println("Not a valid option");
				}


		   }
		
		
	}
	
	
}
