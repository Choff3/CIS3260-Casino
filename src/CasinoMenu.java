import java.util.Scanner;
import java.util.Arrays;
public class CasinoMenu {//main menu class
	
	public static final Customer[] customerList = new Customer[100];//array for holding customers
	public static final Casino[] casinoList = new Casino[100];//array for holding casinos
	private static int selection;//int for user selection

	public static void main(String[] args) {//main method
		
		do {//do loop for repeating the menu as long as the user doesn't exit
			
		Scanner input = new Scanner(System.in);
		Scanner input2 = new Scanner(System.in);
		//create 2 scanner objects for accepting multiple user inputs in the same code block
		
		System.out.println("\nEnter a number to select from the following options.");
		
		System.out.println("1. Create a new customer");
		System.out.println("2. Create a new casino");
		System.out.println("3. Change a customer's name");
		System.out.println("4. Change a casino's name");
		System.out.println("5. Play games as a customer");
		System.out.println("6. Display customer details");
		System.out.println("7. Display casino details");
		System.out.println("8. Exit");
		//print out menu options
		
		selection = input.nextInt();
		//ask the user to select a menu option
		
		switch (selection) {//switch statement for selecting menu options. Menu options correspond with the user selection int
		
		case 1: {//create a customer option
			int i = 1;//initialize the iteration to 1 so that the customer ID starts at 1
			for (boolean cont = true;cont==true;i++) {//repeat the create customer loop while the user continue boolean is true
			System.out.println("What is the customer's name?");
			String name = input2.nextLine();
			System.out.println("What is the customer's balance?");
			double balance = input.nextDouble();
			//ask user for name and balance
			customerList[i] = new Customer(name,balance);
			System.out.println("Customer created");
			//create the customer in the customer array for each iteration
			
			System.out.println("Create another customer? y/n");
			String cont2 = input.next();
			
				switch (cont2) {
				
				case "Y": cont = true;
					break;
				case "y": cont = true;
					break;
				default: cont = false;
				}
			}//repeat the loop if the user wants to keep making customers
		}
		break;
		
		case 2: {//create a casino option
			int i = 1;//initialize the iteration to 1 so that the casino ID starts at 1
			for (boolean cont = true;cont==true;i++) {//repeat the create casino loop while the user continue boolean is true
			System.out.println("What is the name of the casino?");
			String name = input2.nextLine();
			System.out.println("What is the casino's balance?");
			double balance = input2.nextDouble();
			//ask user for name and balance
			casinoList[i] = new Casino(name, balance);
			System.out.println("Casino created");
			//create the casino in the casino array for each iteration
			
			System.out.println("Create another Casino? y/n");
			String cont2 = input.next();
			
				switch (cont2) {
				
				case "Y": cont = true;
					break;
				case "y": cont = true;
					break;
				default: cont = false;
				}
			}//repeat the loop if the user wants to keep making casinos
		}
		break;
		
		case 3: {//change customer name option
			System.out.println("Enter the ID of the customer to change their name.");
			int chosenID = input.nextInt();
			System.out.println("You have chosen:\n");
			Customer.details(customerList[(chosenID)]);
			//ask user to select a customer and display details
			System.out.println("Enter a new name:");
			String newName = input2.nextLine();
			customerList[chosenID].setName(newName);
			System.out.println("The new name has been set.");
			//ask user for a new name and set it
		}
		break;
		
		case 4: {//change casino name option
			System.out.println("Enter the ID of the casino to change its name.");
			int chosenID = input.nextInt();
			System.out.println("You have chosen:\n");
			Casino.details(casinoList[(chosenID)]);
			//ask user to select a casino and display details
			System.out.println("Enter a new name:");
			String newName = input2.nextLine();
			casinoList[chosenID].setName(newName);
			System.out.println("The new name has been set.");
			//ask user for a new name and set it
		}
		break;
		
		case 5: {//play games option
			
			System.out.println("Enter your customer ID.");
			int customerSelect = input.nextInt();//int for selecting customer
			System.out.println("Enter the ID of the casino where you would like to play.");
			int casinoSelect = input.nextInt();//int for selecting casino
			System.out.println("You have selected: "+casinoList[casinoSelect].getName());
			//ask user to select which customer they're playing as and which casino to play at
			
			System.out.println("Enter a number to select a game.");
			System.out.println("1. Twenty-One\n2. High Low\n3. Seven-Eleven\n4. Slots");
			int gameSelection = input.nextInt();
			//ask user which game they want to play
			
			switch (gameSelection) {//switch statement for selecting games
			
			case 1: {
				TwentyOne.bet(casinoSelect, customerSelect);
			}
			break;
			
			case 2: {
				HighLow.game(casinoSelect, customerSelect);
			}
			break;
			
			case 3: {
				SevenEleven.bet(casinoSelect, customerSelect);
			}
			break;
			
			case 4: {
				Slots.bet(casinoSelect, customerSelect);
			}
			break;
			
			}//call each game method and pass the customer and casino selection ints
			
		}//option for selecting a game
		break;
		
		case 6: {//viewing customer details option
			System.out.println("Enter a customer ID to view their name and balance.");
			int chosenID = input.nextInt();
			Customer.details(customerList[(chosenID)]);//call the details method with the chosen customer int
		}
		break;
		
		case 7: {//viewing casino details method
			System.out.println("Enter a casino ID to view their name and balance.");
			int chosenID = input.nextInt();
			Casino.details(casinoList[(chosenID)]);//call the details method with the chosen casino int
		}
		break;
		
		case 8: {//exit option
			System.exit(0);//exit the program
		}
		break;
		default: System.out.println("Please enter a number 1 through 7.");//default option if the user enters a number that doesn't correspond to a menu option
		}
	}while (selection!=8);//run the menu loop while the user hasn't exited
}
}