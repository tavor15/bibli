package com.teamgg.app;

public class Regal extends Container<Medium>{

	@Override
	public Medium search(int key){
		if(getInhalt().containsKey(key)){
			return getInhalt().get(key);
		}
		return null;
	}
	
	

}
