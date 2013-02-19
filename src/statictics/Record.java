package statictics;

import java.util.Collection;
import java.util.HashMap;

public class Record {

	private HashMap<String, Number> data;
	
	public Record() {
		super();
		this.data = new HashMap<String, Number>();
	}
	
	public Collection<String> getRecordName(){
		
		return this.data.keySet();
	}
	
	public Collection<Number> getValues(){
		
		return this.data.values();
	}
	
	public void addRecord(String name,Number val){
		
		this.data.put(name, val);
	}
	
}
