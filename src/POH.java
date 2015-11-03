import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class POH extends PointOfSale
{
	List <ReturnItem> returnList = new ArrayList<ReturnItem>();
	long phone = 0;
	
	public POH(long phone)
	{
		this.phone = phone;
	}
	
	 public void deleteTempItem(int id){
		    boolean ableToOpen=true;
		    try{
		      String temp = "../Database/newTemp.txt";
		      if(System.getProperty("os.name").startsWith("W")||System.getProperty("os.name").startsWith("w")){
		        temp = "..\\Database\\newTemp.txt"; 
		      }
		      File tempF = new File(temp);
		      FileReader fileR = new FileReader(tempFile);
		      BufferedReader reader = new BufferedReader(fileR);
		      BufferedWriter writer = new BufferedWriter(new FileWriter(tempF));
		      String type= reader.readLine();
		      String phone;
		      phone=reader.readLine();
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
		  
		  public void endPOS(String textFile){
		    detectSystem();
		    if (transactionItem.size() > 0)
		    {
			    Management management = new Management();
				returnList = management.getLatestReturnDate(phone);
				double itemPrice = 0;
				totalPrice = 0;
				
				for (int transactionCounter = 0; transactionCounter < transactionItem.size(); transactionCounter++)
			          for (int returnCounter = 0; returnCounter < returnList.size(); returnCounter++)
			        {
			          if (transactionItem.get(transactionCounter).getItemID() == returnList.get(returnCounter).getItemID())
			          {
			            //Applies a value to be payed depending on the amount of days it is late. If it is not late, no value is applied
			            itemPrice = transactionItem.get(transactionCounter).getPrice()* 0.1 * returnList.get(returnCounter).getDays();
			            totalPrice += itemPrice;
			            System.out.println("Item Name: " + transactionItem.get(transactionCounter).getItemName() + "Days Late: " 
			                                 + returnList.get(returnCounter).getDays() + "To be paid: " + itemPrice);
			            System.out.println("Total: " + totalPrice);
			          }
			        }
			   /* totalPrice = totalPrice*taxCalculator(); //calculates price with tax
			    //prints total with taxes
			    for (int counter = 0; counter < transactionItem.size(); counter++){
			      //prints item name - price
			      System.out.format("%d %s x %d  --- $ %.2f\n", transactionItem.get(counter).getItemID(),transactionItem.get(counter).getItemName(),
			                        transactionItem.get(counter).getAmount(), 
			                        transactionItem.get(counter).getPrice()*transactionItem.get(counter).getAmount());
			    }
			    System.out.format("Total with taxes: %.2f\n", totalPrice);*/
			    
				inventory.updateInventory(textFile, transactionItem, databaseItem,false);
				
				management.updateRentalStatus(phone,returnList);
		    	
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
