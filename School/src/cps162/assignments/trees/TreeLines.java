package cps162.assignments.trees;
/**
 * A program to exercise the Binary search tree class.
 * 
 * The program reads a line of input from the user, builds a binary search 
 * tree from its characters, and prints the tree's elements sideways 
 * as well as via an in-order traversal.
 * It reads another line of input, removes each of those characters from
 * the tree, and repeats the sideways printing and in-order traversal.
 * 
 * The tree has a BTnode<Character> header node containing the null character.
 * Having this header node at the top allows us to add or remove the top node.
 * However, we must skip it when starting any printing.  
 * 
 *  @author Beth Katz, April 2018 based on April 2008 code
 */
import java.util.Scanner;

public class TreeLines {
	private static Scanner stdin = new Scanner(System.in);

	public static void main(String[] args) {
		BStree tree = new BStree();
		String data;

		data = getLineOfData("Line of characters to insert");
		for (int i = 0; i < data.length(); i++) {
			tree.insert(data.charAt(i));
		}
		print(tree, "After inserting " + data);
		System.out.println();

		data = getLineOfData("Line of characters to remove");
		for (int i = 0; i < data.length(); i++) {
			tree.remove(data.charAt(i));
		}
		print(tree, "After removing " + data);
		System.out.println();
	}

	/**
	 * Returns a line of data from the user after asking with prompt
	 * 
	 * @param prompt
	 *            what to ask user to obtain input
	 * @return
	 *            string of characters that were on one line of input
	 */
	public static String getLineOfData(String prompt) {
		System.out.println(prompt + ":");
		return stdin.nextLine();
	}

	/**
	 * Prints the tree. This printing includes the provided label,
	 * a sideways representation, and an inorder listing
	 * 
	 * @param tree
	 *            the tree
	 * @param label
	 *            label to print before printing the tree
	 */
	public static void print(BStree tree, String label) {
		System.out.println("------ " + label + " ------");
		System.out.println("Tree sideways");
		tree.printSideways();
		System.out.print("Tree in order: ");
		tree.printInorder();
	}
}