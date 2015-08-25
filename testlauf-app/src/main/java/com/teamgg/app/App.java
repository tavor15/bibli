package com.teamgg.app;

import java.util.Enumeration;

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
        bestand.getInhalt().elements().nextElement().getInhalt().elements().nextElement().add(new Medium("Structure and Interpretation of Computer Programms"));
        bestand.getInhalt().elements().nextElement().getInhalt().elements().nextElement().add(new Medium("Fun with Arithmetics"));
        
        Enumeration<Integer> keys = bestand.getInhalt().elements().nextElement().getInhalt().elements().nextElement().getInhalt().keys();
        int key1 = keys.nextElement();
        int key2 = keys.nextElement();
        
        log("Testausgabe von Testinhalten:");
        log("Medium1-ID: " + key1);
        log("Medium1-Titel: " + bestand.searchMedium(key1).getTitle());
        log("Medium2-ID: " + key2);
        log("Medium2-Titel: " + bestand.searchMedium(key2).getTitle());
        
    }
    
    
    public static void log(String s){
    	System.out.println(s);
    }
}
