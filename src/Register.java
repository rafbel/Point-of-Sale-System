import java.util.Scanner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.*;

public class Register {
  public static void main(String[] args)
  {
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
    Scanner scan=new Scanner(System.in);
    boolean startUp=true;
    String func="";
    while(startUp==true){
      System.out.println("Enter 1 to login, enter 2 to shut down the system.");
      func=scan.next();
      while(!func.equals("1")&&!func.equals("2")){
        System.out.println("Invalid input. Please enter again.");
        func=scan.next();
      }
      
      if(func.equals("1")){
        POSSystem point=new POSSystem();
        point.logIn();
      }
      else{
        startUp=false;
        System.out.println("System is shutting down");
        System.exit(0);
      }
      
    }
  }
  
}


