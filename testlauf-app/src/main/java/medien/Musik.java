package medien;

public class Musik extends Medium{

	/*
	 * from Medium:
	 * private final long id;
	 * private String titel;
	 * private String verlag;
	 */	
	
	private String autor;
	private String interpret;
	private int dauerInSek;
	private String erscheinungsland;
	private int spuren;
	private long ersterscheinung;
	private String medium;
	private String anmerkungen;
	
	public Musik(String titel, String verlag, boolean imBesitz) {
		super(titel, verlag, imBesitz);
		this.setAutor("unbekannt");
		this.setInterpret("unbekannt");
		this.setDauerInSek(-1);
	}
	
	public Musik(String titel, String verlag, boolean imBesitz, String autor, String interpret, int dauerInSek){
		super(titel, verlag, imBesitz);
		this.setAutor(autor);
		this.setVerlag(verlag);
		this.setInterpret(interpret);
		this.setDauerInSek(dauerInSek);
	}
	
	@Override
	public String toString(){
		return (super.toString() +
				"\nAutor: " + getAutor() +
				"\nInterpret: " + getInterpret() +
				"\nDauer in Sekunden: " + getDauerInSek());
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getInterpret() {
		return interpret;
	}

	public void setInterpret(String interpret) {
		this.interpret = interpret;
	}

	public int getDauerInSek() {
		return dauerInSek;
	}

	public void setDauerInSek(int dauerInSek) {
		this.dauerInSek = dauerInSek;
	}

	public String getErscheinungsland() {
		return erscheinungsland;
	}

	public void setErscheinungsland(String erscheinungsland) {
		this.erscheinungsland = erscheinungsland;
	}

	public int getSpuren() {
		return spuren;
	}

	public void setSpuren(int spuren) {
		this.spuren = spuren;
	}

	public long getErsterscheinung() {
		return ersterscheinung;
	}

	public void setErsterscheinung(long ersterscheinung) {
		this.ersterscheinung = ersterscheinung;
	}

	public String getMedium() {
		return medium;
	}

	public void setMedium(String medium) {
		this.medium = medium;
	}

	public String getAnmerkungen() {
		return anmerkungen;
	}

	public void setAnmerkungen(String anmerkungen) {
		this.anmerkungen = anmerkungen;
	}

}
