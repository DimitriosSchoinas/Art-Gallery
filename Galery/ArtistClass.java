package Galery;

import java.io.Serializable;

class ArtistClass extends AbstractUserClass implements Artist, Serializable {

	private static final long serialVersionUID = 1L;
	private String artisticName;

	public ArtistClass(String login, String name, String artisticName, int age, String email) {
		super(login, name, age, email);
		this.artisticName = artisticName;
	}

	@Override
	public String getArtisticName() {

		return artisticName;
	}
}
