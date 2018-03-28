package program.inheritance;

public class dedPoint {
	//Coordinate plane position
	protected int x,y;
	//Constructor
	public dedPoint(int x, int y){
			this.x = x;
			this.y = y;
	}
	//To create unique string for the class.
	public String toString(){
			String s = "X: " + x + " Y: " + y + "\n";
			return s;
	}
	//Set a new point for the instance.
	public void setPoint(int x, int y){
			this.x = x;
			this.y = y;
	}
}
