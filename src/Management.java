import java.io.*;
import java.util.*;
import java.text.*;

public class Management {
 
 private static String userDatabase;
 
 
 public Management(){
   
   if (System.getProperty("os.name").startsWith("W")||System.getProperty("os.name").startsWith("w")){
     userDatabase="..\\Database\\userDatabase.txt";
   }
   else{
     userDatabase= "../Database/userDatabase.txt";
   }
   
 }
 
 public Boolean checkUser(Long phone){ //returns true if user phone is in DB, false if not
     //needs to be cleaned up.. written with terrible style right now but *at least it works*
    //check user will open the user database and check to see if user's phone number is on the list
     try{
       FileReader fileR = new FileReader(userDatabase);
       BufferedReader textReader = new BufferedReader(fileR);
       String line;
       long nextPh = 0;
       //reads the entire database
       line = textReader.readLine(); //skips the first line, which explains how the DB is formatted. 
       while ((line = textReader.readLine()) != null){
         
         
         try {  
           nextPh = Long.parseLong(line.split(" ")[0]);    
         } catch (NumberFormatException e) {  
           continue;  
         } 
         
         
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
 
 public List<ReturnItem> getLatestReturnDate(Long phone) 
 {
   long nextPh = 0;
   boolean outstandingReturns = false;
   
   SimpleDateFormat formatter =  new SimpleDateFormat("MM/dd/yyyy");
   List<ReturnItem> returnList = new ArrayList<ReturnItem>(); //this list will store all items to be used in this sale
   
   String thisReturnDate = null;
   int numberDaysPassed = 0;
   
  //Read from database:
   try{
       FileReader fileR = new FileReader(userDatabase);
       BufferedReader textReader = new BufferedReader(fileR);
       String line;
       
       //reads the entire database
       line = textReader.readLine(); //skips the first line, which explains how the DB is formatted. 
       while ((line = textReader.readLine()) != null){
        
         try {  
           nextPh = Long.parseLong(line.split(" ")[0]);    
         } catch (NumberFormatException e) {  
           continue;  
         } 
         if(nextPh == phone)//finds the user in the database
         { 
           
           //System.out.println("getLatestReturnDate: found phone..");
           
           if(line.split(" ").length >1){
            //System.out.println("you've rented with us before"); 
             //System.out.print("here are the items you've rented before: ");
             for(int i =1; i<line.split(" ").length; i++){
               //line.split(" ")[i] represents 1022,6/31/11,true for example
               String returnedBool = (line.split(" ")[i]).split(",")[2];
               //System.out.print(line.split(" ")[i]);
               //System.out.print(returnedBool);
               boolean b = returnedBool.equalsIgnoreCase("true");
               if (!b){ //if item wasn't returned already
                 outstandingReturns = true; 
                 System.out.println("You still haven't returned item id: "+(line.split(" ")[i]).split(",")[0]+", due: "+(line.split(" ")[i]).split(",")[1]);
                 thisReturnDate = line.split(" ")[i].split(",")[1];
                 
                 try {
					Date returnDate = formatter.parse(thisReturnDate);
					Calendar with = Calendar.getInstance();
					with.setTime(returnDate);
					numberDaysPassed = daysBetween (with);
					ReturnItem returnItem = new ReturnItem(Integer.parseInt(line.split(" ")[i].split(",")[0]) , numberDaysPassed );
					returnList.add(returnItem);
					
				} 
                 catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                 
                 
               }     
             }
           }
           if (!outstandingReturns){
            System.out.println("No outstanding returns"); 
           }
         }
       }
       
       textReader.close();
       fileR.close();
    }
   
   //catches exceptions
     catch(FileNotFoundException ex) {
       System.out.println("cannot open userDB"); 
      
     }
     catch(IOException ex) {
       System.out.println("ioexception");
     }
   
   return returnList;
   
 }
 
 private static int daysBetween(Calendar day1){
	    
	 Calendar day2 = Calendar.getInstance();
	 
	 Calendar dayOne = (Calendar) day1.clone(),
	            dayTwo = (Calendar) day2.clone();

	    if (dayOne.get(Calendar.YEAR) == dayTwo.get(Calendar.YEAR)) {
	        return Math.abs(dayOne.get(Calendar.DAY_OF_YEAR) - dayTwo.get(Calendar.DAY_OF_YEAR));
	    } else {
	        if (dayTwo.get(Calendar.YEAR) > dayOne.get(Calendar.YEAR)) {
	            //swap them
	            Calendar temp = dayOne;
	            dayOne = dayTwo;
	            dayTwo = temp;
	        }
	        int extraDays = 0;

	        int dayOneOriginalYearDays = dayOne.get(Calendar.DAY_OF_YEAR);

	        while (dayOne.get(Calendar.YEAR) > dayTwo.get(Calendar.YEAR)) {
	            dayOne.add(Calendar.YEAR, -1);
	            // getActualMaximum() important for leap years
	            extraDays += dayOne.getActualMaximum(Calendar.DAY_OF_YEAR);
	        }

	        return extraDays - dayTwo.get(Calendar.DAY_OF_YEAR) + dayOneOriginalYearDays ;
	    }
	}
 
 public boolean createUser(Long phone){

   String strPhone = Long.toString(phone);
   File file = new File (userDatabase);
   try {
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
    out.println();
    out.print(strPhone);
    out.close();
    return true;
   } catch (IOException e) {
     System.out.println("cannot write to userDB");
     return false;
   }
 }
 
 public void addRental(Long phone, int ID){ //in development. need to figure out how to append to a specific line and also how to call this from pointofsale but only when its a rental
   //will parse userDatabase until it gets to the line with the phone number, then append the rented item in correct 1022,6/31/11,true format
   long nextPh = 0;
   try{
       FileReader fileR = new FileReader(userDatabase);
       BufferedReader textReader = new BufferedReader(fileR);
       String line;
       //reads the entire database
       line = textReader.readLine(); //skips the first line, which explains how the DB is formatted. 
       while ((line = textReader.readLine()) != null){
        
         try {  
           nextPh = Long.parseLong(line.split(" ")[0]);    
         } catch (NumberFormatException e) {  
           continue;  
         } 
         if(nextPh == phone)//finds the user in the database
         { 
           Date date = new Date();
           Format formatter = new SimpleDateFormat("MM/dd/yy");
           String dateFormat = formatter.format(date);
           String thisSale = ID + ","+dateFormat+","+"true";
           //System.out.println("adding.. "+thisSale);
           //to do: append thisSale to the end of current line
           break; //stops it from reading the rest of the userDB after.
         }
       }
       
       textReader.close();
       fileR.close();
    }
   
   //catches exceptions
     catch(FileNotFoundException ex) {
       System.out.println("cannot open userDB"); 
      
     }
     catch(IOException ex) {
       System.out.println("ioexception");
     }
   
 }
 
 
 
}
