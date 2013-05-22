package Model.Generator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;


import Model.DegreeComparator;
import Model.GraphException;
import Model.Graph_ERROR;
import Model.Graphe;
import Model.InfoEdge;
import Model.InfoVertex;
import Model.Vertex;
import Utils.HashKey;
import Utils.RandomBetween;
import Utils.Set.ForestSeparateSet;
import Utils.Set.Node;

/**
 * Générateur de graphes aléatoires
 * On crée tout d'bord un arbre pour s'assurer que le graphe sera connexe
 * puis on le décore
 * @author KIEFFER
 *
 */
public class RandomGenerator implements Generator {

	
	private int vertex;
	private int edges;
		
	public RandomGenerator(int vertex, int edges) {
		super();
		this.vertex = vertex;
		this.edges = edges;
	}

	public void generate(Graphe g,InfoVertex v,InfoEdge u) throws GraphException, CloneNotSupportedException {
		if((vertex * (vertex-1)/2) < edges ) throw new GraphException(Graph_ERROR.NB_EDGES_ERROR);
		if((vertex-1) > edges ) throw new GraphException(Graph_ERROR.NB_EDGES_ERROR);
		ForestSeparateSet<Vertex> forest = new ForestSeparateSet<Vertex>();
		ArrayList<Node<Vertex>> list = new ArrayList<Node<Vertex>>();
		ArrayList<Vertex> doublon = new ArrayList<Vertex>();
		for(int i=0;i<vertex;i++){
			Vertex tmp = g.addVertex((InfoVertex)v.clone());
			list.add(forest.createSet((tmp)));
			doublon.add(tmp);
		}
		RandomBetween generator = new RandomBetween(System.currentTimeMillis());
		int i = 0;
		// Creation d'un arbre 
		while(i<vertex-1){
			
			int choiceSide1 = generator.randomValue(0, list.size()-1);
			int choiceSide2 = generator.randomValue(0, list.size()-1);
			Node<Vertex> a = list.get(choiceSide1);
			Node<Vertex> b = list.get(choiceSide2);
			if(forest.findSet(a) != forest.findSet(b)){
				
				Vertex A = a.getObject();
				Vertex B = b.getObject();
				g.addEdge((InfoEdge)u.clone(),A, B, true);
				forest.union(a, b);
				i++;
				
				
			}
			
			
		}
		list = null;
		System.gc();
		Collections.sort(doublon, new DegreeComparator());
		int remainingEdge = edges - (vertex-1);
		while(remainingEdge > 0){
			
			for(int j = 0;j<doublon.size()-1 && remainingEdge >0 ; j++){
				
				
				Vertex orig = doublon.get(j);
				Collection<Vertex> n = g.getNeighbours(orig);
				ArrayList<Vertex> clone =  (ArrayList<Vertex>) doublon.clone();
				clone.removeAll(n);
				int choice = generator.randomValue(0, clone.size()-1);
				Vertex dest = clone.get(choice);	
				if(orig != dest){
					
					g.addEdge((InfoEdge)u.clone(),orig, dest, true);
					remainingEdge--;
				}
	
				
			}

	}

	}


}
