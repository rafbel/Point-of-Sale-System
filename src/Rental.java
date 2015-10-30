import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.FileNotFoundException;
import java.io.*; 

import java.util.*;


public class Rental extends PointOfSale{
 
 Long phone;
  
 private static String databaseFile = "..\\Database\\rentalDatabase.txt"; //Currently assumes windows OS, need to add OS detection
 
 public Rental(){}
 
 public void newRental()
 {
   System.out.println("Enter customer's phone number");//assumes customer is in the database, we'll need to add an add customer method later
   Scanner s = new Scanner(System.in);
   while (((phone = s.nextLong())>9999999999l)||(phone<1000000000l)){ //checks to make sure phone is a 10 digit integer
     System.out.println("invalid phone num, please try a 10 digit integer");
   }
   if (!checkUser(phone)){
     System.out.println("user doesnt exist in userDatabase");
   }
   else{

     //returnDate();
     startNew(databaseFile);
     endPOS(1.06,databaseFile,true);
     returnDate();
	 //to do:
     //1) query user for item id
     //2) check to see if item id exists in rentalDatabase, if not go back to 1
     //3) produce total$ to rent (no tax.. I'm pretty sure you dont pay tax for renting things
     //4) add the item to the users line on the userDB


   }
   
 }
 
 public Boolean checkUser(Long phoneNumber){ //returns true if user phone is in DB, false if not
   //needs to be cleaned up.. written with terrible style right now but *at least it works*
  //check user will open the user database and check to see if user's phone number is on the list
   try{
     FileReader fileR = new FileReader("..\\Database\\userDatabase.txt");
     BufferedReader textReader = new BufferedReader(fileR);
     String line;
     //reads the entire database
     line = textReader.readLine(); //skips the first line, which explains how the DB is formatted. 
     while ((line = textReader.readLine()) != null){
       long nextPh = Long.parseLong(line.split(" ")[0]);
       if(nextPh==phone){
         textReader.close();
         fileR.close();
         //System.out.println("user phone number found in userDatabase");
         return true;
       }
       //System.out.println(line.split(" ")[0]);
     }
     textReader.close();
     fileR.close();
     //System.out.println("reached end of userDatabase, phone number not found");
     return false; 
   }
   //catches exceptions
   catch(FileNotFoundException ex) {
     System.out.println("cannot open userDB"); 
    
   }
   catch(IOException ex) {
     System.out.println("ioexception");
   }
   return true;
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
  
  year = cal.get(Calendar.YEAR); //stores return day
  month = cal.get(Calendar.MONTH) + 1; //stores return month
  day = cal.get(Calendar.DAY_OF_MONTH); //stores return year
  
  //Matt add the return date values for the user rented items in your database. We decided to assume the return date will be fixed:
  //2 weeks from now
  
 }
 
 public void continueT(){
   continueTrans(databaseFile);
 }

}
