package Model;

import java.io.Serializable;


/**
 * Classe représentant une arrête. Cette classe doit implémentée l'interface Serializable 
 * @author KIEFFER
 *
 */
public class Edge implements Serializable{
	

	private Vertex start; 
	private Vertex end;
	public InfoEdge u;
	private int id; // identifiant unique attribué à une arrête
	
	public Edge(Vertex start, Vertex end, InfoEdge u,int id) {
		super();
		this.start = start;
		this.start.incrementDegree();
		
		this.end = end;
		this.end.incrementDegree();
		this.u = u;
		this.id = id;
		
		
		
	}
	
	public InfoEdge getInfo(){
		
		return u;
	}
	
	public Vertex getStart(){
		
		return start;
	}
	
	public Vertex getEnd(){
		
		return end;
	}
	
	
	public int getId() {
		return id;
	}

	/**
	 * L'égalité des arrêtes se fait sur leur identifiant
	 */
	public boolean equals(Object arg0) {
		if(!(arg0 instanceof Edge)) return false;
			Edge se = (Edge)arg0;
		return this.id == se.hashCode();
	}


	/**
	 * Le code de hashage sera l'identifiant unique de chaque arrête
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.id;
	}



	
	public String getDescription(Vertex start) {
		
		String s ="";
		if(start.getId() == this.start.getId())
			s+= "Edge id "+this.id+" "+this.start.getId()+"-------------------------->"+this.end.getId();
		else
			s+= "Edge id "+this.id+" "+this.end.getId()+"-------------------------->"+this.start.getId();
		
		return s +" "+this.u;
	}
	
	
	
	

}
