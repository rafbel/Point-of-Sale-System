import java.util.*;
import java.io.*;
public class EmployeeManagement{
  String temp ="Database/newEmployeeDatabase.txt";
  public boolean unixOS = true; 
  public static String employeeDatabase = "Database/employeeDatabase.txt";
  public List<Employee> employees = new ArrayList<Employee>();
  public EmployeeManagement(){}
  
  public List<Employee> getEmployeeList()
  {
	  readFile();
	  return employees;
  }
  
  
  
  public void add(String name,String password, boolean employee)
  {
	  readFile();
	  String toWrite = "";
	  int username= (Integer.parseInt(employees.get(employees.size()-1).getUsername())+1);
	  try{
	      FileWriter fw = new FileWriter(employeeDatabase,true);
	      BufferedWriter bw = new BufferedWriter(fw);
	      if (employee)
	    	  toWrite = Integer.toString(username) +" "+ "Cashier" +" " + name +" "+password;
	      else
	    	  toWrite = Integer.toString(username) +" "+ "Admin" +" " + name +" "+password;
	      //bw.write(System.getProperty( "line.separator" ));
	      bw.write(toWrite);
	      bw.write(System.getProperty( "line.separator" ));
	      bw.close();
	      
	    } catch (FileNotFoundException e) {
	      System.out.println("Unable to open file Log File for logIn"); 
	    }
	    catch (IOException e) {
	      e.printStackTrace();
	    }
  }
  
  public boolean delete(String username)
  {
	  readFile();
	  boolean find = false;
	  int index = -1;
	  
	  for(int i=0;i<employees.size();i++)
	  {
		  if(username.equals((employees.get(i)).getUsername()))
		  {
			  find=true;
			  index=i;
			  break;
		  }
	  }
	  if (!find)
		  return find;
	  
	  else
	  {
		  employees.remove(index);
		  
		  boolean ableToOpen=true;
		    try{
		      File tempF= new File (temp);
		      BufferedWriter writer = new BufferedWriter(new FileWriter(tempF));
		      for (int i=0; i<employees.size();i++){
		        writer.write(employees.get(i).getUsername()+" "+employees.get(i).getPosition()+" "+employees.get(i).getName()+" "+employees.get(i).getPassword());
		        writer.write(System.getProperty( "line.separator" ));
		      }
		      writer.close(); 
		      File file = new File(employeeDatabase);
		      file.delete();
		      tempF.renameTo(new File(employeeDatabase));
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
		    find = ableToOpen;
	  }
	  return find;
	  
  }
  
 
  
  public int update(String username, String password, String position, String name)
  {
	  readFile();
	  int userFound = -1; //user not found
	  int index = -1;
	  for(int i=0;i<employees.size();i++){
	        if(username.equals((employees.get(i).getUsername())))
	        {
	          userFound = 0; //user found
	          index=i;
	          break;
	        }
	  }
	  
	  if (userFound == 0)
	  {
		  if (!(position.equals("Admin")||position.equals("Cashier") || position.equals("")))
			  return userFound = -2;
		  
		  //updates information
		  if (!password.equals(""))
			  employees.get(index).setPassword(password);
		  
		  if (!position.equals(""))
			  employees.get(index).setPosition(position);
		  
	      if (!name.equals(""))
	    	  employees.get(index).setName(name);
	      
	      
	      try{
	        File tempF= new File (temp);
	        FileReader fileR = new FileReader(employeeDatabase);
	        BufferedReader reader = new BufferedReader(fileR);
	        BufferedWriter writer = new BufferedWriter(new FileWriter(tempF));
	        for (int i=0; i<employees.size();i++){
	          writer.write(employees.get(i).getUsername()+" "+employees.get(i).getPosition()+" "+employees.get(i).getName()+" "+employees.get(i).getPassword());
	          writer.write(System.getProperty( "line.separator" ));
	        }
	        fileR.close();
	        writer.close(); 
	        reader.close(); 
	        File file = new File(employeeDatabase);
	        file.delete();
	        tempF.renameTo(new File(employeeDatabase));
	      }
	      catch(FileNotFoundException ex) {
	        System.out.println(
	                           "Unable to open file 'temp'"); 
	      }
	      catch(IOException ex) {
	        System.out.println(
	                           "Error reading file 'temp'");  
	      }
		  
	  }
	  return userFound;
  }
  
  
  
  
  
  
  private void readFile(){
    if (System.getProperty("os.name").startsWith("W")||System.getProperty("os.name").startsWith("w")){
      //unixOS = false; 
      //employeeDatabase = "..\\Database\\employeeDatabase.txt";
    }
    
    String line = null;
    String[] lineSort;
    
    
    //Checks database file for the item  
    try {
  
      FileReader fileR = new FileReader(employeeDatabase);
      BufferedReader textReader = new BufferedReader(fileR);
      //reads the entire database
      employees.clear();
      while ((line = textReader.readLine()) != null)
      {
        lineSort = line.split(" "); //separates words    
        String name=lineSort[2]+" "+lineSort[3];
        employees.add(new Employee(lineSort[0],name,lineSort[1],lineSort[4]));
      }
      textReader.close();
      
    }
    
    //catches exceptions
    catch(FileNotFoundException ex) {
      System.out.println(
                         "Unable to open file '" + 
                         employeeDatabase + "'"); 

    }
    catch(IOException ex) {
      System.out.println(
                         "Error reading file '" 
                           + employeeDatabase + "'");  

    }
  }
}