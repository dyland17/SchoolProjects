package patterns.component;

public class Song implements SongComponent{
	private String songName;
	//Minutes are whole numbers and decimal numbers are seconds.
	private double songLength;
	private int songIndex;
	public Song(String songName,double songLength, int songIndex) {
		this.songName = songName;
		this.songLength = songLength;
		this.songIndex = songIndex;
	}
	@Override
	public void add(SongComponent component) {
		throw new UnsupportedOperationException("A Song cannot add a SongComponent.");
		
	}

	@Override
	public void remove(SongComponent component) {
		throw new UnsupportedOperationException("A Song cannot remove a SongComponent.");
	}

	@Override
	public void displayInfo() {
		System.out.println("\tSong " + songIndex + ":");
		System.out.println("\tTitle: " + songName);
		System.out.println("\tLength: " + songLength +"mins" + "\n");
		
	}

}
