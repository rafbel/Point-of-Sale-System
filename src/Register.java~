import java.util.Scanner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.*;

public class Register {
  
  public static boolean unixOS = true; 
  public static String rentalDatabaseFile = "../Database/rentalDatabase.txt"; 
  public static String itemDatabaseFile = "../Database/itemDatabase.txt"; 
  
  
  public static void main(String[] args)
  {
    if (System.getProperty("os.name").startsWith("W")||System.getProperty("os.name").startsWith("w")){
      unixOS = false; 
      rentalDatabaseFile = "..\\Database\\rentalDatabase.txt"; 
      itemDatabaseFile = "..\\Database\\itemDatabase.txt";
    }
    
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
    
    HandleReturns returns = new HandleReturns();
    String choice = "p";
    
    System.out.print("Welcome to SG Technologies POS System ");
    System.out.println(dateFormat.format(cal.getTime()));
    Scanner cashierInput = new Scanner(System.in);
    
    //retrieve the incomplete transaction
    boolean ableToOpen = true;
    
    String temp = "../Database/temp.txt";
    if(System.getProperty("os.name").startsWith("W")||System.getProperty("os.name").startsWith("w")){
      temp = "..\\Database\\temp.txt"; 
    }
    
    File f=new File(temp);
    if(f.exists() && !f.isDirectory()) { 
      System.out.println(" There is an incomplete transaction. Do you want to retrieve it? y- Yes");
      String retrieve=cashierInput.next();
      if(retrieve.equals("y")){
        try{
          FileReader fileR = new FileReader(temp);
          BufferedReader textReader = new BufferedReader(fileR);
          if(f.length()==0){
            System.out.println("The log file is not valid"); 
            f.delete();
          }
          else{
          String type= textReader.readLine();
          if(type.equals("Sale")){         
            System.out.println("Sale");
            Sale sale=new Sale();
            sale.continueT(itemDatabaseFile); 
          }
          else if(type.equals("Rental")){
            System.out.println("Rental");
            Rental rental=new Rental();
            rental.continueT(rentalDatabaseFile); 
          }
          else{
            System.out.println("The log file is not valid"); 
          }
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
    
    
    
    
    while (!choice.equals("q"))
    {
      System.out.println("Press s to start new sale");
      System.out.println("Press r to start new rental");
      System.out.println("Press h to start a return");
      System.out.println("Press q to shut down system");
      
      choice = cashierInput.next();
      
      if (choice.equals("s")){
        //starts new sale
        Sale sale=new Sale();
        sale.startNew(itemDatabaseFile);
      }
      else if (choice.equals("r")){
        Rental rental=new Rental();
        rental.startNew(rentalDatabaseFile);
      }
      else if (choice.equals("h"))
        returns.newReturn();
     
    }
    
    System.out.println("System shutting down");
    cashierInput.close();
  }
  
}


