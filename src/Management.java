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
 
 public void getLatestReturnDate(Long phone) 
 {
   long nextPh = 0;
   boolean outstandingReturns = false;
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
               if (!b){
                 outstandingReturns = true; 
                 System.out.println("You still haven't returned item id: "+(line.split(" ")[i]).split(",")[0]+", due: "+(line.split(" ")[i]).split(",")[1]);
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
