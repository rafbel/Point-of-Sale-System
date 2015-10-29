import java.util.Scanner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.*;

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
  HandleReturns returns = new HandleReturns();
  String choice = null;
  
  System.out.print("Welcome to SG Technologies POS System ");
  System.out.println(dateFormat.format(cal.getTime()));
  Scanner cashierInput = new Scanner(System.in);
  
  //retrieve the incomplete transaction
  boolean ableToOpen = true;
  File f=new File("temp.txt");
  if(f.exists() && !f.isDirectory()) { 
    System.out.println(" There is an incomplete transaction. Do you want to retrieve it? y- Yes");
    String retrieve=cashierInput.next();
    if(retrieve.equals("y")){
      try{
      FileReader fileR = new FileReader("temp.txt");
        BufferedReader textReader = new BufferedReader(fileR);
        String type= textReader.readLine();
        if(type.equals("Sale")){
         sale.continueT(); 
        }
        else if(type.equals("Rental")){
         rental.continueT(); 
        }
        else{
         System.out.println("The log file is not valid"); 
        }
        textReader.close();
      }
       catch(FileNotFoundException ex) {
        System.out.println(
                           "Unable to open file 'temp'"); 
        ableToOpen = false;
      }
      catch(IOException ex) {
        System.out.println(
                           "Error reading file 'temp'");  
        ableToOpen = false;
      }
    }
}
  
  System.out.println("Press s to start new sale");
  System.out.println("Press r to start new rental");
  System.out.println("Press q to shut down system");
  

  
  while (true)
  {
	  choice = cashierInput.nextLine();
	  
	  if (choice.equals("s")) //starts new sale
		  sale.newSale();
   
	  if (choice.equals("r"))
		  rental.newRental();
	  
	  if (choice.equals("h"))
		  returns.newReturn();
   
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


 