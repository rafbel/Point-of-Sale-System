import java.io.*;
import java.util.*;

public class PointOfSale {
  
	//Polymorphism design pattern applied
	
  //attributes
  private double totalPrice;
  private static float discount = 0.90f;
  
  Inventory inventory = Inventory.getInstance();
  
  private List<Item> databaseItem = new ArrayList<Item>(); //creates a list of all items in the database
  private List<Item> transactionItem = new ArrayList<Item>(); //this list will store all items to be used in this sale
  
  //constructor
  public PointOfSale(){
    
  }
  
  
  public void startNew(String databaseFile) 
  {
     totalPrice = 0;
    
    int itemID; int amount;
    
    if (inventory.accessInventory(databaseFile, databaseItem) == true) //if can access inventory
    {
      Scanner cashierInput = new Scanner(System.in); //determines if there is more items to be added
      do //must register at least one item
      {
    	
        //Cashier enters itemID and amount
        System.out.println("Enter itemID");
        itemID=checkInt();
        
        System.out.println("Enter amount");
        amount =checkInt();
        
        //Calls the enterItem method
        if (enterItem(itemID,amount) == false)
          System.out.println("Item not found. Press e to try again");
        
      } while (cashierInput.next().equals("e")); //press e to add more items
      
      //ask for coupon
      coupon();
      
      //cancel sale
      System.out.println("Do you want to keep the sale?");
      if (cashierInput.next().equals("no")){
        cancelSales();}
      
      
    }
    
    else
    {
      System.out.println("Can't access database.");  }
  }
  
  public void coupon(){
    Scanner cashierInput = new Scanner(System.in);

    String coupon="";
    String couponNo="";
    System.out.println("Do you have a coupon? y-Yes");
    coupon=cashierInput.next();
    if(coupon.equals("y")){
      System.out.println("Enter coupon number");
      couponNo=cashierInput.next();
      
      
      boolean ableToOpen = true;
      String line = null;
      int numLine = 0;
      String[] coupons=new String[1000];
      try {
        FileReader fileR = new FileReader("Database/couponNumber.txt");
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
        ableToOpen = false;
      }
      catch(IOException ex) {
        System.out.println(
                           "Error reading file 'couponNumber'");  
        ableToOpen = false;
      }
      
      //check for coupon
      
      boolean valid=false;
      for(int i=0;i<coupons.length;i++){
        if(couponNo.equals(coupons[i])){
          valid=true; 
        }
      }
      while(valid==false){
        System.out.println("Invalid coupon. Please try again."); 
        couponNo=cashierInput.next();
        for(int i=0;i<coupons.length;i++){
          if(couponNo.equals(coupons[i])){
            valid=true; 
          }
        }
      }
      if(valid){
        totalPrice=totalPrice*discount;
        System.out.format("Total: %.2f\n", totalPrice);
      }
    }
  }
  
  public void cancelSales(){
    databaseItem.clear();
    transactionItem.clear();
    System.out.println("your sale has been cancelled.");
    System.exit(1);
  }
  public boolean enterItem(int itemID, int amount) //might include in a "mother class" in the future
  {
    boolean foundItem = false;
    
    for (int counter = 0; counter < databaseItem.size() && foundItem == false; counter++)
    {
      if (databaseItem.get(counter).getItemID() == itemID) //checks if item is found on the database
      {
        transactionItem.add(new Item(itemID,databaseItem.get(counter).getItemName(),databaseItem.get(counter).getPrice(),amount));
        foundItem = true;
      }
    }
    
    if (foundItem == true)
      updateTotal();
    
    return foundItem;
  }
  
  private void updateTotal() 
  {
    
    //updates total value to be displayed on the screen
    totalPrice += transactionItem.get(transactionItem.size() - 1).getPrice()
      *transactionItem.get(transactionItem.size() - 1).getAmount();
    
    //shows running total on screen and item info
    for (int counter = 0; counter < transactionItem.size(); counter++)
      //prints item name - price
      System.out.format("%s x %d  --- $ %.2f\n", transactionItem.get(counter).getItemName(),
                        transactionItem.get(counter).getAmount(), 
                        transactionItem.get(counter).getPrice()*transactionItem.get(counter).getAmount());
    
    //prints running total
    System.out.format("Total: %.2f\n", totalPrice);
    
  }
  
  public double endPOS(double tax, String databaseFile, Boolean takeFromInventory)
  {
    
	if (takeFromInventory) {
	    totalPrice = totalPrice*tax; //calculates price with tax
	    //prints total with taxes
	    System.out.format("Total with taxes: %.2f\n", totalPrice);
	    inventory.updateInventory(databaseFile, transactionItem, databaseItem,takeFromInventory);
	}
	else
	{
		System.out.format("Total: %.2f\n",totalPrice);
		inventory.updateInventory(databaseFile, transactionItem, databaseItem,takeFromInventory);
	}
	
	return totalPrice;
    
  }
  
  //Checks if the value inserted is an integer
  private static int checkInt(){
	  
    Scanner scan=new Scanner(System.in);
    while(!scan.hasNextInt()){
      System.out.println("The input is not valid. Please try again."); 
      scan.next();
    }
    
    return Integer.parseInt(scan.nextLine());
  }
  
  public void continueTrans(String databaseFile){
    boolean ableToOpen=true;
    try{
      FileReader fileR = new FileReader("temp.txt");
      BufferedReader textReader = new BufferedReader(fileR);
      String line=null;
      int numLine=0;
      List<Integer> itemNo=new ArrayList<Integer>();
      List<Integer> itemAmount=new ArrayList<Integer>();
      String[] lineSort;
      line=textReader.readLine();
      while ((line = textReader.readLine()) != null)
      {
        lineSort = line.split(" ");
        int i = Integer.parseInt(lineSort[0]);
        int j = Integer.parseInt(lineSort[1]);
        itemNo.add(i);
        itemAmount.add(j);
        numLine++;
      }
      for(int k=0;k<itemNo.size();k++){
        //enterItem(itemNo.get(k),itemAmount.get(k)); 
        if (enterItem(itemNo.get(k),itemAmount.get(k)) == false)
          System.out.println("Item not found.");
      }
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
    
    int itemID; int amount;
    
    if (inventory.accessInventory(databaseFile, databaseItem) == true) //if can access inventory
    {
      Scanner cashierInput = new Scanner(System.in);
    do //must register at least one item
      {
        //Cashier enters itemID and amount
        System.out.println("Enter itemID");
        itemID=checkInt();
        
        System.out.println("Enter amount");
        amount =checkInt();
        
        //Calls the enterItem method
        if (enterItem(itemID,amount) == false)
          System.out.println("Item not found. Press e to try again");
        
      } while (cashierInput.next().equals("e")); //press e to add more items
      
      //ask for coupon
      coupon();
      
      //cancel sale
      System.out.println("Do you want to keep the sale?");
      if (cashierInput.next().equals("no")){
        cancelSales();}
      
    }
     else
    {
      System.out.println("Can't access database.");  }
  } 
}

