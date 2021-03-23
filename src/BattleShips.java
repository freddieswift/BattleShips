import java.util.Scanner;
public class BattleShips {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board board = new Board();
		Scanner scanner = new Scanner(System.in);
		
		boolean gameOver = false;
		//gets user input
		//loops until all ships have been sunk
		while (gameOver == false) {
			char xChar = getChar(scanner);
			int yInt = getInt(scanner);
			board.makeGuess(xChar, yInt);
			gameOver = board.checkGameOver();
		}
		scanner.close();
		
		System.out.println("Congratulations! You have won!");
	}
	
	public static char getChar(Scanner scanner) {
		//used to get character input from user
		//checks if character is in range A-J or a-j
		//if not, user is prompted and asked to re enter
		
		boolean valid = false;
		char inputChar = 0;
		do {
			System.out.println("Enter letter");
			String input = scanner.next();
			if (input.matches("[A-Ja-j]")) {
				inputChar = input.charAt(0);
				inputChar = Character.toLowerCase(inputChar);
				valid = true;
			}
			else {
				System.out.println("Please enter a valid character");
			}
			
		} while (valid == false);
		
		return inputChar;
	}
	
	public static int getInt(Scanner scanner) {
		//used to get integer input from user and validate that input
		//get integer from string entered, if not an integer, user is prompted and asked to re enter
		//checks if input is in range 1-5, if not user is prompted and asked to re enter
		//function returns the input
		boolean valid = false;
		int input = 0;
		do {
			System.out.println("Enter number");
			final String i = scanner.next();
			try {
				input = Integer.valueOf(i);
				if (input >= 1 && input <=5) {
					valid = true;
				}
				else {
					System.out.println("Please enter a valid number");
				}
			}
			catch(NumberFormatException ex) {
				System.out.println("Please enter a valid number");
			}
		} while (valid == false);
		
		return input;
	}
}
