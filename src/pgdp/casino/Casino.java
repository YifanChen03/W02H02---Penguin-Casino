package pgdp.casino;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Casino {

	public static void penguBlackJack() {
		// Here is a card deck for your games :)
		// Remember for testing you can use seeds:
		// CardDeck deck = CardDeck.getDeck(420);
		int tokens = 0;
		int startORexit = 0;
		boolean first_loop = true;
		boolean endGame = false;

		tokens = set_tokens(1000);
		System.out.println("Welcome to Pengu-BlackJack!");
		startORexit = startGame();
		if (startORexit == 1) {
			//whatever happens after 1
			while (tokens > 0) {
				if (first_loop) {
					first_loop = false;
					tokens = game(tokens);
				} else {
					if (!endGame) {
						startORexit = startGame();
						if (startORexit == 1) {
							tokens = game(tokens);
						} else {
							endGame = final_sequence(tokens);
						}
					} else {
						break;
					}
				}
			}
			if (tokens == 0) {
				System.out.println("Sorry, you are broke. Better Luck next time.");
				endGame = final_sequence(tokens);
			}
		} else {
			//whatever happens after 2
			endGame = final_sequence(tokens);
		}


	}

	public static String readString() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			return br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int readInt() {
		while (true) {
			String input = readString();
			try {
				return Integer.parseInt(input);
			} catch (Exception e) {
				System.out.println("This was not a valid number, please try again.");
			}
		}
	}

	public static int game(int balance) {
		//this method asks to start the game and contains the entire game
		CardDeck deck = CardDeck.getDeck();

		int bet = 0;
		int card = 0;
		int tokens = balance;
		int p_card_nr = 0;
		int p_points = 0;
		int d_card_nr = 0;
		int d_points = 0;
		int drawORstay = 0;

		print_current_balance(tokens);
		bet = wager(tokens);

		//start of game, player's turn to play
		p_card_nr = 0;
		p_points = 0;
		System.out.println("Player cards:");
		for (int i = 0; i < 2; i++) {
			card = deck.drawCard();
			p_card_nr = p_card_nr + 1;
			p_points = p_points + card;
			System.out.println(p_card_nr + " : " + card);
		}
		print_current_points(p_points, "Current standing");
		while (p_points <= 20) {
			drawORstay = draw_stay();
			if (drawORstay == 1) {
				card = deck.drawCard();
				p_card_nr = p_card_nr + 1;
				p_points = p_points + card;
				System.out.println(p_card_nr + " : " + card);
				print_current_points(p_points, "Current standing");
			} else {
				break;
			}
		}
		if (p_points > 21) {
			System.out.println("You lost " + bet + " tokens.");
			tokens = tokens - bet; //bet is subtracted
		} else if (p_points == 21) {
			System.out.println("Blackjack! You won " + 2 * bet + " tokens.");
			tokens = tokens + 2 * bet;
		} else {
			//dealer's turn to play
			d_card_nr = 0;
			d_points = 0;
			System.out.println("Dealer cards:");
			while (d_points <= p_points) {
				card = deck.drawCard();
				d_card_nr = d_card_nr + 1;
				d_points = d_points + card;
				System.out.println(d_card_nr + " : " + card);
				if (d_points >= 21) {
					break;
				}
			}
			print_current_points(d_points, "Dealer");
			if (d_points > 21) {
				System.out.println("You won " + bet + " tokens.");
				tokens = tokens + bet;
			} else {
				System.out.println("Dealer wins. You lost " + bet + " tokens.");
				tokens = tokens - bet;
			}
		}
		return tokens;
	}

	public static int startGame() {
		int input = 0;
		do {
			System.out.println("(1) Start a game or (2) exit");
			input = readInt();
			if (input != 1 && input !=2) {
				System.out.println("What?!");
			}
		} while (input != 1 && input !=2);
		return input;
	}

	public static int set_tokens(int n) {
		int tokens = n;
		return tokens;
	}

	public static boolean final_sequence(int tokens) {
		System.out.println("Your final balance: " + tokens);
		if (tokens > 1000) {
			System.out.println("Wohooo! Ez profit! :D");
		} else {
			System.out.println("That's very very sad :(");
		}
		System.out.println("Thank you for playing. See you next time.");
		return true;
	}

	public static void print_current_balance(int balance) {
		System.out.println("Your current balance: " + balance);
	}

	public static void print_current_points(int points, String actor) {
		System.out.println(actor + ": " + points);
	}

	public static int wager(int max_wager) {
		int input;
		do {
			System.out.println("How much do you want to bet?");
			input = readInt();
		} while (input <= 0 || input > max_wager);
		return input;
	}

	public static int draw_stay() {
		int input = 0;
		do {
			System.out.println("(1) draw another card or (2) stay");
			input = readInt();
			if (input != 1 && input !=2) {
				System.out.println("What?!");
			}
		} while (input != 1 && input != 2);
		return input;
	}

	public static void main(String[] args) {
		penguBlackJack();
	}

}
