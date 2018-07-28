package patterns.observer;

public class AppTester {
	public static void main(String []args) {
		Student dylan = new Student("Dylan Dewald");
		Student marie = new Student("Ann Marie");
		
		StudentBody studentBody = new StudentBody();
		studentBody.registerObserver(marie);
		studentBody.registerObserver(dylan);
		
		System.out.println("Going to inform student body now.\n");
		studentBody.notifyObservers();
	}
}
