package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import Utils.HashKey;


public class Graphe implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<HashKey,Vertex> listVertex;
	private HashMap<Vertex,HashMap<HashKey,Edge>> listEdge;
	private  int nextIDVertex = 0;
	private  int nextIDEdge = 0;
	private int nbVertex;
	private int nbEdge;
	
	public Graphe()
	{
		listVertex = new HashMap<HashKey,Vertex> ();
		listEdge = new HashMap<Vertex,HashMap<HashKey,Edge>>();
		this.nbVertex = 0;
		this.nbEdge = 0;
	}
	
	public Vertex addVertex(InfoVertex data)
	{
		// generer l'id
		Vertex v = new Vertex(data, 0, nextIDVertex++);
		this.listVertex.put(new HashKey(v.hashCode()), v);
		this.listEdge.put(v, new HashMap<HashKey, Edge>());
		this.nbVertex++;
		return v;
	}
	
	/**
	 * Même id pour le cas non-orienté : cela ne pose pas de problème puisque les 2 arrêtes produites
	 * ne seront pas dans le même table de hashage
	 * @param data 
	 * @param start
	 * @param end
	 * @param nonOriented
	 * @return
	 */
	public Edge addEdge(InfoEdge data,Vertex start,Vertex end,boolean nonOriented)
	{
		int id = nextIDEdge++; 
		Edge e = new Edge(start, end, data,id);
		this.listEdge.get(start).put(new HashKey(e.hashCode()), e);
		if(nonOriented){
			Edge e1 = new Edge(end, start, data,id);
			this.listEdge.get(end).put(new HashKey(e1.hashCode()), e1);
		}
		this.nbEdge++;
		return e;
				
 	}


	public void addVertex(Collection<Vertex> vertexList) {
		// TODO Auto-generated method stub
		for(Vertex v : vertexList){
			
			this.listVertex.put(new HashKey(v.hashCode()), v);
			this.listEdge.put(v, new HashMap<HashKey, Edge>());
			
		}
		this.nbVertex += vertexList.size();
	}


	public int getVertexNumber() {
		// TODO Auto-generated method stub
		return this.nbVertex;
	}


	public int getEdgesNumber() {
		// TODO Auto-generated method stub
		return this.nbEdge;
	}

	

	public Collection<Edge> getEdges(Vertex v) throws GraphException{
	
		if(!this.listEdge.containsKey(v)) throw new GraphException(Graph_ERROR.VERTEX_ERROR);
		return  this.listEdge.get(v).values();
		
	}
	
	public Collection<Vertex> getNeighbours(Vertex v) throws GraphException{
		
		if(!this.listEdge.containsKey(v)) throw new GraphException(Graph_ERROR.VERTEX_ERROR);
		ArrayList<Vertex> neighbours = new ArrayList<Vertex>();
		Collection<Edge> edges = this.getEdges(v);
		for(Edge e : edges){
			neighbours.add(e.getEnd());
		}
		return neighbours;
		
	}

	public int getDegree(int id) throws GraphException{
		
		HashKey key = new HashKey(id);
		if(!this.listVertex.containsKey(key)) throw new GraphException(Graph_ERROR.VERTEX_ERROR);
		return this.listVertex.get(key).getDegree();
		
	}
	
	public int getDegree(Vertex v) throws GraphException{
		
		return this.getDegree(v.hashCode());
	}
	

	public Collection<Edge> getAllEdges() {
		// TODO Auto-generated method stub
		Collection<Edge> edges = new ArrayList<Edge>();
		Collection<Vertex> vertex= listVertex.values();
		for(Vertex v :vertex){
			
			edges.addAll(listEdge.get(v).values());
			
		}
		return edges;
	}

	public Collection<Vertex> getAllVertex() {
		// TODO Auto-generated method stub
		return listVertex.values();
	}

	public Vertex get(int id) throws GraphException {
		// TODO Auto-generated method stub
		HashKey key = new HashKey(id);
		if(!this.listVertex.containsKey(key)) throw new GraphException(Graph_ERROR.VERTEX_ERROR);
		return listVertex.get(key);
	}

	public Edge getEdge(Vertex start,Vertex end) throws GraphException{
		// TODO Auto-generated method stub
		Collection<Edge> edges = this.getEdges(start);
		for(Edge e : edges){
			
			if(end == e.getEnd()) return e;
		}
		return null;
	}

	
	public Edge  getEdge(int idStart , int idEnd) throws GraphException {
		// TODO Auto-generated method stub
		HashKey keyStart = new HashKey(idStart);
		HashKey keyEnd  = new HashKey(idEnd);
		if((!this.listVertex.containsKey(keyStart)) || (!this.listVertex.containsKey(keyEnd))){
			throw new GraphException(Graph_ERROR.VERTEX_ERROR);
		}
		return this.getEdge(this.listVertex.get(keyStart), this.listVertex.get(keyEnd));
		
	}

	public void removeVertex(Vertex v) throws GraphException  {
		// TODO Auto-generated method stub

		this.listVertex.remove(new HashKey(v.hashCode()));
		int dim = this.listEdge.get(v).size();
		for(Edge e: this.getAllEdges()){
			if(e.getStart() == v || e.getEnd() == v)
				this.removeEdge(e);
		}
		this.listEdge.remove(v);
		nbVertex--;
		System.gc();
	}
	
	public void removeEdge(Edge e)  {
		// TODO Auto-generated method stub
		Vertex start = e.getStart();
		Vertex end = e.getEnd();
		this.listEdge.get(start).remove(new HashKey(e.hashCode()));
		start.decrementDegree();
		this.listEdge.get(end).remove(new HashKey(e.hashCode()));
		end.decrementDegree();
		nbEdge--;
		System.gc();
		
	}

	@Override
	public String toString() {
		
		String s="";
		Collection<Vertex> vertex = listVertex.values();
		for(Vertex v:vertex){
			s+=v+" : \n";
			Collection<Edge> edges = listEdge.get(v).values();
			for(Edge e : edges){
				s+=e.getDescription(v)+"\n";
			}
			s+="\n\n";
		}
		return s;
	}

	
	

}
