import java.util.Scanner;

public class Slots {
	
	static public int bet;
	static private int spins;
	static int totalPayout;
	//declare variables
	
	public static void bet(int cas, int cus) {//method for placing bets
		
		Scanner input = new Scanner(System.in);
		System.out.println("$5 per spin. How many spins?");
		spins = input.nextInt();
		//ask user how much to bet
		
		bet = spins*5;
	
		if (bet > (CasinoMenu.customerList[cus].getBalance())) {
			System.out.println("You don't have enough money in your balance.");
		}//if the customer doesn't have enough money in their balance
		
		else {
			game(cas, cus, spins);
		}//if the bet is acceptable, call the game method
		
	}

	public static void game(int cas, int cus, int spins) {

		CasinoMenu.customerList[cus].setBalance(((CasinoMenu.customerList[cus].getBalance())-(spins*5)));//subtract the spin fees from the customer account
		
		for(int i = 0; i < spins; i++) {//for loop for number of spins
		
		int random = (int) (Math.random()*101);
		int payout = 0;
		//initialize a random int and the payout int
		System.out.println(random);
		
		if (random <= 70) {
			payout = 1;
		}//70% chance
		if ( (random > 70) && (random <= 90) ){
			payout = 5;
		}//20% chance
		if ( (random > 90) && (random < 100) ){
			payout = 10;
		}//9% chance
		if (random == 100) {
			payout = 100;
		}//1% chance
		//payout is determined by the value of the random int
		
		System.out.println("You won $"+payout+".");
		
		totalPayout = totalPayout + payout;//add the payout for each loop iteration to the total payout
		
		System.out.println("Your total payout is $"+totalPayout+".");
		
		}
		CasinoMenu.customerList[cus].setBalance(((CasinoMenu.customerList[cus].getBalance())+totalPayout));//add the total payout to the customer's balance
		System.out.println("Your payout of $"+totalPayout+" has been added to your balance.");
	}
}
