//latest push added operating system detection for pathing
import java.io.*; 
public class Sale extends PointOfSale{

 //attributes
 //private static String databaseFile = "..\\Database\\itemDatabase.txt"; //determines the name of the databaseFile for sale
 
 //methods
 
 //constructor
 public Sale() {
 }
 
 public void newSale()
 {
   try{
    File file = new File("..\\Database\\temp.txt");

   // if file doesnt exists, then create it
   if (!file.exists()) {
    file.createNewFile();
   }

   FileWriter fw = new FileWriter(file.getAbsoluteFile());
   BufferedWriter bw = new BufferedWriter(fw);
   bw.write("Sale");
   bw.write(System.getProperty( "line.separator" ));
   bw.close();


  } catch (IOException e) {
   e.printStackTrace();
  }
  startNew(itemDatabaseFile);
  endPOS(itemDatabaseFile,true,null);
 }
 
 public void continueT(){
   continueTrans(itemDatabaseFile);
 }
  
}
 

