package Model;

import java.io.Serializable;




public class Vertex implements Serializable
{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public  InfoVertex t;
	private int degree;
	private int id;
	
	// Arraylist<>
	
	public Vertex(InfoVertex t, int degree, int id) 
	{
		super();
		this.t = t;
		this.degree = degree;
		this.id = id;
	}//constructeur
	
	
	
	public int getId() {
		return id;
	}


	public boolean equals(Object arg0) {
		if(!(arg0 instanceof Vertex)) return false;
			Vertex se = (Vertex)arg0;
		return this.id == se.hashCode();
	}

	public InfoVertex getInfo(){
	
		return t;
	}
	
	public int getDegree(){
		
		return this.degree;
	}
	
	public void setDegree(int degree) {
		this.degree = degree;
	}



	public void incrementDegree(){
		
		this.degree++;
	}
	
	public void decrementDegree(){
		
		this.degree--;
	}
	


	
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public String toString() {
		return this.getId()+" "+this.t;
	}
	
	
	
	

}
