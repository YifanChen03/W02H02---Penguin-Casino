package pgdp.casino;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Casino {

	public static void penguBlackJack() {
		// Here is a card deck for your games :)
		// Remember for testing you can use seeds:
		// CardDeck deck = CardDeck.getDeck(420);
		CardDeck deck = CardDeck.getDeck();
		int tokens = 0;
		int input = 0;

		tokens = set_tokens(1000);
		input = startGame();
		if (input == 1) {
			//whatever happens after 1
		} else {
			//whatever happens after 2
			System.out.println("Your final balance: " + tokens);
			if (tokens > 1000) {
				System.out.println("Wohooo! Ez profit! :D");
			} else {
				System.out.println("That's very very sad :(");
			}
			System.out.println("Thank you for playing. See you next time.");
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

	public static int startGame() {
		int input = 0;
		System.out.println("Welcome to Pengu-BlackJack!");
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

	public static void main(String[] args) {
		penguBlackJack();
	}

}
