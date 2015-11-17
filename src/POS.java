import java.io.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class POS extends PointOfSale {
  public POS(){};
  
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
      String phone;
      writer.write(type);
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
  
  public void endPOS(String textFile){
    detectSystem();
    boolean bool=true;
    if (transactionItem.size()>0){
    totalPrice = totalPrice*taxCalculator(); //calculates price with tax
    //prints total with taxes
    bool=payment();
    if(bool==true){
    for (int counter = 0; counter < transactionItem.size(); counter++){
      //prints item name - price
      System.out.format("%d %s x %d  --- $ %.2f\n", transactionItem.get(counter).getItemID(),transactionItem.get(counter).getItemName(),
                        transactionItem.get(counter).getAmount(), 
                        transactionItem.get(counter).getPrice()*transactionItem.get(counter).getAmount());
    }
    System.out.format("Total with taxes: %.2f\n", totalPrice);
    inventory.updateInventory(textFile, transactionItem, databaseItem,false);
    }
    }
    //delete log file
    File file = new File(tempFile);
      file.delete();
      if(bool==true){
    //invoice record file
    try{
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
  Calendar cal = Calendar.getInstance();
      String t = "Database/saleInvoiceRecord.txt";
      if(System.getProperty("os.name").startsWith("W")||System.getProperty("os.name").startsWith("w")){
        //t = "..\\Database\\saleInvoiceRecord.txt"; 
      }
      FileWriter fw2 = new FileWriter(t,true);
      BufferedWriter bw2 = new BufferedWriter(fw2);
      bw2.write(dateFormat.format(cal.getTime()));
      bw2.write(System.getProperty( "line.separator" ));
      for(int i=0;i<transactionItem.size();i++){
       String log=Integer.toString(transactionItem.get(i).getItemID())+" "+transactionItem.get(i).getItemName()+" "+
                        Integer.toString(transactionItem.get(i).getAmount())+" "+
                        Double.toString(transactionItem.get(i).getPrice()*transactionItem.get(i).getAmount());
       bw2.write(log);
      bw2.write(System.getProperty( "line.separator" ));
      }
      bw2.write("Total with tax: "+totalPrice);
      bw2.newLine();
      bw2.close();
      
    } catch (FileNotFoundException e) {
      System.out.println("Unable to open file Log File for logout"); 
    }
    catch (IOException e) {
      e.printStackTrace();
    }  
      }
     databaseItem.clear();
    transactionItem.clear();
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
