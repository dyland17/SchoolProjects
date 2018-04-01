package cps162.assignments.linkedlist;
/**
 * 
 * @author Dylan Dewald
 *
 *<h2>Class Invariant: </h2>
 *					<p>SortedList will be initialized with an instance variable of listHead being equal to null.
 *						  When the listHead variable is null then the list is empty with no elements to retrieve.
 *						   As the SortedList has it's insert(double newNum) method called it will sort the double 
 *						  newNum in ascending order. To see the list in a string, the toString() method can be called.
 *					</p>
 */
public class SortedList {
	private NodeDouble listHead;
	public SortedList(){
		listHead = null;
	}
	/**
	 * 
	 * @param newNum <p>This double is inserted into the SortedList in ascending order.</p>
	 * 
	 *								<h2>Description: </h2>
	 *											<p>If the listHead is null then the double becomes the new 
	 *												  double value of listHead. If the double parameter is 
	 *												  smaller than the listHead's value then it becomes the 
	 *												  new listHead with the previous listHead being it's new 
	 *												  link. Lastly, it will enter the value newNum in the 
	 *												  appropriate place in ascending order depending on
	 *												  the current list values.
	 *											</p>
	 */
	public void insert(double newNum){
		NodeDouble preceding = getPrecedingNode(newNum);
		//Nothing in list.
		if(listHead == null){
			listHead = new NodeDouble(newNum, null);
		}//List has item but the newNum is less then all the other elements.
		else if(preceding == null){
			NodeDouble newNode = new NodeDouble(newNum, listHead);
			listHead = newNode;
		}//Found place to insert newNode.
		else{
			NodeDouble newNode = new NodeDouble(newNum,preceding.getLink());
			preceding.setLink(newNode);
		}
	}
	/**
	 *  @return <p>Representation of the current list status in a string.</p>
	 */
	@Override
	public String toString(){
		String info = "";
		NodeDouble currentNode;
		for(currentNode = listHead; currentNode != null; currentNode = currentNode.getLink()){
			info += currentNode.getData() + " ";
		}
		info = info.trim();
		return info;
	}
	/**
	 * 
	 * @param value This value is tested to find the nodeDouble behind this value parameter.
	 * @return A nodeDouble that is the previous node based on the value parameter. 
	 */
	private NodeDouble getPrecedingNode(double value) {
		NodeDouble precursor = null;
		NodeDouble cursor = listHead;
		while(cursor != null && value > cursor.getData()){
				precursor = cursor;
				cursor = cursor.getLink();
		}
		return precursor;
	}
	
	/**
	 * 
	 * @param index is the NodeDouble place in the list order.
	 * @return the NodeDouble corresponding to the index parameter.
	 * @throws NullPointerException
	 */
	public NodeDouble getItem(int index) throws NullPointerException{
		if(index < 0 ){
			return null;
		}
		NodeDouble currentNode = listHead;
		for(int i = 0; i < index; i++){
			if(currentNode == null){
				return null;
			}
			else if(i != index){
				currentNode = currentNode.getLink();
			}
		}
		return currentNode;
	}
}
