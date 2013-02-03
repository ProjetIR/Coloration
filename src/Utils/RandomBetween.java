package Utils;

import java.awt.Color;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

public class RandomBetween {

	private Random gen;
	
	public RandomBetween(long l){
		
		this.gen = new Random(l);
	}

	public int randomValue(int i ,int j){
		
		return ((gen.nextInt(j+1-i)))+i;
	}
	
	public Color randomColor(){
		
		int r = randomValue(0, 255);
		int g = randomValue(0, 255);
		int b = randomValue(0, 255);
		
		return new Color(r,g,b);
	}
	
	public Color giveNewRandomColor(Collection<Color> listColor){
		HashSet<Color> hash = new HashSet<Color>(listColor);
		Color n = randomColor();
		while(hash.contains(n)){
			n = randomColor();
		}
		return randomColor();
		
	}

}
