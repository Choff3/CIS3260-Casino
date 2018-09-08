import java.util.Scanner;

public class TwentyOne {
	
	static double bet = 0;
	static double pot = 0;
	
	static int cTotal;//value of dealer hand
	static boolean userWin;//boolean for if the user won
	static boolean casinoWin;//boolean for casino win
	static boolean draw;//boolean for a tie
	
	static String[] suit = {"Hearts","Diamonds","Spades","Clubs"}; //initialize array for card suits
	static String[] value = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"}; //initialize array for card face value
	static String[][] deck = new String[4][13]; //initialize 2d array to hold all 52 cards
	static int[] deckValue = {10,2,3,4,5,6,7,8,9,10,10,10,10};//array for card value
	
	public static void bet(int cas, int cus) {//method for placing bets
		
		Scanner input = new Scanner(System.in);
		System.out.println("How much would you like to bet?");
		bet = input.nextDouble();
		//ask the user how much they want to bet and set input to the bet variable
		
		if (bet > (CasinoMenu.customerList[cus].getBalance())) {
			System.out.println("You don't have enough money in your balance.");
		}//if the customer doesn't have enough money in their balance
		
		else if (bet > (CasinoMenu.casinoList[cas].getBalance())) {
			System.out.println("The casino have enough money in its balance.");
		}//if the casino doesn't have enough money in its balance
		
		else if (bet<0) {
			System.out.println("You must enter a bet greater than 0.");
		}//if the bet is less than 0
		
		else {
			game(cas, cus);
		}//if the bet is acceptable, call the game method
		
	}
	
	public static void game(int cas, int cus) {
		
		userWin = false;//boolean for if the user won
		casinoWin = false;//boolean for casino win
		draw = false;//boolean for a tie
		
		int uRandom1 = (int) (Math.random()*13);
		int uRandom2 = (int) (Math.random()*13);
		int cRandom1 = (int) (Math.random()*13);
		int cRandom2 = (int) (Math.random()*13);
		//random ints for drawing cards
		
		int userCardValue1 = deckValue[uRandom1];
		int userCardValue2 = deckValue[uRandom2];
		int casinoCardValue1 = deckValue[cRandom1];
		int casinoCardValue2 = deckValue[cRandom2];
		//assigning drawn cards value
		
		for(int outer = 0; outer < 4; outer++) { //loop to combine the suit and value array into the deck array
			
			for(int inner = 0; inner < 13; inner++) { 
				
				deck[outer][inner] = (  (value[inner]) + " of " + (suit[outer])); //set each value in the deck array to be a string combined from the suit and value arrays
			}
		}//loop to combine suits and face values into deck array
		
	String userCard1 = deck[(int) (Math.random()*4)][uRandom1];
	String userCard2 = deck[(int) (Math.random()*4)][uRandom2];
	String casinoCard1 = deck[(int) (Math.random()*4)][cRandom1];
	//drawing names of cards
	
	int uTotal = (userCardValue1+userCardValue2);
	cTotal = (casinoCardValue1+casinoCardValue2);
	//variables for keeping track of the user and dealer hand value
	
	Scanner input = new Scanner(System.in);
	/*System.out.println("How much would you like to bet?");
	bet = input.nextDouble();*/
	
	CasinoMenu.customerList[cus].setBalance(((CasinoMenu.customerList[cus].getBalance())-bet));//deduct the bet from the user's balance
	CasinoMenu.casinoList[cas].setBalance(((CasinoMenu.casinoList[cas].getBalance())-bet));//deduct the bet from the casino's balance
	pot = pot + bet*2;
	
	System.out.println("You have the " + userCard1 + " and the "+ userCard2 +" for a total of "+(userCardValue1+userCardValue2)+".");
	System.out.println("The dealer has the " + casinoCard1+".");
	//print the names of user and dealer cards

	boolean uHit;//boolean for user hitting
	String userInput;//string for user input
	boolean bust = false;
	
	do {//loop for hitting and staying
		System.out.println("Hit or Stay?");//ask user to hit or stay
		userInput = input.next();//capture input
		
				switch (userInput) {
		
				case "HIT": uHit = true;
					break;
				case "Hit": uHit = true;
					break;
				case "hit": uHit = true;
					break;
				default: uHit = false;
				}
				//switch statement to determine whether the user has hit or not and adjust the hit boolean
				
				if (uHit) {//if user hits, draw new card
					int nURandom = (int) (Math.random()*13);//new random int
					int newCardValue = deckValue[nURandom];//new card value
					String newCard = deck[(int) (Math.random()*4)][nURandom];//new card name
					
					System.out.println("You have drawn " +newCard+".");//tells the user what they have drawn

					uTotal = uTotal+newCardValue;//add the new card to the user point total
					
					System.out.println("The value of your hand is "+uTotal);//tells the user their total
							
					if (uTotal>21) {//if the user busts
						uHit = false;//change the user hit boolean value to end the loop
						bust = true;
						System.out.println("You busted!");//tell the user they busted
						casinoWin = true;//set casino win variable to indicate that the user lost
						}
					}
				
				if (!bust) {//if user hits and does not bust
					if (cTotal<17) {//if dealer has less than 17
						int nCRandom = (int) (Math.random()*13);
						int newCardValue = deckValue[nCRandom];
						String newCard = deck[(int) (Math.random()*4)][nCRandom];
						//draw a new card for the dealer
						cTotal = cTotal+newCardValue;//add new card value to dealer total
						System.out.println("The dealer hit and drew a "+newCard+" for a total of "+cTotal+".");//tell the user the dealer's new card
					}
					else {
						System.out.println("The dealer stays.");
					}
					
					if (cTotal>21) {//if dealer busts
						System.out.println("The dealer busted!");
						uHit = false;
						userWin = true;
					}//end the loop and the user wins
				}
	}
	while (uHit);//loop continues while user is hitting
	
	if ( (!bust) && (cTotal<17) ) {
		do{
			int nCRandom2 = (int) (Math.random()*13);
			int newCardValue = deckValue[nCRandom2];
			String newCard = deck[(int) (Math.random()*4)][nCRandom2];
			//draw a new card
			cTotal = cTotal+newCardValue;//add the new card to the dealer total
			System.out.println("The dealer hit and drew a "+newCard+" for a total of "+cTotal+".");
			
				if (cTotal>21) {
					System.out.println("The dealer busted!");
					userWin = true;
				}//if statement to check for dealer bust
		}
		while (cTotal<17);
	}//the user didn't bust and the dealer has less than 17, repeat the previous loop until the dealer busts or stays
	
	if (!casinoWin && !userWin) {//if neither player busts
		
			if ( uTotal > cTotal) {
				userWin = true;
			}//user wins if their value is higher
	
			else if ( uTotal < cTotal) {
				casinoWin = true;
			}//casino wins if their total is higher
			
			else if (casinoWin && userWin) {
				draw = true;
			}//if both players bust, it's a draw
			
			else if (cTotal==uTotal) {
				draw = true;
			}//if both players have the same value, it's a draw
	}
	
	
	if (casinoWin) {
		System.out.println("The dealer has "+cTotal+". You lose!");
		CasinoMenu.casinoList[cas].setBalance(((CasinoMenu.casinoList[cas].getBalance())+pot));
	}//if dealer wins, print the dealer total and losing message
	if (userWin) {
		System.out.println("The dealer has "+cTotal+". You win!");
		CasinoMenu.customerList[cus].setBalance(((CasinoMenu.customerList[cus].getBalance())+pot));
	}//if user wins, print the dealer total and winning message
	if (draw) {
		System.out.println("You and the dealer tied");
		CasinoMenu.customerList[cus].setBalance(((CasinoMenu.customerList[cus].getBalance())+bet));
		CasinoMenu.casinoList[cas].setBalance(((CasinoMenu.casinoList[cas].getBalance())+bet));
	}//if there is a draw, print draw message
	}
}
	