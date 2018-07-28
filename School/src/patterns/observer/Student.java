package patterns.observer;

import java.util.Random;

public class Student implements Observer {
	private String name;
	private int id_number;
	public Student(String name) {
		this.name = name;
		Random randomGen = new Random(System.nanoTime());
		id_number = randomGen.nextInt();
	}
	@Override
	public void update(String updateMessage) {
		System.out.println(name + "\n" + id_number + "\nMessage below: ");
		System.out.println(updateMessage+ "\n");
	}

}
