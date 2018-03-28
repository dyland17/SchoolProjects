package cps162.warmups;

import java.util.Random;

public class Warmups2 {

	public static int sumPos(int[][] array){
		int sumOfPositives = 0;
		for(int row = 0; row < array.length; row++){
			for(int col = 0; col < array[row].length ; col++){
				if(array[row][col] > 0)
					sumOfPositives++;
			}
		}
		return sumOfPositives;
	}
	
	public static void printArray(int[][] array){
		for(int row = 0; row < array.length; row++){
			for(int col = 0; col < array[row].length ; col++){
				System.out.println(array[row][col] + ", ");
			}
		}
	}
	public static void main(String []args){
		int[][] array = new int[6][6];
		Random rand = new Random(10);
		for(int row = 0; row < array.length; row++){
			for(int col = 0; col < array[row].length; col++){
				array[row][col] = rand.nextInt();
			}
		}
		int posNumCount = Warmups2.sumPos(array);
		System.out.println(posNumCount);
		Warmups2.printArray(array);
	}
}
