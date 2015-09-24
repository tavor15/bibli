package Container;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Iterator;

import com.teamgg.app.Suche;

import medien.Medium;

public class Bestand extends Container<Bereich> implements Suche{
	
	private static final String safeFileName = "bibli.safe";

	private static final long serialVersionUID = 23L;

	public Bestand(String name) {
		super.setName(name);
		super.setInhalt(new HashSet<Bereich>());
	}
	
	public static void safe(Bestand s) {
		try {
			File file = new File(safeFileName);
			if(!file.exists()) file.createNewFile();
			FileOutputStream fs = new FileOutputStream(file);
			BufferedOutputStream buffered = new BufferedOutputStream(fs);
			ObjectOutputStream out = new ObjectOutputStream(buffered);
			out.writeObject(s);
			out.flush();
			out.close();
			buffered.flush();
			buffered.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Bestand load(){
		Bestand b = null;
		try {
			File file = new File(safeFileName);
			if(!file.exists()) return null;
			FileInputStream fs = new FileInputStream(file);
			BufferedInputStream buffered = new BufferedInputStream(fs);
			ObjectInputStream in = new ObjectInputStream(buffered);
			b = (Bestand) in.readObject();
			in.close();
			buffered.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return b;
	}

	public Medium suche(int id) {
		Iterator<Bereich> i = getInhalt().iterator();
		Iterator<Regal> j;
		Iterator<Medium> k;
		Medium temp;
		while(i.hasNext()){
			j = i.next().getInhalt().iterator();
			while(j.hasNext()){
				k = j.next().getInhalt().iterator();
				while(k.hasNext()){
					temp = k.next();
					if(temp.getId() == id) return temp;
				}
			}
		}
		return null;
	}

	public HashSet<Medium> keysToMedia(HashSet<Integer> set) {
		HashSet<Medium> erg = new HashSet<Medium>();
		Iterator<Integer> it = set.iterator();
		int temp;
		while(it.hasNext()){
			temp = it.next();
			erg.add(this.suche(temp));
		}
		return erg;
	}
	
	

}
