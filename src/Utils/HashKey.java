package Utils;

import java.io.Serializable;

/**
 * Classe enveloppe pour les identifiants
 * @author KIEFFER
 *
 */
public class HashKey implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int key;
	
	public HashKey(int key){
		
		this.key = key;
	}
	
	public int getKey(){
		
		return this.key;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.key;
	}
	
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if(!(arg0 instanceof HashKey)) return false;
		return ((HashKey)arg0).getKey() == this.key;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "cle : "+this.key;
	}

	
}
