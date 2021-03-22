import java.util.Random;
public class Board {

		static char [][] boardArray = new char [5][10];
		
		public Board() {
			
			populateBoard();
			drawBoard();
		}
		
		private void populateBoard() {
			placeShip(5);
			//placeShip(4);
			//placeShip(4);
		}
		
		private void placeShip(int shipLength) {
			Random rand = new Random();
			
			boolean shipPlaced = false;
			
			while (shipPlaced == false) {
				
				//generate random coordinates
				int x = rand.nextInt(10);
				int y = rand.nextInt(5);
				
				//generate random orientation (0 = horizontal, 1 = vertical)
				int orentation = rand.nextInt(2);
				
				//if horizontal
				if(orentation == 0) {
					// check if the ship will run off the board
					if (x + (shipLength - 1) <= ( boardArray[0].length -1)) {
						
						//check to see if there is already a ship there
						boolean clash = false;
						for (int i = x; i < (x + shipLength); i++ ) {
							if (boardArray[y][i] == 'S') {
								clash = true;
							}
						}
						
						//if there is no ship there then place ship
						//end while loop by setting shipPlaced to true
						if (clash == false) {
							for (int i = x; i < (x + shipLength); i++) {
								boardArray[y][i] = 'S';
							}
							shipPlaced = true;
						}
					}
				}
				//if vertical
				else {
					// check if the ship will run off the board
					if (y + (shipLength -1) <= ( boardArray.length -1)) {
						
						//check to see if there is already a ship there
						boolean clash = false;
						for (int i = y; i < (y + shipLength); i++ ) {
							if (boardArray[i][x] == 'S') {
								clash = true;
							}
						}
						
						//if there is no ship there
						if (clash == false) {
							for (int i = y; i < (y + shipLength); i++) {
								boardArray[i][x] = 'S';
							}
							shipPlaced = true;
						}
					}
					
				}
			}
			
		}
		
		
		
		
		public void drawBoard() {
//			System.out.print("\033[H\033[2J");  
//			System.out.flush();
			System.out.println(" ABCDEFGHIJ");
			for (int i = 0; i <= boardArray.length-1; i++) {
				System.out.print(i + 1);
				for (int j = 0; j <= boardArray[i].length-1; j++) {
					if (boardArray[i][j] != 'S') {
						System.out.print(boardArray[i][j]);
					}
					else {
						System.out.print(' ');
					}
					
					//System.out.print(boardArray[i][j]);
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
			if (boardArray[y][x] == 'S') {
				boardArray[y][x] = 'H';
			}
			else {
				boardArray[y][x] = 'M';
			}
			drawBoard();
		}
		
		public boolean checkGameOver() {
			boolean gameOver = true;
			
			for (int i = 0; i <= (boardArray.length-1); i++) {
				for (int j = 0; j <= (boardArray[0].length-1); j++) {
					if (boardArray[i][j] == 'S') {
						gameOver = false;
					}
				}
			}
			return gameOver;
			
		}
		
		//private boolean updateBoard(int orientation, int x, int y, int shipLength) {
//			boolean shipPlaced = false;
//			//to store length of board in either x or y direction depending on orientation
//			//used to check whether a ship placed at coordinates 'x,y' will fit on the board
//			//if ship is placed horizontally, non of the coordinates it occupies can have an x value greater than 9 (length of boardArray in x direction -1)
//			int boardLength;
//			//used to store the coordinate that will be changing
//			//i.e. if ship is placed horizontally, the x value will be changing when the ship is placed
//			int coord;
//			
//			//depending on orientation, used to store the respective column or row ship will be placed in
//			char [] columnOrRow;
//			
//			//if orientation is horizontal
//			if (orientation == 0) {
//				boardLength = boardArray[0].length - 1;
//				coord = x;
//				columnOrRow = new char[10];
//				columnOrRow = boardArray[y];
//			}
//			//if orientation is vertical
//			else {
//				boardLength = boardArray.length -1;
//				coord = y;
//				columnOrRow = new char[5];
//				for (int i = 0; i <= boardLength; i++) {
//					columnOrRow[i] = boardArray[i][x];
//				}
//			}
//			
//			//check to see if ship will fit on the board
//			if (coord + (shipLength - 1) <= boardLength) {
//				//used to check whether
//				boolean clash = false;
//				for (int i = coord; i <= (coord + shipLength -1); i++ ) {
//					if (columnOrRow[i] == 'S') {
//						clash = true;
//					}
//				}
//				
//				if (clash == false) {
//					for (int i = 0; i <= (coord + shipLength -1); i++) {
//						columnOrRow[i] = 'S';
//						
//						if (orientation == 0) {
//							boardArray[y] = columnOrRow;
//						}
//						else {
//							for(int j = 0; i <= boardLength; i++) {
//								boardArray[j][x] = columnOrRow[j];
//							}
//						}
//					}
//					shipPlaced = true;
//				}
//			}
//			
//			return shipPlaced;
//		}
//		
//		private void placeShip(int shipLength) {
//			Random rand = new Random();
//			
//			boolean shipPlaced = false;
//			
//			while (shipPlaced == false) {
//				
//				//generate random coordinates
//				int x = rand.nextInt(10);
//				int y = rand.nextInt(5);
//				
//				//generate random orientation (0 = horizontal, 1 = vertical)
////				int orientation = rand.nextInt(2);
//				int orientation = 0;
//				shipPlaced = updateBoard(orientation, x, y, shipLength);
//				
//			}
//			
//		}
}
