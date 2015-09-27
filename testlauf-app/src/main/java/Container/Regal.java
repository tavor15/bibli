package Container;

import java.util.HashSet;
import java.util.Iterator;

import com.teamgg.app.Suche;

import medien.Medium;

public class Regal extends Container<Medium> implements Suche{
	

	private static final long serialVersionUID = 21L;
	
	/**
	 * Pfad des Regals in der Bibliotheks-Struktur im UNIX-Stil (bestand/bereich/regal)
	 */
	private String pfad;

	public Regal(Bereich bereich, String name) {
		super.setName(name);
		super.setInhalt(new HashSet<Medium>());
		super.setSuche(bereich.getSuche());
		setPfad(bereich.getName() + "/" + this.getName());
	}
	
	@Override
	/**
	 * Fuegt dem Regal ein neues Medium hinzu und loest die Funktion add(Medium) im Suchsystem aus, 
	 * wodurch die ID des Mediums in die entsprechenden Eigenschafts- und Wert-Listen eingetragen wird.
	 */
	public boolean add(Medium m){
		if(super.add(m)) return getSuche().add(m);
		else return false;
	}
	
	/**
	 * Suche eines Mediums anhand dessen ID (also des hash-Wertes)
	 * @return gefundenes Medium oder null
	 */
	public Medium suche(int id) {
		Iterator<Medium> i = getInhalt().iterator();
		Medium temp;
		while(i.hasNext()){
			temp = i.next();
			if(temp.getId() == id) return temp;
		}
		return null;
	}
	
	/**
	 * Umwandlung einer Menge von IDs in Medien
	 * @return Menge der gefunden Medien
	 */
	public HashSet<Medium> keysToMedia(HashSet<Integer> set) {
		HashSet<Medium> erg = new HashSet<Medium>();
		Iterator<Integer> it = set.iterator();
		int temp;
		while(it.hasNext()){
			temp = it.next();
			erg.add(this.suche(temp));
		}
		return erg;
	}

	public String getPfad() {
		return pfad;
	}

	public void setPfad(String pfad) {
		this.pfad = pfad;
	}

	



}
