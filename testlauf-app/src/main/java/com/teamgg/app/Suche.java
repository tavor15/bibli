package com.teamgg.app;

import java.util.HashSet;

import medien.Medium;

public interface Suche {

	/**
	 * Suche ein Medium anhand dessen ID
	 * @param id
	 * @return
	 */
	public Medium suche(int id);
	
	/**
	 * Suche eine Medien-Menge anhand deren IDs
	 * @param set
	 * @return
	 */
	public HashSet<Medium> keysToMedia(HashSet<Integer> set);
	
}
