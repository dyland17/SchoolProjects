

//File: DoubleArraySeq.java from the package edu.colorado.collections

//This is an assignment for students to complete after reading Chapter 3 of
//"Data Structures and Other Objects Using Java" by Michael Main.


/******************************************************************************
 * This class is a homework assignment;
 * A DoubleArraySeq is a collection of double numbers.
 * The sequence can have a special "current element," which is specified and 
 * accessed through four methods that are not available in the sequence class 
 * (start, getCurrent, advance and isCurrent).
 *
 * @note
 *   (1) The capacity of a sequence can change after it's created, but
 *   the maximum capacity is limited by the amount of free memory on the 
 *   machine. The constructor, addAfter, addBefore, clone, 
 *   and concatenation will result in an
 *   OutOfMemoryError when free memory is exhausted.
 *   <p>
 *   (2) A sequence's capacity cannot exceed the maximum integer 2,147,483,647
 *   (Integer.MAX_VALUE). Any attempt to create a larger capacity
 *   results in a failure due to an arithmetic overflow. 
 *
 * @note
 *   This file contains mostly blank implementations ("stubs")
 *   because this is a Programming Project for students.
 *
 * @see
 *   Unmodified older Java Source Code for this class 
 *   <A HREF="http://www.cs.colorado.edu/~main/edu/colorado/collections/DoubleArraySeq.java">
 *   (www.cs.colorado.edu/~main/edu/colorado/collections/DoubleArraySeq.java)
 *   </A>
 *
 * @version
 *   March 2018
 *   
 * @author Dylan Dewald 
 * 
 ******************************************************************************/
public class DoubleArraySeq implements Cloneable
{
	// Invariant of the DoubleArraySeq class:
	//   1. manyItems is the number of elements in the sequence.
	//   2. For an empty sequence (with no elements), we do not care what is 
	//      stored in any of data; for a non-empty sequence, the elements of the
	//      sequence are stored in order in data[0] through data[manyItems-1],
	//      and we don't care what's in the rest of data.
	//   3. If there is a current element, then it is in data[currentIndex];
	//      if there is no current element, then currentIndex equals manyItems. 
	private double[ ] data;
	private int manyItems;
	private int currentIndex; 

	/**
	 * Initialize an empty sequence with an initial capacity of 10.  Note that
	 * the addAfter and addBefore methods work efficiently (without needing more 
	 * memory) until this capacity is reached.
	 * @param - none
	 * @postcondition
	 *   This sequence is empty and has an initial capacity of 10.
	 * @exception OutOfMemoryError
	 *   Indicates insufficient memory for: 
	 *   new double[10].
	 **/   
	public DoubleArraySeq( )throws OutOfMemoryError
	{
		data = new double[10];
		manyItems = 0;
		currentIndex = manyItems;
	}


	/**
	 * Initialize an empty sequence with a specified initial capacity. Note that
	 * the addAfter and addBefore methods work efficiently (without needing more 
	 * memory) until this capacity is reached.
	 * @param initialCapacity
	 *   the initial capacity of this sequence
	 * @precondition
	 *   initialCapacity is non-negative.
	 * @postcondition
	 *   This sequence is empty and has the given initial capacity.
	 * @exception IllegalArgumentException
	 *   Indicates that initialCapacity is negative.
	 * @exception OutOfMemoryError
	 *   Indicates insufficient memory for: 
	 *   new double[initialCapacity].
	 **/   
	public DoubleArraySeq(int initialCapacity) throws OutOfMemoryError, IllegalArgumentException
	{
		if(initialCapacity < 0) {
			throw new IllegalArgumentException("Initial capacity can not be negative");
		}
		data = new double[initialCapacity];
		manyItems = 0;
		currentIndex = manyItems;
		
	}

	/**
	 * Add a new element to this sequence, after the current element. 
	 * If the new element would take this sequence beyond its current capacity,
	 * then the capacity is increased before adding the new element.
	 * @param element
	 *   the new element that is being added
	 * @postcondition
	 *   A new copy of the element has been added to this sequence. If there was
	 *   a current element, then the new element is placed after the current
	 *   element. If there was no current element, then the new element is placed
	 *   at the end of the sequence. In all cases, the new element becomes the
	 *   new current element of this sequence. 
	 * @exception OutOfMemoryError
	 *   Indicates insufficient memory for increasing the sequence's capacity.
	 * @note
	 *   An attempt to increase the capacity beyond Integer.MAX_VALUE will cause 
	 *   the sequence to fail with an arithmetic overflow.
	 **/
	public void addAfter(double element) throws OutOfMemoryError{
		if(checkIfSizeNeedsToIncrease(1)){
			increaseArray();
		}
		if(isCurrent()){
			//move elements forward and add new element before those elements
			if(manyItems - currentIndex > 1){
				moveElementForward(currentIndex+1);
				data[currentIndex+1] = element;
			}
			else{
				data[(currentIndex+1)] = element;
			}
			currentIndex++;
		}
		else{
			data[currentIndex] = element;
		}
		manyItems++;
	}

	/**
	 * Move forward, so that the current element is now the next element in
	 * this sequence.
	 * @param - none
	 * @precondition
	 *   isCurrent() returns true. 
	 * @postcondition
	 *   If the current element was already the end element of this sequence 
	 *   (with nothing after it), then there is no longer any current element. 
	 *   Otherwise, the new element is the element immediately after the 
	 *   original current element.
	 * @exception IllegalStateException
	 *   Indicates that there is no current element, so 
	 *   advance may not be called.
	 **/
	public void advance( ) throws IllegalStateException
	{
		if(isCurrent()){
			currentIndex++;
		}
		else{
			throw new IllegalStateException("Cannot advance currentIndex further.");
		}
		
	}


	/**
	 * Add a new element to this sequence, before the current element. 
	 * If the new element would take this sequence beyond its current capacity,
	 * then the capacity is increased before adding the new element.
	 * @param element
	 *   the new element that is being added
	 * @postcondition
	 *   A new copy of the element has been added to this sequence. If there was
	 *   a current element, then the new element is placed before the current
	 *   element. If there was no current element, then the new element is placed
	 *   at the start of the sequence. In all cases, the new element becomes the
	 *   new current element of this sequence. 
	 * @exception OutOfMemoryError
	 *   Indicates insufficient memory for increasing the sequence's capacity.
	 * @note
	 *   An attempt to increase the capacity beyond Integer.MAX_VALUE will 
	 *   cause the sequence to fail with an arithmetic overflow.
	 **/
	public void addBefore(double element) throws OutOfMemoryError{	
		if(isCurrent()){
			//move elements forward and add new element before those elements
			if(manyItems >= 1){
				if((manyItems+1) > data.length){
					increaseArray();
				}
				moveElementForward(currentIndex);
				data[currentIndex] = element;
			}
		}
		else{
			if(manyItems != 0){
				currentIndex = 0;
				moveElementForward(currentIndex);
			}
			data[currentIndex] = element;
		}
		manyItems++;
	}


	/**
	 * Place the contents of another sequence at the end of this sequence.
	 * @param addend
	 *   a sequence whose contents will be placed at the end of this sequence
	 * @precondition
	 *   The parameter, addend, is not null. 
	 * @postcondition
	 *   The elements from addend have been placed at the end of 
	 *   this sequence. The current element of this sequence remains where it 
	 *   was, and the addend is also unchanged.
	 * @exception NullPointerException
	 *   Indicates that addend is null. 
	 * @exception OutOfMemoryError
	 *   Indicates insufficient memory to increase the size of this sequence.
	 * @note
	 *   An attempt to increase the capacity beyond
	 *   Integer.MAX_VALUE will cause an arithmetic overflow
	 *   that will cause the sequence to fail.
	 **/
	public void addAll(DoubleArraySeq addend) throws OutOfMemoryError{
		if(addend != null){
			double[] addData = addend.getData();
			int addEndTotalItems = addend.size();
			boolean currentBeforeAddAll = isCurrent();
			if(checkIfSizeNeedsToIncrease(addEndTotalItems)){
				increaseArray();
			}	
			for(int index = 0; index < addEndTotalItems; index++){
				data[(manyItems)] = addData[index];
				manyItems++;
			}
			if(currentBeforeAddAll == false){
				currentIndex = manyItems;
			}
		}
		else{
			throw new NullPointerException("addend is null.");
		}
	}   


	/**
	 * Generate a copy of this sequence.
	 * @param - none
	 * @return
	 *   The return value is a copy of this sequence. Subsequent changes to the
	 *   copy will not affect the original, nor vice versa.
	 * @exception OutOfMemoryError
	 *   Indicates insufficient memory for creating the clone.
	 **/ 
	public DoubleArraySeq clone( ) throws OutOfMemoryError{  
		DoubleArraySeq cloneSeq = new DoubleArraySeq(data.length);
		cloneSeq.addAll(this);
		cloneSeq.setSize(manyItems);
		cloneSeq.setCurrentIndex(currentIndex);
		return cloneSeq;
	}


	/**
	 * Create a new sequence that contains all the elements from one sequence
	 * followed by another.
	 * @param s1
	 *   the first of two sequences
	 * @param s2
	 *   the second of two sequences
	 * @precondition
	 *   Neither s1 nor s2 is null.
	 * @return
	 *   a new sequence that has the elements of s1 followed by the
	 *   elements of s2 (with no current element)
	 * @exception NullPointerException.
	 *   Indicates that one of the arguments is null.
	 * @exception OutOfMemoryError
	 *   Indicates insufficient memory for the new sequence.
	 * @note
	 *   An attempt to create a sequence with a capacity beyond
	 *   Integer.MAX_VALUE will cause an arithmetic overflow
	 *   that will cause the sequence to fail.
	 **/   
	public static DoubleArraySeq concatenation(DoubleArraySeq s1, DoubleArraySeq s2)throws OutOfMemoryError, NullPointerException{
		if(s1 != null && s2 !=null){
			DoubleArraySeq concatSeq = new DoubleArraySeq();
			concatSeq.addAll(s1);
			concatSeq.addAll(s2);
			return concatSeq;
		}
		else{
			throw new NullPointerException("One the objects passed in the parameters is null.");
		}
	}


	/**
	 * Change the current capacity of this sequence.
	 * @param minimumCapacity
	 *   the new capacity for this sequence
	 * @postcondition
	 *   This sequence's capacity has been changed to at least minimumCapacity.
	 *   If the capacity was already at or greater than minimumCapacity,
	 *   then the capacity is left unchanged.
	 * @exception OutOfMemoryError
	 *   Indicates insufficient memory for: new int[minimumCapacity].
	 **/
	public void ensureCapacity(int minimumCapacity)throws OutOfMemoryError{
		if(minimumCapacity < getCapacity() && minimumCapacity > size()){
			double[] newData = new double[minimumCapacity];
			for(int index = 0; index < minimumCapacity; index++){
				newData[index] = data[index];
			}
			data = newData;
		}
	}


	/**
	 * Accessor method to get the current capacity of this sequence. 
	 * The add method works efficiently (without needing
	 * more memory) until this capacity is reached.
	 * @param - none
	 * @return
	 *   the current capacity of this sequence
	 **/
	public int getCapacity( )
	{
		return data.length;
	}


	/**
	 * Accessor method to get the current element of this sequence. 
	 * @param - none
	 * @precondition
	 *   isCurrent() returns true.
	 * @return
	 *   the current element of this sequence
	 * @exception IllegalStateException
	 *   Indicates that there is no current element, so 
	 *   getCurrent may not be called.
	 **/
	public double getCurrent( ) throws IllegalStateException{
		if(isCurrent()){
			return data[currentIndex];
		}
		else{
			throw new IllegalStateException("No current element to return.");
		}
	}


	/**
	 * Accessor method to determine whether this sequence has a specified 
	 * current element that can be retrieved with the 
	 * getCurrent method. 
	 * @param - none
	 * @return
	 *   true (there is a current element) 
	 *   or false (there is no current element at the moment)
	 **/
	public boolean isCurrent( ){
		return (manyItems == currentIndex) ? false : true;
	}

	/**
	 * Remove the current element from this sequence.
	 * @param - none
	 * @precondition
	 *   isCurrent() returns true.
	 * @postcondition
	 *   The current element has been removed from this sequence, and the 
	 *   following element (if there is one) is now the new current element. 
	 *   If there was no following element, then there is now no current 
	 *   element.
	 * @exception IllegalStateException
	 *   Indicates that there is no current element, so 
	 *   removeCurrent may not be called. 
	 **/
	public void removeCurrent( )throws IllegalStateException{
		if(isCurrent()){
			int originalSize = data.length;
			increaseArray();
			for(int index = currentIndex; index < manyItems; index++){
				data[index] = data[(index + 1)];
			}
			double[ ]newData = new double[originalSize];
			for(int i = 0; i < originalSize; i++) {
				newData[i] = data[i];
			}
			data = newData;
			manyItems--;
		}
		else{
			throw new IllegalStateException("No current index to remove.");
		}
	}


	/**
	 * Determine the number of elements in this sequence.
	 * @param - none
	 * @return
	 *   the number of elements in this sequence
	 **/ 
	public int size( )
	{
		return manyItems;
	}


	/**
	 * Set the current element at the front of this sequence.
	 * @param - none
	 * @postcondition
	 *   The front element of this sequence is now the current element (but 
	 *   if this sequence has no elements at all, then there is no current 
	 *   element).
	 **/ 
	public void start( )
	{
		currentIndex = 0;
	}


	/**
	 * Reduce the current capacity of this sequence to its actual size (i.e., the
	 * number of elements it contains). The current item does not change.
	 * @param - none
	 * @postcondition
	 *   This sequence's capacity has been changed to its current size.
	 * @exception OutOfMemoryError
	 *   Indicates insufficient memory for altering the capacity. 
	 **/
	public void trimToSize( ) throws OutOfMemoryError{
		double[] newArray = new double[manyItems];
		for(int index = 0; index < manyItems; index++){
			newArray[index] = data[index];
		}
		data = newArray;
	}

	/**
	 * Provide a string representation of the sequence with current item 
	 * in parentheses
	 * @param - none
	 * @postcondition string representation returned but sequence is unchanged
	 * @return string displaying sequence 
	 **/
	public String toString( ){
		String answer = "";

		for (int i = 0; i < manyItems; i++) {
			if (i == currentIndex) {
				answer += "(" + data[i] + ") ";
			} else {
				answer += data[i] + " ";  		   
			}
		}
		return answer;
	}
	//Helper method to check if an amount of indexes were added to data, dictated by amountAdded, will exceed data.length.
	//Returns true if this number does not exceed data.length.
	//Returns false otherwise.
	private boolean checkIfSizeNeedsToIncrease(int amountAdded){
		return ((manyItems + amountAdded) > data.length) ? true : false;
		
	}
	//Helper method to increase the data array and then copy over values.
	private void increaseArray(){
		double []newDataCapacityArray = new double[(data.length * 2 + 1)];
		for(int i = 0; i < data.length; i++) {
			newDataCapacityArray[i] = data[i];
		}
		data = newDataCapacityArray;
	}
	//A recursive method that moves all items from index CI forward in the array.
	//Ex. If the index was one before this method then now its two after this method.
	private void moveElementForward(int CI){
		if(CI != (manyItems-1)){
			moveElementForward((CI+1));
		}
		data[(CI+1)] = data[CI];
	}
	//Helper method used by addAll method and returns data array.
	private double[] getData(){
		return data;
	}
	//Only used by clone method to help set up clone
	private void setCurrentIndex(int newCurrentIndex){
			currentIndex = newCurrentIndex;
	}
	//Only used by clone method to help set up clone
	private void setSize(int newSize){
		manyItems = newSize;
	}
	public static void main(String []args){
		DoubleArraySeq arr = new DoubleArraySeq();
		arr.setSize(18);
		System.out.println(arr);
	}
}