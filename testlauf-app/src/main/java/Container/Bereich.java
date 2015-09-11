package Container;

import java.util.HashSet;
import java.util.Iterator;

import com.teamgg.app.Suche;

import medien.Medium;

public class Bereich extends Container<Regal> implements Suche{
	
	private String pfad;

	public Bereich(Bestand bestand, String name) {
		super.setName(name);
		super.setInhalt(new HashSet<Regal>());
		super.setSuche(bestand.getSuche());
		setPfad(bestand.getName() + "/" + this.getName());
	}

	public Medium suche(int id) {
		Iterator<Regal> j;
		Iterator<Medium> k;
		Medium temp;
		j = getInhalt().iterator();
		while(j.hasNext()){
			k = j.next().getInhalt().iterator();
			while(k.hasNext()){
				temp = k.next();
				if(temp.getId() == id) return temp;
			}
		}
		return null;
	}
	
	public String getPfad() {
		return pfad;
	}

	public void setPfad(String pfad) {
		this.pfad = pfad;
	}

	

}
