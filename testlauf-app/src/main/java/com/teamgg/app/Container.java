package com.teamgg.app;

import java.util.Hashtable;

public abstract class Container<T>{

	private Hashtable<Integer,T> inhalt;	// Inhaltstabelle, traegt Inhalte
	
	public Container(){
		setInhalt(new Hashtable<Integer,T>());
	};
	
	public boolean add(T obj){
		if((inhalt.put(obj.hashCode(), obj)) == null) return true;
		else return false;
	}
	
	public boolean remove(T obj){
		if(inhalt.contains(obj)){
			inhalt.remove(obj);
			return true;
		}else return false;
	}
	
	public boolean remove(int key){
		if(inhalt.containsKey(key)){
			inhalt.remove(key);
			return true;
		}else return false;
	}
	
	public abstract T search(int key);

	public Hashtable<Integer, T> getInhalt() {
		return inhalt;
	}

	public void setInhalt(Hashtable<Integer, T> inhalt) {
		this.inhalt = inhalt;
	}
	
}
