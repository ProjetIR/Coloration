package algorithm.Agents;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class State {

	private ArrayList<Color> colors;

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

	public void setColors(ArrayList<Color> colors) {
		this.colors = colors;
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
