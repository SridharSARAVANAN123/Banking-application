//Program To Find The Balance,Withdraw,Deposit,Closing(for Basic,Standard,Premiuim,Joint Account)
/*In This Program I Have Used Pojo Method(Class Should Be In Public,Class Properties should be in private,
*Getters And Setters Must Be Included,Default Constructors Must Be Used).
*Not to do in pojo (should not extend predefined classes(scanner,array etc.,) ,not implements predefined interfaces ,should not have annotations.*/
//Multi-Level Inheritance ,UserDefined Exception Handling ANd System Defined Exception Handling Are Used.
//HashMap Is Used For Storing the Customer Data 
//Wrapper Class Is Used
//LocalDate and time is used




package  banking;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

import java.util.Scanner;

import java.util.concurrent.ThreadLocalRandom;

public class Main_banking_Process {
	static Scanner input = new Scanner(System.in);
	static Basic_Account basic_account = new Basic_Account();
	static Standard_Account standard_account = new Standard_Account();
	static Premium_Account premium_account = new Premium_Account();
	static Joint_Account.joint_account joint_account1 = new Joint_Account().new joint_account();
	private static final String QUERY = "select AccountNumber,Name,AccountType from customers where AccountNumber =? And Name =?";
	
	private static final String INSERT_customers_SQL = "INSERT INTO customers" +
	        "  (AccountNumber, Name, AccountType, Date) VALUES " +
	        " (?, ?, ?, ?);"
	         ;

	public static void main(String[] args) throws Exception {
basic_account.setTotalBalance(20000.00);
standard_account.setTotalBalance(200000.00);
premium_account.setTotalBalance(2000000.00);

		System.out.println("Welcome To ABC Bank");
		System.out.println(" ABC Bank of India (BOI) is an Indian public sector bank headquartered in Bandra Kurla Complex, Mumbai. \nFounded in 1906, it has been government-owned since nationalisation in 1969. \nBoI is a founder member of SWIFT (Society for Worldwide Inter Bank Financial Telecommunications), which facilitates provision of cost-effective financial processing and communication services.\nAs on 31 December 2022, Bank of India's total business stands at ₹1,037,549 crore (US$130 billion), has 5,108 branches and 5,551 ATMs around the world (including 24 overseas branches)");
		System.out.println("");
		System.out.println("Have you tried our new simplified and intuitive business banking platform? Log in to abcbusiness.abc or sign up to avail business banking services.");
		
		try {
			System.out.println("Are You An Existing Account Holder Or New Account Holder Or Just Visiting");
			
			Integer inputs = input.nextInt();
			if (inputs == 1) {
				existingCustomer();
			}

			else if (inputs == 2) {
				newCustomer();
			}
			else if(inputs==3){
				System.out.println("*The Basic Savings Bank Account or BSBA is a Savings Account that does not have a minimum balance. In contrast, a BSBDA has a maximum account balance that has to be maintained. \nThe BSBDA holder will get an ATM cum Debit Card as a part of the account opening formalities.");
				System.out.println("*The Standard savings Bank Account or SSBA generally allow you to earn interest on your money, although they usually pay lower rates than other savings products. ");
				System.out.println("*Premium savings accounts are specialised savings accounts that offer improved and enhanced banking features and customised services. \nThese types of accounts are best suited for people that have an extreme paucity of time, need premium services or require relaxed and customised norms.");
				System.out.println("*A joint account is a bank account that has been opened by two or more individuals or entities. \nJoint accounts are commonly opened by close relatives or by business partners in an unincorporated business, but it can be used in other circumstances.");
				System.out.println("Do You Wish To Open account in our bank");
				Integer answer =input.nextInt();
				if(answer==1) {
					newCustomer();
				}
				if(answer==2) {
					System.out.println("Thanking You For Visting, Hope We'll Start Our journey one day !!!!!");
				}
			}
			else {
				System.out.println("Entered Option Is Not Available");
			}
		} catch (Exception e) {
			System.out.println("Please Enter The Type Correctly Or You Have Entred Mismatched Type");

		}
	}

	static void existingCustomer() throws Exception {
System.out.println("Hi Customer ,We Are Trying To Provide You Our Best Choices");
		System.out.println("Please Enter Your Account Details For  Searching");
		System.out.println("Enter Your Account Number");
		int AccountNumber = input.nextInt();
		System.out.println("Enter Your Name");
		String Name = input.next();
		System.out.println("Enter Your Account Type");
		String AccountType = input.next();
		try (Connection connection = DriverManager
	            .getConnection("jdbc:mysql://localhost:3306/bankmangement?", "root", "7397");

	            // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
	            preparedStatement.setInt(1,AccountNumber);
	            preparedStatement.setString(2,Name);
	           
	            System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            ResultSet rs = preparedStatement.executeQuery();

	            // Step 4: Process the ResultSet object.
	            while (rs.next()) {
	            	String number = rs.getString("AccountNumber");
	                String name = rs.getString("Name");
	                String type = rs.getString("AccountType");
	          System.out.println("Hi ! : "+Name);
	             System.out.println("Your Account Number Is : "+number); 
	             System.out.println("Your Account Type Is : "+type);
	             switch (type) {
					case "Premium": {

						
						premium_account.show();
	break;
					}
					case "Basic": {
						basic_account.show();
						break;
					}
					case "Standard": {
						standard_account.show();
						break;
					}
					case "Joint": {
						
						joint_account1.show();
						break;
					}

					}  
	            
	            }
	        } catch (SQLException e) {
	            printtheSQLException(e);
	        }
	        // Step 4: try-with-resource statement will auto close the connection.
	}
		
		
		
		
	
	static void newCustomer() throws Exception {
		try {
			LocalDate date = LocalDate.now();
			String Date = date.toString();
		System.out.println("*The Basic Savings Bank Account or BSBA is a Savings Account that does not have a minimum balance. In contrast, a BSBDA has a maximum account balance that has to be maintained. \nThe BSBDA holder will get an ATM cum Debit Card as a part of the account opening formalities.");
		System.out.println("*The Standard savings Bank Account or SSBA generally allow you to earn interest on your money, although they usually pay lower rates than other savings products. ");
		System.out.println("*Premium savings accounts are specialised savings accounts that offer improved and enhanced banking features and customised services. \nThese types of accounts are best suited for people that have an extreme paucity of time, need premium services or require relaxed and customised norms.");
		System.out.println("*A joint account is a bank account that has been opened by two or more individuals or entities. \nJoint accounts are commonly opened by close relatives or by business partners in an unincorporated business, but it can be used in other circumstances.");
		System.out.println("^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v^v");
		System.out.println("Enter Your Details Below Mentioned");
		System.out.println("Choose Your Account Type Basic,Standard,Premium,Joint ");
		System.out.println("Enter The Account Type ");
		String AccountType = input.next();
		System.out.println("Enter Your Name:");
		String Name = input.next();
		System.out.println("Please Check Again the mentioned details");
		System.out.println(" Your Account Type Is :" + AccountType);
		System.out.println(" Your Name  Is :" + Name);
		System.out.println("Please Select Yes If The Mentioned Details Is Correct");
		String answer = input.next();
		int a = ThreadLocalRandom.current().nextInt();
		int AccountNumber = Math.abs(a);
		System.out.println("Your Account Number Is : "+AccountNumber);
		
		switch (answer) {
		case "Yes":{
//			Account_Details.put(b, account_holder_Name);
//			Account_Type.put(account_holder_Name, accountType);
			try (Connection connections = DriverManager
	        .getConnection("jdbc:mysql://localhost:3306/bankmangement?", "root", "7397");
					
	        // Step 2:Create a statement using connection object
	        PreparedStatement preparedStatements = connections.prepareStatement(INSERT_customers_SQL)) {
			
	 preparedStatements.setInt(1, AccountNumber);
   preparedStatements.setString(2,Name);
  preparedStatements.setString(3, AccountType);
  preparedStatements.setString(4, Date);
  preparedStatements.executeUpdate();    	
  connections.close();

}
  catch (SQLException e) {

        // print SQL exception information
    	 printtheSQLException(e);
  }
 finally {
	 
 }	
		
			break;
		}
		default:	
			System.out.println("Please Visit Again  ");
		}
		}
		catch(Exception e) {
			System.out.println("Please Enter The Correct Match ,You Have entered input That Does'nt Match");
			
		}
	}
	public static void printtheSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                System.out.println("Entered Name Is Not In Our List");
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
}
}