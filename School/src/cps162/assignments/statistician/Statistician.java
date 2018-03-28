package cps162.assignments.statistician;

/******************************************************************************
 * A Statistician keeps track of statistics about a sequence of double numbers.
 * Outline of Java Source Code for this class was obtained from:
 *   http://www.cs.colorado.edu/~main/edu/colorado/homework/Statistician.java
 *   
 * Beth Katz modified it to use different method names and extra methods
 * January 2007 and January 2008
 * The student(s) listed below implemented it.
 * 
 * @author Dylan Dewald
 * 
 ******************************************************************************/

public class Statistician implements Cloneable {  
	/****************************
	 * class invariant:
	 * - resetting the statistician resets all values here
	 * - these values are computed since the most recent reset
	 * - sumOfValues contains the sum of all values entered (or 0)
	 * - sumOfValues may have a value signifying arithmetic errors
	 *        Double.POSITIVE_INFINITY or Double.NEGATIVE_INFINITY
	 * - count contains the number of values but may contain
	 *        Integer.MAX_VALUE if too many values are entered
	 * - smallestValue contains smallest value entered (undefined if none)
	 * - largestValue contains largest value entered (undefined if none)
	 */
	private double sumOfValues;
	private int count;
	private double smallestValue;
	private double largestValue;

	/**
	 * Initialize a new Statistician.  
	 * @param none
	 * @postcondition
	 *   This Statistician is newly initialized and has not yet been 
	 *   given any numbers.
	 **/   
	public Statistician( )
	{
		sumOfValues = 0.0;
		count = 0;
		smallestValue = Double.NaN;
		largestValue = Double.NaN;
	}        

	/**
	 * Reset this Statistician. 
	 * @param none
	 * @postcondition
	 *   This Statistician is reinitialized as if it has never been 
	 *   given any numbers.
	 **/
	public void reset( )
	{
		sumOfValues = 0.0;
		count = 0;
		smallestValue = Double.NaN;
		largestValue = Double.NaN;
	}
	
	/**
	 * Returns a separate copy of this Statistician that will appear
	 * to be indistinguishable from the original but separate
	 * @postcondition
	 *   The returned Statistician is a separate copy of this Statistician
	 */
	public Statistician clone()
	{	
		Statistician newStat = new Statistician();
		newStat.add(this);
		newStat.setSmallestValue(this.smallest());
		newStat.setLargestValue(this.largest());
		return newStat;
		
	}

	/**
	 * Give a new number to this Statistician. 
	 * @param number
	 *   the new number that is being given to this Statistician
	 * @postcondition
	 *   The specified number has been given to this Statistician 
	 *   and it will be included in any subsequent statistics.
	 **/
	public void insert(double number)
	{
		count++;
		sumOfValues += number;
		if(nanChecker(this)){
			setSmallestValue(number);
			setLargestValue(number);
			return;
		}
		if(number < smallestValue) {
			smallestValue = number;
		}
		else if(number > largestValue) {
			largestValue = number;
		}
	}

	/**
	 * Add the numbers of another Statistician (addend) to this Statistician.
	 * @param addend
	 *   a Statistician whose numbers will be added to this Statistician
	 * @precondition
	 *   The parameter, addend, is not null. 
	 * @postcondition
	 *   The numbers from addend have been added to this Statistician. 
	 *   After the operation, this Statistician acts as if it were given 
	 *   all of its numbers and also given all of the numbers from the 
	 *   addend.
	 * @exception NullPointerException
	 *   Indicates that addend is null.
	 **/
	public void add(Statistician addend)throws NullPointerException{
		if(addend == null) {
			throw new NullPointerException();
		}
		count += addend.length();
		sumOfValues += addend.sum();
		
		double addendSmallD = addend.smallest();
		double addendLargeD = addend.largest();
		if(nanChecker(this)){
			smallestValue = addendSmallD;
			largestValue = addendLargeD;
			return;
		}
		if(smallestValue > addendSmallD){
			smallestValue = addendSmallD;
		}
		if(largestValue < addendLargeD){
			largestValue = addendLargeD;
		}
	}

	/**
	 * Compare this Statistician to another object for equality.
	 * @param obj
	 *   an object with which this Statistician will be compared
	 * @return
	 *   A return value of true indicates that obj refers to a Statistican 
	 *   object with the same length, sum, mean, largest and smallest as 
	 *   this Statistician. Otherwise the return value is false.
	 * Note:
	 *   If obj is null or does not refer to a Statistician object, 
	 *   then the answer is false.
	 **/   
	public boolean equals(Object obj)
	{
		if(obj instanceof Statistician) {
			Statistician newStat = (Statistician) obj;
			if(newStat.length() == count && newStat.smallest() == smallestValue && 
					newStat.largest() == largestValue && newStat.sum() == sumOfValues) {
				return true;
			}
			else {
				return false;
			}
			
		}
		else {
			return false;
		}
	} 


	/**
	 * Determine how many numbers have been given to this Statistician.
	 * @param none
	 * @return
	 *   count of how many numbers have been given to this Statistician
	 *   since it was initialized or reinitialized.
	 * Note:
	 *   Giving a Statistician more than Integer.MAX_VALUE numbers, 
	 *   will cause failure with an arithmetic overflow.
	 **/ 
	public int length( )
	{
		try {
			if(count >= Integer.MAX_VALUE) {
				throw new ArithmeticException(Integer.toString(Integer.MAX_VALUE));
			}
			else {
				return count;
			}
		}
		catch(ArithmeticException ae) {
			System.out.println(ae.getMessage());
			System.out.println("Count has exceeded the max value of int. Now returning max value as length.");
			return Integer.MAX_VALUE;
		}
	}

	/**
	 * Determine the sum of all the numbers that have been given to this 
	 * Statistician.
	 * @param none
	 * @return
	 *   the sum of all the number that have been given to this 
	 *   Statistician since it was initialized or reinitialized.
	 * Note:
	 *   If the sum exceeds the bounds of double numbers, then the answer
	 *   from this method may be Double.POSITIVE_INFINITY or
	 *   Double.NEGATIVE_INFINITY.
	 **/ 
	public double sum( )
	{
		if(sumOfValues >= Double.POSITIVE_INFINITY) {
			sumOfValues = Double.POSITIVE_INFINITY;
			return Double.POSITIVE_INFINITY;
		}
		else if(sumOfValues <= Double.NEGATIVE_INFINITY) {
			sumOfValues = Double.NEGATIVE_INFINITY;
			return Double.NEGATIVE_INFINITY;
		}
		else {
			return sumOfValues;
		}
	}


	/**
	 * Determine the arithmetic average of all the numbers that have been 
	 * given to this Statistician.
	 * @param none
	 * @return
	 *   the arithmetic mean of all the number that have been given to this 
	 *   Statistician since it was initialized or reinitialized.
	 * Note:
	 *   If this Statistician has been given more than Integer.MAX_VALUE 
	 *   numbers, then this method fails because of arithmetic overflow.
	 *   If length() is zero, then the answer is Double.NaN.
	 *   If sum() exceeds the bounds of double numbers, then the answer 
	 *   may be Double.POSITIVE_INFINITY or Double.NEGATIVE_INFINITY.
	 **/ 
	public double mean( )
	{
		int countNum = length();
		if(countNum == 0) {
			return Double.NaN;
		}
		double sum = sum();
		if(sum <= Double.NEGATIVE_INFINITY || sum >= Double.POSITIVE_INFINITY){
			return sum;
		}
		else{
			return (sum/countNum);
		}
		
	}


	/**
	 * Determine smallest number that has been given to this Statistician.
	 * @param none
	 * @return
	 *   the smallest number that has been given to this Statistician
	 *   since it was initialized or reinitialized.
	 * Note:
	 *   If length() is zero, then the answer is Double.NaN.
	 **/ 
	public double smallest( )
	{
		if(smallestValue <= Double.NEGATIVE_INFINITY) {
			return Double.NEGATIVE_INFINITY;
		}
		else {
			return smallestValue;
		}
	}


	/**
	 * Determine largest number that has been given to this Statistician.
	 * @param none
	 * @return
	 *   the largest number that has been given to this Statistician
	 *   since it was initialized or reinitialized.
	 * Note:
	 *   If length() is zero, then the answer is Double.NaN.
	 **/ 
	public double largest( )
	{
		if(largestValue >= Double.POSITIVE_INFINITY) {
			return Double.POSITIVE_INFINITY;
		}
		else {
			return largestValue;
		}
	}


	/**
	 * Create a new Statistician that behaves as if it was given all 
	 * the numbers from this and the other Statistician 
	 * @param other
	 *   an existing Statistician
	 * @precondition
	 *   Neither this nor the other Statistician is null.
	 * @return
	 *   a new Statistician that acts as if it was given all the 
	 *   numbers from this Statistician and the other Statistician 
	 * @exception NullPointerException.
	 *   Indicates that the argument is null.
	 **/   
	public Statistician union(Statistician other)throws NullPointerException{
		if(other == null){
			throw new NullPointerException();
		}
		Statistician stats = new Statistician();
		stats.add(this);
		stats.add(other);
		return stats;
	}
	//Gives information about the object when called.
	public String toString(){
		String info = new String();
		info += "My length is: " + count + "\n";
		info += "My sum of numbers equals: " + sumOfValues + "\n";
		info += "My smallest number is: " + smallestValue + "\n";
		info += "My largest number is: " + largestValue + "\n";
		return info;
	}
	//Set the smallestValue variable.
	public void setSmallestValue(double newValue){
		smallestValue = newValue;
	}
	//Set the largestValue variable.
	public void setLargestValue(double newValue){
		largestValue = newValue;
	}
	//Checks to see if the Statistician passed has a Nan variable either
		//in the smallestValue or the largestValue variables. Returns true
		//if one is NaN or both are NaN.
	public boolean nanChecker(Statistician statBeingChecked){
		boolean statIsNan = false;
		if(Double.isNaN(statBeingChecked.smallest())){
			statIsNan = true;
		}
		if(Double.isNaN(statBeingChecked.largest())){
			statIsNan = true;
		}
		return statIsNan;
	}

}
