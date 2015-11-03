import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.FileNotFoundException;
import java.io.*; 

import java.util.*;


public class Rental extends PointOfSale{
  
  private Long phone;
  
  //private static String databaseFile = "..\\Database\\rentalDatabase.txt"; //Currently assumes windows OS, need to add OS detection
  
  Management management = new Management();
  
  public Rental(){
    
  }
  
  public void newRental()
  {
    try{ File file = new File("..\\Database\\temp.txt");
      // if file doesnt exists, then create it
      if (!file.exists()) {
        file.createNewFile();
      }
      //temp log
      FileWriter fw = new FileWriter(file.getAbsoluteFile());
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write("Rental");
      bw.write(System.getProperty( "line.separator" ));
      
      
      
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
            //this will have the same body as the last else block (succeseful rental scenario) of this method
            //checking last rented item:
            String phoneStr=Long.toString(phone);
            bw.write(phoneStr);//add phone to temp
            bw.write(System.getProperty( "line.separator" ));
            bw.close();//end temp log
            management.getLatestReturnDate(phone);
            startNew(rentalDatabaseFile);
            endPOS(rentalDatabaseFile,true,null);
            returnDate();
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
        
        //returnDate();
        //checking last rented item:
        management.getLatestReturnDate(phone);
        startNew(rentalDatabaseFile);
        endPOS(rentalDatabaseFile,true,null);
        returnDate();
        //to do:
        //1) query user for item id
        //2) check to see if item id exists in rentalDatabase, if not go back to 1
        //3) produce total$ to rent (no tax.. I'm pretty sure you dont pay tax for renting things
        //4) add the item to the users line on the userDB
        
        
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  
  
  
  
  
  
  private void returnDate()
  {
    DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, ''yy");
    Calendar cal = Calendar.getInstance();
    int year,month,day;
    
    System.out.print("Today's date: ");
    System.out.println(dateFormat.format(cal.getTime())); //prints today's date
    
    System.out.print("Return Date: ");
    
    cal.add(Calendar.DAY_OF_MONTH, 14);
    
    System.out.println(dateFormat.format(cal.getTime())); //prints return date
    
    year = cal.get(Calendar.YEAR); //stores return year
    month = cal.get(Calendar.MONTH) + 1; //stores return month
    day = cal.get(Calendar.DAY_OF_MONTH); //stores return day
    
    //Matt add the return date values for the user rented items in your database. We decided to assume the return date will be fixed:
    //2 weeks from now
    
  }
  
  public void continueT(){
   continueTrans(rentalDatabaseFile);
   endPOS(rentalDatabaseFile,true,null);
   returnDate();
 }
  
}
