package medien;

/**
 * Superklasse fuer alle Medien mit grundlegenden Eigenschaften
 * @author nachlicht
 *
 */
public class Medium {
	
	private final long id;
	private String titel;
	private String verlag;
	
	/**
	 * Konstruktor ist in alle Konstruktoren der Unterklassen einzubinden und erfuellt Funktion der einmaligen ID-Vergabe
	 * durch Erzeugung des hash-Codes aus dem Objekt mit seinem Titel und dem Verlag. Doppeleintraege werden durch die add-Funktion
	 * des Containers abgefangen.
	 * @param titel
	 * @param verlag
	 */
	protected Medium(String titel, String verlag){
		this.titel = titel;
		this.verlag = verlag;
		this.id = this.hashCode();
	}
	
	@Override
	public String toString(){
		return ("ID: " + this.getId() +
				"\nTitel: " + this.getTitel() +
				"\nVerlag: " + this.getVerlag());
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public long getId() {
		return id;
	}

	public String getVerlag() {
		return verlag;
	}

	public void setVerlag(String verlag) {
		this.verlag = verlag;
	}
	
	

}
