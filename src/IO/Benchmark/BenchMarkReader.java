package IO.Benchmark;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Model.GraphException;
import Model.Graphe;
import Model.InfoEdge;
import Model.InfoVertex;
import Model.Vertex;

/**
 * Classe permettant de lire un fichier .col issu des challenges DIMACS
 * @author KIEFFER
 *
 */
public class BenchMarkReader {
	
	private BufferedReader reader;
	private InfoVertex infoVertex;
	private InfoEdge infoEdge;

	public BenchMarkReader(String filePath,InfoVertex infoVertex,InfoEdge infoEdge) {
		super();
		try {
			this.reader = new BufferedReader(new FileReader(filePath));
			this.infoVertex = infoVertex;
			this.infoEdge = infoEdge;
		} catch (FileNotFoundException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
	}
	
	public Graphe read(){
		boolean dimFind = false;
		Graphe g = new Graphe();
		int nbVertex = 0;
		int nbEdge = 0;
		try {
			String line; 
		while((line = reader.readLine()) != null ){
			
			if(!line.startsWith("p") && dimFind == false)
					continue;
			
			if(!dimFind){
				String[] tab = line.split(" ");
				nbVertex = Integer.parseInt(tab[2]);
				nbEdge = Integer.parseInt(tab[3]);
				dimFind = true;
				for(int i = 0 ;i < nbVertex ;i++){
					g.addVertex((InfoVertex)infoVertex.clone());
				}
			}else{
				if(nbEdge > 0){
					String[] tab = line.split(" ");
					int start = Integer.parseInt(tab[1])-1; 
					int end =  Integer.parseInt(tab[2])-1;
					Vertex vStart = g.get(start);
					Vertex vEnd = g.get(end);
					g.addEdge((InfoEdge)infoEdge.clone(), vStart, vEnd, true);
					nbEdge--;
				
				}
					
			}
			
			}
			g.setNextIDVertex(g.getVertexNumber());
			g.setNextIDEdge(g.getEdgesNumber());
		} catch (IOException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		} catch (CloneNotSupportedException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		} catch (GraphException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
		return g;
	}
	
	
	

}
