package com.teamgg.app;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import Container.Bestand;
import medien.Buch;
import medien.Film;
import medien.Medium;
import medien.Musik;

public class Suchsystem {

	private HashMap<String,HashMap<String,HashSet<Integer>>> index;
	// Eigenschaft - Wert - Liste der betreffenden Medien (als Hash-Werte)
	
	
	
	public Suchsystem(Bestand b){
		index = new HashMap<String,HashMap<String,HashSet<Integer>>>();
	}
	
	
	/**
	 * Editiert einen vorhandenen Eintrag an der Stelle "eigenschaft" mit "wert" 
	 * und ergaenzt die entsprechende Liste falls noetig.
	 * @param eigenschaft
	 * @param wert
	 * @param id
	 * @return true bei Informationsgewinn
	 */
	public boolean add(String eigenschaft, String wert, int id){
		add(eigenschaft, wert);
		if(!index.get(eigenschaft).get(wert).contains(id))
			index.get(eigenschaft).get(wert).add(id);
		else return false; // kein neuer Eintrag, kein Informationsgewinn
		return true; // neue Info gespeichert
	}
	
	@Deprecated
	/**
	 * Fuegt der Liste, die Eintraege der "eigenschaft" beherbergt, "wert" als neue leere Liste von IDs hinzu.
	 * @param eigenschaft
	 * @param wert
	 * @return
	 */
	public boolean add(String eigenschaft, String wert){	//Nutzen?
		if(!index.containsKey(eigenschaft))
			index.put(eigenschaft, new HashMap<String,HashSet<Integer>>());
		if(!index.get(eigenschaft).containsKey(wert))
			index.get(eigenschaft).put(wert, new HashSet<Integer>());
		else return false; // kein neuer Eintrag, kein Informationsgewinn
		return true; // neue Info gespeichert	
	}
	
	/**
	 * Kontrolliert das uebergebene Medium auf seine Ableitungsklasse und fuegt die ID entsprechend den Eigenschaften und Werten
	 * dem Index hinzu bzw. erstellt neue Listen fuer neue Eigenschaften und Werte und versieht diese mit der ID
	 * des Mediums als ersten Eintrag.
	 * @param m
	 * @return
	 */
	public boolean add(Medium m){
		
		add("verlag",m.getVerlag(),m.getId());
		
		if(m.getClass().isAssignableFrom(Buch.class)){ //-----------------------------------Buch-----------------
			add("klasse","buch",m.getId());
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
			//add("seiten",b.getSeiten(),b.getId());	// ohne Vergleichsoperatoren nicht wirklich sinnvoll (Erweiterung)
			add("bindung",b.getBindung(),b.getId());
			add("sprache",b.getSprache(),b.getId());
			// ALLE Eigenschaften hinzufuegen oder nur gruppenbildende?
		}else if(m.getClass().isAssignableFrom(Film.class)){ //------------------------------Film--------------------
			add("klasse","film",m.getId());
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
		}else if(m.getClass().isAssignableFrom(Musik.class)){ //-----------------------------Musik----------------------
			add("klasse","musik",m.getId());
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
	
	/**
	 * Exklusive Suche nach Medien anhand eines zu uebergebenden Suchausdrucks 
	 * der Form "[eigenschaft_1]:[wert_1] [eigenschaft_2]:[wert_2] [...]" (Bsp.: autor:max_mustermann).
	 * @param qry
	 * @return Menge der Ergebnis-Schluessel (hash-Werte der Medien)
	 */
	public HashSet<Integer> suche(String qry){
		
		HashSet<Integer> erg = new HashSet<Integer>();
		HashSet<Integer> antiErg = new HashSet<Integer>();
		
		qry = qry.toLowerCase();
		
		String[] token = qry.split(" ");
		String[] term = new String[2];
		Iterator<Integer> it;
		int temp;
		for(int i=0;i<token.length;i++){
			try{
				term = token[i].split(":");
			}catch(IndexOutOfBoundsException ex){
				ex.printStackTrace();
				System.err.println("Syntaxfehler in der Suchformel! Korrekte Form: eigenschaft:wert");
			}
			if(term[0].equals("autor")) erg.addAll(getAutor(term[1]));
			else if(term[0].equals("verlag")){
				if(erg.isEmpty()) erg.addAll(getVerlag(term[1]));
				else{
					it = erg.iterator();
					while(it.hasNext()){
						temp = it.next();
						if(!erg.contains(temp)) antiErg.add(temp);
					}
				}
			}
			else if(term[0].equals("klasse")){
				
				if(erg.isEmpty()) 
					if(getKlasse(term[1]) != null) erg.addAll(getKlasse(term[1]));
					else log("klasse " + term[1] + " nicht gefunden!");
				else{
					it = erg.iterator();
					while(it.hasNext()){
						temp = it.next();
						if(!erg.contains(temp)) antiErg.add(temp);
					}
				}
			}
			else if(term[0].equals("auflage")){
				if(erg.isEmpty()) erg.addAll(getAuflage(term[1]));
				else{
					it = erg.iterator();
					while(it.hasNext()){
						temp = it.next();
						if(!erg.contains(temp)) antiErg.add(temp);
					}
				}
			}
			else if(term[0].equals("bindung")){
				if(erg.isEmpty()) erg.addAll(getBindung(term[1]));
				else{
					it = erg.iterator();
					while(it.hasNext()){
						temp = it.next();
						if(!erg.contains(temp)) antiErg.add(temp);
					}
				}
			}
			else if(term[0].equals("sprache")){
				if(erg.isEmpty()) erg.addAll(getSprache(term[1]));
				else{
					it = erg.iterator();
					while(it.hasNext()){
						temp = it.next();
						if(!erg.contains(temp)) antiErg.add(temp);
					}
				}
			}
			else if(term[0].equals("untertitel")){
				if(erg.isEmpty()) erg.addAll(getUntertitel(term[1]));
				else{
					it = erg.iterator();
					while(it.hasNext()){
						temp = it.next();
						if(!erg.contains(temp)) antiErg.add(temp);
					}
				}
			}
			else if(term[0].equals("regisseur")){
				if(erg.isEmpty()) erg.addAll(getRegisseur(term[1]));
				else{
					it = erg.iterator();
					while(it.hasNext()){
						temp = it.next();
						if(!erg.contains(temp)) antiErg.add(temp);
					}
				}
			}
			else if(term[0].equals("erscheinungsland")){
				if(erg.isEmpty()) erg.addAll(getLand(term[1]));
				else{
					it = erg.iterator();
					while(it.hasNext()){
						temp = it.next();
						if(!erg.contains(temp)) antiErg.add(temp);
					}
				}
			}
			else if(term[0].equals("medium")){
				if(erg.isEmpty()) erg.addAll(getMedium(term[1]));
				else{
					it = erg.iterator();
					while(it.hasNext()){
						temp = it.next();
						if(!erg.contains(temp)) antiErg.add(temp);
					}
				}
			}
			else{
				System.err.println("Eigenschaft " + term[0] + " nicht gefunden!");
			}
			
		}
		it = antiErg.iterator();
		while(it.hasNext()){
			temp = it.next();
			if(erg.contains(temp)) erg.remove(temp);
		}
		return erg;
	}
	
	private HashSet<Integer> getKlasse(String klasse){
		return index.get("klasse").get(klasse);
	}
	
	private HashSet<Integer> getAutor(String autor){
		return index.get("autor").get(autor);
	}
	
	private HashSet<Integer> getMedium(String medium){
		return index.get("medium").get(medium);
	}
	
	private HashSet<Integer> getVerlag(String verlag){
		return index.get("verlag").get(verlag);
	}
	
	private HashSet<Integer> getAuflage(String auflage){
		return index.get("auflage").get(auflage);
	}
	
	private HashSet<Integer> getBindung(String bindung){
		return index.get("bindung").get(bindung);
	}
	
	private HashSet<Integer> getSprache(String sprache){
		return index.get("sprache").get(sprache);
	}
	
	private HashSet<Integer> getUntertitel(String unter){
		return index.get("untertitel").get(unter);
	}
	
	private HashSet<Integer> getRegisseur(String regisseur){
		return index.get("regisseur").get(regisseur);
	}
	
	private HashSet<Integer> getLand(String land){
		return index.get("erscheinungsland").get(land);
	}
	
	public static void log(String s){
		System.out.println(s);
	}

}
