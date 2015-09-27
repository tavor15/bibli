package medien;

import java.io.Serializable;

/**
 * Superklasse fuer alle Medien mit grundlegenden Eigenschaften
 * @author nachlicht
 *
 */
public class Medium implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 10L;
	private final int id;
	private String titel;
	private String verlag;
	private boolean imBesitz;
	
	/**
	 * Konstruktor ist in alle Konstruktoren der Unterklassen einzubinden und erfuellt Funktion der einmaligen ID-Vergabe
	 * durch Erzeugung des hash-Codes aus dem Objekt mit seinem Titel und dem Verlag. Doppeleintraege werden durch die add-Funktion
	 * des Containers abgefangen.
	 * @param titel
	 * @param verlag
	 */
	public Medium(String titel, String verlag, boolean imBesitz){
		setTitel(titel);
		setVerlag(verlag);
		setImBesitz(imBesitz);
		this.id = this.hashCode();
	}
	
	@Override
	/**
	 * Generiert den Hash-Code aus Titel und Verlag. Eintraege mit identischem Titel u. Verlag in verschiedenen Regalen
	 * tragen die gleiche ID.
	 * @return hash-Code
	 */
	public int hashCode(){
		return this.getTitel().hashCode() * 13 + this.getVerlag().hashCode() * 17;
	}
	
	/**
	 * Formschoene Ausgabe der Eigenschaften des Mediums.
	 */
	@Override
	public String toString(){
		String ant;
		if(this.isImBesitz()) ant="Ja";
		else ant="Nein";
		return ("ID: " + this.getId() +
				"\nBesitz: " + ant +
				"\nTitel: " + this.getTitel() +
				"\nVerlag: " + this.getVerlag());
	}

	public String getTitel() {
		return this.titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public int getId() {
		return id;
	}

	public String getVerlag() {
		return this.verlag;
	}

	public void setVerlag(String verlag) {
		 this.verlag = verlag;
	}


	public boolean isImBesitz() {
		return imBesitz;
	}

	public void setImBesitz(boolean imBesitz) {
		this.imBesitz = imBesitz;
	}

}
