package container;

import java.util.Hashtable;

public abstract class Container<T>{

	private Hashtable<Integer,T> inhalt;	// Inhaltstabelle, traegt Inhalte
	private String name;
	
	public Container(String name){
		setInhalt(new Hashtable<Integer,T>());
		setName(name);
	};
	
	/**
	 * Teste auf Vorhandensein von obj in Hashtable und fuege ein, falls negatives Ergebnis
	 * @param obj
	 * @return
	 */
	public boolean add(T obj){
		if(inhalt.containsKey(obj.hashCode())) return false;
		else inhalt.put(obj.hashCode(), obj);
		return true;
	}
	
	/**
	 * Fuege obj (auf jeden Fall) in Hashtable ein und liefere false, falls obj einen neuen Eintrag darstellt
	 * @param obj
	 * @return
	 */
	public boolean edit(T obj){
		// loesche alte eintraege bei titel oder verlags-aenderung
		if((inhalt.put(obj.hashCode(), obj)) != null) return true;
		else return false;
	}
	
	/**
	 * Teste auf Vorhandensein von obj in Hashtable und entferne es, liefere sodann true
	 * @param obj
	 * @return
	 */
	public boolean remove(T obj){
		if(inhalt.contains(obj)){
			inhalt.remove(obj);
			return true;
		}else return false;
	}
	
	/**
	 * Teste key auf Vorhandensein in Hashtable und entferne zugehoerigen Eintrag, liefere sodann true
	 * @param key
	 * @return
	 */
	public boolean remove(int key){
		if(inhalt.containsKey(key)){
			inhalt.remove(key);
			return true;
		}else return false;
	}
	
	/**
	 * Abstrakte Suchmethode zur Spezifizierung auf die jeweiligen Kriterien der Ableitungsklassen
	 * @param key
	 * @return
	 */
	public abstract T search(int key);

	public Hashtable<Integer, T> getInhalt() {
		return inhalt;
	}

	public void setInhalt(Hashtable<Integer, T> inhalt) {
		this.inhalt = inhalt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
