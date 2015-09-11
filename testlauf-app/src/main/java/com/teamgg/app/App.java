package com.teamgg.app;

import java.util.ArrayList;

import Container.Bereich;
import Container.Bestand;
import Container.Regal;
import medien.Buch;
import medien.Medium;

public class App {

	public static void main(String[] args) {

		log("Bibli v0.2\nWillkommen im System!");
		
		Bestand best = new Bestand("Bibli");
		best.setSuche(new Suchsystem(best));
		
		Bereich testlabor = new Bereich(best,"Testlabor");
		Regal test = new Regal(testlabor, "Test");
		
		ArrayList<String> autoren = new ArrayList<String>();
		autoren.add("Max_Musterman");
		autoren.add("Frank_Walter_Steinmeyer");
		
		Medium none = new Medium("Nicht vorhanden","fehlt", false);
		
		Buch buch = new Buch("Titeltest", "Verlagtest", true, autoren, "200", 
				none, none, "Hartband", "1", 0, "gutes Buch", "12345678", "Takalog");
		
		best.add(testlabor);
		testlabor.add(test);
		test.add(buch);
		
		String erg = best.getInhalt().iterator().next().getInhalt().iterator().next().getInhalt().iterator().next().toString();
		
		log(erg);
		
		log(best.suche(buch.getId()).getTitel());
		
		
	}

	public static void log(String s) {
		System.out.println(s);
	}

}
