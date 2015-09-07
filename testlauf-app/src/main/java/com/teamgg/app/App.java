package com.teamgg.app;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;

import container.Bereich;
import container.Bestand;
import container.Regal;
import medien.Buch;

/**
 * Erstes Testprogramm zum Projekt Bibli
 * 
 * @author nachlicht
 *
 */
public class App {

	public static void main(String[] args) {

		log("Bibli v0.1\nWillkommen im System!");

		Bestand bestand = new Bestand("bibli");

		bestand.add(new Bereich("entwicklung"));
		bestand.getInhalt().elements().nextElement().add(new Regal("test"));

		DateFormat df = new SimpleDateFormat("dd.mm.yyyy");

		long date1 = 0;
		long date2 = 0;
		try {
			date1 = df.parse("22.02.1999").getTime();
			date2 = df.parse("11.01.1710").getTime(); // teste datum vor 1970
		} catch (ParseException e) {
			e.printStackTrace();
		}

		ArrayList<String> autoren = new ArrayList<String>();
		autoren.add("Harold Abelson");
		autoren.add("Gerald Jay Sussman");
		autoren.add("Julie Sussman");
		bestand.getInhalt().elements().nextElement().getInhalt().elements().nextElement().add(bestand,
				new Buch("Structure and Interpretation of Computer Programms", "MIT-Press", false, autoren, 200, null,
						null, "Festeinband", 1, date1, "Wenn du sonst nichts zu tun hast, lies SICP!", "0262010771",
						"Englisch"));

		// teste hash-Funktion
		if (bestand.getInhalt().elements().nextElement().getInhalt().elements().nextElement().add(bestand,
				new Buch("Structure and Interpretation of Computer Programms", "MIT-Press", false, autoren, 200, null,
						null, "Festeinband", 1, date1, "Wenn du sonst nichts zu tun hast, lies SICP!", "0262010771",
						"Englisch"))) {
			log("Doppeleintrag erfolgreich");
		} else {
			log("Doppeleintrag gescheitert");
		}

		ArrayList<String> autoren2 = new ArrayList<String>();
		autoren2.add("Hans Peter Washington");
		bestand.getInhalt().elements().nextElement().getInhalt().elements().nextElement().add(bestand,
				new Buch("Fun with Arithmetics", "Bertold-Brecht-Verlag", false, autoren2, 100, null, null,
						"Taschenbuch", 4, date2, "Dieses Buch muss nicht wirklich existieren.", "0129348715",
						"Englisch"));

		Enumeration<Integer> keys = bestand.getInhalt().elements().nextElement().getInhalt().elements().nextElement()
				.getInhalt().keys();

		log("Testausgabe von Testinhalten:");

		while (keys.hasMoreElements()) {
			log("");
			log(bestand.searchMedium(keys.nextElement()).toString());
		}

	}

	public static void log(String s) {
		System.out.println(s);
	}
}
