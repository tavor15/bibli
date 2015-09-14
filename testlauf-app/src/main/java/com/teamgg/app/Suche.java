package com.teamgg.app;

import java.util.HashSet;

import medien.Medium;

public interface Suche {

	public Medium suche(int id);
	
	public HashSet<Medium> keysToMedia(HashSet<Integer> set);
	
}
