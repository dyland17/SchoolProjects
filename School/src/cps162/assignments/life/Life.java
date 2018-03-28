package cps162.assignments.life;

import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author Dylan Dewald
 * 
 * <p><b>Details: </b> The Life class can create, print, and update a 2 dimensional array of booleans.
 * 				 It will collect information from the user during the runProgram method. 
 * 				 Later, the runProgram method will fill a 2 dimension array with boolean values then 
 * 				 update the array 5 times with the update method. </p>
 * <p><b>Date completed: </b> 02/04/18 </p>
 */
public class Life {
	private boolean [][] matrix;
	private int birthLow, birthHigh;
	private int liveLow, liveHigh;
	//Constructor to set up matrix and instance variables.
	public Life(long seed, int rows, int columns, int birthLow, int birthHigh, int liveLow, int liveHigh){
		Life.checkForErrors(rows, columns, birthLow, birthHigh, liveLow, liveHigh);
		this.birthLow = birthLow;
		this.birthHigh = birthHigh;
		this.liveLow = liveLow;
		this.liveHigh = liveHigh;
		matrix = Life.fillMatrix(rows, columns, seed);
	}
	
	/**
	 * @author Dylan Dewald
	 * @param rowCount Row amount for 2D array.
	 * @param columnCount Column amount for 2D array.
	 * @param seed To make the Random class perform the same calculation every call. 
	 * 	   <b>Category:</b> Initialization. <br><br> 
	 * 	   <b>Details:</b> It will fill a 2D boolean array using a seed for the Random class. </p>
	 */
	public static boolean[][] fillMatrix(int rowCount, int columnCount, long seed){
		if(rowCount <= 0 || columnCount <= 0){
			return null;
		}
		boolean[][] matrix = new boolean[rowCount][columnCount];
		
		Random rand = new Random(seed);
		for(int r = 1; r < (rowCount - 1); r++){
			for(int c = 1; c < (columnCount - 1); c++){
				matrix[r][c] = rand.nextBoolean();
			}
		}
		return matrix;
	}
	/**
	 * @author Dylan Dewald
	 * @param matrix The 2D array that will be printed out. <br>
	 * <p> <b>Category:</b> Printing. <br><br> 
	 * 	   <b>Details:</b> It will print out a 2 dimensional array in an organized fashion. </p>
	 */
	public static String printMatrix(boolean[][] matrix){
		String matrixString = "\n";
		for(int r = 0; r < matrix.length; r++){
			for(int c = 0; c < matrix[r].length; c++){
				if(!matrix[r][c]){
					matrixString += " - ";
				}
				else{
					matrixString += " # ";
				}
			}
			matrixString += "\n";
		}
		return matrixString;
		
	}
	//Prints the formatted matrix but with a message above it. 
	public static void printMatrix(boolean[][] matrix, String message){
		System.out.println();
		System.out.println(message);
		printMatrix(matrix);
	}
	//Updates newMatrix with new values through the being born process.
		//Also will have a life span process dictating if the current index will continue to live.
		//This is all dictated by what the user entered for the birthing range and the living range.
	public void update(){
		final boolean[][] viewMatrix = Life.copyOfMatrix(matrix);  
		int neighbors = 0;
		
		for(int row = 1; row < (viewMatrix.length - 1); row++){
			for(int col = 1; col < (viewMatrix[row].length - 1); col++){
				neighbors = Life.checkForNeighbors(row,col,viewMatrix);
				
				if(viewMatrix[row][col] && neighbors < liveLow || neighbors > liveHigh){
					matrix[row][col] = false;
				}
				else if(!viewMatrix[row][col] && neighbors >= birthLow && neighbors <= birthHigh){
					matrix[row][col] = true;
				}
				neighbors = 0;
			}
		}
	}
	public boolean[][] world(){
		return Life.copyOfMatrix(matrix);
	}
	/**
	 * 
	 * @author Dylan Dewald
	 * @param oldMatrix This matrix will be copied.
	 * <p><b>Details: </b> This method will take a 2 dimensional array, make a copy 
	 * 		and return it.</p>
	 */
	public static boolean[][] copyOfMatrix(boolean[][] oldMatrix) {
		boolean[][] viewMatrix = oldMatrix.clone();
		for(int row = 0; row < oldMatrix.length; row++){
			viewMatrix[row] = oldMatrix[row].clone();
		}
		return viewMatrix;
	}
	/**
	 * 
	 * @author Dylan Dewald
	 * @param row The selected row index in matrix.
	 * @param col The selected column index in matrix.
	 * @param matrix The 2 dimensional array being checked for a neighbor.
	 * @param neighbors current count of neighbors.
	 * <p><b>Details: </b> If the neighbor reads true then neighbors increases by 1.</p>
	 */
	public static int checkIsNeighbor(int row, int col, boolean[][] matrix, int neighbors){
		if(matrix[row][col]){
			neighbors++;
		}
		return neighbors;
	}
	//Checking index matrix[row][col] for how many neighbors are around it.
		//Then returning the count of neighbors.
	public static int checkForNeighbors(int row, int col, boolean[][] matrix){
		int neighbors = 0;
		for(int i = -1; i < 2; i++){
			for(int j = -1; j < 2; j++){
				neighbors = checkIsNeighbor(row + i, col + j, matrix, neighbors);
			}
		}
		return neighbors;
	}
	//Provides a question to be asked and gathers information with Scanner.
		//In this case we are expecting an Integer.
	public static int questionAnswerInt(String message, Scanner scan){
		if(scan == null){
			return 0;
		}
		int answer = 0;
		System.out.println(message);
		answer = scan.nextInt();
		return answer;
	}
	//Provides a question to be asked and gathers information with Scanner. 
		//In this case we are expecting a Long.
	public static long questionAnswerLong(String message, Scanner scan){
		if(scan == null){
			return 0L;
		}
		long answer = 0;
		System.out.println(message);
		answer = scan.nextLong();
		return answer;
	}
	/**
	 * @author Dylan Dewald
	 * @param rows user provided rows.
	 * @param cols user provided columns.
	 * @param birthLow user provided birthLow.
	 * @param birthHigh user provided birthHigh.
	 * @param liveLow user provided liveLow.
	 * @param liveHigh user provided liveHigh.
	 * @return True if there was an error with the data.
	 */
	public static void checkForErrors(int rows, int cols, int birthLow, int birthHigh,
										 int liveLow, int liveHigh) {
		if(rows <= 0 || cols <= 0){
			throw new IllegalArgumentException("To few of rows or columns.");
		}
		else if(birthLow < 0 || birthHigh > 9 || birthLow > birthHigh){
			throw new IllegalArgumentException("The birth range was not right.");
		}
		else if(liveLow < 0 || liveHigh > 9 || liveLow > liveHigh){
			throw new IllegalArgumentException("The living range was not right.");
		}
	}
	//Prints out information on this instance of the Life object.
	@Override
	public String toString() {
		String info = "birthLow: " + birthLow + " birthHigh: " + birthHigh + "\n";
		info += "liveLow: " + liveLow + " liveHigh: " + liveHigh + "\n";
		info += printMatrix(matrix);
		return info;
	}
	//Runs an example of the program. Gathering the data needed then prints the matrix.
	public static void runProgram(){
		Scanner scan = new Scanner(System.in);
		int rows = Life.questionAnswerInt("How many rows?", scan);
		int cols = Life.questionAnswerInt("How many columns?", scan);
		long seed = Life.questionAnswerLong("What is the seed?", scan);
		int birthLow = Life.questionAnswerInt("What is birthLow?", scan);
		int birthHigh = Life.questionAnswerInt("What is birthHigh?", scan);
		int liveLow = Life.questionAnswerInt("What is liveLow?", scan);
		int liveHigh = Life.questionAnswerInt("What is liveHigh?", scan);
		Life.checkForErrors(rows, cols, birthLow, birthHigh, liveLow, liveHigh);
		Life lifeObject = new Life(seed,rows,cols,birthLow,birthHigh,liveLow,liveHigh);
		
		System.out.println(printMatrix(lifeObject.world()));
		for(int i = 1; i <= 5; i++){
			lifeObject.update();
			System.out.println(printMatrix(lifeObject.world()));
		}
		scan.close();
	}
	
	public static void main(String []args){
		Life.runProgram();
	}
}