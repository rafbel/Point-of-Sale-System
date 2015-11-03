
public class Employee {
 
 //attributes
 private String username;
 private String name;
 private String position;
 private String password; //amount of items on inventory
 
 //methods
 public Employee(String username,String name, String position,String password)
 {
  this.username = username; this.name = name; this.position = position; this.password = password;
 }
 
 String getUsername() {return username;}
 String getName() {return name;}
 String getPosition() {return position;}
 String getPassword() {return password;}      
}
