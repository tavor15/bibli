package com.teamgg.app;

public class Medium {
	
	private final int id;
	private String title;
	
	public Medium(String title){
		this.title = title;
		this.id = System.identityHashCode(this);	//erzeuge Hashcode aus sich selbst
		//geht das so? Testen!
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}
	
	

}
