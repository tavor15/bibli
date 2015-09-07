package com.teamgg.app;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

import container.Bereich;
import container.Bestand;
import container.Regal;
import medien.Buch;
import medien.Film;
import medien.Medium;
import medien.Musik;

public class Suche {

	private HashMap<String, ArrayList<Integer>> autoren;
	private HashMap<String, ArrayList<Integer>> verlage;
	private HashMap<String, ArrayList<Integer>> sprachen;
	private HashMap<String, ArrayList<Integer>> media;
	private HashMap<String, ArrayList<Integer>> regisseure;
	private HashMap<String, ArrayList<Integer>> erscheinungslaender;
	private HashMap<String, ArrayList<Integer>> untertitel;
	private HashMap<String, ArrayList<Integer>> einbaende;

	/**
	 * Rueckgabe von erg durch such()
	 */
	volatile private ArrayList<Integer> erg;

	public Suche(Bestand b) {
		this.setAutoren(new HashMap<String, ArrayList<Integer>>());
		this.setVerlage(new HashMap<String, ArrayList<Integer>>());
		this.setErscheinungslaender(new HashMap<String, ArrayList<Integer>>());
		this.setSprachen(new HashMap<String, ArrayList<Integer>>());
		this.setMedia(new HashMap<String, ArrayList<Integer>>());
		this.setRegisseure(new HashMap<String, ArrayList<Integer>>());
		this.setUntertitel(new HashMap<String, ArrayList<Integer>>());
		this.setEinbaende(new HashMap<String, ArrayList<Integer>>());
		erg = new ArrayList<Integer>();
		build(b);
	}

	public Suche(HashMap<String, ArrayList<Integer>> autoren, HashMap<String, ArrayList<Integer>> verlage,
			HashMap<String, ArrayList<Integer>> sprachen,
			HashMap<String, ArrayList<Integer>> media, HashMap<String, ArrayList<Integer>> regisseure,
			HashMap<String, ArrayList<Integer>> erscheinungslaender, HashMap<String, ArrayList<Integer>> untertitel,
			HashMap<String, ArrayList<Integer>> einbaende) {
		this.setAutoren(autoren);
		this.setVerlage(verlage);
		this.setSprachen(sprachen);
		this.setMedia(media);
		this.setRegisseure(regisseure);
		this.setErscheinungslaender(erscheinungslaender);
		this.setUntertitel(untertitel);
		this.setEinbaende(einbaende);
		erg = new ArrayList<Integer>();
	}
	
	private void build(Bestand b){
		
		Enumeration<Bereich> enumBer = b.getInhalt().elements();
		Enumeration<Regal> enumReg;
		Enumeration<Medium> enumMed;
		Medium m;
		Buch buch;
		Film film;
		Musik musik;
		ArrayList<String> temp;
		Iterator<String> i;
		String element;
		int id;
		while(enumBer.hasMoreElements()){
			enumReg = enumBer.nextElement().getInhalt().elements();
			while(enumReg.hasMoreElements()){
				enumMed = enumReg.nextElement().getInhalt().elements();
				while(enumMed.hasMoreElements()){
					m = enumMed.nextElement();
					
					id = m.getId();
					
					if(!verlage.containsKey(m.getVerlag()))
						verlage.put(m.getVerlag(), new ArrayList<Integer>());
					if(!verlage.get(m.getVerlag()).contains(id))
						verlage.get(m.getVerlag()).add(id);
					
					if(m.getClass().isInstance(Buch.class)){
						buch = (Buch)m;
						
						temp = buch.getAutoren();
						i = temp.iterator();
						while(i.hasNext()){
							element = i.next();
							if(!autoren.containsKey(element))
								autoren.put(element, new ArrayList<Integer>());
							if(!autoren.get(element).contains(id))
								autoren.get(element).add(id);
						}
						
						if(!einbaende.containsKey(buch.getBindung()))
							einbaende.put(buch.getBindung(),new ArrayList<Integer>());
						if(!einbaende.get(buch.getBindung()).contains(id))
							einbaende.get(buch.getBindung()).add(id);
						
						if(!sprachen.containsKey(buch.getSprache()))
							sprachen.put(buch.getSprache(), new ArrayList<Integer>());
						if(!sprachen.get(buch.getSprache()).contains(id))
							sprachen.get(buch.getSprache()).add(id);
												
					}else if(m.getClass().isInstance(Film.class)){
						film = (Film) m;
						
						//TODO impl siehe oben
					}else if(m.getClass().isInstance(Musik.class)){
						//TODO impl siehe oben
					}
				}
			}
		}
	}

	public ArrayList<Integer> suche(Bestand b, String zeile) {

		String[] terms = zeile.split(" ");
		
		String[] token = zeile.split(" ");
		String[] term;
		for (int i = 0; i < token.length; i++) {
			term = token[i].split(":");
			if (term[0].equals("autor"))
				sucheAutor(term[1]);
			//TODO Restvergleiche und -Suchen
		}
		return erg;
	}

	private ArrayList<Integer> sucheAutor(String autor) {
		if(autoren.containsKey(autor)){
			return autoren.get(autor);
		}
		return null;
	}
	
	private ArrayList<Integer> sucheVerlag(String verlag) {
		if(verlage.containsKey(verlag)){
			return verlage.get(verlag);
		}
		return null;
	}
	
	
	private ArrayList<Integer> sucheSprache(String sprache) {
		if(sprachen.containsKey(sprache)){
			return sprachen.get(sprache);
		}
		return null;
	}
	
	private ArrayList<Integer> sucheMedium(String med) {
		if(media.containsKey(med)){
			return media.get(med);
		}
		return null;
	}
	
	private ArrayList<Integer> sucheRegisseure(String reg) {
		if(regisseure.containsKey(reg)){
			return regisseure.get(reg);
		}
		return null;
	}
	
	private ArrayList<Integer> sucheErscheinungslaender(String land) {
		if(erscheinungslaender.containsKey(land)){
			return erscheinungslaender.get(land);
		}
		return null;
	}
	
	private ArrayList<Integer> sucheUntertitel(String unter) {
		if(untertitel.containsKey(unter)){
			return untertitel.get(unter);
		}
		return null;
	}
	
	private ArrayList<Integer> sucheEinbaende(String bund) {
		if(einbaende.containsKey(bund)){
			return einbaende.get(bund);
		}
		return null;
	}

	public HashMap<String, ArrayList<Integer>> getAutoren() {
		return autoren;
	}

	public void setAutoren(HashMap<String, ArrayList<Integer>> autoren) {
		this.autoren = autoren;
	}

	public HashMap<String, ArrayList<Integer>> getVerlage() {
		return verlage;
	}

	public void setVerlage(HashMap<String, ArrayList<Integer>> verlage) {
		this.verlage = verlage;
	}

	public HashMap<String, ArrayList<Integer>> getSprachen() {
		return sprachen;
	}

	public void setSprachen(HashMap<String, ArrayList<Integer>> sprachen) {
		this.sprachen = sprachen;
	}

	public HashMap<String, ArrayList<Integer>> getMedia() {
		return media;
	}

	public void setMedia(HashMap<String, ArrayList<Integer>> media) {
		this.media = media;
	}

	public HashMap<String, ArrayList<Integer>> getRegisseure() {
		return regisseure;
	}

	public void setRegisseure(HashMap<String, ArrayList<Integer>> regisseure) {
		this.regisseure = regisseure;
	}

	public HashMap<String, ArrayList<Integer>> getErscheinungslaender() {
		return erscheinungslaender;
	}

	public void setErscheinungslaender(HashMap<String, ArrayList<Integer>> erscheinungslaender) {
		this.erscheinungslaender = erscheinungslaender;
	}

	public HashMap<String, ArrayList<Integer>> getUntertitel() {
		return untertitel;
	}

	public void setUntertitel(HashMap<String, ArrayList<Integer>> untertitel) {
		this.untertitel = untertitel;
	}

	public HashMap<String, ArrayList<Integer>> getEinbaende() {
		return einbaende;
	}

	public void setEinbaende(HashMap<String, ArrayList<Integer>> einbaende) {
		this.einbaende = einbaende;
	}
	
	
}

