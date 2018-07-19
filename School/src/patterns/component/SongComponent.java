package patterns.component;

public interface SongComponent {
	public void add(SongComponent component);
	public void remove(SongComponent component);
	public void displayInfo();
}
