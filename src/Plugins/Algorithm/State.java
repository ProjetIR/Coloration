package Plugins.Algorithm;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * Classe représentant la ressource partagée entre les agents et l'agent maître
 * @author KIEFFER
 *
 */
public class State {

	private ArrayList<Color> colors;
	public  boolean masterSleep = true;
	public double t = 1;

	public State(ArrayList<Color> colors) {
		super();
		this.colors = colors;	
	}

	public Color[] getColors() {
		Color[] tab = new Color[colors.size()];
		for(int i =0 ;i<colors.size();i++){
			tab[i] = colors.get(i);
		}
		return tab;
	}

	
	

	

	

	public Collection<Color> getCollectionColors() {

		return colors;
	}
	
	
	public void addColor(Color c){
		this.colors.add(c);
	}
	
	public int getNumberColors(){
		
		return colors.size();
	}
	
	
	@Override
	public String toString() {
		// TODO Stub de la méthode généré automatiquement
		String s ="";
		for(Color c : colors){
			s+=c+";";
		}
		return s;
	}

	
	
	
	
	
	
}
