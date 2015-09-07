package container;

import java.util.Enumeration;

import medien.Medium;

public class Bereich extends Container<Regal>{

	public Bereich(String name) {
		super(name);
	}

	@Override
	/**
	 * Suche Regal im Bereich mit hash-key
	 * @param key
	 */
	public Regal search(int key) {
		if(getInhalt().containsKey(key)){
			return getInhalt().get(key);
		}
		return null;
	}
	
	/**
	 * Suche Medium in allen Regalen des Bereichs mit key
	 * @param key
	 * @return Medium
	 */
	public Medium searchMedium(int key){
		Medium ret;
		Enumeration<Regal> elements = getInhalt().elements();
		while(elements.hasMoreElements()){
			ret = elements.nextElement().search(key);
			if(ret != null && ret.hashCode() == key)
				return ret;
		}
		return null;
	}

}
