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
	
	public Musik(String titel, String verlag) {
		super(titel, verlag);
		this.setAutor("unbekannt");
		this.setInterpret("unbekannt");
		this.setDauerInSek(-1);
	}
	
	public Musik(String titel, String verlag, String autor, String interpret, int dauerInSek){
		super(titel, verlag);
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

}
