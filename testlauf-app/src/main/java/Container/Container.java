package Container;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import com.teamgg.app.Suchsystem;

public abstract class Container<T> implements Serializable{

	private static final long serialVersionUID = 20L;
	private HashSet<T> inhalt;
	private String name;
	private Suchsystem suche;
	
	public boolean add(T element){
		if(inhalt.contains(element)) return false;
		inhalt.add(element);
		return true;
	}
	
	public boolean remove(T element){
		if(inhalt.contains(element)){
			inhalt.remove(element);
			return true;
		}
		return false;
	}
	
	public boolean addAll(Collection<? extends T> col){
		return inhalt.addAll(col);
	}
	
	@Override
	public String toString(){
		String erg = this.getClass().getName() + ": " + name + "\n";
		Iterator<T> i = inhalt.iterator();
		while(i.hasNext()){
			erg = erg + i.next().toString() + "\n######\n";
		}
		return erg;
	}
	
	public HashSet<T> getInhalt() {
		return inhalt;
	}

	public void setInhalt(HashSet<T> inhalt) {
		this.inhalt = inhalt;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Suchsystem getSuche() {
		return suche;
	}
	public void setSuche(Suchsystem suche) {
		this.suche = suche;
	}

	
	

}
