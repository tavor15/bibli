package com.teamgg.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import Container.Bereich;
import Container.Bestand;
import Container.Regal;
import medien.Buch;
import medien.Medium;

public class App {

	public static void main(String[] args) {

		log("Bibli v0.2\nWillkommen im System!\n");
		
		Bestand best = new Bestand("Bibli");
		best.setSuche(new Suchsystem(best));
		
		Bereich testlabor = new Bereich(best,"Testlabor");
		Regal test = new Regal(testlabor, "Test");
		
		ArrayList<String> autoren = new ArrayList<String>();
		autoren.add("フィ-san");
		autoren.add("Frank Walter Steinmeyer");
		autoren.add("Samuel Korona");
		
		ArrayList<String> autoren2 = new ArrayList<String>();
		autoren2.add("Kalam sal Amar");
		autoren2.add("Walther");
				
		Buch buch = new Buch("Titeltest", "Verlagtest", true, autoren, "200", 
				"", "sequel", "Hartband", "2", 423895, "gutes Buch", "12345678", "Suaheli");
		
		Buch buch2 = null;
		try {
			buch2 = new Buch("Die Welt und フィ", "Yen Press", true, autoren2, "1124", 
					"tanze!", "Die Welt und Wir", "Hartband", "1", new SimpleDateFormat("dd.mm.yyyy").parse("22.01.2010").getTime(), "", "12345678", "Deutsch");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		best.add(testlabor);
		testlabor.add(test);
		test.add(buch);
		test.add(buch2);
		
		//log(best.suche(buch.getId()).toString());
		
		//log("");
		
		HashSet<Medium> set;
		set = best.keysToMedia(best.getSuche().suche("titel:die_welt_UND_フィ"));
		Iterator<Medium> it = set.iterator();
		while(it.hasNext()){
			log(it.next().toString());
			if(it.hasNext())log("--------");
		}
		
		
	}

	public static void log(String s) {
		System.out.println(s);
	}

}
