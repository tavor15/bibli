package medien;

/**
 * Superklasse fuer alle Medien mit grundlegenden Eigenschaften
 * @author nachlicht
 *
 */
public class Medium {
	
	private final int id;
	private String[] titelUndVerlag = new String[2];
	private boolean imBesitz;
	
	/**
	 * Konstruktor ist in alle Konstruktoren der Unterklassen einzubinden und erfuellt Funktion der einmaligen ID-Vergabe
	 * durch Erzeugung des hash-Codes aus dem Objekt mit seinem Titel und dem Verlag. Doppeleintraege werden durch die add-Funktion
	 * des Containers abgefangen.
	 * @param titel
	 * @param verlag
	 */
	public Medium(String titel, String verlag, boolean imBesitz){
		setTitel(titel.replace(' ', '_').toLowerCase());
		setVerlag(verlag.replace(' ', '_').toLowerCase());
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
		return this.getTitelUndVerlag()[0];
	}

	public void setTitel(String titel) {
		this.getTitelUndVerlag()[0] = titel;
	}

	public int getId() {
		return id;
	}

	public String getVerlag() {
		return this.getTitelUndVerlag()[1];
	}

	public void setVerlag(String verlag) {
		 this.getTitelUndVerlag()[1] = verlag;
	}

	private String[] getTitelUndVerlag() {
		return titelUndVerlag;
	
	
	
	}

	public boolean isImBesitz() {
		return imBesitz;
	}

	public void setImBesitz(boolean imBesitz) {
		this.imBesitz = imBesitz;
	}

}
