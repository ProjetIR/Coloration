package Utils;

import java.util.Random;

public class RandomBetween {

	private Random gen;
	
	public RandomBetween(long l){
		
		this.gen = new Random(l);
	}

	public int randomValue(int i ,int j){
		
		return ((gen.nextInt(j+1-i)))+i;
	}
	


}
