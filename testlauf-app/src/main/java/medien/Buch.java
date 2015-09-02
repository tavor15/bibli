package medien;

import java.util.ArrayList;

public class Buch extends Medium{
	
	/*
	 * from Medium:
	 * private final long id;
	 * private String titel;
	 * private String verlag;
	 */
	
	private ArrayList<String> autoren;
	private int seiten;
	private Medium prequel;
	private Medium sequel;
	
	public Buch(String titel, String verlag, ArrayList<String> autoren, int seiten, Medium prequel, Medium sequel) {
		super(titel, verlag);
		this.setAutoren(autoren);
		this.setPrequel(prequel);
		this.setSeiten(seiten);
		this.setSequel(sequel);
	}
	
	@Override
	public String toString(){
		return (super.toString() +
				"\nAutoren: " + getAutoren() +
				"\nSeiten: " + getSeiten() +
				"\nPrequel: " + getPrequel() +
				"\nSequel: " + getSequel());
	}

	public ArrayList<String> getAutoren() {
		return autoren;
	}

	public void setAutoren(ArrayList<String> autoren) {
		this.autoren = autoren;
	}

	public int getSeiten() {
		return seiten;
	}

	public void setSeiten(int seiten) {
		this.seiten = seiten;
	}

	public Medium getPrequel() {
		return prequel;
	}

	public void setPrequel(Medium prequel) {
		this.prequel = prequel;
	}

	public Medium getSequel() {
		return sequel;
	}

	public void setSequel(Medium sequel) {
		this.sequel = sequel;
	}

}
