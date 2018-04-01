package cps162.assignments.linkedlist;
// Test the SortedList class
// By __________, March 2018

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestSortedList {

	@Test
	public void a_check42( ) {
       		SortedList list = new SortedList( );
		list.insert(42);
		assertTrue("Incorrect value for one item",
				list.toString( ).equals("42.0"));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void nullListAndInsertValueSmallerThanListTest(){
		SortedList list = getEmptySortedList();
		assertEquals("list data is null", "", list.toString());
		double[] elements = {20.2, 100, 15.5, 5.0};
		addElementsToList(list, elements);
		Assert.assertEquals(5.0, list.getItem(0).getData(), .0001);
	}
	@Test
	public void getItemTest(){
		SortedList list = getEmptySortedList();
		list.insert(5);
		//Get head item
		assertEquals(5,list.getItem(0).getData(),.0001);
		double[] elements = {10.0,25.0,40.0};
		addElementsToList(list, elements);
		//Get a middle element
		assertEquals(25,list.getItem(2).getData(),.0001);
		//Get tail item
		assertEquals(null, list.getItem(4));
		//Get last item
		assertEquals(40, list.getItem(3).getData(),.0001);
		//Get item smaller than 0
		assertEquals(null, list.getItem(-2));
		//Get item way bigger than list.
		assertEquals(null, list.getItem(10));
	}
	
	@Test
	public void insertDoubleInBetweenValuesTest(){
		SortedList list = getEmptySortedList();
		
		double[] elements = {20.0,50.0,100.0,40.0};
		addElementsToList(list, elements);
		NodeDouble node = list.getItem(0).getLink();
		assertEquals(40,node.getData(),.0001);
		
		list.insert(80);
		node = list.getItem(3);
		assertEquals(80,node.getData(),.0001);
	}
	
	@Test
	public void insertAtEndOfListTest(){
		SortedList list = getEmptySortedList();
		
		double[] elements = {2.5,22.2,1.0,50.0};
		addElementsToList(list, elements);
		
		System.out.println(list);
		assertEquals(50,list.getItem(3).getData(),.0001);
	}
	
	@Test
	public void insertDuplicatesTest(){
		SortedList list = getEmptySortedList();
		
		double[] elements = {5.5,75.5,5.5,200.2};
		addElementsToList(list, elements);
		
		System.out.println(list);
		assertEquals("5.5 5.5 75.5 200.2", list.toString());
	}
	
	@Test
	public void insertEndTest(){
		SortedList list = getEmptySortedList();
		
		double[] elements = {20.2,1.2,-2.5,100.0};
		addElementsToList(list, elements);

		System.out.println(list);
		assertEquals("-2.5 1.2 20.2 100.0", list.toString());
	}
	
	@Test
	public void addElementsToListTest(){
		SortedList list = getEmptySortedList();
		double[] elements = {50.0, 25.5,1.2,55.0};
		addElementsToList(list, elements);
		assertEquals("1.2 25.5 50.0 55.0", list.toString());
	}
	
	@Test(expected = NullPointerException.class)
	public void addElementsToListNullTest(){
		SortedList list = null;
		double[] elements = {50.0, 25.5,1.2,55.0};
		addElementsToList(list, elements);
	}
	
	@Test(expected = NullPointerException.class)
	public void addElementsToListWithNullElementsTest(){
		SortedList list = getEmptySortedList();
		double[] elements = null;
		addElementsToList(list, elements);
	}

	private SortedList getEmptySortedList(){
		return new SortedList();
	}
	private void addElementsToList(SortedList list, double[] element){
		if(list != null || element != null){
			for(int i = 0; i < element.length; i++){
				list.insert(element[i]);
			}
		}
		else{
			throw new NullPointerException("The list provided is null or element array is 0.");
		}
	}
}
