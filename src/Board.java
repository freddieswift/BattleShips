import java.util.Random;
public class Board {

		static char [][] boardArray = new char [5][10];
		
		public Board() {
			populateBoard();
			drawBoard();
		}
		
		private void populateBoard() {
			placeShip(5);
			placeShip(4);
			placeShip(4);
		}
		
		public void drawBoard() {
			//prints the board to the user
			System.out.println("  A B C D E F G H I J");
			for (int i = 0; i <= boardArray.length-1; i++) {
				System.out.print(i + 1 + " ");
				for (int j = 0; j <= boardArray[i].length-1; j++) {
					//if there is a ship at the coordinate, do not print 'S'
					if (boardArray[i][j] != 'S') {
						System.out.print(boardArray[i][j] + " ");
					}
					else {
						System.out.print("  ");
					}
					//System.out.print(boardArray[i][j] + " ");
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
				drawBoard();
			}
		}
		
		public void makeGuess(int x, int y) {
			//if there is a ship [y][x], update that coordinate to 'H' to represent hit
			if (boardArray[y][x] == 'S') {
				boardArray[y][x] = 'H';
			}
			//if there is no ship at [y][x], record 'M' for miss  
			else {
				boardArray[y][x] = 'M';
			}
			drawBoard();
		}
		
		public boolean checkGameOver() {
			boolean gameOver = true;
			//loops through the board to check if there are any ships remaining
			//if there are, then game is not over
			for (int i = 0; i <= (boardArray.length-1); i++) {
				for (int j = 0; j <= (boardArray[0].length-1); j++) {
					if (boardArray[i][j] == 'S') {
						gameOver = false;
					}
				}
			}
			return gameOver;
		}
		
		private boolean updateBoard(int orientation, int x, int y, int shipLength) {
			// new and improved method of placing the ships. 
			// a ship can only be placed if it will not run off the board and there are no clashes with other ships
			// these checks need to be made whether the ship is horizontal or vertical
			// instead of writing the same code out twice, the variables needed to complete these checks are set up beforehand depending on the orientation
			boolean shipPlaced = false;
			
			//to store length of board in either x or y direction depending on orientation
			//used to check whether a ship placed at coordinates 'x,y' will fit on the board
			//if ship is placed horizontally, non of the coordinates it occupies can have an x value greater than the length of boardArray in x direction -1
			int boardLength;
			//used to store the coordinate that will be changing
			//i.e. if ship is placed horizontally, the x value will be changing when the ship is placed
			int coord;
			
			//depending on orientation, used to store the respective column or row ship will be placed in
			char [] columnOrRow;
			
			//if orientation is horizontal
			if (orientation == 0) {
				boardLength = boardArray[0].length - 1;
				coord = x;
				columnOrRow = new char[10];
				columnOrRow = boardArray[y];
			}
			//if orientation is vertical
			else {
				boardLength = boardArray.length -1;
				coord = y;
				columnOrRow = new char[5];
				for (int i = 0; i <= boardLength; i++) {
					columnOrRow[i] = boardArray[i][x];
				}
			}
			
			//check to see if ship will fit on the board
			if (coord + (shipLength - 1) <= boardLength) {
				//used to check whether there is a ship at any of the potential coordinates
				boolean clash = false;
				//loop through board to check if there is a clash
				for (int i = coord; i <= (coord + shipLength -1); i++ ) {
					if (columnOrRow[i] == 'S') {
						clash = true;
					}
				}
				
				//if there is no ship at any of the potential coordinates
				if (clash == false) {
					//loop through the coordinates and place 'S' for ship in all of them
					for (int i = coord; i <= (coord + shipLength -1); i++) {
						columnOrRow[i] = 'S';
						//'columnOrRow' is local to the function so boardArray needs to be updated
						//if columnOrRow is a row, then replace the respective row in boardArray
						if (orientation == 0) {
							boardArray[y] = columnOrRow;
						}
						//if columnOrRow is a column, then loop down the respective column in boardArray, replacing the values in the process
						else {
							for(int j = coord; j <= boardLength; j++) {
								boardArray[j][x] = columnOrRow[j];
							}
						}
					}
					//ship successfully placed
					shipPlaced = true;
				}
			}
			return shipPlaced;
		}
		
		private void placeShip(int shipLength) {
			Random rand = new Random();
			
			boolean shipPlaced = false;
			
			while (shipPlaced == false) {
				
				//generate random coordinates
				int x = rand.nextInt(10);
				int y = rand.nextInt(5);
				
				//generate random orientation (0 = horizontal, 1 = vertical)
				int orientation = rand.nextInt(2);
				//int orientation = 0;
				shipPlaced = updateBoard(orientation, x, y, shipLength);
				
			}
			
		}
		
		
		/*This was my first attempt at populating the board. It works as intended, however there is some repetition.
		 * The processes of checking to see if the ship will run off the board, checking to see if there is already
		 * a ship at any of the potential coordinates and placing the ship were written out twice. The version of the
		 * code being executed depends on the orientation of the ship. The newer method 'updateBoard' sets up the variables
		 * needed by this process before the process is executed. This means there is only one version being executed.
		 */
		
		
			//private void placeShip(int shipLength) {
//			
//			Random rand = new Random();
//			
//			//used to check if the ship has been successfully placed
//			boolean shipPlaced = false;
//			
//			while (shipPlaced == false) {
//				
//				//generate random coordinates
//				int x = rand.nextInt(10);
//				int y = rand.nextInt(5);
//				
//				//generate random orientation (0 = horizontal, 1 = vertical)
//				int orentation = rand.nextInt(2);
//				
//				//if horizontal
//				if(orentation == 0) {
//					//check if the ship will run off the board
//					//checks to see if the coordinate of the end of the ship will be out of the bounds of the array containing the board information
//					if (x + (shipLength - 1) <= ( boardArray[0].length -1)) {
//						
//						//check to see if there is already a ship there
//						boolean clash = false;
//						for (int i = x; i < (x + shipLength); i++ ) {
//							if (boardArray[y][i] == 'S') {
//								clash = true;
//							}
//						}
//						
//						//if there is no ship there then place ship
//						//end while loop by setting shipPlaced to true
//						if (clash == false) {
//							for (int i = x; i < (x + shipLength); i++) {
//								boardArray[y][i] = 'S';
//							}
//							shipPlaced = true;
//						}
//					}
//				}
//				//if vertical
//				else {
//					//check if the ship will run off the board
//					if (y + (shipLength -1) <= ( boardArray.length -1)) {
//						
//						//check to see if there is already a ship there
//						//if there is, then don't place ship
//						boolean clash = false;
//						for (int i = y; i < (y + shipLength); i++ ) {
//							if (boardArray[i][x] == 'S') {
//								clash = true;
//							}
//						}
//						
//						//if there is no ship there then place ship
//						if (clash == false) {
//							for (int i = y; i < (y + shipLength); i++) {
//								boardArray[i][x] = 'S';
//							}
//							shipPlaced = true;
//						}
//					}
//				}
//			}
//		}
}


