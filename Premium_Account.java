package banking;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;




public class Premium_Account extends Standard_Account {
	//Scanner input =new Scanner(System.in);
	private static final String QUERY = "select AccountNumber, Date,Type_Withdraw_Deosit,Withdraw_Deposit_Amount from basic where AccountNumber =? And Date=?";
	
	Double totalBalance;
	 public Double getTotalBalance(Double totalBalance) {
			return totalBalance;
		}


		public void setTotalBalance(Double totalBalance) {
			this.totalBalance = totalBalance;
		}

//Method For Balance
public void balance() {
	System.out.println("Your Premium Account Balance Is :"+totalBalance);

	System.out.println("Enter Your Account Number :");
	int AccountNumber = input.nextInt();
	LocalDate date = LocalDate.now(); 
	String Date = date.toString();
	try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/bankmangement?", "root", "7397");

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
            preparedStatement.setInt(1,AccountNumber);
            preparedStatement.setString(2,Date);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String type = rs.getString("Type_Withdraw_Deosit");
                double amount = rs.getDouble("Withdraw_Deposit_Amount");
           System.out.println(type);
                System.out.println(amount);
                if(type=="Deposit") {
           	  double Balance=amount+2000000.00;
            	  System.out.println("Your Balance is : "+Balance);
            	  
              
                 }
              else if(type=="Withdraw") {
            	  double Balance=2000000.00-amount;
            	  System.out.println("Your Balance is : "+Balance);
            }  
              
              else {
            	  System.out.println("No History Previous Balance"+totalBalance);
              }
              
            } } catch (SQLException e) {
            printtheSQLException(e);
        }
}

//Method For Withdraw
public void withdraw() throws Exception
{
	System.out.println("You Have Choosen To Withdraw Amount : Enter the Amount");

	Integer withdrawAmount=input.nextInt();
	if(withdrawAmount<=100000) {
		System.out.println("You Can Withdraw The Entered Amount "+withdrawAmount);
	Double	afterWithdrawal=totalBalance-withdrawAmount;
		System.out.println("Do You Wanna See Your Balance Amount After Withdrawal - Enter 1 For See Balance ");
		Integer option =input.nextInt();
		
		if (option==1) {
			System.out.println("Calculating Your Account Balance.........Please Wait");
			Thread.sleep(1000);
			System.out.println("Your Premium Account Balance After The Withdrawal Amount"+" "+withdrawAmount+" "+"is"+afterWithdrawal);
		
			LocalDate nowDate=LocalDate.now();
			System.out.println("DATE : "+nowDate);
			LocalTime nowTime=LocalTime.now();
			System.out.println("TIME : "+nowTime);
			System.out.println("Your Withdrawal Summary For Today IS :"+nowDate);
			System.out.println("Your Total Balance Before Withdraw Is :"+totalBalance);
			System.out.println("Your Withdraw Amount :"+withdrawAmount);
			System.out.println("Your Total Balance After Withdraw Is :"+afterWithdrawal);
//			ArrayList<Double> storage =new ArrayList<>();
//			storage.add(totalBalance);
//			storage.add( (double) withdrawAmount);
//			storage.add(afterWithdrawal);
//			System.out.println("Your Withdrawal Summary For Today IS with Total Balance ,Withdraw Amount ,After WithdrawAmount:"+storage);	
//			
		}
		else if(option>=2){
			try {
			throw new Exception("ThankYou For Using Our Bank");
			}
			catch(Exception e) {
				System.out.println("Have A Nice Day!!!!!");
			}
			
			}
		
	}
	else {
		
		System.out.println("Entered Amount"+" "+ withdrawAmount+" "+" is Greater than Limit set for Premium account 1,00,000"+"\nYou Cannot Withdraw today Come again tommorow");
		System.out.println("Do You Wish To Pay Fine And Withdraw");
		Double afterWithdrawal=totalBalance-withdrawAmount;
		Integer option =input.nextInt();
		if(option==1) {
			
			System.out.println("Your Account Balance Is "+afterWithdrawal);
			LocalDate nowDate=LocalDate.now();
			System.out.println("DATE : "+nowDate);
			LocalTime nowTime=LocalTime.now();
			System.out.println("TIME : "+nowTime);
			System.out.println("Your Withdrawal Summary For Today IS :"+nowDate);
			System.out.println("Your Total Balance Before Withdraw Is :"+totalBalance);
			System.out.println("Your Withdraw Amount :"+withdrawAmount);
			System.out.println("Your Total Balance After Withdraw Is :"+afterWithdrawal);
		
		}
	
	}
}

//Method For Deposit
 public void deposit() throws Exception {
	 System.out.println("You Have Selected to deposit-Enter The Deposit Amount ");
	 Integer depositAmount=input.nextInt();
	Double afterDeposit=totalBalance+depositAmount;
	System.out.println("Do You Wanna See Your Balance Amount After Deposit");
	Integer option =input.nextInt();
	if (option==1) {
		System.out.println("CalCulating Your Account Balance.........Please Wait");
		Thread.sleep(1000);
		System.out.println("Your Premium Account Balance After The Deposit Amount"+" "+depositAmount+" "+"is"+" "+afterDeposit);
//		ArrayList<Double> storage =new ArrayList<>();
//		storage.add(totalBalance);
//		storage.add((double) depositAmount);
//		storage.add(afterDeposit);
//		System.out.println("Your Deposited Summary For Today IS with Total Balance , Deposited Amount ,After DepositAmount:"+storage);
//		
		LocalDate nowDate=LocalDate.now();
		System.out.println("DATE : "+nowDate);
		LocalTime nowTime=LocalTime.now();
		System.out.println("TIME : "+nowTime);
		System.out.println("Your Deposited Summary For Today IS :"+nowDate);
		System.out.println("Your Total Balance Before Deposit Is :"+totalBalance);
		System.out.println("Your Deposited Amount :"+depositAmount);
		System.out.println("Your Total Balance After Deposit Is :"+afterDeposit);
	}
	else {
	
		try {
			throw new Exception("Have A Good Day!!!!");
			}
			finally {
				System.out.println("Thank You For Using Our Bank");
			}
			
			}
	
	}

	void premium_account() {
		System.out.println("You Have Selected To Close Your PreMium Account ");
		System.out.println("But We Can't Close PreMiuim Account");
	}
//Method For Selecting Option
void show() throws Exception {
	try {
	System.out.println("The services available free in the 'Premium Savings Bank  Account’ will include deposit , withdrawal of cash and Balance Checking;");
	System.out.println("Do You Wanna Check Balance Or Withdraw or Deposit or Close Account");
	Integer option =input.nextInt();
	if(option==1) {
		balance();
	}
	else if(option==2) {
		withdraw();
	}
	else if(option==3) {
		deposit();
		
	}
	else if(option==4) {
		premium_account();
	}
	else if(option>=5) {
		try {
			throw new Exception("Entered Method Is Not Available");
			}
			finally {
				System.out.println("ThankYou For Using Our bank");
			}
			}
	}
	catch(Exception e) {
		System.out.println("Please Enter The Correct Method");
	}
	
	}
}