import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Rental extends PointOfSale{
 
 //Note: Still needs to create Database file for rental
 
 private static String databaseFile = "..\\Database\\rentalDatabase.txt"; //Currently assumes windows OS, need to add OS detection
 
 public Rental(){}
 
 public void newRental()
 {
  startNew(databaseFile);
  endPOS(1.06,databaseFile);
  returnDate();
 }
 
 
 private void returnDate()
 {
  DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, ''yy");
  Calendar cal = Calendar.getInstance();
  
  System.out.print("Today's date: ");
  System.out.println(dateFormat.format(cal.getTime()));
  System.out.print("Return Date: ");
  cal.add(Calendar.DAY_OF_MONTH, 14);
  System.out.println(dateFormat.format(cal.getTime()));
  
 }
 
 public void continueT(){
   continueTrans(databaseFile);
 }

}
