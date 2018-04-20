package cps162.assignments.hourglass;

import java.util.Scanner;

public class Hourglass {

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
	
	public static void makeHourGlass(int size){
		Hourglass.makeHourGlass(size, 0);
	}
	
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
	
	public static void printCount(int count, String s){
		if(count > 0){
			count--;
			System.out.print(s + " ");
			printCount(count,s);
			
		}
	}
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
