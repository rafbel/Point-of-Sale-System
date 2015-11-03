import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.*;

public class Sale{ 
  public Sale() {
  }
  
  public void startNew(String textFile){  
    try{
      String temp = "../Database/temp.txt";
      if(System.getProperty("os.name").startsWith("W")||System.getProperty("os.name").startsWith("w")){
        temp = "..\\Database\\temp.txt"; 
      }
      File file = new File(temp);
      file.delete();
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
    
    PointOfSale point=new POS();
    point.detectSystem();
    point.addItems(textFile);
    point.removeItems();
    point.coupon();
    point.endPOS(textFile);
  }
  
  public void continueT(String textFile){
    PointOfSale point=new POS();
    point.detectSystem();
    point.retrieveTemp(textFile);
    System.out.println("Do you want to continue adding items? y- Yes");
    Scanner scan=new Scanner(System.in);
    String bool=scan.next();
    if(bool.equals("y")){
      point.addItems(textFile);
    }
    point.removeItems();
    point.coupon();
    point.endPOS(textFile);
  }
  
  
}