package cps162.assignments.trees;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class BStreeTest {
	@Test
	public void insertWithNodeTest(){
		//Empty list
		BStree tree = new BStree();
		char charBeingTested;
		tree.insert('s');
		charBeingTested = tree.getStart().getData();
		assertEquals('s', charBeingTested);
		//New node to the left of start
		tree.insert('b');
		charBeingTested = tree.getStart().getLeft().getData();
		assertEquals('b',charBeingTested);
		//New node to the right of start
		tree.insert('z');
		charBeingTested = tree.getStart().getRight().getData();
		assertEquals('z',charBeingTested);
		//New node to the right->left position
		tree.insert('t');
		charBeingTested = tree.getStart().getRight().getLeft().getData();
		assertEquals('t', charBeingTested);
		//New node to the left->right position
		tree.insert('c');
		charBeingTested = tree.getStart().getLeft().getRight().getData();
		assertEquals('c', charBeingTested);
		//Restarting tree and adding a couple of the same character.
		tree = new BStree();
		tree.insert('s');
		tree.insert('s');
		charBeingTested = tree.getStart().getLeft().getData();
		assertEquals('s', charBeingTested);
	}
	@Test
	public void removeFromNodeTest(){
		BStree tree = createInitialTree();
		char charBeingTested;
		
		//remove root with both children being filled.
		tree.insert('t');
		tree.insert('b');
		tree.remove('s');
		charBeingTested = tree.getStart().getData();
		assertEquals('b',charBeingTested);
		charBeingTested = tree.getStart().getRight().getData();
		assertEquals('t',charBeingTested);
		
		//remove root with only one right child filled.
		tree = createInitialTree();
		tree.insert('z');
		tree.remove('s');
		charBeingTested = tree.getStart().getData();
		assertEquals('z',charBeingTested);
		
		//creating example one tree to test inner tree.
			//removing d that has a null right tree but has a left tree.
		tree = createExampleTreeOne();
		tree.remove('d');
		charBeingTested = tree.getStart().getLeft().getData();
		assertEquals('c', charBeingTested);
		
			//removing v which has a right child but a null left child.
		tree.remove('v');
		charBeingTested = tree.getStart().getRight().getData();
		assertEquals('z',charBeingTested);
		System.out.println();
		System.out.println();
		tree.printSideways();
	}
	
	private BStree createInitialTree(){
		BStree tree = new BStree();
		tree.insert('s');
		return tree;
	}
	private BStree createExampleTreeOne(){
		BStree tree = createInitialTree();
		tree.insert('d');
		tree.insert('v');
		tree.insert('b');
		tree.insert('z');
		tree.insert('a');
		tree.insert('c');
		tree.insert('x');
		tree.printSideways();
		return tree;
	}
}
