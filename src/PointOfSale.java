import java.io.*;
import java.util.*;

abstract class PointOfSale {
  //attributes
  public double totalPrice=0;
  private static float discount = 0.90f;
  public boolean unixOS = true; 
  public double tax=1.06;
  
  public boolean returnSale=true;
  
  //public static String rentalDatabaseFile = "../Database/rentalDatabase.txt"; 
  public static String couponNumber = "Database/couponNumber.txt";
  //determines the name of the databaseFile for sale
  public static String tempFile="Database/temp.txt";
  //detects windows OS, changes databaseFile string to use "\" protocol
  /*if (System.getProperty("os.name").startsWith("W")||System.getProperty("os.name").startsWith("w")){
   unixOS = false; 
   couponNumber= "..\\Database\\couponNumber.txt"; 
   rentalDatabaseFile = "..\\Database\\rentalDatabase.txt"; 
   itemDatabaseFile = "..\\Database\\itemDatabase.txt";
   tempFile="..\\Database\\temp.txt";
   }*/
  
  Inventory inventory = Inventory.getInstance();
  
  public List<Item> databaseItem = new ArrayList<Item>(); //creates a list of all items in the database
  public List<Item> transactionItem = new ArrayList<Item>(); //this list will store all items to be used in this sale
  
  
  public boolean startNew(String databaseFile)
  {
	  if (inventory.accessInventory(databaseFile, databaseItem) == true) //if can access inventory
	    return true;
	  
	  return false;
  }
  
  public boolean enterItem(int itemID, int amount) //might include in a "mother class" in the future
  {
    detectSystem();
    boolean foundItem = false;
    
    for (int counter = 0; counter < databaseItem.size() && foundItem == false; counter++)
    {
      if (databaseItem.get(counter).getItemID() == itemID) //checks if item is found on the database
      {
        transactionItem.add(new Item(itemID,databaseItem.get(counter).getItemName(),databaseItem.get(counter).getPrice(),amount));
        foundItem = true;
      }
    }
    
    //if (foundItem == true)
    //updateTotal();
    return foundItem;
  }
  
  public double updateTotal() 
  {
    //updates total value to be displayed on the screen
    totalPrice += transactionItem.get(transactionItem.size() - 1).getPrice()
      *transactionItem.get(transactionItem.size() - 1).getAmount();
    
    //shows running total on screen and item info
    //for (int counter = 0; counter < transactionItem.size(); counter++){
      //prints item name - price
      /*System.out.format("%d %s x %d  --- $ %.2f\n", transactionItem.get(counter).getItemID(),transactionItem.get(counter).getItemName(),
                        transactionItem.get(counter).getAmount(), 
                        transactionItem.get(counter).getPrice()*transactionItem.get(counter).getAmount());*/
    
    //prints running total
   // System.out.format("Total: %.2f\n", totalPrice);
    return totalPrice;
  }
  
  
  
  public boolean coupon(String couponNo)
  {
      String line = null;
      int numLine = 0;
      String[] coupons=new String[1000];
      try {
        FileReader fileR = new FileReader(couponNumber);
        BufferedReader textReader = new BufferedReader(fileR);
        //reads the entire database
        while ((line = textReader.readLine()) != null)
        {
          coupons[numLine]=line;
          numLine++;
        }        
        
        textReader.close();
      }
      
      //catches exceptions
      catch(FileNotFoundException ex) {
        System.out.println(
                           "Unable to open file 'couponNumber'"); 
      }
      catch(IOException ex) {
        System.out.println(
                           "Error reading file 'couponNumber'");  
      }
      
      //check for coupon
      
      boolean valid=false;
      for(int i=0;i<coupons.length;i++){
        if(couponNo.equals(coupons[i]))
        {
          valid=true; 
          break;
        }
      }
      if (valid)
    	  totalPrice *=discount;
      
      return valid;
  }
  
  
  /*protected static int checkInt(){
    
    Scanner scan=new Scanner(System.in);
    while(!scan.hasNextInt()){
      System.out.println("The input is not valid. Please try again."); 
      
      scan.next();
    }
    
    return Integer.parseInt(scan.next());
  }
  
  protected static double checkDouble(){
    
    Scanner scan=new Scanner(System.in);
    while(!scan.hasNextDouble()){
      System.out.println("The input is not valid. Please try again."); 
      
      scan.next();
    }
    
    return Double.parseDouble(scan.next());
  }
  
  public double taxCalculator ()
  {
    System.out.println("Please select your state: 1. PA 2. NJ 3. NY");
    int state = checkInt();
    while(state<1 ||state> 3){
      
      System.out.println("State is invalid. Please enter again");
      state=checkInt();
      
    }
    if (state == 1 ){
      tax = 1.06;
    }
    else if (state == 2)
    {
      tax = 1.07;
    }
    else if (state == 3)
    {
      tax = 1.04;
    }             
    return tax;
  }*/
  
  public void createTemp(int id, int amount){
    try{
      FileWriter fw = new FileWriter(tempFile,true);
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write(id +" "+ amount);
      bw.write(System.getProperty( "line.separator" ));
      bw.close();
    }
    
    catch (IOException e)
    {
      System.err.println("Error: " + e.getMessage());
    }    
  }
  

  
  public boolean removeItems(int itemID)
  {
	  boolean inTheList=false;
      int index=-1;
      for (int i=0; i<transactionItem.size();i++){
        if (itemID==transactionItem.get(i).getItemID()){
          index=i;
          inTheList=true;                
        }
      }
      if (inTheList==true)
      {
        totalPrice -= transactionItem.get(index).getPrice()*transactionItem.get(index).getAmount();
        deleteTempItem(itemID);
        transactionItem.remove(transactionItem.get(index));
        if (transactionItem.size()==0){
          File file=new File (tempFile);
          file.delete();
        }
        return true;
      }
      return false;
  }
  
  public double getTotal() {return totalPrice;}
  
  public void detectSystem(){
    if (System.getProperty("os.name").startsWith("W")||System.getProperty("os.name").startsWith("w")){
      //unixOS = false; //these lines are commented out for running on netbeans, which uses a linux protocol despite OS
      //couponNumber= "..\\Database\\couponNumber.txt"; 
      //tempFile="..\\Database\\temp.txt";
    }
  }
  
  public boolean creditCard(String card)
  {
	  int length = card.length();
	  if (length != 16)
		  return false;
	  int index = 0;
	  while (index < length)
	  {
		  if (card.charAt(index)>'9' || card.charAt(index) < '0')
			  return false;
		  index++;
	  }
	  return true;
  }
  
  public Item lastAddedItem() {return transactionItem.get(transactionItem.size() - 1); }
  public List <Item> getCart(){return transactionItem;}
  public int getCartSize(){return transactionItem.size();}
  
  public abstract double endPOS(String textFile);
  public abstract void deleteTempItem(int id);
  public abstract void retrieveTemp(String textFile);
}