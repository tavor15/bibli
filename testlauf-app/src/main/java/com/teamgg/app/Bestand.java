package com.teamgg.app;

import java.util.Enumeration;

public class Bestand extends Container<Bereich>{

	
	public Bestand(){
		super();
		
	}

	/**
	 * Suche nach Bereich in Bestand mit key
	 */
	@Override
	public Bereich search(int key) {
		if(getInhalt().containsKey(key)){
			return getInhalt().get(key);
		}
		return null;
	}
	
	/**
	 * Suche nach Regal ueber Bestand mit key
	 * @param key
	 * @return
	 */
	public Regal searchRegal(int key){
		Regal ret;
		Enumeration<Bereich> elements = getInhalt().elements();
		while(elements.hasMoreElements()){
			ret = elements.nextElement().search(key);
			if(ret != null && ret.hashCode() == key)
				return ret;
		}
		return null;
	}
	
	/**
	 * Suche nach Medium ueber Bestand mit key
	 * @param key
	 * @return
	 */
	public Medium searchMedium(int key){
		Bereich ber;
		Regal reg;
		Medium med;
		Enumeration<Bereich> elements = getInhalt().elements();	//erhalte Bereiche
		while(elements.hasMoreElements()){
			ber = elements.nextElement();
			Enumeration<Regal> regale = ber.getInhalt().elements();	//erhalte Regale
			if(regale.hasMoreElements()){
				reg = regale.nextElement();
				med = reg.search(key);	//suche in jedem Regal nach Medium
				if(med != null && med.hashCode() == key)
					return med;
			}
		}
		return null;
	}
	

	
}
