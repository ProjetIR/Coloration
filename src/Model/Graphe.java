package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import Utils.HashKey;

/**
 * Classe représentant la structure de donnée d'un graphe non-orienté
 * Cette classe est composée de:
 * 
 * 	- une table de hashage où l'on stocke les sommets (clé = wrapper(identifiantSommet))
 *  - une seconde table de hashage où les sommets sont les clés et les valeurs de tables de hashages
 *    contenant le voisinage du sommet en question
 *    
 * accès en O(1) si coeff adéquat
 * @author KIEFFER
 *
 */
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
	
	
	public int getNextIDVertex() {
		return nextIDVertex;
	}

	public void setNextIDVertex(int nextIDVertex) {
		if(nextIDVertex>=0)
			this.nextIDVertex = nextIDVertex;
	}

	public int getNextIDEdge() {
		return nextIDEdge;
	}

	public void setNextIDEdge(int nextIDEdge) {
		if(nextIDEdge >= 0)
			this.nextIDEdge = nextIDEdge;
	}
	
	/**
	 * Permet de créer un nouveau sommet à partir d'une instance de la classe
	 * InfoVertex représentant une classe de stockage d'information pour le vertex
	 * typiquement la couleur, le poid ,etc...
	 * @param data
	 * @return
	 */
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
	 * Permet de créer une arrête entre deux sommets
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
			start.setDegree(start.getDegree()-1);
			end.setDegree(end.getDegree()-1);
		}
		this.nbEdge++;
		return e;
				
 	}

    /**
     * On peut ajouter une collection de vertex déjà construit
     * @param vertexList
     */
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

	
	/**
	 * Retourne la liste des arrêtes d'un sommet v du graphe
	 * @param v
	 * @return
	 * @throws GraphException
	 */
	public Collection<Edge> getEdges(Vertex v) throws GraphException{
	
		if(!this.listEdge.containsKey(v)) throw new GraphException(Graph_ERROR.VERTEX_ERROR);
		return  this.listEdge.get(v).values();
		
	}
	
	/**
	 * Retourne les sommets voisins d'un sommet v
	 * @param v
	 * @return
	 * @throws GraphException
	 */
	public Collection<Vertex> getNeighbours(Vertex v) throws GraphException{
		
		if(!this.listEdge.containsKey(v)) throw new GraphException(Graph_ERROR.VERTEX_ERROR);
		ArrayList<Vertex> neighbours = new ArrayList<Vertex>();
		Collection<Edge> edges = this.getEdges(v);
		for(Edge e : edges){
			neighbours.add(e.getEnd());
		}
		return neighbours;
		
	}
	
	/**
	 * Renvoit vrai si deux sommets v et u sont voisins
	 * @param v
	 * @param u
	 * @return
	 * @throws GraphException
	 */
	public boolean areNeighbours(Vertex v, Vertex u) throws GraphException{
		for(Vertex w : this.getNeighbours(v)){
			if(w.equals(u)){
				return true;
			}
		}
		return false;
	}

	/**
	 * Retourne le degré d'un sommet à partir de son id
	 * @param id
	 * @return
	 * @throws GraphException
	 */
	public int getDegree(int id) throws GraphException{
		
		HashKey key = new HashKey(id);
		if(!this.listVertex.containsKey(key)) throw new GraphException(Graph_ERROR.VERTEX_ERROR);
		return this.listVertex.get(key).getDegree();
		
	}
	
	/**
	 * Retourne le degré d'un sommet à partir de sa référence
	 * @param v
	 * @return
	 * @throws GraphException
	 */
	public int getDegree(Vertex v) throws GraphException{
		
		return this.getDegree(v.hashCode());
	}
	

	/**
	 * Retourne toutes les arrêtes du graphe
	 * @return
	 */
	public Collection<Edge> getAllEdges() {
		// TODO Auto-generated method stub
		HashSet<Edge> ensemble = new HashSet<Edge>();
		Collection<Vertex> vertex= listVertex.values();
		for(Vertex v :vertex){
			
			ensemble.addAll(listEdge.get(v).values());
			
		}
		return ensemble;
	}

	/**
	 * Retourne tous les sommets du graphe
	 * @return
	 */
	public Collection<Vertex> getAllVertex() {
		// TODO Auto-generated method stub
		return listVertex.values();
	}

	/**
	 * Retourne un sommet du graphe à partir de son id
	 * @param id
	 * @return
	 * @throws GraphException
	 */
	public Vertex get(int id) throws GraphException {
		// TODO Auto-generated method stub
		HashKey key = new HashKey(id);
		if(!this.listVertex.containsKey(key)) throw new GraphException(Graph_ERROR.VERTEX_ERROR);
		return listVertex.get(key);
	}

	/**
	 * Retourne une arrête à partir de ses sommets si elle existe
	 * @param start
	 * @param end
	 * @return
	 * @throws GraphException
	 */
	public Edge getEdge(Vertex start,Vertex end) throws GraphException{
		// TODO Auto-generated method stub
		Collection<Edge> edges = this.getEdges(start);
		for(Edge e : edges){
			
			if(end == e.getEnd()) return e;
		}
		return null;
	}

	/**
	 * Retourne une arrête à partir des id de ses sommets si elle existe
	 * @param idStart
	 * @param idEnd
	 * @return
	 * @throws GraphException
	 */
	public Edge  getEdge(int idStart , int idEnd) throws GraphException {
		// TODO Auto-generated method stub
		HashKey keyStart = new HashKey(idStart);
		HashKey keyEnd  = new HashKey(idEnd);
		if((!this.listVertex.containsKey(keyStart)) || (!this.listVertex.containsKey(keyEnd))){
			throw new GraphException(Graph_ERROR.VERTEX_ERROR);
		}
		return this.getEdge(this.listVertex.get(keyStart), this.listVertex.get(keyEnd));
		
	}

	/**
	 * Supprimer un sommet du graphe
	 * @param v
	 * @throws GraphException
	 */
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
	
	/**
	 * Supprime une arrête du graphe
	 * @param e
	 */
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
	
	/**
	 * Renvoit la densité moyenne du graphe
	 * @return
	 */
	public double averageDensity(){
		double val = 0;
		for(Vertex v : this.getAllVertex()){
			val += v.getDegree();
		}
		return val/this.getVertexNumber();
	}
/*
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
*/
	
	

}
