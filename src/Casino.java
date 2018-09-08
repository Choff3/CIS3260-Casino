public class Casino {//casino class
	
	private double balance;
	private String name;
	private static int nextID = 1;
	private static int casinoID;
	//declare variables
	
	Casino(){//no arg default constructor
		
		casinoID = nextID++;
		balance = 50000;
		name = "default casino";
		//set each variable to the default value
	
	}
	
	Casino(String x, double y){//2 arg constructor for custom values
		
		casinoID = nextID++;
		balance = y;
		name = x;
		//set each variable to the default value
	
	}
	
	public void setBalance(double x) {
		balance = x;
		if (balance<0) {//if statement to prevent balance from going below 0
			balance = 0;
		}
	}//set balance method
	
	public void setName(String x) {
		name = x;
		balance = balance-15000;//charge the casino $15,000 for the name change
		System.out.println("The casino has been charged $15,000 for the name change.");
	}//set name method
	
	public double getBalance() {
		return balance;
	}//get balance method

	public String getName() {
		return name;
	}//get name method
	
	static void details(Casino x){//details method for printing casino info
		System.out.println("Casino Name: "+x.name);
		System.out.println("Casino Balance: $"+x.balance);
	}
}