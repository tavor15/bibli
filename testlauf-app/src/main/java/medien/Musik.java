package medien;

import java.util.ArrayList;

public class Musik extends Medium{

	/*
	 * from Medium:
	 * private final long id;
	 * private String titel;
	 * private String verlag;
	 */	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 12L;
	private ArrayList<String> autoren;
	private ArrayList<String> interpreten;
	private int dauerInSek;
	private String erscheinungsland;
	private int spuren;
	private long ersterscheinung;
	private String medium;
	private String anmerkungen;
	
	public Musik(String titel, String verlag, boolean imBesitz) {
		super(titel, verlag, imBesitz);
		this.setAutoren(new ArrayList<String>());
		this.setInterpreten(new ArrayList<String>());
		this.setDauerInSek(-1);
	}
	
	public Musik(String titel, String verlag, boolean imBesitz, ArrayList<String> autor, ArrayList<String> interpret, int dauerInSek){
		super(titel, verlag, imBesitz);
		this.setAutoren(autoren);
		this.setInterpreten(interpreten);
		this.setDauerInSek(dauerInSek);
	}
	
	@Override
	public String toString(){
		return (super.toString() +
				"\nAutoren: " + getAutoren() +
				"\nInterpreten: " + getInterpreten() +
				"\nDauer in Sekunden: " + getDauerInSek());
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

	public ArrayList<String> getInterpreten() {
		return interpreten;
	}

	public void setInterpreten(ArrayList<String> interpreten) {
		this.interpreten = interpreten;
	}

	public ArrayList<String> getAutoren() {
		return autoren;
	}

	public void setAutoren(ArrayList<String> autoren) {
		this.autoren = autoren;
	}

}
