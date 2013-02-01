package Algorithm.Agents;

import java.awt.Color;
import java.util.ArrayList;

public class State {

	private ArrayList<Color> colors;
	private boolean end;
	
	public State(ArrayList<Color> colors, boolean end) {
		super();
		this.colors = colors;
		this.end = end;
	}

	public Color[] getColors() {
		Color[] tab = new Color[colors.size()];
		for(int i =0 ;i<colors.size();i++){
			tab[i] = colors.get(i);
		}
		return tab;
	}

	public void setColors(ArrayList<Color> colors) {
		this.colors = colors;
	}

	public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}
	
	
	
}
