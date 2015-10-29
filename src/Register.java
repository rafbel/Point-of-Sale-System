import java.util.Scanner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Register {
 
 public static void main(String[] args)
 {
  DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, ''yy");
  Calendar cal = Calendar.getInstance();
   //1) prompt user login using logIn() method
   
  //2) print out menu of functionalities and wait for user to select one.. those include:
  //SALE : should redirect to sale class and the sale process used in prototype.. we need to add logging to the sale process
  //RENTAL: redirect to rental class, needs logging and customer user management (add custmer, edit customer, verify customer) 
  //RETURN: redirect to return class, needs logging
  //LOGOUT: saves cashier's log files, calls logIn to override user globals
  //
  //if logged in user is an admin:
  //EDIT USER DATABASE : add/delete users, overwrite passwords, change privelages
  //EDIT ITEM DATABASE : add new items, delete existing items, override price, quantity 
  //EDIT COUPON DATABASE: add coupons, delete coupons, change coupons
  //EDIT CUSTOMER DATABASE: should have the same functionality as a regular cashier during a 
   
   
   
  //for prototype
  Sale sale = new Sale();
  Rental rental = new Rental();
  String choice = null;
  
  System.out.print("Welcome to SG Technologies POS System ");
  System.out.println(dateFormat.format(cal.getTime()));
  System.out.println("Press s to start new sale");
  System.out.println("Press r to start new rental");
  System.out.println("Press q to shut down system");
  
  Scanner cashierInput = new Scanner(System.in);
  
  while (true)
  {
	  choice = cashierInput.nextLine();
	  
	  if (choice.equals("s")) //starts new sale
		  sale.newSale();
   
	  if (choice.equals("r"))
		  rental.newRental();
   
	  if (choice.equals("q"))
		  break;
    
   System.out.print("Welcome to SG Technologies POS System  ");
   System.out.println(dateFormat.format(cal.getTime())); 
   System.out.println("Press s to start new sale");
   System.out.println("Press r to start new rental");
   System.out.println("Press q to shut down system");
   
  }
  
  System.out.println("System shutting down");
  cashierInput.close();
 }
 
 
}


 