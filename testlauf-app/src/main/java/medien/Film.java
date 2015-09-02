package medien;

public class Film extends Medium{
	
	/*
	 * from Medium:
	 * private final long id;
	 * private String titel;
	 * private String verlag;
	 */

	private String autor;
	private String regisseur;
	private Medium vorlage;
	private Medium prequel;
	private Medium sequel;
	
	public Film(String titel, String verlag) {
		super(titel, verlag);
		this.setAutor("unbekannt");
		this.setPrequel(null);
		this.setRegisseur("unbekannt");
		this.setSequel(null);
		this.setTitel("unbekannt");
		this.setVorlage(null);
	}
	
	public Film(String titel, String verlag, String autor, String regisseur, Medium vorlage, Medium prequel, Medium sequel) {
		super(titel, verlag);
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

	public Medium getVorlage() {
		return vorlage;
	}

	public void setVorlage(Medium vorlage) {
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

}
