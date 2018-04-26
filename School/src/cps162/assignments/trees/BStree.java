package cps162.assignments.trees;
/**
 * A Binary search tree class.
 * 
 * The tree has a BTnode<Character> header node containing the null character.
 * Having this header node at the top allows us to add or remove the top node.
 * However, we must skip it when starting any printing.
 * 
 *  @author Beth Katz, April 2018 based on code from April 2007
 *  @author student name 
 */

public class BStree implements BTnode.Visitor<Character> {
	private BTnode<Character> bst;

	/**
	 * constructs an empty binary search tree; bst is the header node; 
	 * first content node will be at its right
	 */
	public BStree() {
		bst = new BTnode<Character>('\0');
	}

	/**
	 * insert item into tree
	 * 
	 * @param item
	 *            item to be inserted in tree
	 */
	public void insert(char item) {
		if (item > '\0') {
			insertWithNode(bst, item);
		}
	}

	/**
	 * insert item into tree rooted at node
	 * precondition of node not null 
	 * look ahead to insert before falling off end of subtree
	 * 
	 * @param node
	 *            root of current subtree
	 * @param item
	 *            item to be inserted in tree
	 */
	private void insertWithNode(BTnode<Character> node, char item) {
		// student writes this code
	}

	/**
	 * remove target from tree
	 * 
	 * @param target
	 *            item to be removed if it exists in tree
	 */
	public void remove(char target) {
		if (target > '\0') {
			removeFromNode(null, bst, target);
		}
	}

	/**
	 * remove target from tree rooted at root with provided parent
	 * 
	 * @param parent
	 *            parent of node (needed for attaching possibly changed node)
	 * @param node
	 *            node at top of current sub-tree that may contain target
	 * @param target
	 *            item to be removed if it exists in tree
	 */
	private void removeFromNode(BTnode<Character> parent, BTnode<Character> node, char target) {
		// student writes this code
	}

	/**
	 * Replaces old child with new child in parent's links. Figures out whether it
	 * was a left or right child. Precondition that parent is not null.
	 * 
	 * @param parent
	 *            node that has children with one needing replacement
	 * @param oldChild
	 *            node that will be discarded
	 * @param newChild
	 *            node that will be attached in oldChild's place
	 */
	private void replaceChild(BTnode<Character> parent, BTnode<Character> oldChild, BTnode<Character> newChild) {
		// student writes this code
	}

	/**
	 * Find the rightmost node (largest value), disconnect it from the tree, and
	 * return its data. Precondition that node and parent are not null.
	 * 
	 * @param parent
	 *            parent of this node so that it can be deleted
	 * @param node
	 *            current node that potentially is rightmost
	 * @return the data from the rightmost node which has been removed
	 */
	private char dataFromDeletedRightmost(BTnode<Character> parent, BTnode<Character> node) {
		// student writes this code
		return 'X';
	}

	/**
	 * Prints the tree inorder using visit method
	 */
	public void printInorder() {
		Btree.inorder(bst.getRight(), this);
	}

	/**
	 * Prints the tree sideways
	 */
	public void printSideways() {
		Btree.printSideways(bst.getRight(), 0);
	}

	/**
	 * when a node is visited, print its data
	 * 
	 * @param node
	 *            the node being visited
	 */
	public void visit(BTnode<Character> node) {
		System.out.print(node.getData());
	}

}
