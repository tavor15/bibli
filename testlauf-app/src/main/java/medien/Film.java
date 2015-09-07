package medien;

import java.util.ArrayList;

public class Film extends Medium{
	
	/*
	 * from Medium:
	 * private final long id;
	 * private String titel;
	 * private String verlag;
	 */

	private String autor;
	private String regisseur;
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
		this.setAutor("unbekannt");
		this.setPrequel(null);
		this.setRegisseur("unbekannt");
		this.setSequel(null);
		this.setTitel("unbekannt");
		this.setVorlage(null);
	}
	
	public Film(String titel, String verlag, boolean imBesitz, String autor, String regisseur, 
			String vorlage, Medium prequel, Medium sequel) {
		super(titel, verlag, imBesitz);
		this.setAutor(autor);
		this.setPrequel(prequel);
		this.setRegisseur(regisseur);
		this.setSequel(sequel);
		this.setTitel(titel);
		this.setVorlage(vorlage);
	}
	
	@Override
	public String toString(){
		return (super.toString() +
				"\nAutor: " + getAutor() +
				"\nRegisseur: " + getRegisseur() +
				"\nVorlage: " + getVorlage() +
				"\nPrequel: " + getPrequel() +
				"\nSequel: " + getSequel());
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getRegisseur() {
		return regisseur;
	}

	public void setRegisseur(String regisseur) {
		this.regisseur = regisseur;
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

}
