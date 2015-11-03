import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;

public class POSSystem{
  public boolean unixOS = true; 
  public static String employeeDatabase = "../Database/employeeDatabase.txt";
  public static String rentalDatabaseFile = "../Database/rentalDatabase.txt"; 
  public static String itemDatabaseFile = "../Database/itemDatabase.txt"; 
  public List<Employee> employees = new ArrayList<Employee>();
  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
  Calendar cal=null;
  private int index=-1;
  
  private void readFile(){
    if (System.getProperty("os.name").startsWith("W")||System.getProperty("os.name").startsWith("w")){
      unixOS = false; 
      employeeDatabase = "..\\Database\\employeeDatabase.txt";
      rentalDatabaseFile = "..\\Database\\rentalDatabase.txt"; 
      itemDatabaseFile = "..\\Database\\itemDatabase.txt";
    }
    boolean ableToOpen = true;
    
    String line = null;
    String[] lineSort;
    int numLine = 0;
    
    //Checks database file for the item  
    try {
      FileReader fileR = new FileReader(employeeDatabase);
      BufferedReader textReader = new BufferedReader(fileR);
      //reads the entire database
      while ((line = textReader.readLine()) != null)
      {
        lineSort = line.split(" "); //separates words    
        String name=lineSort[2]+" "+lineSort[3];
        employees.add(new Employee(lineSort[0],name,lineSort[1],lineSort[4]));
        numLine++;
      }
      textReader.close();
      
    }
    
    //catches exceptions
    catch(FileNotFoundException ex) {
      System.out.println(
                         "Unable to open file '" + 
                         employeeDatabase + "'"); 
      ableToOpen = false;
    }
    catch(IOException ex) {
      System.out.println(
                         "Error reading file '" 
                           + employeeDatabase + "'");  
      ableToOpen = false;
    }
  }
  
  public void logIn(){
    readFile();
    Scanner scan=new Scanner(System.in);
    System.out.println("Please enter username");
    String username=scan.next();
    boolean find=false;
    do{
      for(int i=0;i<employees.size();i++){
        if(username.equals((employees.get(i)).getUsername())){
          find=true;
          index=i;
          break;
        }
      }
      if(find==false){
        System.out.println("The username is invalid. Please enter again.");
        username=scan.next();
      }
    }while(find==false);
    
    System.out.println("Please enter password.");
    String password=scan.next();
    while(!password.equals((employees.get(index)).getPassword())){
      System.out.println("Wrong password. Please enter again.");
      password=scan.next();
    }
    
    //enter successful
    cal = Calendar.getInstance();
    System.out.print("Welcome to SG Technologies POS System ");
    System.out.println(dateFormat.format(cal.getTime()));
    logInToFile((employees.get(index)).getUsername(),(employees.get(index)).getName(),(employees.get(index)).getPosition(),cal);
    
    if(((employees.get(index)).getPosition()).equals("Cashier")){
      cashier(); 
    }
    else if(((employees.get(index)).getPosition()).equals("Admin")){
      admin();
    }  
    scan.close();
  }
  
  private void cashier(){
    String choice="p";
    Scanner cashierInput=new Scanner(System.in);
    //retrieve the incomplete transaction
    boolean ableToOpen = true;
    
    String temp = "../Database/temp.txt";
    if(System.getProperty("os.name").startsWith("W")||System.getProperty("os.name").startsWith("w")){
      temp = "..\\Database\\temp.txt"; 
    }
    
    File f=new File(temp);
    if(f.exists() && !f.isDirectory()) { 
      System.out.println(" There is an incomplete transaction. Do you want to retrieve it? y- Yes");
      String retrieve=cashierInput.next();
      if(retrieve.equals("y")){
        try{
          FileReader fileR = new FileReader(temp);
          BufferedReader textReader = new BufferedReader(fileR);
          if(f.length()==0){
            System.out.println("The log file is not valid"); 
            f.delete();
          }
          else{
            String type= textReader.readLine();
            if(type.equals("Sale")){         
              System.out.println("Sale");
              Sale sale=new Sale();
              sale.continueT(itemDatabaseFile); 
            }
            else if(type.equals("Rental")){
              System.out.println("Rental");
              Rental rental=new Rental();
              rental.continueT(rentalDatabaseFile); 
            }
            
            else if(type.equals("Return")){
                System.out.println("Return");
                HandleReturns returns = new HandleReturns();
                //returns.continueT(rentalDatabaseFile); got to make this happen
              }
            
            else{
              System.out.println("The log file is not valid"); 
            }
          }
          textReader.close();
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
    //new transaction
    while (!choice.equals("o"))
    {
      System.out.println("Press s to start new sale");
      System.out.println("Press r to start new rental");
      System.out.println("Press h to start a return");
      System.out.println("Press o to log out");
      
      choice = cashierInput.next();
      
      if (choice.equals("s")){
        //starts new sale
        Sale sale=new Sale();
        sale.startNew(itemDatabaseFile);
      }
      else if (choice.equals("r")){
        Rental rental=new Rental();
        rental.startNew(rentalDatabaseFile);
      }
      else if (choice.equals("h")){
        HandleReturns returns = new HandleReturns();
        returns.newReturn();
      }
      else if(choice.equals("o")){
        logOutToFile((employees.get(index)).getUsername(),(employees.get(index)).getName(),(employees.get(index)).getPosition(),cal);
      }
    }
    cashierInput.close();
  }
  
  private void admin(){
    String choice="p";
    Scanner cashierInput=new Scanner(System.in);
    while (!choice.equals("o"))
    {
      System.out.println("Press a to add employee information");
      System.out.println("Press d to delete employee information");
      System.out.println("Press u to update employee information");
      System.out.println("Press o to log out");
      
      choice = cashierInput.next();
      
      if (choice.equals("a")){
        EmployeeManagement e=new EmployeeManagement();
        e.add();
      }
      else if (choice.equals("d")){
        EmployeeManagement e=new EmployeeManagement();
        e.delete();
      }
      else if (choice.equals("u")){
        EmployeeManagement e=new EmployeeManagement();
        e.update();
      }
      else if(choice.equals("o")){
        logOutToFile((employees.get(index)).getUsername(),(employees.get(index)).getName(),(employees.get(index)).getPosition(),cal);
      }
    }
    cashierInput.close();
  }
  
  private void logInToFile(String username,String name,String position,Calendar cal){
    try{
      String temp = "../Database/employeeLogfile.txt";
      if(System.getProperty("os.name").startsWith("W")||System.getProperty("os.name").startsWith("w")){
        temp = "..\\Database\\employeeLogfile.txt"; 
      }
      FileWriter fw = new FileWriter(temp,true);
      BufferedWriter bw = new BufferedWriter(fw);
      String log=name+" ("+username+" "+position+") logs into POS System. Time: "+dateFormat.format(cal.getTime());
      bw.write(log);
      bw.write(System.getProperty( "line.separator" ));
      bw.close();
      
    } catch (FileNotFoundException e) {
      System.out.println("Unable to open file Log File for logIn"); 
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  private void logOutToFile(String username,String name,String position,Calendar cal){
    try{
      String temp = "../Database/employeeLogfile.txt";
      if(System.getProperty("os.name").startsWith("W")||System.getProperty("os.name").startsWith("w")){
        temp = "..\\Database\\employeeLogfile.txt"; 
      }
      FileWriter fw = new FileWriter(temp,true);
      BufferedWriter bw = new BufferedWriter(fw);
      String log=name+" ("+username+" "+position+") logs out of POS System. Time: "+dateFormat.format(cal.getTime());
      bw.write(log);
      bw.write(System.getProperty( "line.separator" ));
      bw.close();
      
    } catch (FileNotFoundException e) {
      System.out.println("Unable to open file Log File for logout"); 
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}