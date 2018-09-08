import java.util.Scanner;

public class HighLow {

	static double bet = 0;
	static double pot = 0;
	static int uWinCount =0;//int for tracking wins
	static boolean userLose = false;//boolean for tracking if the user lost
	static boolean play = true;//boolean for continuing to play
	//declare variables
	
	public static double bet(int cas, int cus) {//method for placing bets
		
		Scanner input = new Scanner(System.in);
		System.out.println("How much would you like to bet?");
		bet = input.nextDouble();
		//ask the user how much they want to bet and set input to the bet variable
		
		if (bet<=0) {
			System.out.println("You must enter a bet greater than 0.");
		}//display error if bet is below 1
		
		if (bet > (CasinoMenu.customerList[cus].getBalance())) {
			System.out.println("You don't have enough money in your balance.");
			bet = 0;//set bet to 0 to indicate a bad bet
		}//if the customer doesn't have enough money in their balance
		
		if (bet > (CasinoMenu.casinoList[cas].getBalance())) {
			System.out.println("The casino doesn't have enough money in its balance.");
			bet = 0;//set bet to 0 to indicate a bad bet
		}//if the casino doesn't have enough money in its balance

		return bet;//return the bet value
		
	}
	
	public static void game(int cas, int cus) {

		String[] suit = {"Hearts","Diamonds","Spades","Clubs"}; //initialize array for card suits
		String[] value = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"}; //initialize array for card face value
		String[][] deck = new String[4][13]; //initialize 2d array to hold all 52 cards
		Boolean[][] deckStatus = new Boolean[4][13]; //initialize boolean array to keep track of whether a card has been drawn or not
		
		Scanner input = new Scanner(System.in); //new scanner object
		
		for(int outer1 = 0; outer1 < 4; outer1++) { //loop to set all values of deckStatus to true
			
			for(int inner1 = 0; inner1 < 13; inner1++) { 
		
				deckStatus[outer1][inner1] = true; //set each value in the array to trues
			}
		}
		
		for(int outer = 0; outer < 4; outer++) { //loop to combine the suit and value array into the deck array
			
			for(int inner = 0; inner < 13; inner++) { 
				
				deck[outer][inner] = (  (value[inner]) + " of " + (suit[outer])); //set each value in the deck array to be a string combined from the suit and value arrays
			}
		}
		
		boolean guess = false;//boolean for whether the user guesses higher or lower
		//boolean play = true;//boolean for continuing to play
		boolean high = false;//boolean for if the next card is actually higher or lower
		String currentCard;//string variable for the most recently drawn card
		int currentCardValue = 0;//initialize current card
		int randomSuit = (int) (Math.random()*4); //generate a random int to select a random suit
		int randomValue = (int) (Math.random()*13); //generate a random int to select a random card value

				String lastCard = deck[randomSuit][randomValue];
				int lastCardValue = randomValue;
				System.out.println(lastCard);
				deckStatus[randomSuit][randomValue] = false; //set the corresponding value in the deckStatus boolean array to false, indicating that the card has been drawn

			do {
				
				bet = bet(cas,cus);//call the bet method
				
				if (bet==0) {
					play = false;
				}//if the bet is 0 (indicating a bad bet), end the play
				
				else{//if the bet is good, continue the game
				
				CasinoMenu.customerList[cus].setBalance(((CasinoMenu.customerList[cus].getBalance())-bet));//deduct the bet from the user's balance
				CasinoMenu.casinoList[cas].setBalance(((CasinoMenu.casinoList[cas].getBalance())-bet));//deduct the bet from the casino's balance
				pot = pot + bet*2;//set pot to the previous pot plus new bet times 2
				
				System.out.println("Will the next card be higher or lower?");
				String uHL = input.next();
				//accept string input for whether the user guesses higher or lower
				
				switch (uHL) {
				case "Higher": guess = true;
					break;
				case "higher": guess = true;
					break;
				case "high": guess = true;
					break;
				case "h": guess = true;
					break;
				//if the user guesses higher, the guess boolean will be set to true
				default: guess = false;
				//otherwise the guess boolean will be set to false
				}
				//switch statement for translating the user's answer into the guess boolean
				
				randomSuit = (int) (Math.random()*4); //generate a random int to select a random suit
				randomValue = (int) (Math.random()*13); //generate a random int to select a random card value
				
				if(deckStatus[randomSuit][randomValue] == true) { //boolean statement to check if the card has already been drawn
						currentCard = deck[randomSuit][randomValue];
						currentCardValue = randomValue;
						System.out.println(currentCard);
						deckStatus[randomSuit][randomValue] = false; //set the corresponding value in the deckStatus boolean array to false, indicating that the card has been drawn
					}
				
					if(currentCardValue >= lastCardValue) {
						high = true;
					}
					else {
						high = false;
					}
					//set the high boolean according to whether the card is higher or lower
					
					if (guess==high) {
						System.out.println("You win!");
						uWinCount++;//increase the number of wins
					}//if user guess boolean matches the high boolean, the user wins
					else {
						System.out.println("You lose!");
						CasinoMenu.casinoList[cas].setBalance(((CasinoMenu.casinoList[cas].getBalance())+pot));//add the pot to the casino balance
						play = false; //end the loop because the user lost
						userLose = true;
					}//if the booleans don't match, the user loses
					
					lastCardValue = currentCardValue;//set the last card value to the current card value
					
					System.out.println("Play again?");
					String again = input.next();
					//ask the user if they want to keep playing
					
					switch (again) {
					
					case "YES": play = true;
						break;
					case "Yes": play = true;
						break;
					case "yes": play = true;
						break;
					case "y": play = true;
						break;
					default: play = false;
					}
					//switch statement for translating the user's answer into the play boolean
				}
				}
			while(play);//run the game loop while the user play variable is true
			
			if (!userLose) {
			
				if (uWinCount>=4 && bet!=0) {//if the user wins at least 4 times and the bet was acceptable
					System.out.println("You won $"+pot+" and a bonus $50!");
					CasinoMenu.customerList[cus].setBalance(((CasinoMenu.customerList[cus].getBalance())+pot+50));
					//add the pot plus $50 to user's balance
				}
					
					//if the user wins less than 4 times
					System.out.println("You won $"+pot+"!");
					CasinoMenu.customerList[cus].setBalance(((CasinoMenu.customerList[cus].getBalance())+pot));
					//add the pot to user's balance
				
				}
			}
	}