package statictics;

import java.util.ArrayList;
import java.util.Collection;

import Model.Graphe;

public abstract class Observator {
	
	protected Graphe g;
	protected ArrayList<Number> values;
	
	public Observator(Graphe g) {
		super();
		this.g = g;
		this.values = new ArrayList<Number>();
	}
	
	public Collection<Number> getValues(){
		
		return values;
	}
	
	public abstract void update();
	public abstract String recordType();
	public Number getLastValue(){
		if(values.size() == 0 ) return null;
		return values.get(values.size()-1);
	}

}
