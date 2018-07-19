package patterns.component;

public class SongComponentFactory {
	public static SongComponent createThreeDaysGrace() {
		//Entire group and lead artist.
		SongComponent TDG = new GroupName("Three Days Grace", "Adam Gontier");
		//Album one
		SongComponent lifeStartsNow = new GroupName("Life Starts Now", "Three Days Grace");
		lifeStartsNow.add(new Song("Break",3.12,1));
		lifeStartsNow.add(new Song("Bully",3.38,2));
		lifeStartsNow.add(new Song("Bitter Taste",4.0, 3));
		TDG.add(lifeStartsNow);
		//Album two
		SongComponent human = new GroupName("Human", "Three Days Grace");
		human.add(new Song("Fallen Angel", 3.06,1));
		human.add(new Song("I Am Machine", 3.2,2));
		human.add(new Song("One Two Many", 2.41,3));
		TDG.add(human);
		return TDG;
	}
}
