//latest push added operating system detection for pathing

public class Sale extends PointOfSale{

 //attributes
 private static String databaseFile = "Database/itemDatabase.txt"; //determines the name of the databaseFile for sale
 
 //methods
 
 //constructor
 public Sale() {
   //detects windows OS, changes databaseFile string to use "\" protocol
   //if (System.getProperty("os.name").startsWith("W")||System.getProperty("os.name").startsWith("w")){
     //System.out.println("windows OS detected.. boop beep bop");
     //databaseFile = "Database\\itemDatabase.txt";
   //}
 
 }
 
 public void newSale()
 {
  startNew(databaseFile);
  endPOS(1.06,databaseFile);
 }
 
  
}
 

