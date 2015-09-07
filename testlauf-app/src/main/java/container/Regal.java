package container;

import medien.Medium;

public class Regal extends Container<Medium>{

	public Regal(String name) {
		super(name);
	}

	@Override
	/**
	 * Suche Medium mit Schluessel im Regal
	 * @param key Integer
	 */
	public Medium search(int key){
		if(getInhalt().containsKey(key)){
			return getInhalt().get(key);
		}
		return null;
	}
	
	@Override
	/**
	 * Die Methode fuegt ein neues Medium dem Regal hinzu mit Redundanz-Pruefung auf Regal.
	 * @param m Medium
	 */
	public boolean add(Medium m){
		return super.add(m);
	}
	
	/**
	 * Hinzufuegen eines Mediums zum Regal mit Redundanz-Pruefung auf dem gesamten Bestand.
	 * @param b Bestand
	 * @param m Medium
	 * @return false bei Redundanz, true sonst
	 */
	public boolean add(Bestand b, Medium m){
		if(b.getInhalt().containsKey(m.getId())){
			System.err.println("Identischer Eintrag bereits im Bestand!");
			return false;
		}else
			super.add(m);
		return true;
	}
	

}
