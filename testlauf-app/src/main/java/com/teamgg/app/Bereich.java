package com.teamgg.app;

import java.util.Enumeration;

public class Bereich extends Container<Regal>{

	@Override
	public Regal search(int key) {
		if(getInhalt().containsKey(key)){
			return getInhalt().get(key);
		}
		return null;
	}
	
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
