package Container;

import java.util.HashSet;
import java.util.Iterator;

import com.teamgg.app.Suche;

import medien.Medium;

public class Bestand extends Container<Bereich> implements Suche{

	public Bestand(String name) {
		super.setName(name);
		super.setInhalt(new HashSet<Bereich>());
	}

	public Medium suche(int id) {
		Iterator<Bereich> i = getInhalt().iterator();
		Iterator<Regal> j;
		Iterator<Medium> k;
		Medium temp;
		while(i.hasNext()){
			j = i.next().getInhalt().iterator();
			while(j.hasNext()){
				k = j.next().getInhalt().iterator();
				while(k.hasNext()){
					temp = k.next();
					if(temp.getId() == id) return temp;
				}
			}
		}
		return null;
	}

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
	
	

}
