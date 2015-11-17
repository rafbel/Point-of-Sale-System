import java.io.*;
import java.util.*;

abstract class PointOfSale {
  //attributes
  public double totalPrice=0;
  private static float discount = 0.90f;
  public boolean unixOS = true; 
  public double tax=0;
  
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
  
  public void addItems(String databaseFile){
    detectSystem();
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
          System.out.println("Item not found. Press 'e' to try again");
        else{
          //add items and amount into temp
          createTemp(itemID,amount);
          updateTotal();
          System.out.println("Press 'e' to insert another item. Press anything else to close cart.");
        }
      } while (cashierInput.next().equals("e")); //press e to add more items
    }
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
  
  public void updateTotal() 
  {
    //updates total value to be displayed on the screen
    totalPrice += transactionItem.get(transactionItem.size() - 1).getPrice()
      *transactionItem.get(transactionItem.size() - 1).getAmount();
    
    //shows running total on screen and item info
    for (int counter = 0; counter < transactionItem.size(); counter++){
      //prints item name - price
      System.out.format("%d %s x %d  --- $ %.2f\n", transactionItem.get(counter).getItemID(),transactionItem.get(counter).getItemName(),
                        transactionItem.get(counter).getAmount(), 
                        transactionItem.get(counter).getPrice()*transactionItem.get(counter).getAmount());
    }
    
    //prints running total
    System.out.format("Total: %.2f\n", totalPrice);
    
  }
  
  public boolean payment(){
    boolean r=true;
    System.out.println("The total price with tax is "+totalPrice);
    System.out.println("What payment do you want to choose? 1-Cash 2-Credit Card");
    Scanner scan=new Scanner(System.in);
    String pay=scan.next();
    while(!pay.equals("1")&&!pay.equals("2")){
      System.out.println("Wrong number. Please enter again");
      pay=scan.next();
    }
    if(pay.equals("1")){
      r=cash();
    }
    else if(pay.equals("2")){
      credit();
    }
    return r;
  }
  
  public boolean cash(){
    Scanner scan=new Scanner(System.in);
    System.out.println("Please enter the number customer actual paid.");
    double totalPay=checkDouble();
    double remain=totalPrice-totalPay;
    if(remain<0){
      System.out.println("You don't have enough money. The transaction is cancelled.");
      return false;
    }
    else{
      System.out.println("The change is "+remain); 
      return true;
    }
  }
  
  public void credit(){
    System.out.println("Please enter the credit card number.");
    String num=creditCard();
  }
  
  public void coupon(){
    detectSystem();
    if (transactionItem.size()>0){
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
  }
  
  private static int checkInt(){
    
    Scanner scan=new Scanner(System.in);
    while(!scan.hasNextInt()){
      System.out.println("The input is not valid. Please try again."); 
      
      scan.next();
    }
    
    return Integer.parseInt(scan.next());
  }
  
  private static double checkDouble(){
    
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
  }
  
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
  
  public void removeItems(){
    detectSystem();
    Scanner cashierInput=new Scanner(System.in);
    System.out.println("Do you want to remove any item? y-yes");
    if (cashierInput.next().equals("y")){
      //cancel transaction
      System.out.println("Enter c to cancel entire sale, enter anything else to delete one item.");
      String cancel=cashierInput.next();
      if(cancel.equals("c")){
        transactionItem.clear();
        System.out.println("The cart is empty. Thank you");
        File file=new File (tempFile);
        file.delete();
      }
      else{
        
        boolean e =false;
        do{
          System.out.println("Enter the item number you would like to remove");
          int remove=checkInt();
          boolean inTheList=false;
          int index=-1;
          for (int i=0; i<transactionItem.size();i++){
            if (remove==transactionItem.get(i).getItemID()){
              index=i;
              inTheList=true;                
            }
          }
          if (inTheList==true){
            deleteTempItem(remove);
            transactionItem.remove(transactionItem.get(index));
            if (transactionItem.size()==0){
              System.out.println("The cart is empty. Thank you");
              File file=new File (tempFile);
              file.delete();
              e=false;
            }
            else if (transactionItem.size()>0){
              updateTotal();
              System.out.println("Press 'e' to remove another item. Press anything else to close cart.");
              e=cashierInput.next().equals("e");
            }
          }
          else
            System.out.println("The item is not in your cart.Press 'e' to try again");
        }while(e&&transactionItem.size()>0);
      }
    }
  }
  
  public void detectSystem(){
    if (System.getProperty("os.name").startsWith("W")||System.getProperty("os.name").startsWith("w")){
      //unixOS = false; //these lines are commented out for running on netbeans, which uses a linux protocol despite OS
      //couponNumber= "..\\Database\\couponNumber.txt"; 
      //tempFile="..\\Database\\temp.txt";
    }
  }
  
  public static String creditCard(){
    Scanner scan=new Scanner(System.in);
    String a=scan.next();
    int l=a.length();
    int i=0;
    while(l!=16){
      System.out.println("Invalid credit card number. Please enter again.");
      a=scan.next();
      l=a.length();
    }
    while(i<l){
      if(a.charAt(i)>'9'||a.charAt(i)<'0'){
        System.out.println("Invalid credit card number. Please enter again.");
        i=0;
        a=scan.next();
        l=a.length();
      }
      else{
        i++;
      }
    }
    return a;
  }
  
  
  public abstract void endPOS(String textFile);
  public abstract void deleteTempItem(int id);
  public abstract void retrieveTemp(String textFile);
}