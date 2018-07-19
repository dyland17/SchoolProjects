package patterns.component;

public class SongPlayer {
	public static void main(String[] args) {
		SongComponent threeDaysGrace = SongComponentFactory.createThreeDaysGrace();
		threeDaysGrace.displayInfo();
		//To demonstrate that a song cannot add another song.
		SongComponent song = new Song("title",0.0,1);
		song.add(new Song("anotherSong",0.0,1));
	}
}
