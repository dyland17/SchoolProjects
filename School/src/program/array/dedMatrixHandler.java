package program.array;
import java.util.LinkedList;

	/*
	 *Name: Dylan Dewald
	 *Date: 01/28/16 
	 *Purpose: The main objective of this class is to take an
	 *				integer array and convert into a
	 *				multi-dimensional array(Matrix)
	 *				and find the saddle points within
	 *				that matrix. Finally, print them out to
	 *				the console.
	*/
public class dedMatrixHandler {
	//These arrays represent matrices, but they must
		//be made into multi-dimensional arrays first.
	private final int[] line1 = {3,2,5,2,3,1,4,8};
	private final int[] line2 = {3,3,3,2,4,1,1,6,4,2,5};
	private final int[] line3 = {4,2,9,3,5,8,6,3,2,1};
	private final int[] line4 = {4,3,1,2,3,4,2,4,6,8,1,3,5,7};
	private final int[] line5 = {3,2,5,2,6,8,3,8};
	private int matrixNumber = 0;
	//Constructor
	public dedMatrixHandler(){
		//setup matrices and start finding saddle points.
		setupDoubleMatrix(line1);
		setupDoubleMatrix(line2);
		setupDoubleMatrix(line3);
		setupDoubleMatrix(line4);
		setupDoubleMatrix(line5);
	}
	
	
	
	//Setup double array matrix. The first two elements
		// must be the row and column numbers. They have
		// to be in that order.
	public void setupDoubleMatrix(int[] array){
		//Updating matrix number variable
		matrixNumber++;
		//setup of the double array
			//The 0 is the row number and the 1 is the column number.
		int row = array[0];
		int col = array[1];
		
		int[][] darray = new int[row][col];
		//Index is to keep track of the real elements of the array.
		int index = 2;
		for(int x = 0; x < row; x++){
			for(int y = 0; y < col; y++){
				darray[x][y] = array[index];
				index++;
			}
		}
		//To print out the matrix
		System.out.print("Matrix " + matrixNumber + ": ");
		for(int x = 0; x < row; x++){
			for(int y = 0; y < col; y++){
				
				System.out.print(darray[x][y] + ", ");
			}
		}
		System.out.println();
		findSaddlePoints(darray, row, col);
		
	}
	
	public void findSaddlePoints(int[][] darray, int r, int c) {
		//Starting points in the darray.
		int indexRow = 0;
		int indexCol = 0;
		//done is to check when to break out of the while loop.
		boolean done = false;
		//This list holds the saddle points for us.
		LinkedList<Integer> saddlePointNumbers = new LinkedList<Integer>();
		while(done == false){
			//This boolean is set to true because we can assume we
				// found the saddle point till proven otherwise.
			boolean foundASaddlePoint = true;
			
			//checking row first
			for(int x = 0; x < r; x++){
				//This breaks out of the loop if we have proven that
					// the current index is not a saddle point.
				if(foundASaddlePoint == false){
					break;
				}
				//This checks if the current index and the loop index are the
					//same number, if they are we continue to the next iteration
					// in the loop.
				if(x == indexRow){
					continue;
				}
				//This proves the current element vvvvvvvvvvvvvvvvv is not a saddle point.
				if(darray[x][indexCol] >= darray[indexRow][indexCol]){
					foundASaddlePoint = false;
				}
			}	
			
			//checking col second
			for(int y = 0; y < c; y++){
				//This breaks out of the loop if we have proven that
					// the current index is not a saddle point.
				if(foundASaddlePoint == false){
					break;
				}
				//This checks if the current index and the loop index are the
					//same number, if they are we continue to the next iteration
					// in the loop.
				if(y == indexCol){
					continue;
				}
				//This proves the current element vvvvvvvvvvvvvvvvv is not a saddle point.
				if(darray[indexRow][y] >= darray[indexRow][indexCol]){
					foundASaddlePoint = false;
				}
			}
			
			//If foundASaddlePoint is true we add it to the LinkedList.
			if(foundASaddlePoint == true){
				saddlePointNumbers.add(darray[indexRow][indexCol]);
			}
			
			//increasing indexes, also checking bounds.
			indexCol++;
			if(indexCol > (c - 1)){
				indexCol -= c;
				indexRow++;
			}
			
			if(indexRow > (r - 1)){
				done = true;
			}
		}
		printSaddlePoints(saddlePointNumbers);
	}
	
	public void printSaddlePoints(LinkedList list){
		if(list.size() == 0){
			System.out.println("There are no Saddle Points.");
		}
		for(int x = 0; x < list.size(); x++){
			System.out.println("SaddlePoint " + (x + 1) + ": " + list.get(x));
		}
		System.out.println();
	}



	public static void main(String[] args){
		dedMatrixHandler handler = new dedMatrixHandler();
	}
}
