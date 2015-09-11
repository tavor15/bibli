package com.teamgg.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import Container.Bestand;
import medien.Buch;
import medien.Film;
import medien.Medium;
import medien.Musik;

public class Suchsystem {

	private HashMap<String,HashMap<String,ArrayList<Integer>>> index;
	// Eigenschaft - Wert - Liste der betreffenden Medien (als Hash-Werte)
	
	public Suchsystem(Bestand b){
		index = new HashMap<String,HashMap<String,ArrayList<Integer>>>();
	}
	
	public boolean add(String eigenschaft, String wert, int id){
		add(eigenschaft, wert);
		if(!index.get(eigenschaft).get(wert).contains(id))
			index.get(eigenschaft).get(wert).add(id);
		else return false; // kein neuer Eintrag, kein Informationsgewinn
		return true; // neue Info gespeichert
	}
	
	public boolean add(String eigenschaft, String wert){
		if(!index.containsKey(eigenschaft))
			index.put(eigenschaft, new HashMap<String,ArrayList<Integer>>());
		if(!index.get(eigenschaft).containsKey(wert))
			index.get(eigenschaft).put(wert, new ArrayList<Integer>());
		else return false; // kein neuer Eintrag, kein Informationsgewinn
		return true; // neue Info gespeichert	
	}
	
	public boolean add(Medium m){
		
		add("verlag",m.getVerlag(),m.getId());
		
		if(m.getClass().isInstance(Buch.class)){ //-----------------------------------Buch-----------------
			Buch b = (Buch) m;
			/*
				private Medium prequel;
				private Medium sequel;
				private long ersterscheinung;
				private String anmerkungen;
				private String isbn;
			 */
			
			Iterator<String> i = b.getAutoren().iterator();
			while(i.hasNext()){
				add("autor", i.next(), b.getId());
			}
			
			add("auflage",b.getAuflage(),b.getId());
			add("seiten",b.getSeiten(),b.getId());
			add("bindung",b.getBindung(),b.getId());
			add("sprache",b.getSprache(),b.getId());
			// ALLE Eigenschaften hinzufuegen oder nur gruppenbildende?
			
		}else if(m.getClass().isInstance(Film.class)){ //------------------------------Film--------------------
			Film f = (Film) m;
			/*
				private Medium prequel;
				private Medium sequel;
				private int laufzeitInSek;
				private String anmerkungen;
			 */
			
			Iterator<String> i = f.getAutoren().iterator();
			while(i.hasNext()){
				add("autor", i.next(), f.getId());
			}
			i = f.getRegisseure().iterator();
			while(i.hasNext()){
				add("regisseur", i.next(), f.getId());
			}	
			
			add("vorlage",f.getVorlage(),f.getId());
			add("medium",f.getMedium(),f.getId());
			add("land",f.getErscheinungsland(),f.getId());
			
			i = f.getSprachen().iterator();
			while(i.hasNext()){
				add("sprache", i.next(), f.getId());
			}
			
			i = f.getUntertitel().iterator();
			while(i.hasNext()){
				add("untertitel", i.next(), f.getId());
			}
			//ALLE Eigenschaften?
		}else if(m.getClass().isInstance(Musik.class)){ //-----------------------------Musik----------------------
			Musik u = (Musik) m;
			/*
				private int dauerInSek;
				private int spuren;
				private long ersterscheinung;
				private String anmerkungen;
			 */
			
			Iterator<String> i = u.getAutoren().iterator();
			while(i.hasNext()){
				add("autor", i.next(), u.getId());
			}
			
			i = u.getInterpreten().iterator();
			while(i.hasNext()){
				add("interpret", i.next(), u.getId());
			}
			
			add("land",u.getErscheinungsland(),u.getId());
			add("medium",u.getMedium(),u.getId());
			// auch hier ALLE Eigenschaften??
		}
		return false;
	}
	
	public static void log(String s){
		System.out.println(s);
	}

}
