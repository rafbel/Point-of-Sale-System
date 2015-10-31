//latest push added operating system detection for pathing

public class Sale extends PointOfSale{

 //attributes
 //private static String databaseFile = "..\\Database\\itemDatabase.txt"; //determines the name of the databaseFile for sale
 
 //methods
 
 //constructor
 public Sale() {
  
 
 }
 
 public void newSale()
 {
  startNew(itemDatabaseFile);
  endPOS(1.06,itemDatabaseFile,true);
 }
 
 public void continueT(){
   continueTrans(itemDatabaseFile);
 }
  
}
 

