package com.teamgg.app;

import java.util.ArrayList;
import java.util.Enumeration;

import container.Bereich;
import container.Bestand;
import container.Regal;
import medien.Buch;
import medien.Medium;

/**
 * Erstes Testprogramm zum Projekt Bibli
 * @author nachlicht
 *
 */
public class App 
{

    public static void main( String[] args ) {
    	
       log( "Bibli v0.1\nWillkommen im System!");
        
        Bestand bestand = new Bestand();
        
        bestand.add(new Bereich());
        bestand.getInhalt().elements().nextElement().add(new Regal());
        
        ArrayList<String> autoren = new ArrayList<String>();
        autoren.add("Harold Abelson");
        autoren.add("Gerald Jay Sussman");
        autoren.add("Julie Sussman");
        bestand.getInhalt().elements().nextElement().getInhalt().elements().nextElement().add(new Buch("Structure and Interpretation of Computer Programms",
        		"Robert-Koch-Verlag",autoren, 200, null, null));
        
        ArrayList<String> autoren2 = new ArrayList<String>();
        autoren2.add("Hans Peter Washington");
        bestand.getInhalt().elements().nextElement().getInhalt().elements().nextElement().add(new Buch("Fun with Arithmetics",
        		"Bertold-Brecht-Verlag", autoren2, 100, null, null));
        
        //teste hash-Funktion
        if(bestand.getInhalt().elements().nextElement().getInhalt().elements().nextElement().add(new Buch("Fun with Arithmetics",
        		"Bertold-Brecht-Verlag", autoren2, 100, null, null))){
        	log("Doppelte Einfuegung detektiert!");
        }
        
        
        Enumeration<Integer> keys = bestand.getInhalt().elements().nextElement().getInhalt().elements().nextElement().getInhalt().keys();
        int key1 = keys.nextElement();
        int key2 = keys.nextElement();
        
        log("Testausgabe von Testinhalten:");
        
        /*log("Medium1-ID: " + key1);
        log("Medium1-Titel: " + bestand.searchMedium(key1).getTitel());
        log("Medium2-ID: " + key2);
        log("Medium2-Titel: " + bestand.searchMedium(key2).getTitel());*/
        //funktioniert!
        log("");
        log(bestand.searchMedium(key1).toString());
        log("");
        log(bestand.searchMedium(key2).toString());
    }
    
    
    public static void log(String s){
    	System.out.println(s);
    }
}
