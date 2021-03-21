import java.util.Random;
public class Board {

		static char [][] boardArray = new char [5][10];
//		Coord [] battleShip = new Coord [5];
//		Coord [] destroyer1, destroyer2 = new Coord [4];
		
		public Board() {
			placeShip (5);
			
			drawBoard();
		}
		
		private void placeShip(int length) {
			Random rand = new Random();
			
			//generate random coordinates
			int x = rand.nextInt(10);
			int y = rand.nextInt(5);
			
			//generate random orientation (0 = horizontal, 1 = vertical)
			int orentation = rand.nextInt(2);
			
			//if horizontal
			if(orentation == 0) {
				
			}
			//if vertical
			else {
				
			}
		}
		
		
		
		
		public void drawBoard() {
//			System.out.print("\033[H\033[2J");  
//			System.out.flush();
			System.out.println(" ABCDEFGHIJ");
			for (int i = 0; i <= boardArray.length-1; i++) {
				System.out.print(i + 1);
				for (int j = 0; j <= boardArray[i].length-1; j++) {
					System.out.print(boardArray[i][j]);
				}
				System.out.print('\n');
			}
		}
		
		public void validateGuess(char xChar, int yInt) {
			//Function to validate the guess from the user
			//find ASCII value of character
			int xInt = (int) xChar;
			//check if xChar is in range 'a' - 'j' (0-10)
			//check if yInt is in range 1-5
			if (xInt >= 97 && xInt <= 106 && yInt >= 1 && yInt <= 5) {
				//Convert letter to corresponding numerical value i.e. a -> 0, b -> 1 etc.
				xInt = xInt - 49;
				xInt = Character.getNumericValue(xInt);
				//-1 to get array index
				yInt = yInt - 1;
				makeGuess(xInt, yInt);
			}
			else {
				System.out.println("Please enter a valid guess");
			}
		}
		
		public void makeGuess(int xInt, int yInt) {
			boardArray[yInt][xInt] = 'H';
			drawBoard();
		}
}
