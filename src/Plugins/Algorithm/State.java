package Plugins.Algorithm;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class State {

	private ArrayList<Color> colors;
	private double temp;
	private int maxDegree;

	public State(ArrayList<Color> colors,double temp,int maxDegree) {
		super();
		this.colors = colors;
		this.temp = temp;
		this.maxDegree = maxDegree;
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
	
	public double getTemperature(){
		return this.temp;
	}
	
	public void setTemperature(double newTemp){
		this.temp = newTemp;
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

	public int getMaxDegree() {
		// TODO Auto-generated method stub
		return maxDegree;
	}
	
	
	
	
	
}
