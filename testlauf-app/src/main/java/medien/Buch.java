package medien;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Buch extends Medium{
	
	/*
	 * from Medium:
	 * private final long id;
	 * private String titel;
	 * private String verlag;
	 */
	
	private ArrayList<String> autoren;
	private String seiten;
	private Medium prequel;
	private Medium sequel;
	private String bindung;
	private String auflage;
	private long ersterscheinung;
	private String anmerkungen;
	private String isbn;
	private String Sprache;
	
	public Buch(String titel, String verlag, boolean imBesitz, ArrayList<String> autoren, String seiten, Medium prequel, Medium sequel,
			    String bindung, String auflage, long ersterscheinung, String anmerkungen, String isbn, String sprache) {
		super(titel, verlag, imBesitz);
		this.setAutoren(autoren);
		this.setPrequel(prequel);
		this.setSeiten(seiten);
		this.setSequel(sequel);
		this.setBindung(bindung.replace(' ', '_').toLowerCase());
		this.setAuflage(auflage);
		this.setErsterscheinung(ersterscheinung);
		this.setAnmerkungen(anmerkungen);
		this.setIsbn(isbn);
		this.setSprache(sprache.replace(' ', '_').toLowerCase());
	}
	
	@Override
	public String toString(){
		DateFormat df = new SimpleDateFormat("dd.mm.yyyy");
		String datum = df.format(new Date(getErsterscheinung()));
		return (super.toString() +
				"\nAutoren: " + getAutoren() +
				"\nSeiten: " + getSeiten() +
				"\nPrequel: " + getPrequel() +
				"\nSequel: " + getSequel() +
				"\nBindung: " + getBindung() +
				"\nAuflage: " + getAuflage() +
				"\nErsterscheinung: " + datum +
				"\nSprache: " + getSprache() +
				"\nISBN: " + getIsbn() +
				"\nAnmerkungen: " + getAnmerkungen());
	}

	public ArrayList<String> getAutoren() {
		return autoren;
	}

	public void setAutoren(ArrayList<String> autoren) {
		this.autoren = autoren;
	}

	public String getSeiten() {
		return seiten;
	}

	public void setSeiten(String seiten) {
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

	public String getBindung() {
		return bindung;
	}

	public void setBindung(String bindung) {
		this.bindung = bindung;
	}

	public String getAuflage() {
		return auflage;
	}

	public void setAuflage(String auflage) {
		this.auflage = auflage;
	}

	public long getErsterscheinung() {
		return ersterscheinung;
	}

	public void setErsterscheinung(long ersterscheinung) {
		this.ersterscheinung = ersterscheinung;
	}

	public String getAnmerkungen() {
		return anmerkungen;
	}

	public void setAnmerkungen(String anmerkungen) {
		this.anmerkungen = anmerkungen;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getSprache() {
		return Sprache;
	}

	public void setSprache(String sprache) {
		Sprache = sprache;
	}

}
