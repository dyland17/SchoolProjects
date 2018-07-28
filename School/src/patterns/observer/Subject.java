package patterns.observer;

public interface Subject {
	public void registerObserver(Observer newObserver);
	public void removeObserver(Observer removeObserver);
	public void notifyObservers();
}
