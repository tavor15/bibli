package medien;

import java.util.ArrayList;

public class Film extends Medium{
	
	/*
	 * from Medium:
	 * private final long id;
	 * private String titel;
	 * private String verlag;
	 */

	private ArrayList<String> autoren;
	private ArrayList<String> regisseure;
	private String vorlage; //erweiterung als medium
	private Medium prequel;
	private Medium sequel;
	private int laufzeitInSek;
	private String medium;
	private ArrayList<String> sprachen;
	private ArrayList<String> untertitel;
	private String erscheinungsland;
	private String anmerkungen;
	
	public Film(String titel, String verlag, boolean imBesitz) {
		super(titel, verlag, imBesitz);
		this.setAutoren(new ArrayList<String>());
		this.setPrequel(null);
		this.setRegisseure(new ArrayList<String>());
		this.setSequel(null);
		this.setTitel("unbekannt");
		this.setVorlage(null);
	}
	
	public Film(String titel, String verlag, boolean imBesitz, ArrayList<String> autoren, ArrayList<String> regisseure, 
			String vorlage, Medium prequel, Medium sequel) {
		super(titel, verlag, imBesitz);
		this.setAutoren(autoren);
		this.setPrequel(prequel);
		this.setRegisseure(new ArrayList<String>());
		this.setSequel(sequel);
		this.setTitel(titel);
		this.setVorlage(vorlage);
	}
	
	@Override
	public String toString(){
		return (super.toString() +
				"\nAutoren: " + getAutoren() +
				"\nRegisseure: " + getRegisseure() +
				"\nVorlage: " + getVorlage() +
				"\nPrequel: " + getPrequel() +
				"\nSequel: " + getSequel());
	}

	public String getVorlage() {
		return vorlage;
	}

	public void setVorlage(String vorlage) {
		this.vorlage = vorlage;
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

	public int getLaufzeitInSek() {
		return laufzeitInSek;
	}

	public void setLaufzeitInSek(int laufzeitInSek) {
		this.laufzeitInSek = laufzeitInSek;
	}

	public String getMedium() {
		return medium;
	}

	public void setMedium(String medium) {
		this.medium = medium;
	}

	public ArrayList<String> getSprachen() {
		return sprachen;
	}

	public void setSprachen(ArrayList<String> sprachen) {
		this.sprachen = sprachen;
	}

	public ArrayList<String> getUntertitel() {
		return untertitel;
	}

	public void setUntertitel(ArrayList<String> untertitel) {
		this.untertitel = untertitel;
	}

	public String getErscheinungsland() {
		return erscheinungsland;
	}

	public void setErscheinungsland(String erscheinungsland) {
		this.erscheinungsland = erscheinungsland;
	}

	public String getAnmerkungen() {
		return anmerkungen;
	}

	public void setAnmerkungen(String anmerkungen) {
		this.anmerkungen = anmerkungen;
	}

	public ArrayList<String> getAutoren() {
		return autoren;
	}

	public void setAutoren(ArrayList<String> autoren) {
		this.autoren = autoren;
	}

	public ArrayList<String> getRegisseure() {
		return regisseure;
	}

	public void setRegisseure(ArrayList<String> regisseure) {
		this.regisseure = regisseure;
	}

}
