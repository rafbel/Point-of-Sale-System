SG Technologies POS System
Alpha Release 
CSE216 - Software Engineering
Dec 9, 2015

Overview:

This folder contains the final version of our Software Engineering project, a fully functioning and robust Point of Sale system.
Project documentation and class submissions are in the Documentation directory

Databases: CouponNumber, employeeDatabase,employee log file, itemDatabase,rentalDatabase,saleinvoiceRecord,userDatabase

Source code:Employee.java, EmployeeManagement.java,HandleReturns.java,Inventory.java,Management.java, POH.java,PointOfSales.java,PointOfSalesTest.java, POR.java, POS.java,POSSystem.java,Register.java,ReturnItem.java,Sale.java

Tests: EmployeeManagementTest.java,EmployeeTest.java,HandReturn.java,POSSystemTest.java,POSTest.java

Design Patterns: Singleton, Abstract Factory 

————————————————————————————————————————————————

Source Codes:
Employee.java - this class gets employee’s username, name, position, and password to the system, and set it in use if information are correct. 

EmployeeManagement.java - this class asks the employees to enter their authorization and determine their position. It also allows the user to to update the employee database. 

HandleReturns.java - this class gets user’s information, (creates a new one if doesn’t exists),it will remove the item on the rented database file if the item is find. 

Inventory.java - this class access inventory database and make updates accordingly. It uses singleton pattern which restricts instantiation of the class to one object.

Item.java - this class contains get methods for the items on the database. 

Management.java - this class uses customer’s phone number to check if they exists in the database. If data can be found, returns the latest return date and check if they have outstanding returns. It also adds, updates rental information to the database.

POR.java, POS.java, POH.java, PointOfSale.java - Classes POR, POS, and POH extend abstract class PointOfSale. PointOfSales is the abstract class that gets factories for POR, POS, and POH. PointOfSale allows users to add item, enter item, remove unwanted items and calculates the total with tax. It also checks if the user has a coupon, and make reductions on the total accordingly. 

POSSystem.java - this is the interface for our project. It has a Welcome and logIn function that checks the users’ status (cashier or admin) For cashier, it allows cashier to choose from sale, rental and return functions. For admins, it takes the admin to employee management and allow admins to add, delete, update the employee information.

Rental.java - this class gets into the rental database, find the user in the database, or create an account if doesn’t exists. Then it allow users to add, remove rental items. 

ReturnItem.java - finds the itemID in the DB and mark the return date. 
 
Sale.java - this class gets into the rental database, find the user in the database, or create an account if doesn’t exists. Then it allow users to add, remove rental items. 


