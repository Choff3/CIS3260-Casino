public class Customer {//customer class
	
	private double balance;
	private String name;
	private static int nextID = 1;
	private static int customerID;
	//declare variables
	
	Customer(){//no arg default constructor
		
		customerID = nextID++;
		balance = 1000;
		name = "default customer";
		//set each variable to the default value
	
	}//constructor
	
	Customer(String x, double y){//2 arg constructor for setting custom values
		
		customerID = nextID++;
		balance = y;
		name = x;
		//set each variable to the default value
	
	}//constructor
	
	public void setBalance(double x) {//method for changing balance
		balance = x;
		if (balance<0) {//prevent balnce from going below 0
			balance = 0;
		}
	}
	
	public void setName(String x) {
		name = x;
	}//set customer name
	
	public double getBalance() {
		return balance;
	}//get balance method

	public String getName() {
		return name;
	}//get name method
	
	static void details(Customer x){//details method for printing customer info
		System.out.println("Customer Name: "+x.name);
		System.out.println("Customer Balance: $"+x.balance);
	}
}