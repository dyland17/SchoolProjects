package cps162.assignments.hourglass;

import java.util.Scanner;
/**
 * <h1>Hourglass: </h1> <p>Has methods to print an hourglass depending on the choice of the user.
 * 													The user may choose 1 or anything higher, otherwise it prints "Input is less than 1".</p> 
 * 
 * @author Dylan Dewald
 */
public class Hourglass {
	//Main method to run program.
	public static void main(String []args){
		int size;
		Scanner scan = new Scanner(System.in);
		System.out.println("How big of an hourglass?");
		size = scan.nextInt();
		if(size < 1){
			System.out.println("Input is less than 1");
			return;
		}
		Hourglass.makeHourGlass(size);
	}
	/**
	 * 
	 * @param size of the hour glass across.
	 * 
	 * <h1>Details:</h1><p>This is the public method of makeHourGlass that will be called by other classes.
	 * 											This method simply calls the private version of makeHourGlass with an extra 
	 * 											parameter.</p>
	 */
	public static void makeHourGlass(int size){
		Hourglass.makeHourGlass(size, 0);
	}
	/**
	 * 
	 * @param size of the hourglass being printed.
	 * @param spaces amount of spaces for that line of the hourglass.
	 * 
	 * <h1>Details: </h1><p>Will make an hourglass picture of a certain size dependent on the size parameter.
	 * 											 The picture is made with * and is done recursively. It uses a couple helper methods
	 * 											 like printSpaces and printCount.<p/>
	 */
	private static void makeHourGlass(int size, int spaces){
		
		System.out.println();
		Hourglass.printSpaces(spaces);
		if(size == 1){	
			printCount(size, "*");
			System.out.println();
		}
		else{
			printCount(size, "*");
			makeHourGlass((size-1), (spaces+1));
		}
		Hourglass.printSpaces(spaces);
		printCount(size, "*");
		System.out.println();
	}
	/**
	 * 
	 * @param count is the amount of times you want String s to be repeated.
	 * @param s is the Sring that will be printed a certain amount depending on count.
	 */
	public static void printCount(int count, String s){
		if(count > 0){
			count--;
			System.out.print(s + " ");
			printCount(count,s);
			
		}
	}
	/**
	 * 
	 * @param count prints amount of spaces recursively on one line.
	 */
	public static void printSpaces(int count){
		if(count == 0){
			return;
		}
		if(count == 1){
			System.out.print(" ");	
		}
		else{
			System.out.print(" ");
			printSpaces((count-1));
		}
	}
}
