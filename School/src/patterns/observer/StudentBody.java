package patterns.observer;

import java.util.ArrayList;

public class StudentBody implements Subject {
	private ArrayList<Observer> studentList;
	public static final String GRADESUPDATED = "Student body please check your email. " +
			 																				"Grades have been updated.";
	public StudentBody() {
		studentList = new ArrayList<Observer>();
	}
	@Override
	public void registerObserver(Observer newObserver) {
		studentList.add(newObserver);
	}

	@Override
	public void removeObserver(Observer removeObserver) {
		studentList.remove(removeObserver);
	}

	@Override
	public void notifyObservers() {
		studentList.stream().forEach(student -> student.update(GRADESUPDATED));
	}

	

}
