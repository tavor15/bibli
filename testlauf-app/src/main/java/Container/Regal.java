package Container;

import java.util.HashSet;
import java.util.Iterator;

import com.teamgg.app.Suche;

import medien.Medium;

public class Regal extends Container<Medium> implements Suche{
	
	private String pfad;

	public Regal(Bereich bereich, String name) {
		super.setName(name);
		super.setInhalt(new HashSet<Medium>());
		super.setSuche(bereich.getSuche());
		setPfad(bereich.getName() + "/" + this.getName());
	}
	
	@Override
	public boolean add(Medium m){
		super.add(m);
		getSuche().add(m);
		return false;
	}
	
	public Medium suche(int id) {
		Iterator<Medium> i = getInhalt().iterator();
		Medium temp;
		while(i.hasNext()){
			temp = i.next();
			if(temp.getId() == id) return temp;
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
