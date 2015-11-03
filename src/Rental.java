import java.io.*;
import java.util.*;

public class Rental{
  
  private Long phone;
  Management management = new Management();
  
  public Rental() {
  }
  
  public void startNew(String textFile){
    
    String temp = "../Database/temp.txt";
    if(System.getProperty("os.name").startsWith("W")||System.getProperty("os.name").startsWith("w")){
      temp = "..\\Database\\temp.txt"; 
    }
    try{ 
      File file = new File(temp);
      file.delete();
      // if file doesnt exists, then create it
      if (!file.exists()) {
        file.createNewFile();
      }
      //temp log
      FileWriter fw = new FileWriter(file.getAbsoluteFile());
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write("Rental");
      bw.write(System.getProperty( "line.separator" ));
      
      
      
      System.out.println("Enter customer's phone number");//assumes customer is in the database, we'll need to add an add customer method later
      Scanner s = new Scanner(System.in);
      while (((phone = s.nextLong())>9999999999l)||(phone<1000000000l)){ //checks to make sure phone is a 10 digit integer
        System.out.println("invalid phone num, please try a 10 digit integer");
      }
      s.close();
      if (!management.checkUser(phone)){
        System.out.println("user doesnt exist in userDatabase, create new user? y - yes");
        s = new Scanner(System.in);
        String newUser = s.nextLine();
        s.close();
        
        if(newUser.equals("y")){
          //System.out.println("creating new user..");
          if(management.createUser(phone)){
            System.out.println("New user created, continuing with rental..");
            //this will have the same body as the last else block (succeseful rental scenario) of this method
            //checking last rented item:
            String phoneStr=Long.toString(phone);
            bw.write(phoneStr);//add phone to temp
            bw.write(System.getProperty( "line.separator" ));
            bw.close();//end temp log
            management.getLatestReturnDate(phone);
            PointOfSale point=new POR(phone);
            point.addItems(textFile);
            point.removeItems();
            point.coupon();
            point.endPOS(textFile);
          }
          else{
            System.out.println("Couldn't create new user.."); 
            
          }
          
        }
        else{
          System.out.println("You need an account to rent something.");
        }
        
      }
      else{
        
        //returnDate();
        //checking last rented item:
        management.getLatestReturnDate(phone);
        
        
        PointOfSale point=new POR(phone);
        point.detectSystem();
        point.addItems(textFile);
        point.removeItems();
        point.coupon();
        point.endPOS(textFile);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    
  }
  
  public void continueT(String textFile){
    PointOfSale point=new POR(this.phone);
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