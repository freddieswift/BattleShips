
public class Coord {
	private int x;
	private int y;
	
	public Coord (int x, int y) {
		this.x = x;
		this.y = x;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public boolean equivalent(int x, int y) {
		if (x == this.x && y == this.y) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
