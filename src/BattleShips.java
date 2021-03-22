import java.util.Scanner;
public class BattleShips {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board board = new Board();
		Scanner scanner = new Scanner(System.in);
		
		boolean gameOver = false;
		while (gameOver == false) {
			System.out.println("Enter number");
			int yInt = scanner.nextInt();
			
			System.out.println("Enter letter");
			String xString = scanner.next();
			char xChar = xString.charAt(0);
			
			board.validateGuess(xChar, yInt);
			gameOver = board.checkGameOver();
		}
		
		System.out.println("Congratulations! You have won!");
		
		
	}
	
	 
	

}
