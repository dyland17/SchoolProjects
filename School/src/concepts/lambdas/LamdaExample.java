package concepts.lambdas;

import java.util.Arrays;
import java.util.List;

public class LamdaExample {
	public static void main(String[] args) {
		List<Integer> intNum = Arrays.asList(2,3,7);
		//Printing expressions using lambda
		intNum.forEach((Integer i) -> System.out.println(i));
		//Get running total.
			//Original way.
		Integer total = 0;
		for(Integer i: intNum)
			total +=i;
		System.out.println(total);
		//Done with an interface
		total = 0;
		Sum sum = (List<Integer> list) -> {
				int totalSum = 0;
				for(Integer i: list) {
					totalSum += i;
				}
				return totalSum;
		}; 
		//sum.perform can be used anywhere now without creating a
			//new class to implement this method in Sum interface.
		total = sum.perform(intNum);
		System.out.println(total);		
	}
}

interface Sum{
	public Integer perform(List<Integer> list);
}
