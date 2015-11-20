import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;

public class POSSystem{
  public boolean unixOS = true; 
  public static String employeeDatabase = "Database/employeeDatabase.txt";
  public static String rentalDatabaseFile = "Database/rentalDatabase.txt"; 
  public static String itemDatabaseFile = "Database/itemDatabase.txt"; 
  public List<Employee> employees = new ArrayList<Employee>();
  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
  Calendar cal=null;
  private int index=-1;
  
  private void readFile(){
    if (System.getProperty("os.name").startsWith("W")||System.getProperty("os.name").startsWith("w")){
      //unixOS = false; //commented out to support netbeans 
      //employeeDatabase = "..\\Database\\employeeDatabase.txt";
      //rentalDatabaseFile = "..\\Database\\rentalDatabase.txt"; 
      //itemDatabaseFile = "..\\Database\\itemDatabase.txt";
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
  
  private void logInToFile(String username,String name,String position,Calendar cal){
    try{
      String temp = "Database/employeeLogfile.txt";
      if(System.getProperty("os.name").startsWith("W")||System.getProperty("os.name").startsWith("w")){
        //temp = "..\\Database\\employeeLogfile.txt";  //commented out to support netbeans
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
  
  public boolean checkTemp()
  {
	  String temp = "Database/temp.txt";
	  File f=new File(temp);
	    if(f.exists() && !f.isDirectory())
	    	return true;
	    return false;
  }
  
  public String continueFromTemp(long phone)
  {
	  		String temp = "Database/temp.txt";
	  		File f=new File(temp);

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
	              //System.out.println("Sale");
	              POS sale=new POS();
	              sale.retrieveTemp(itemDatabaseFile); 
	              return "Sale";
	            }
	            else if(type.equals("Rental")){
	              //System.out.println("Rental");
	              POR rental=new POR(phone);
	              rental.retrieveTemp(rentalDatabaseFile); 
	              return "Rental";
	            }
	            
	            else if(type.equals("Return")){
	                //System.out.println("Return");
	                POH returns = new POH(phone);
	                returns.retrieveTemp(rentalDatabaseFile);
	  	          	textReader.close();
	  	          	return "Return";
	              }
	            
	          }
	        }
	        catch(FileNotFoundException ex) {
	          //System.out.println(
	               //              "Unable to open file 'temp'"); 
	        }
	        catch(IOException ex) {
	          //System.out.println(
	            //                 "Error reading file 'temp'");  
	          //ableToOpen = false;
	        }
	        return "";
  }
  
  private void logOutToFile(String username,String name,String position,Calendar cal){
    try{
      String temp = "Database/employeeLogfile.txt";
      if(System.getProperty("os.name").startsWith("W")||System.getProperty("os.name").startsWith("w")){
        //temp = "..\\Database\\employeeLogfile.txt"; 
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
  
  
  public int logIn(String userAuth, String passAuth){
     readFile();
     String username=userAuth;
     boolean find=false;
     for(int i=0;i<employees.size();i++){
       if(username.equals((employees.get(i)).getUsername())){
          find=true;
          index=i;
          break;
         }
       }
     if (find == true)
     {
      String password=passAuth;
      if(!password.equals((employees.get(index)).getPassword())){
        return 0; //didnt find employee password
      }
      
      else if(((employees.get(index)).getPosition()).equals("Cashier")){
        return 1;  //returns cashier status
      }
      else if(((employees.get(index)).getPosition()).equals("Admin")){
        return 2; //returns admin status
      }  
     }
     
     return 0;//didnt find employee username
  }
  
  
  
  
}