import java.util.Scanner;

public class SevenEleven {
	
	static double bet;
	
public static void bet(int cas, int cus) {//method for placing bets
		
		Scanner input = new Scanner(System.in);
		System.out.println("How much would you like to bet?");
		bet = input.nextDouble();
		//ask the user how much they want to bet and set input to the bet variable
		
		if (bet > (CasinoMenu.customerList[cus].getBalance())) {
			System.out.println("You don't have enough money in your balance.");
		}//if the customer doesn't have enough money in their balance
		
		if (bet > (CasinoMenu.casinoList[cas].getBalance())) {
			System.out.println("You don't have enough money in your balance.");
		}//if the casino doesn't have enough money in their balance
		
		if (bet<0) {
			System.out.println("You must enter a bet greater than 0.");
		}//if the bet is less than 0
		
		else {
			game(cas, cus);
		}//if the bet is acceptable, call the game method
		
	}

	public static void game(int cas, int cus) {
		
		CasinoMenu.customerList[cus].setBalance(((CasinoMenu.customerList[cus].getBalance())-bet));
		CasinoMenu.casinoList[cas].setBalance(((CasinoMenu.casinoList[cas].getBalance())-bet));
		//subtract the bet from user and casino balance
		
		int userDie1 = 1 + (int) (Math.random()*6);
		int userDie2 = 1 + (int) (Math.random()*6);
		int casinoDie1 = 1 + (int) (Math.random()*6);
		int casinoDie2 = 1 + (int) (Math.random()*6);
		//ints for dice values
		
		boolean user7or11 = false;//boolean for checking if the user has 7 or 11
		if ( ((userDie1+userDie2)==7) || ((userDie1+userDie2)==11) ) {
			user7or11 = true;
		}//if statement check for user 7 or 11
		
		boolean casino7or11 = false;//boolean for checking if the casino has 7 or 11
		if ( ((casinoDie1+casinoDie2)==7) || ((casinoDie1+casinoDie2)==11) ) {
			casino7or11 = true;
		}//if statement check for casino 7 or 11
		
		
		boolean userWin = false;
		boolean casinoWin = false;
		boolean draw = false;
		//initialize win and draw conditions
		
		if ( (userDie1+userDie2) == (casinoDie1+casinoDie2) ) {
			draw = true;
		}//user and casino had the same dice values, therefore draw
		
		else if (user7or11 && !casino7or11 ){
	
			userWin = true;
			
		}//user 7 or 11, therefore user wins
		
		else if (!user7or11 && casino7or11){
			
			casinoWin = true;
			
		}//casino 7 or 11, therefore casino wins
		
		else if ( (userDie1+userDie2) > (casinoDie1+casinoDie2) ) {
			
			userWin = true;
			
		}//user higher, therefore user wins
		
		else if ( (userDie1+userDie2) < (casinoDie1+casinoDie2) ) {
			
			casinoWin = true;
			
		} //casino higher, therefore casino wins
	
		
		System.out.println("You have a "+userDie1+" and a "+userDie2+".");
		System.out.println("The casino has a "+casinoDie1+" and a "+casinoDie2+".");
		if (casinoWin) {//if casino wins
			System.out.println("The casino wins.");
			CasinoMenu.casinoList[cas].setBalance(((CasinoMenu.casinoList[cas].getBalance())+(bet*2)));
		}//display casino win message and pay the casino the pot
		if (userWin) {//if user wins
			System.out.println("You win!");
			CasinoMenu.customerList[cus].setBalance(((CasinoMenu.customerList[cus].getBalance())+(bet*2)));
		}//display user win message and pay the user the pot
		if (draw) {//if draw
			System.out.println("You and the casino tied.");
			CasinoMenu.customerList[cus].setBalance(((CasinoMenu.customerList[cus].getBalance())+bet));
			CasinoMenu.casinoList[cas].setBalance(((CasinoMenu.casinoList[cas].getBalance())+bet));
		}//display draw message and return the bets to the user and casino
	
	}

}