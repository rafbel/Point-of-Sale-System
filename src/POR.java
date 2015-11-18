import java.io.*;
import java.util.*;

public class POR extends PointOfSale {
  long phoneNum;
  
  public POR(long phoneNum){
    this.phoneNum = phoneNum;
  };
  public void deleteTempItem(int id){
    boolean ableToOpen=true;
    try{
      String temp = "Database/newTemp.txt";
      if(System.getProperty("os.name").startsWith("W")||System.getProperty("os.name").startsWith("w")){
        //temp = "..\\Database\\newTemp.txt"; 
      }
      File tempF = new File(temp);
      FileReader fileR = new FileReader(tempFile);
      BufferedReader reader = new BufferedReader(fileR);
      BufferedWriter writer = new BufferedWriter(new FileWriter(tempF));
      String type= reader.readLine();
      String phone=reader.readLine();
      //this.phoneNum = Long.parseLong(phone);
      //System.out.println(this.phoneNum);
      writer.write(type);
      writer.write(System.getProperty("line.separator"));
      writer.write(phone);
      writer.write(System.getProperty("line.separator"));
      for (int i =0; i<transactionItem.size();i++){
        if (transactionItem.get(i).getItemID()!=id){
          writer.write(transactionItem.get(i).getItemID() +" "+ transactionItem.get(i).getAmount());
          writer.write(System.getProperty( "line.separator" ));
        }
        
      }
      fileR.close();
      writer.close(); 
      reader.close(); 
      File file = new File(tempFile);
      file.delete();
      boolean successful = tempF.renameTo(new File(tempFile));
      
    }
    catch(FileNotFoundException ex) {
      System.out.println(
                         "Unable to open file 'temp'"); 
      ableToOpen = false;
    }
    catch(IOException ex) {
      System.out.println(
                         "Error reading file 'temp'");  
      ableToOpen = false;
    }
    
  }
  
  public double endPOS(String textFile){
    boolean bool=true;
    Management man = new Management();
    man.addRental(this.phoneNum, this.transactionItem);
    detectSystem();
    if (transactionItem.size()>0){
      totalPrice = totalPrice*tax; //calculates price with tax
      //prints total with taxes
      //bool=payment();
      //f(bool==true){
        /*for (int counter = 0; counter < transactionItem.size(); counter++){
          //prints item name - price
          System.out.format("%d %s x %d  --- $ %.2f\n", transactionItem.get(counter).getItemID(),transactionItem.get(counter).getItemName(),
                            transactionItem.get(counter).getAmount(), 
                            transactionItem.get(counter).getPrice()*transactionItem.get(counter).getAmount());
        }
        System.out.format("Total with taxes: %.2f\n", totalPrice);
        inventory.updateInventory(textFile, transactionItem, databaseItem,true);
      }*/
    }
    //delete log file
    File file = new File(tempFile);
    file.delete();
    databaseItem.clear();
    transactionItem.clear();
    return totalPrice;
  }
  
  public void retrieveTemp(String textFile){
    boolean ableToOpen=true;
    try{
      FileReader fileR = new FileReader(tempFile);
      BufferedReader textReader = new BufferedReader(fileR);
      String line=null;
      int numLine=0;
      String[] lineSort;
      line=textReader.readLine();
      inventory.accessInventory(textFile, databaseItem);
      line=textReader.readLine();
      System.out.println("Phone number:");
      System.out.println(line);
      
      while ((line = textReader.readLine()) != null)
      {
        lineSort = line.split(" ");
        int itemNo = Integer.parseInt(lineSort[0]);
        int itemAmount = Integer.parseInt(lineSort[1]);
        enterItem(itemNo,itemAmount);
        numLine++;
      }
      
      updateTotal();
    }
    catch(FileNotFoundException ex) {
      System.out.println(
                         "Unable to open file 'temp'"); 
      ableToOpen = false;
    }
    catch(IOException ex) {
      System.out.println(
                         "Error reading file 'temp'");  
      ableToOpen = false;
    }

  }
}
