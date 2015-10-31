import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
 
 public void getLatestReturnDate(Long phone) //In development
 {
  //Read from database:
   try{
       FileReader fileR = new FileReader(userDatabase);
       BufferedReader textReader = new BufferedReader(fileR);
       String line;
       
       //reads the entire database
       line = textReader.readLine(); //skips the first line, which explains how the DB is formatted. 
       while ((line = textReader.readLine()) != null){
        
         long nextPh = Long.parseLong(line.split(" ")[0]);
         if(nextPh == phone)//finds the user in the database
         { 
           //Retrieves the latest rental date
        //Then calculates the number of days passed since that return date
        //Returns that integer
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
