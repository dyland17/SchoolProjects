package program.haccbucks;
//keeps track of the indexes of the matches by the user.
public class dedMatches {
	private int []index;
	private int size;
	public dedMatches(){
		index = new int[6];
		size = 0;
	}
	
	public void enterMatch(int indexOfMatch){
		index[size] = indexOfMatch;
		size++;
	}
	
	public int calculateConsecutives(){
		int cindex;
		int count = 0;
		for(cindex = 0;cindex < size; cindex++){
			if(cindex < 5){
				if(index[(cindex+1)] > index[cindex] && index[(cindex+1)] - index[cindex] == 1){
					count++;
				}
			}
		}
		return count;
	}
}
