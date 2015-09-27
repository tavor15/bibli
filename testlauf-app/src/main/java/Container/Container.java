package Container;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import com.teamgg.app.Suchsystem;

public abstract class Container<T> implements Serializable{

	private static final long serialVersionUID = 20L;
	
	/**
	 * Eigentlicher Container, sozusagen der body
	 */
	private HashSet<T> inhalt;
	
	/**
	 * Titel der Einheit
	 */
	private String name;
	
	/**
	 * Instanz des Suchsystems, das mit der Listung von Eigenschaften aus Medien betraut wird
	 */
	private Suchsystem suche;
	
	/**
	 * Prueft auf Existenz des Elementes im Container und fuegt es im negativen Fall hinzu.
	 * (siehe add() in Klasse HashSet)
	 * @param element
	 * @return true bei Hinzufuegung, false sonst
	 */
	public boolean add(T element){
		return inhalt.add(element);
	}
	
	/**
	 * Prueft auf Existenz des Elements im Container und loescht es im positiven Fall.
	 * (siehe remove() in Klasse HashSet)
	 * @param element
	 * @return true bei Loeschung, false sonst
	 */
	public boolean remove(T element){
		return inhalt.remove(element);
	}
	
	/**
	 * Siehe addAll() in HashMap
	 * @param col
	 * @return 
	 */
	public boolean addAll(Collection<? extends T> col){
		return inhalt.addAll(col);
	}
	
	@Override
	/**
	 * Formschoene Ausgabe des Containers mit Titel und Inhalten
	 */
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
