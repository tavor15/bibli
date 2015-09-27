package com.teamgg.app;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import Container.Bestand;
import medien.Buch;
import medien.Film;
import medien.Medium;
import medien.Musik;

public class Suchsystem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 30L;
	
	/**
	 * Diese Baumstruktur speichert die Eigenschaften aller Medien, deren Werte und die
	 * IDs derer Medien, die [eigenschaft] mit [wert] besitzen.
	 * 
	 * Ebenen: Eigenschaft - Wert - Liste der IDs
	 * Beispiel: 
	 * verlag -> cornelsen -> 12398, 91320598, 43285120, -23845, 92356, ...
	 * autor -> hermann_hesse -> 1284125, 1248521, 98903215, ...
	 */
	private HashMap<String,HashMap<String,HashSet<Integer>>> index;
	
	public Suchsystem(Bestand b){
		index = new HashMap<String,HashMap<String,HashSet<Integer>>>();
	}
	
	//bei Persistierung mit XML seperat speichern (nur index-Array speichern?)
	/*public static void safe(Suchsystem s){
		try {
			BufferedOutputStream buffered = new BufferedOutputStream(new FileOutputStream("index.safe"));
			ObjectOutputStream out = new ObjectOutputStream(buffered);
			out.writeObject(s);
			out.flush();
			out.close();
			buffered.flush();
			buffered.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Suchsystem load(){
		Suchsystem s = null;
		try {
			BufferedInputStream buffered = new BufferedInputStream(new FileInputStream("index.safe"));
			ObjectInputStream in = new ObjectInputStream(buffered);
			s = (Suchsystem) in.readObject();
			in.close();
			buffered.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}*/
	
	/**
	 * Editiert einen vorhandenen Eintrag an der Stelle "eigenschaft" mit "wert" 
	 * und ergaenzt die entsprechende Liste falls noetig.
	 * @param eigenschaft
	 * @param wert
	 * @param id
	 * @return true bei Informationsgewinn
	 */
	public boolean add(String eigenschaft, String wert, int id){
		add(eigenschaft.toLowerCase(), wert.toLowerCase());
		if(!index.get(eigenschaft.toLowerCase()).get(wert.toLowerCase()).contains(id))
			index.get(eigenschaft.toLowerCase()).get(wert.toLowerCase()).add(id);
		else return false; // kein neuer Eintrag, kein Informationsgewinn
		return true; // neue Info gespeichert
	}
	
	/**
	 * Fuegt dem Index ein Datum hinzu, durch Extrahieren des Jahres zu einem String.
	 * @param eigenschaft
	 * @param wert
	 * @param id
	 * @return
	 */
	private boolean addDate(String eigenschaft, long wert, int id){
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		String date = df.format(new Date(wert));
		add(eigenschaft.toLowerCase(),date);
		if(!index.get(eigenschaft.toLowerCase()).get(date).contains(id))
			index.get(eigenschaft.toLowerCase()).get(date).add(id);
		else return false; // kein neuer Eintrag, kein Informationsgewinn
		return true; // neue Info gespeichert
		
	}
	
	/**
	 * Fuegt der Liste, die Eintraege der "eigenschaft" beherbergt, "wert" als neue leere Liste von IDs hinzu.
	 * @param eigenschaft
	 * @param wert
	 * @return
	 */
	private boolean add(String eigenschaft, String wert){
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
	 * @return true bei Neueintrag, false bei Editierung (auSSer bei Verlag oder Titel)
	 */
	public boolean add(Medium m){
		
		boolean neu = false;
		
		add("verlag",m.getVerlag(),m.getId());
		
		//pruefe Medium m auf Klasse Buch
		if(m.getClass().isAssignableFrom(Buch.class)){ //-----------------------------------Buch-----------------
			
			neu = add("klasse","buch",m.getId());
			Buch b = (Buch) m;
			
			Iterator<String> i = b.getAutoren().iterator();
			while(i.hasNext()){
				add("autor", i.next(), b.getId());
			}
			
			add("auflage",b.getAuflage(),b.getId());
			add("prequel",b.getPrequel(),b.getId());
			add("sequel",b.getSequel(),b.getId());
			add("bindung",b.getBindung(),b.getId());
			add("sprache",b.getSprache(),b.getId());
			addDate("jahr",b.getErsterscheinung(),b.getId());
			add("isbn",b.getIsbn(),b.getId());
			add("titel",b.getTitel(),b.getId());
			
		//pruefe Medium m auf Klasse Film
		}else if(m.getClass().isAssignableFrom(Film.class)){ //------------------------------Film--------------------
			
			neu = add("klasse","film",m.getId());
			Film f = (Film) m;
			
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
			add("prequel",f.getPrequel(),f.getId());
			add("sequel",f.getSequel(),f.getId());
			add("titel",f.getTitel(),f.getId());
			
			i = f.getSprachen().iterator();
			while(i.hasNext()){
				add("sprache", i.next(), f.getId());
			}
			
			i = f.getUntertitel().iterator();
			while(i.hasNext()){
				add("untertitel", i.next(), f.getId());
			}
			
		//pruefe Medium m auf Klasse Musik
		}else if(m.getClass().isAssignableFrom(Musik.class)){ //-----------------------------Musik----------------------
			
			neu = add("klasse","musik",m.getId());
			Musik u = (Musik) m;
			
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
			addDate("jahr",u.getErsterscheinung(),u.getId());
			add("titel",u.getTitel(),u.getId());
		}
		return neu;
	}
	
	/**
	 * Exklusive Suche nach Medien anhand eines zu uebergebenden Suchausdrucks 
	 * der Form "[eigenschaft_1]:[wert_1] [eigenschaft_2]:[wert_2] [...]" (Bsp.: autor:max_mustermann). 
	 * Erlaubte Eigenschaften: autor, verlag, titel, klasse (buch/musik/film), auflage, bindung (bei Buch, z.B. Hartband), 
	 * sprache, sequel, prequel, jahr, untertitel, regisseur, land, medium (z.B. DVD, VHS, CD, ...)
	 * @param qry
	 * @return Menge der Ergebnis-Schluessel (hash-Werte der Medien)
	 */
	public HashSet<Integer> suche(String qry){
		
		HashSet<Integer> erg = new HashSet<Integer>();
		HashSet<Integer> antiErg = new HashSet<Integer>();
		
		qry = qry.toLowerCase();	//Suchstring zu Kleinbuchstaben
		
		String[] token = qry.split(" ");	//bilde Menge von Substrings anhand des Leerzeichens --> [autor:testautor][verlag:testverlag][...]
		String[] term = new String[2];		//Platzhalter fuer Eigenschaft + Wert, also [autor][testautor]
		Iterator<Integer> it;
		int temp;
		for(int i=0;i<token.length;i++){
			
			try{
				term = token[i].split(":");	//teile Substrings der Suchanfrage an Doppelpunkt "autor:testautor" --> ["autor"]["testautor"]
			}catch(IndexOutOfBoundsException ex){
				ex.printStackTrace();
			}
			
			if(term[0].equals("autor")){	// gesuchte Eigenschaft ist "autor"?
				if(erg.isEmpty()) erg.addAll(getAutor(term[1]));	// Ergebnismenge leer? fuege ihr alle IDs der Werke des Autors hinzu
				else{
					it = erg.iterator();
					while(it.hasNext()){
						temp = it.next();
						if(!erg.contains(temp)) antiErg.add(temp);	// Medium ist nicht bereits in Ergbnismenge? Fuege es der Abzugsmenge hinzu
					}
				}
			}
			else if(term[0].equals("verlag")){	// gesuchte Eigenschaft ist Verlag?
				if(erg.isEmpty()) erg.addAll(getVerlag(term[1]));	// Ergebnismenge leer? fuege ihr alle IDs der Medien des Verlags hinzu
				else{
					it = erg.iterator();
					while(it.hasNext()){
						temp = it.next();
						if(!erg.contains(temp)) antiErg.add(temp); // Nicht bereits in Ergebnismenge? Eintrag in Abzugsmenge!
					}
				}
			}
			else if(term[0].equals("titel")){	// vgl. oben
				if(erg.isEmpty()) erg.addAll(getTitel(term[1]));
				else{
					it = erg.iterator();
					while(it.hasNext()){
						temp = it.next();
						if(!erg.contains(temp)) antiErg.add(temp);
					}
				}
			}
			else if(term[0].equals("klasse")){	// vgl. oben
				
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
			else if(term[0].equals("sequel")){
				if(erg.isEmpty()) erg.addAll(getSequel(term[1]));
				else{
					it = erg.iterator();
					while(it.hasNext()){
						temp = it.next();
						if(!erg.contains(temp)) antiErg.add(temp);
					}
				}
			}
			else if(term[0].equals("prequel")){
				if(erg.isEmpty()) erg.addAll(getPrequel(term[1]));
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
			else if(term[0].equals("jahr")){
				if(erg.isEmpty()) erg.addAll(getJahr(term[1]));
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
		
		// Ergbnismenge ist jetzt das Ergebnis einer ODER-Suche
		
		it = antiErg.iterator();
		while(it.hasNext()){
			temp = it.next();
			if(erg.contains(temp)) erg.remove(temp);	// entferne alle Eintraege aus Ergebnismenge, die in Abzugsmenge sind
		}
		
		// Ergbnismenge ist jetzt das Ergebnis einer UND-Suche
		
		return erg;
	}
	
	private HashSet<Integer> getKlasse(String klasse){
		return index.get("klasse").get(klasse.toLowerCase());
	}
	
	private HashSet<Integer> getTitel(String titel){
		return index.get("titel").get(titel.toLowerCase().replace('_', ' '));
	}
	
	private HashSet<Integer> getJahr(String jahr){
		return index.get("jahr").get(jahr);
	}
	
	private HashSet<Integer> getPrequel(String prequel){
		return index.get("prequel").get(prequel.toLowerCase().replace('_', ' '));
	}
	
	private HashSet<Integer> getSequel(String sequel){
		return index.get("sequel").get(sequel.toLowerCase().replace('_', ' '));
	}
	
	private HashSet<Integer> getAutor(String autor){
		return index.get("autor").get(autor.toLowerCase().replace('_', ' '));
	}
	
	private HashSet<Integer> getMedium(String medium){
		return index.get("medium").get(medium.toLowerCase());
	}
	
	private HashSet<Integer> getVerlag(String verlag){
		return index.get("verlag").get(verlag.toLowerCase().replace('_', ' '));
	}
	
	private HashSet<Integer> getAuflage(String auflage){
		return index.get("auflage").get(auflage);
	}
	
	private HashSet<Integer> getBindung(String bindung){
		return index.get("bindung").get(bindung.toLowerCase());
	}
	
	private HashSet<Integer> getSprache(String sprache){
		return index.get("sprache").get(sprache.toLowerCase());
	}
	
	private HashSet<Integer> getUntertitel(String unter){
		return index.get("untertitel").get(unter.toLowerCase());
	}
	
	private HashSet<Integer> getRegisseur(String regisseur){
		return index.get("regisseur").get(regisseur.toLowerCase().replace('_', ' '));
	}
	
	private HashSet<Integer> getLand(String land){
		return index.get("erscheinungsland").get(land.toLowerCase().replace('_', ' '));
	}
	
	public static void log(String s){
		System.out.println(s);
	}

}
