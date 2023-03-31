package banking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;

public class Basic_Account extends Main_banking_Process {
	private static final String QUERY = "select AccountNumber, Date,Type_Withdraw_Deosit,Withdraw_Deposit_Amount from basic where AccountNumber =? And Date=?";
	private static final String DELETE_customers_SQL = "delete from customers where AccountNumber = ?;";

	private Double totalBalance;

	public Double getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(Double totalBalance) {
		this.totalBalance = totalBalance;
	}

//Method For Balance-To View The Balance
	void balance() {
		System.out.println("Your Joint Account Balance Is :" + totalBalance);
		System.out.println("Enter Your Account Number :");
		int AccountNumber = input.nextInt();
		LocalDate date = LocalDate.now();
		String Date = date.toString();
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmangement?", "root",
				"7397");

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
			preparedStatement.setInt(1, AccountNumber);
			preparedStatement.setString(2, Date);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String type = rs.getString("Type_Withdraw_Deosit");
				double amount = rs.getDouble("Withdraw_Deposit_Amount");
				System.out.println(type);
				System.out.println(amount);
				if (type == "Deposit") {
					double Balance = amount + 20000;
					System.out.println("Your Balance is : " + Balance);

				} else if (type == "Withdraw") {
					double Balance = 20000 - amount;
					System.out.println("Your Balance is : " + Balance);
				}

				else {
					System.out.println("No History Previous Balance" + totalBalance);
				}

			}
		} catch (SQLException e) {
			printtheSQLException(e);
		}
		// Step 4: try-with-resource statement will auto close the connection.
	}

//Method For Withdrawal
	void withdraw() throws Exception {
		try {
			System.out.println("Enter Your Account Number");
			System.out.println("You Have Choosen To Withdraw Amount :Enter the Amount");

			Integer withdrawAmount = input.nextInt();
			if (withdrawAmount <= 10000) {
				System.out.println("You Can Withdraw The Entered Amount " + withdrawAmount);
				Double afterWithdrawal = totalBalance - withdrawAmount;
				System.out.println("Do You Wanna See Your Balance Amount After Withdrawal -Enter 1 For See Balance ");
				Integer option = input.nextInt();
				if (option == 1) {
					System.out.println("CalCulating Your Account Balance.........Please Wait");
					Thread.sleep(1000);
					System.out.println("Your Basic Account Balance After The Withdrawal Amount" + " " + withdrawAmount
							+ " " + "is" + afterWithdrawal);
					LocalDate nowDate = LocalDate.now();
					System.out.println("DATE : " + nowDate);
					LocalTime nowTime = LocalTime.now();
					System.out.println("TIME : " + nowTime);
					System.out.println("Your Withdraw Summary For Today IS :" + nowDate);
					System.out.println("Your Total Balance Before Withdraw Is :" + totalBalance);
					System.out.println("Your Withdraw Amount :" + withdrawAmount);
					System.out.println("Your Total Balance After Withdraw Is :" + afterWithdrawal);

				} else if (option >= 2) {
					try {
						throw new Exception("ThankYou For Using Our Bank");
					} catch (Exception e) {
						System.out.println("Have A Nice Day!!!!!!");
					}
				}
//	ArrayList<Double> storage =new ArrayList<>();
//		//Collection storage = new ArrayList();
//	//	storage.add("Total Balance");
//		storage.add(totalBalance);
//		//storage.add("Withdraw Amount");
//		storage.add( (double) withdrawAmount);
//	//	storage.add("After Withdrawal");
//		storage.add(afterWithdrawal);
//		System.out.println(storage);	

			} else {
				System.out
						.println("Entered Amount" + withdrawAmount + " Greater than Limit set for basic account 10,000"
								+ "\nYou Cannot Withdraw today Come again tommorow");
				System.out.println("Do You Wish To Pay Fine And Withdraw");
				Double afterWithdrawal = totalBalance - withdrawAmount;
				Integer option = input.nextInt();
				if (option == 1) {

					System.out.println("Your Account Balance Is " + afterWithdrawal);

					LocalDate nowDate = LocalDate.now();
					System.out.println("DATE : " + nowDate);
					LocalTime nowTime = LocalTime.now();
					System.out.println("TIME : " + nowTime);
					System.out.println("Your Withdrawal Summary For Today IS :" + nowDate);
					System.out.println("Your Total Balance Before Withdraw Is :" + totalBalance);
					System.out.println("Your Withdraw Amount :" + withdrawAmount);
					System.out.println("Your Total Balance After Withdraw Is :" + afterWithdrawal);
				}
//	ArrayList<Double> storage =new ArrayList<>();
//	storage.add(totalBalance);
//	storage.add( (double) withdrawAmount);
//	storage.add(afterWithdrawal);
//	System.out.println("Your Withdrawal Summary For Today IS with Total Balance ,Withdraw Amount ,After WithdrawAmount:"+storage);	
//	
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

// Method For Deposit
	void deposit() throws Exception {
		try {
			System.out.println("You Have Selected to deposit-Enter The Deposit Amount ");
			Integer depositAmount = input.nextInt();
			Double afterDeposit = totalBalance + depositAmount;
			System.out.println("Do You Wanna See Your Balance Amount After Deposit");
			Integer option = input.nextInt();
			if (option == 1) {
				System.out.println("CalCulating Your Account Balance.........Please Wait");
				Thread.sleep(1000);
				System.out.println("Your Basic Account Balance After The Deposit Amount" + " " + depositAmount + " "
						+ "is" + " " + afterDeposit);
			} else {
				try {
					throw new Exception("ThankYou For Using Our Bank");
				} catch (Exception e) {
					System.out.println(e);
				}
			}
			LocalDate nowDate = LocalDate.now();
			System.out.println("DATE : " + nowDate);
			LocalTime nowTime = LocalTime.now();
			System.out.println("TIME : " + nowTime);
			System.out.println("Your Deposited Summary For Today IS :" + nowDate);
			System.out.println("Your Total Balance Before Deposit Is :" + totalBalance);
			System.out.println("Your Deposited Amount :" + depositAmount);
			System.out.println("Your Total Balance After Deposit Is :" + afterDeposit);
//	ArrayList<Double> storage =new ArrayList<>();
//	storage.add(totalBalance);
//	storage.add((double) depositAmount);
//	storage.add(afterDeposit);
//	System.out.println("Your Deposited Summary For Today IS with Total Balance , Deposited Amount ,After DepositAmount:"+storage);
//	
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	void basic_account() {
		System.out.println("You Have Selected To Close The Basic Account ");
		System.out.println("Do You Wanna CLose It?");
		System.out.println("Enter Yes To CLose ");
		String option = input.next();
		switch (option) {
		case "Yes": {
			System.out.println("Enter Your Account Number ");
			int AccountNumber = input.nextInt();
			try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmangement?",
					"root", "7397");

					// Step 2:Create a statement using connection object
					PreparedStatement statement = connection.prepareStatement(DELETE_customers_SQL);) {

				statement.setInt(1, AccountNumber);
				// Step 3: Execute the query or update query
				int result = statement.executeUpdate();

				if (result == 0) {
					System.out.println("Entered Account Number Doen't Exist");

				} else {
					System.out.println("Number of records affected :: " + result);
					System.out.println("Account Has Been CLosed");
				}
			} catch (SQLException e) {

				// print SQL exception information
				printtheSQLException(e);
			}

			break;
		}
		default: {
			System.out.println("Have You Changed Your Mind ,Thats a Good Thought!!!!!");
		}
		}
	}

// Method For Option Selection
	void show() throws Exception {
		try {
			System.out.println(
					"The services available free in the 'Basic Savings Bank  Account’ will include deposit , withdrawal of cash and Balance Checking And Closing The Account;");
			System.out.println("Do You Wanna Check Balance Or Withdraw or Deposit or Close Account");
			int option = input.nextInt();
			if (option == 1) {
				balance();
			} else if (option == 2) {
				withdraw();
			} else if (option == 3) {
				deposit();
			} else if (option == 4) {
				basic_account();
			} else if (option >= 5) {
				try {
					throw new Exception("Entered Method Is Not Available");
				} finally {
					System.out.println("Thank You For Using Our Bank");
				}
			}
		} catch (Exception e) {
			System.out.println("Please Enter The Correct Match Either 1 ,2,3,4");
		}
	}

	public static void printtheSQLException(SQLException ex) {
		for (Throwable e : ex) {
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
